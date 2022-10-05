package com.boot.springbootgraphQl.service.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boot.springbootgraphQl.model.Book;
import com.boot.springbootgraphQl.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BookDataFetcher implements DataFetcher<Book> {
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book get(DataFetchingEnvironment environment) throws Exception {
		
		String isn=environment.getArgument("id");
		return bookRepository.findById(isn).orElse(null);
	}

}
