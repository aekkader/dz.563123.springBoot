package dz.springBoot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import dz.springBoot.model.User;
import dz.springBoot.service.UserService;

import java.util.List;

/**
 * 
 * Rest API Controller for USER
 * 	- Login	
 * 	- Find All
 * 	- Find by ID
 * 	- Update 
 * 	- Delete
 * 
 * @author AEK
 *
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
    /**
     * Login : connexion
     * @param user
     * @return
     */
	@PostMapping("/login")
//    @ResponseStatus(HttpStatus.FOUND)
//    public ResponseEntity<User> login(@PathVariable String username, @PathVariable String password) {
	public ResponseEntity<User> login(@RequestBody User user) {
		return userService.login(user.getUsername(), user.getPassword())
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
    
    /**
     * Find all users
     * @return
     */
	@GetMapping
	public List<User> findAllUsers() {
		return userService.findAllUsers();
	}

    /**
     * Find user by ID
     * @param id
     * @return
     */
	@GetMapping("/{id}")
	public ResponseEntity<User> findUserById(@PathVariable Long id) {
		return userService.findUserById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create new user
     * @param user
     * @return
     */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody @Validated User user) {
		return userService.createUser(user);
	}

    /**
     * Update user
     * @param id
     * @param user
     * @return
     */
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
		return userService.findUserById(id)
				.map(userObj -> {
					user.setId(id);
					return ResponseEntity.ok(userService.updateUser(user));
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

    /**
     * Delete user by ID
     * @param id
     * @return
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		return userService.findUserById(id)
				.map(user -> {
					userService.deleteUserById(id);
					return ResponseEntity.ok(user);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
    }
	
}
