package com.learn.spring5web.bootstrap;

import com.learn.spring5web.model.Author;
import com.learn.spring5web.model.Book;
import com.learn.spring5web.model.Publisher;
import com.learn.spring5web.repositories.AuthorRepository;
import com.learn.spring5web.repositories.BookRepository;
import com.learn.spring5web.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author author1 = new Author("J.K", "Rowling");
        Book book1 = new Book("Harry Potter and the Sorcerer's Stone", "12345");
        Book book2 = new Book("Harry Potter and the Chamber of Secrets", "12345");
        Publisher publisher1 = new Publisher("Barnes & Noble", "Address 1", "City 1", "TX", "9999");

        author1.getBooks().add(book1);
        author1.getBooks().add(book2);
        book1.getAuthors().add(author1);
        book2.getAuthors().add(author1);
        book1.setPublisher(publisher1);
        book2.setPublisher(publisher1);

        this.publisherRepository.save(publisher1);
        this.authorRepository.save(author1);
        this.bookRepository.save(book1);
        this.bookRepository.save(book2);

        publisher1.getBooks().add(book1);
        publisher1.getBooks().add(book2);


        System.out.println("Number of authors in repository "+ this.authorRepository.count());
        System.out.println("Number of books in repository "+ this.bookRepository.count());
        System.out.println("Number of publishers in repository "+ this.publisherRepository.count());
    }
}
