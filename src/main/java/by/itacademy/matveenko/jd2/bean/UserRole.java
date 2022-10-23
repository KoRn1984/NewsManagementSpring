package by.itacademy.matveenko.jd2.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
public enum UserRole implements GrantedAuthority {
	ADMIN(1, "ROLE_ADMIN"), USER(2, "ROLE_USER"), GUEST(3, "ROLE_GUEST");
	
	@Id
	@Column(name="id")
	private int role;
	
	@Column(name="role")
	private String name;
	
	UserRole (int role, String name) {
		this.role = role;
		this.name = name;
	};
	
	public int getRole() {
		return role;
	}	
	
	public String getName() {
		return name;
	}

	@Override
	public String getAuthority() {
		return getName();
	}	
}