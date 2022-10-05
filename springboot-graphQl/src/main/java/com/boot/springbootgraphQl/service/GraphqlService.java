package com.boot.springbootgraphQl.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;

import com.boot.springbootgraphQl.model.Book;
import com.boot.springbootgraphQl.repository.BookRepository;
import com.boot.springbootgraphQl.service.datafetcher.AllBookDataFetcher;
import com.boot.springbootgraphQl.service.datafetcher.BookDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphqlService {
	
	@Autowired
	BookRepository bookRepository;
	
	@Value("classpath:books.graphql")
	Resource resource;
	
	private GraphQL graphQL;
	
	@Autowired
	private AllBookDataFetcher allBookDataFetcher;
	
	@Autowired
	private BookDataFetcher bookDataFetcher;
	
	@PostConstruct
	private void loadSchema() throws IOException{
		loadDataIntoHSQL();
		File schemaFile=resource.getFile();
		TypeDefinitionRegistry typeRegistry=new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring= buildRuntimeWiring();
		GraphQLSchema schema=new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		graphQL=GraphQL.newGraphQL(schema).build();
	}

	private void loadDataIntoHSQL() {
		Stream.of(new Book("111", "Akash", "Dey publisher", new String[] {"Chola","Vindi"}, "1993"),
				new Book("112", "Goutam", "Con publisher", new String[] {"Alu"}, "1994"),
				new Book("113", "Raju", "Thele wala", new String[] {"Chola","Kolcha"}, "1996"),
				new Book("114", "Tinush", "Chai Break", new String[] {"Namak","Gobi"}, "1963")
				).forEach(book-> bookRepository.save(book));
		
	}

	private RuntimeWiring buildRuntimeWiring() {
		// TODO Auto-generated method stub
		return RuntimeWiring.newRuntimeWiring()
				.type("Query",typeWiring->typeWiring
					.dataFetcher("allBooks", allBookDataFetcher)
					.dataFetcher("book",  bookDataFetcher))
				.build();
	}
	
	public GraphQL getGraphql() {
		return graphQL;
	}

}
