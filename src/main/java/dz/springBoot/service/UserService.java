package dz.springBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.springBoot.exception.UserRegistrationException;
import dz.springBoot.model.User;
import dz.springBoot.repo.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * 
 * UserService : define all Methods (Find, FindAll, Login, Update, Delete)
 * 
 * @author AEK
 *
 */
@Service
public class UserService  {
	
	private final UserRepository userRepository;
	
	
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	/**
	 * Login By Username and password
	 * @param username
	 * @param password
	 * @return
	 */
	public Optional<User> login(String username, String password) {
		return userRepository.login(username, password);
	}
	
	/**
	 * Create new user
	 * 	- Check if already exist
	 * 	- Save new user
	 * @param user
	 * @return
	 */
	public User createUser(User user) {
		Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
		if(userOptional.isPresent()) {
			throw new UserRegistrationException("User with email "+ user.getEmail()+" already exists");
		}
		return userRepository.save(user);
	}

	/**
	 * Update user
	 * @param user
	 * @return
	 */
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	/**
	 * Find All User
	 * @return
	 */
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * Find user by ID
	 * @param id
	 * @return
	 */
	public Optional<User> findUserById(Long id) {
		return userRepository.findById(id);
	}
	
	/**
	 * Delete user by ID
	 * @param id
	 */
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

}
