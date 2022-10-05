package com.boot.springbootgraphQl.service.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boot.springbootgraphQl.model.Book;
import com.boot.springbootgraphQl.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllBookDataFetcher implements DataFetcher<List<Book>>{
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> get(DataFetchingEnvironment environment) throws Exception {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

}
