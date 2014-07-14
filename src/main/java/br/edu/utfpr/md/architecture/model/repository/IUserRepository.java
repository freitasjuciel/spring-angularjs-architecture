package br.edu.utfpr.md.architecture.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.edu.utfpr.md.architecture.model.entity.User;

public interface IUserRepository extends PagingAndSortingRepository<User, Long> 
{
	public User findByUsername( String username );
}
