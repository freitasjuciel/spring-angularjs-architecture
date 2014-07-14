package br.edu.utfpr.md.architecture.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.utfpr.md.architecture.infrastructure.mail.SampleMailRepository;
import br.edu.utfpr.md.architecture.model.entity.Role;
import br.edu.utfpr.md.architecture.model.entity.User;
import br.edu.utfpr.md.architecture.model.repository.IUserRepository;

@Service
@Transactional
public class AccountService implements UserDetailsService
{

	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private SampleMailRepository mailRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/*-------------------------------------------------------------------
	 * 		 					BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException 
	{
		final User user = this.userRepository.findByUsername( username );
		
		if ( user == null )
		{
			throw new UsernameNotFoundException("Email not found.");
		}
		
		return user;
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public User createAccount( User user )
	{
		user.setEnabled(false);
		user.setPassword( this.passwordEncoder.encode( user.getPassword() ) );
		user.setRole( Role.USER );
		
		user = this.userRepository.save( user );
		
		this.mailRepository.sendConfirmRegisterMail(user);
		
		return user;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public User confirmAccount( Long id )
	{
		final User user = this.userRepository.findOne(id);
		
		user.setEnabled(true);
		return this.userRepository.save(user);
	}
	
	/**
	 * 
	 * @param pageable
	 * @return
	 */
	public Page<User> list( Pageable pageable )
	{
		return this.userRepository.findAll( pageable );
	}
}
