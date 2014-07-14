package br.edu.utfpr.md.architecture.application.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class NavigationController 
{
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(Locale locale, Model model) 
	{
		return "site/view/index.html";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String authentication(Locale locale, Model model) 
	{
		return "authentication/index.html";
	}
}
