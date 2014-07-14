package br.edu.utfpr.md.architecture.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.utfpr.md.architecture.model.entity.Post;
import br.edu.utfpr.md.architecture.model.service.PostService;

@Controller
@RequestMapping(value="post")
public class PostController 
{
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	
	@Autowired
	private PostService postService;
	
	/*-------------------------------------------------------------------
	 * 		 					BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param pageable
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Page<Post> list( @RequestParam int page, @RequestParam int size )
	{
		return this.postService.listPosts( new PageRequest(page, size) );
	}
	
	/**
	 * 
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value="{id}", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Post findById( @PathVariable Long id )
	{
		return this.postService.findById(id);
	}
	
	/**
	 * 
	 * @param post
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Post insert( @RequestBody Post post )
	{
		return this.postService.insert( post );
	}
	
	/**
	 * 
	 * @param post
	 * @return
	 */
	@RequestMapping(method=RequestMethod.PUT, produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Post update( @RequestBody Post post )
	{
		return this.postService.update( post );
	}
	
	/**
	 * 
	 * @param post
	 * @return
	 */
	@RequestMapping(value="{id}/like", method=RequestMethod.PUT, produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Post like( @PathVariable Long id )
	{
		return this.postService.like( id );
	}
	
	/**
	 * 
	 * @param post
	 * @return
	 */
	@RequestMapping(value="{id}", method=RequestMethod.DELETE, produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Boolean delete( @PathVariable Long id )
	{
		this.postService.remove(id);
		
		return true;
	}
}
