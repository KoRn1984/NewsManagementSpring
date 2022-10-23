package by.itacademy.matveenko.jd2.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import by.itacademy.matveenko.jd2.bean.User;

@Entity
@Table(name="users")
public class User implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(name="id")
	private int id;
	
	@Column(name="login")
	private String userName;
	
	@Column(name="password")
    private String password;
    
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
    private String userSurname;
    
	@Column(name="email")
    private String email;
    
	@ManyToMany(fetch = FetchType.EAGER)
	@Enumerated(EnumType.ORDINAL)
	@JoinColumn(name="role")	
	private UserRole role; 
	
    public User() {
    }    
     
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setUsername(String userName) {
    	this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
    
    public static class Builder {
        private User newUser;
        
        public Builder() {
            this.newUser = new User();
        }
        
        public Builder withId(int id) {
            newUser.setId(id);
            return this;
        }

        public Builder withUsername(String userName) {
            newUser.setUsername(userName);
            return this;
        }
        public Builder withPassword(String password) {
            newUser.setPassword(password);
            return this;
        }
        public Builder withName(String name) {
            newUser.setName(name);
            return this;
        }
        public Builder withUserSurname(String userSurname) {
            newUser.setUserSurname(userSurname);
            return this;
        }

        public Builder withEmail(String email) {
            newUser.setEmail(email);
            return this;
        }

        public Builder withRole(UserRole role) {
            newUser.setRole(role);
            return this;
        }
        
        public User build() {
            return newUser;
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User that = (User) obj;
        return Objects.equals(id, that.id) && Objects.equals(userName, that.userName) && Objects.equals(password, that.password) 
        	   && Objects.equals(name, that.name) && Objects.equals(userSurname, that.userSurname) 
        	   && Objects.equals(email, that.email) && Objects.equals(role, that.role);
    }
    
    @Override
	public int hashCode() {
    	int result = (int) (id ^ (id >>> 32));
    	result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (userSurname != null ? userSurname.hashCode() : 0);		
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (role != null ? role.hashCode() : 0);
		return result;
	}

	@Override
    public String toString() {
        return "User {" +
        		"id='" + id + '\'' +
        		", login='" + userName + '\'' +
                ", password=" + password +
                ", userName='" + name + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", email='" + email + '\'' +                
                ", role='" + role + '\'' +
                '}';
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<UserRole> roles = new ArrayList<>();
		roles.add(this.role);		
		return roles;
	}

	@Override
	public String getUsername() {		
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}  
}