package com.io.ghosttms.serviceimpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.io.ghosttms.entity.Role;
import com.io.ghosttms.entity.User;
import com.io.ghosttms.exceptionhandler.InvalidCredentialsException;
import com.io.ghosttms.exceptionhandler.ResourceNotFoundException;
import com.io.ghosttms.repository.UserRepository;
import com.io.ghosttms.security.CustomUserDetails;
import com.io.ghosttms.security.JWTHelper;
import com.io.ghosttms.service.UserService;
import com.io.ghosttms.util.RequestObject;
import com.io.ghosttms.util.ResponseObject;
import com.io.ghosttms.util.UserDto;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	final UserRepository userRepo;
	final JWTHelper jwtHelper;
	final ModelMapper modelMapper;


	public UserDto registerUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		user.setRole(Role.USER);
		
		
		
		return this.modelMapper.map(this.userRepo.save(user), UserDto.class);
	}

	@Override
	public ResponseObject loginUser(RequestObject requestObj) {
		
		User user;
		try {
			user = this.userRepo.findByEmail(requestObj.getEmail()).orElseThrow(()-> new ResourceNotFoundException("User", "email", requestObj.getEmail()));
		} catch (ResourceNotFoundException e) {
			return null;
		}
		String token = jwtHelper.generateToken(new CustomUserDetails(user));
		ResponseObject resObj = new ResponseObject();
		resObj.setEmail(user.getEmail());
		resObj.setToken(token);
		
		return resObj;
	}

	@Override
	public UserDto updateUser(UserDto dto){
		System.out.println(dto.getEmail());
	    User user = userRepo.findByEmail(dto.getEmail())
			    .orElseThrow(() -> new ResourceNotFoundException("User", "Email", dto.getEmail()));
			// 2. Validate that at least one field has data (using a helper)
		    if (isAllEmpty(dto.getFullName(), dto.getPassword(), dto.getPhoneNumber())) {
		        throw new InvalidCredentialsException("Fields are Empty", "Enter at least one");
		    }

	    

	    // 3. Use Optional to handle "if not empty" logic cleanly
	    Optional.ofNullable(dto.getFullName())
	            .filter(s -> !s.isBlank())
	            .ifPresent(user::setFullName);

	    Optional.ofNullable(dto.getPassword())
	            .filter(s -> !s.isBlank())
	            .ifPresent(pw -> user.setPassword(pw));

	    Optional.ofNullable(dto.getPhoneNumber())
	            .filter(s -> s!=0)
	            .ifPresent(phone -> user.setPhoneNumber(Long.valueOf(phone)));

	    // 4. Save (Auditing handles the date)
	    User updatedUser = userRepo.save(user);
	    return modelMapper.map(updatedUser, UserDto.class);
	}

	private boolean isAllEmpty(Object...fields ) {
	    return Arrays.stream(fields).allMatch(f -> {
	        if (f == null) return true;
	        if (f instanceof String) return ((String) f).trim().isEmpty();
	        if (f instanceof Long) return (Long)f == 0L; // Optional: treats 0 as "empty"
	        return false;
	    });
	}
	
	
	public UserDto getUserByEmail(String email) {
		
		return modelMapper.map(userRepo.findByEmail(email), UserDto.class);
	}

	@Override
	public void deleteUser(String email) {
		User user = userRepo.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User", "email", email));
		System.out.println("Delete");
		userRepo.delete(user);		
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = userRepo.findAll();
		
		return users.stream().map(e->modelMapper.map(e, UserDto.class)).collect(Collectors.toList());
	}


	
}
