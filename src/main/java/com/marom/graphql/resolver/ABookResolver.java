package com.marom.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.marom.graphql.model.ABook;
import com.marom.graphql.model.Author;
import com.marom.graphql.repository.AuthorRepository;
import org.springframework.stereotype.Component;

@Component
public class ABookResolver implements GraphQLResolver<ABook> {

    private AuthorRepository authorRepository;

    public ABookResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(ABook book) {
        return authorRepository.findById(book.getAuthor().getId()).get()    ;
    }
}
