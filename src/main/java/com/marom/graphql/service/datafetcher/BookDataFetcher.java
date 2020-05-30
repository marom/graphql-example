package com.marom.graphql.service.datafetcher;

import com.marom.graphql.model.Book;
import com.marom.graphql.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class BookDataFetcher implements DataFetcher<Book> {

    BookRepository bookRepository;

    public BookDataFetcher(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book get(DataFetchingEnvironment environment) {
        return bookRepository.findById(environment.getArgument("id")).get().;
    }
}
