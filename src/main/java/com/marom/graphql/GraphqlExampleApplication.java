package com.marom.graphql;

import com.marom.graphql.exception.GraphQLErrorAdapter;
import com.marom.graphql.model.ABook;
import com.marom.graphql.model.Author;
import com.marom.graphql.repository.ABookRepository;
import com.marom.graphql.repository.AuthorRepository;
import com.marom.graphql.resolver.ABookResolver;
import com.marom.graphql.resolver.Mutation;
import com.marom.graphql.resolver.Query;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class GraphqlExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlExampleApplication.class, args);
    }

    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                List<GraphQLError> clientErrors = errors.stream()
                        .filter(this::isClientError)
                        .collect(Collectors.toList());

                List<GraphQLError> serverErrors = errors.stream()
                        .filter(e -> !isClientError(e))
                        .map(GraphQLErrorAdapter::new)
                        .collect(Collectors.toList());

                List<GraphQLError> e = new ArrayList<>();
                e.addAll(clientErrors);
                e.addAll(serverErrors);
                return e;
            }

            protected boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
            }
        };
    }

    @Bean
    public CommandLineRunner demo(AuthorRepository authorRepository, ABookRepository bookRepository) {
        return (args) -> {
            Author author = new Author("Herbert", "Schildt");
            authorRepository.save(author);

            bookRepository.save(new ABook("Java: A Beginner's Guide, Sixth Edition", "0071809252", 728, author));
        };
    }

}
