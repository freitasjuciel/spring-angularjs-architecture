package br.edu.utfpr.md.architecture.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.utfpr.md.architecture.model.entity.User;
import br.edu.utfpr.md.architecture.model.service.AccountService;

@Controller
@RequestMapping(value="account")
public class AccountController
{
	@Autowired
	private AccountService accountService;

	@RequestMapping(method=RequestMethod.POST, produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody User create( @RequestBody User user )
	{
		return this.accountService.createAccount( user );
	}
	
	@RequestMapping(value="{id}/confirm", method=RequestMethod.POST, produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody User confirm( @PathVariable Long id )
	{
		return this.accountService.confirmAccount(id);
	}
	
	@RequestMapping(method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Page<User> list()
	{
		return this.accountService.list( null );
	}
	
	@RequestMapping(value="authenticated", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody User authenticated()
	{
		return User.getAuthenticatedUser();
	}
}