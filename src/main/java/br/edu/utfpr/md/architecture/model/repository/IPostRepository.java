package br.edu.utfpr.md.architecture.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.edu.utfpr.md.architecture.model.entity.Post;

public interface IPostRepository extends PagingAndSortingRepository<Post, Long> {

}
