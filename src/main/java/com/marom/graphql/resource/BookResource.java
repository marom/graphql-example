package com.marom.graphql.resource;

import com.marom.graphql.service.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/rest/books")
@RestController
public class BookResource {

    GraphQLService service;

    public BookResource(GraphQLService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
        ExecutionResult executionResult = service.getGraphQL().execute(query);
        return new ResponseEntity<>(executionResult, HttpStatus.OK);
    }
}
