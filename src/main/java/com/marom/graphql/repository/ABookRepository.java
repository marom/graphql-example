package com.marom.graphql.repository;

import com.marom.graphql.model.ABook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ABookRepository extends JpaRepository<ABook, Long> {
}
