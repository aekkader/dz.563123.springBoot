package dz.springBoot;

import com.fasterxml.jackson.databind.ObjectMapper;

import dz.springBoot.controller.UserController;
import dz.springBoot.model.Role;
import dz.springBoot.model.User;
import dz.springBoot.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * JUnit Test for User Controller : Find, ...
 * 
 * @author AEK
 *
 */
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	private List<User> userList;
	private User user;
	
	private Long userId = 1L;
	private String username = "user001";
	private String password = "pwd001";
	private String email = "user001pwd001@gmail.com";
	
	
	/**
	 * Init Before each test
	 */
	@BeforeEach
	void setUp() {
		this.userList = new ArrayList<>();
		this.userList.add(new User(1L, "user001pwd001@gmail.com", "pwd001", "user001"));
		this.userList.add(new User(2L, "user001pwd001@gmail.com", "pwd001", "user001"));
		this.userList.add(new User(3L, "user001pwd001@gmail.com", "pwd001", "user001"));
		
		user = new User(userId, email, password, username);
		Role roleAdmin = new Role();
		roleAdmin.setId(1L);
		roleAdmin.setName("ROLE_SUPERUSER");
//        roleAdmin.setUsers(users);
		user.getRoles().add(roleAdmin);
	}
	
	/**
     * JUnit Test for find all users
	 * @throws Exception
	 */
	@Test
	void testFindAllUsers() throws Exception {
		given(userService.findAllUsers())
			.willReturn(this.userList);
		
		this.mockMvc.perform(get("/api/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(userList.size())));
	}
    
	/**
	 * JUnit Test for Login
	 * @throws Exception
	 */
	@Test
	void testLogin() throws Exception {
		given(userService.login(username, password))
			.willReturn(Optional.of(user));
		
		this.mockMvc.perform(post("/api/users/login")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk())
				
				.andExpect(jsonPath("$.email", is(user.getEmail())))
				.andExpect(jsonPath("$.password", is(user.getPassword())))
				.andExpect(jsonPath("$.username", is(user.getUsername())));
	}
    
	/**
     * JUnit Test for : find user by ID
	 * @throws Exception
	 */
    @Test
    void testFindUserById() throws Exception {
    	given(userService.findUserById(userId))
    		.willReturn(Optional.of(user));
    	
    	this.mockMvc.perform(get("/api/users/{id}", userId))
    			.andExpect(status().isOk())
    			
    			.andExpect(jsonPath("$.email", is(user.getEmail())))
    			.andExpect(jsonPath("$.password", is(user.getPassword())))
    			.andExpect(jsonPath("$.username", is(user.getUsername())));
    }

    /**
     * JUnit Test for : Create new user
     * @throws Exception
     */
    @Test
    void testCreateNewUser() throws Exception {
    	given(userService.createUser(any(User.class)))
    		.willAnswer((invocation) -> invocation.getArgument(0));
    	
    	this.mockMvc.perform(post("/api/users")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(objectMapper.writeValueAsString(user)))
    			.andExpect(status().isCreated())
    			
    			.andExpect(jsonPath("$.email", is(user.getEmail())))
    			.andExpect(jsonPath("$.password", is(user.getPassword())))
    			.andExpect(jsonPath("$.username", is(user.getUsername())));
    }

    /**
     * JUnit Test for : Update user
     * @throws Exception
     */
    @Test
    void testUpdateUser() throws Exception {
    	given(userService.findUserById(userId))
    		.willReturn(Optional.of(user));
    	given(userService.updateUser(any(User.class)))
    		.willAnswer((invocation) -> invocation.getArgument(0));
    	
    	this.mockMvc.perform(put("/api/users/{id}", userId)
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(objectMapper.writeValueAsString(user)))
    			.andExpect(status().isOk())
    			
    			.andExpect(jsonPath("$.email", is(user.getEmail())))
    			.andExpect(jsonPath("$.password", is(user.getPassword())))
    			.andExpect(jsonPath("$.username", is(user.getUsername())));
    }
    
    /**
     * JUnit Test for : delete user by ID
     * @throws Exception
     */
    @Test
    void testDeleteUser() throws Exception {
    	given(userService.findUserById(userId))
    		.willReturn(Optional.of(user));
    	doNothing().when(userService).deleteUserById(userId);
    	
    	this.mockMvc.perform(delete("/api/users/{id}", userId))
    			.andExpect(status().isOk())
    			
    			.andExpect(jsonPath("$.email", is(user.getEmail())))
    			.andExpect(jsonPath("$.password", is(user.getPassword())))
    			.andExpect(jsonPath("$.username", is(user.getUsername())));
    }

}
