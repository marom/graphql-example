package com.marom.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.marom.graphql.model.ABook;
import com.marom.graphql.model.Author;
import com.marom.graphql.repository.ABookRepository;
import com.marom.graphql.repository.AuthorRepository;


public class Query implements GraphQLQueryResolver {

    private ABookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Query(AuthorRepository authorRepository, ABookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Iterable<ABook> findAllBooks() {
        return bookRepository.findAll();
    }

    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public long countBooks() {
        return bookRepository.count();
    }
    public long countAuthors() {
        return authorRepository.count();
    }
}
