package kodlamaio.HRMS.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.HRMS.business.abstracts.UserService;
import kodlamaio.HRMS.core.utilities.result.DataResult;
import kodlamaio.HRMS.entities.concretes.User;
import kodlamaio.HRMS.entities.dtos.UserLoginDto;
import kodlamaio.HRMS.entities.dtos.UserLoginReturnDto;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UsersController {
	
	private UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<User>> getAll(){
		return this.userService.getAll();
	}
	
	@GetMapping("/getByEmail")
	public DataResult<User> getByEmail(String email){
		return this.userService.getByEmail(email);
	}
	
	public DataResult<User> getById (int id){
		return this.userService.getById(id);
	}
	
	@PostMapping("/login")
	public DataResult<UserLoginReturnDto> login(@RequestBody UserLoginDto userLoginDto) {
		return this.userService.login(userLoginDto);
	}

}
