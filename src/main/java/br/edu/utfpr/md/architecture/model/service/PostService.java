package br.edu.utfpr.md.architecture.model.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.utfpr.md.architecture.model.entity.Post;
import br.edu.utfpr.md.architecture.model.entity.User;
import br.edu.utfpr.md.architecture.model.repository.IPostRepository;

@Service
@Transactional
public class PostService 
{
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	
	@Autowired
	private IPostRepository postRepository;
	
	/*-------------------------------------------------------------------
	 * 		 					BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param post
	 * @return
	 */
	@PreAuthorize("hasAnyRole({'ADMIN', 'USER'})")
	public Post insert( Post post )
	{
		post.setId(null);
		post.setDate( Calendar.getInstance() );
		post.setUser( User.getAuthenticatedUser() );
		
		return this.postRepository.save( post );
	}
	
	/**
	 * 
	 * @param post
	 * @return
	 */
	@PreAuthorize("hasAnyRole({'ADMIN', 'USER'})")
	public Post update( Post post )
	{
		return this.postRepository.save( post );
	}
	
	/**
	 * 
	 * @param id
	 */
	@PreAuthorize("hasAnyRole({'ADMIN', 'USER'})")
	public void remove( Long id )
	{
		this.postRepository.delete( id );
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Post like( Long id )
	{
		final Post post = this.postRepository.findOne( id );
		
		if ( post.getLikes() == null )
		{
			post.setLikes( 0L );
		}
		
		post.setLikes( post.getLikes() + 1 );
		
		return this.postRepository.save( post );
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Post findById( Long id )
	{
		return this.postRepository.findOne( id );
	}
	
	/**
	 * 
	 * @param pageable
	 * @return
	 */
	public Page<Post> listPosts( Pageable pageable )
	{
		return this.postRepository.findAll( pageable );
	}
}
