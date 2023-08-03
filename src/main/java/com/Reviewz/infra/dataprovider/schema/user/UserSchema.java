package com.Reviewz.infra.dataprovider.schema.user;

import java.util.*;

import com.Reviewz.infra.dataprovider.schema.review.ReviewSchema;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Reviewz.core.user.model.User;


@Table(name = "users")
@Entity
public class UserSchema implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String login;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private UserRole role;

	@OneToMany(mappedBy = "user")
	private Set<ReviewSchema> reviews = new HashSet<>();

	public UserSchema(UUID id, String name, String login, String password, UserRole role) {
		super();
		this.id = id;
		this.name = name;
		this.login = login;
		this.password = password;
		this.role = role;
	}
	
	public UserSchema(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.login = user.getLogin();
		this.password = user.getPassword();
		this.role = user.getRole();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserSchema() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
	
	public UserRole getRole() {
		return role;
	}

	public Set<ReviewSchema> getReviews() {
		return reviews;
	}

	public void setReviews(Set<ReviewSchema> reviews) {
		this.reviews = reviews;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.role == UserRole.ADMIN){
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
					new SimpleGrantedAuthority("ROLE_USER"));
		}
		else {
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		}
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserSchema that)) return false;

		if (!Objects.equals(id, that.id)) return false;
		if (!Objects.equals(name, that.name)) return false;
		if (!Objects.equals(login, that.login)) return false;
		if (!Objects.equals(password, that.password)) return false;
		if (role != that.role) return false;
		return Objects.equals(reviews, that.reviews);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (role != null ? role.hashCode() : 0);
		result = 31 * result + (reviews != null ? reviews.hashCode() : 0);
		return result;
	}
}
