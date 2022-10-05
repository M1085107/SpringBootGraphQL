package com.boot.springbootgraphQl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.springbootgraphQl.model.Book;

public interface BookRepository extends JpaRepository<Book, String>{

}
