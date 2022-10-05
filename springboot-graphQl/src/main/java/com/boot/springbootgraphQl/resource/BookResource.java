package com.boot.springbootgraphQl.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.springbootgraphQl.service.GraphqlService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/rest/books")
public class BookResource {
	
	@Autowired
	private GraphqlService graphqlService;

	@PostMapping
	public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
		
		ExecutionResult execute=graphqlService.getGraphql().execute(query);
		return new ResponseEntity<Object>(execute,HttpStatus.OK);
		
	}
}
