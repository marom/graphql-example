package com.marom.graphql.repository;

import com.marom.graphql.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
