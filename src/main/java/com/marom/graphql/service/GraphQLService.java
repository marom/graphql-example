package com.marom.graphql.service;

import com.marom.graphql.model.Book;
import com.marom.graphql.repository.BookRepository;
import com.marom.graphql.service.datafetcher.AllBooksDataFetcher;
import com.marom.graphql.service.datafetcher.BookDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

@Service
public class GraphQLService {

    @Value("classpath:books.graphql")
    Resource resource;

    public GraphQLService(AllBooksDataFetcher allBooksDataFetcher, BookDataFetcher bookDataFetcher, BookRepository bookRepository) {
        this.allBooksDataFetcher = allBooksDataFetcher;
        this.bookDataFetcher = bookDataFetcher;
        this.bookRepository = bookRepository;
    }

    private GraphQL graphQL;
    private AllBooksDataFetcher allBooksDataFetcher;
    private BookDataFetcher bookDataFetcher;
    private BookRepository bookRepository;


    @PostConstruct
    private void loadSchema() throws IOException {

        //Load Books into the Book Repository
        loadDataIntoHSQL();

        // get the schema
        File schemaFile = resource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }


    private void loadDataIntoHSQL() {

        Stream.of(
                new Book("123", "Book of Clouds", "Kindle Edition",
                        Arrays.asList("Chloe Aridjis"), "Nov 2017"),
                new Book("124", "Cloud Arch & Engineering", "Orielly",
                        Arrays.asList("Peter", "Sam"), "Jan 2015"),
                new Book("125", "Java 9 Programming", "Orielly",
                        Arrays.asList("Venkat", "Ram"), "Dec 2016")
        ).forEach(book -> {
            bookRepository.save(book);
        });
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allBooks", allBooksDataFetcher)
                        .dataFetcher("book", bookDataFetcher))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
