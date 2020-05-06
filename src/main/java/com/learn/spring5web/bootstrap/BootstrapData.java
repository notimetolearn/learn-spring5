package com.learn.spring5web.bootstrap;

import com.learn.spring5web.model.Author;
import com.learn.spring5web.model.Book;
import com.learn.spring5web.repositories.AuthorRepository;
import com.learn.spring5web.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author author1 = new Author("First Name 1", "Last Name 1");
        Book book1 = new Book("Book 1", "12345");

        author1.getBooks().add(book1);
        book1.getAuthors().add(author1);

        this.authorRepository.save(author1);
        this.bookRepository.save(book1);

        Author author2 = new Author("First Name 2", "Last Name 2");
        Book book2 = new Book("Book 2", "12346");

        author2.getBooks().add(book2);
        book2.getAuthors().add(author2);

        this.authorRepository.save(author2);
        this.bookRepository.save(book2);

        System.out.println("Number of authors in repository "+ this.authorRepository.count());
    }
}
