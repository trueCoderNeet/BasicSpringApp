package guru.springframework.spring5webapp.bootstrap;

import antlr.DocBookCodeGenerator;
import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    @Override
    public void run(String... args) throws Exception {
        Publisher publisher =new Publisher();
        publisher.setName("Om publications");
        publisher.setAddress("3rd main, Om Publisher street");
        publisher.setCity("Bangalore");
        publisher.setState("Karnataka");
        publisherRepository.save(publisher);

        Author eric = new Author("Eric","Evans");
        Book book=new Book("Domain Driven Design","123456");
        eric.getBooks().add(book);
        book.getAuthors().add(eric);
        book.setPublisher(publisher);

        authorRepository.save(eric);
        bookRepository.save(book);
        publisherRepository.save(publisher);


        Author rod = new Author("Rodd","Jhonson");
        Book rodBook=new Book("J2EE Development without EJB","123457");
        rod.getBooks().add(book);
        rodBook.getAuthors().add(rod);
        rodBook.setPublisher(publisher);


        publisher.getBooks().add(rodBook);
        publisher.getBooks().add(book);

        authorRepository.save(rod);
        bookRepository.save(rodBook);
        publisherRepository.save(publisher);

        System.out.println("Started in bootstrap");
        System.out.println("Number of books:"+bookRepository.count());
        System.out.println("Publisher number of books"+publisher.getBooks().size());
    }

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository= publisherRepository;
    }
}
