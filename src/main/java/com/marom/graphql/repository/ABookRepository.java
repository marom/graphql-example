package com.marom.graphql.repository;

import com.marom.graphql.model.ABook;
import org.springframework.data.repository.CrudRepository;

public interface ABookRepository extends CrudRepository<ABook, Long> {
}
