package com.marom.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.marom.graphql.exception.BookNotFoundException;
import com.marom.graphql.model.ABook;
import com.marom.graphql.model.Author;
import com.marom.graphql.repository.ABookRepository;
import com.marom.graphql.repository.AuthorRepository;

public class Mutation implements GraphQLMutationResolver {
    private ABookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Mutation(AuthorRepository authorRepository, ABookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Author newAuthor(String firstName, String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);

        authorRepository.save(author);

        return author;
    }

    public ABook newBook(String title, String isbn, Integer pageCount, Long authorId) {
        ABook book = new ABook();
        book.setAuthor(new Author(authorId));
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPageCount(pageCount != null ? pageCount : 0);

        bookRepository.save(book);

        return book;
    }

    public boolean deleteBook(Long id) {
        bookRepository.deleteById(id);
        return true;
    }

    public ABook updateBookPageCount(Integer pageCount, Long id) {
        ABook book = bookRepository.findById(id).get();
        if(book == null) {
            throw new BookNotFoundException("The book to be updated was found", id);
        }
        book.setPageCount(pageCount);

        bookRepository.save(book);

        return book;
    }
}
