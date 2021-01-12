package dz.springBoot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dz.springBoot.model.User;

import java.util.Optional;

/**
 * 
 * User Repository : we use JPA
 * 
 * @author AEK
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	
	
	/**
	 * Login By Username and Password
	 * @param username
	 * @param password
	 * @return
	 */
//    @Query("select u from USER u where u.email=? and u.password=?")
	@Query("select u from USER u where u.username=? and u.password=?")
	Optional<User> login(String username, String password);
	
	
	/**
	 * Find user by mail : 
	 * 	- When save new user (pb Duplicate)
	 * @param email
	 * @return
	 */
	Optional<User> findByEmail(String email);
	
}
