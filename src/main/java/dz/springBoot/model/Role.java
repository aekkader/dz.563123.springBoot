package dz.springBoot.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *  Rôles de l’utilisateur : un ou plusieurs rôles peuvent être assignés à l’utilisateur.
 *  	Rôles disponibles : ROLE_USER, ROLE_SUPERUSER, ROLE_ADMIN, ROLE_NEW
 *  
 * @author AEK
 *
 */
@Entity
@Table(name = "ROLE")
public class Role {

	
	@Column(name = "id")
    @Id
    Long id;

    @Column(name = "name")
    String name;
    
    
    @ManyToMany(mappedBy = "roles")
    private List < User > users;
    
    
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    
}

