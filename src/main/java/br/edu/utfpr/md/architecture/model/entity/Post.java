package br.edu.utfpr.md.architecture.model.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="AR_POST")
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown=true)
public class Post 
{
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar date;
	
	private Long likes;
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	private User user;
	
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public Post() 
	{
	}
	/**
	 * 
	 * @param id
	 * @param title
	 * @param content
	 * @param date
	 * @param likes
	 * @param userId
	 * @param username
	 */
	public Post( Long id, String title, String content, Calendar date, Long likes, Long userId, String username )
	{
		this.setId(id);
		this.setTitle(title);
		this.setContent(content);
		this.setDate(date);
		this.setLikes(likes);
		this.setUser( new User(userId, username, null, null) );
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
	
	public String getTitle() 
	{
		return title;
	}
	
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	public String getContent() 
	{
		return content;
	}
	
	public void setContent(String content) 
	{
		this.content = content;
	}
	
	public Calendar getDate() 
	{
		return date;
	}
	
	public void setDate(Calendar date) 
	{
		this.date = date;
	}
	
	public Long getLikes() 
	{
		return likes;
	}
	
	public void setLikes(Long likes) 
	{
		this.likes = likes;
	}
	
	public User getUser() 
	{
		return user;
	}
	
	public void setUser(User user) 
	{
		this.user = user;
	}
}
