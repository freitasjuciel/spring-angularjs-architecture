package br.edu.utfpr.md.architecture.infrastructure.system;

import java.util.Locale;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.edu.utfpr.md.architecture.model.entity.Role;
import br.edu.utfpr.md.architecture.model.entity.User;
import br.edu.utfpr.md.architecture.model.repository.IUserRepository;

public class InitializeBootstrap
{
	/**
	 * 
	 */
	private static final Logger LOG = Logger.getLogger(InitializeBootstrap.class.getName());
	
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//Repository
	/**
	 * 
	 */
	@Autowired
	private IUserRepository userRepository;
	
	/*-------------------------------------------------------------------
	 *				 		     BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@PostConstruct
	public void postConstruct() 
	{
		//Configura o locale default da JVM para ser o Brasileiro
		Locale.setDefault( new Locale("pt", "BR") );

		this.bootstrapUsers();
	}

	/**
	 * 
	 */
	public void bootstrapUsers()
	{
		if ( this.userRepository.count() == 0 )
		{
			LOG.info("Bootstraping users...");

			final User user = new User();
			
			user.setUsername("admin");
			user.setRole( Role.ADMIN );
			user.setEnabled(true);
			user.setEmail("admin@jess.com");
			user.setPassword( this.passwordEncoder.encode("admin123") );

			this.userRepository.save( user );
			
			LOG.info("Default users were added.");
		}
	}
}