package dz.springBoot.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Utilisateur avec les informations suivantes :
 * 	- Nom utilisateur
 * 	- Email
 *  - Password
 *  - Rôles : la liste de roles
 *  
 * @author AEK
 *
 */
@Entity
@Table(name = "USER")
public class User {

    @Column(name = "id")
    @Id
    Long id;

    @Column(name = "username")
    String username;
    
    @Column(name = "email")
    String email;
    
    @Column(name = "password")
    String password;
	
    
    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
       name="user_role",
       joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
       inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})


    private List<Role> roles;
    
    
    /**
     * 
     * @param id
     * @param email
     * @param password
     * @param username
     */
    public User(Long id, String email, String password,  String username) {
		super();
		roles = new ArrayList<>();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}
    
    
	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}    

}
