package br.edu.utfpr.md.architecture.model.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="AR_USER")
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown=true)
public class User implements UserDetails
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 291869624489653531L;

	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String username;
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	@Size(min=6)
	private String password;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private Role role;
	
	@Column(nullable=false)
	private Boolean enabled;
	
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public User() 
	{
		this.setEnabled(false);
	}
	/**
	 * 
	 * @param id
	 * @param username
	 * @param password
	 * @param role
	 */
	public User( Long id, String username, String password, Role role ) 
	{
		this.setId(id);
		this.setUsername(username);
		this.setPassword(password);
		this.setRole(role);
	}

	/*-------------------------------------------------------------------
	 * 		 					GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	
	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public Role getRole() 
	{
		return role;
	}
	
	public void setRole(Role role) 
	{
		this.role = role;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	@Override
	public String getPassword()
	{
		return this.password;
	}
	
	public void setEnabled( Boolean enabled )
	{
		this.enabled = enabled;
	}
	
	/*-------------------------------------------------------------------
	 * 		 					USER DETAILS
	 *-------------------------------------------------------------------*/
	
	public static User getAuthenticatedUser()
	{
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if ( authentication != null && authentication.getPrincipal() instanceof User )
		{
			return ( User ) authentication.getPrincipal();
		}
		
		return null;
	}
	
	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		final Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		
		authorities.add( this.role );
		return authorities;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() 
	{
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() 
	{
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() 
	{
		return enabled;
	}
}
