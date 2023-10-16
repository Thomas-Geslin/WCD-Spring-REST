package springproject.wcdspringrest.controller;

import org.springframework.web.bind.annotation.*;
import springproject.wcdspringrest.entity.Book;
import springproject.wcdspringrest.repository.BookRepository;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    public final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/books")
    public List<Book> getAll() {
        return repository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable long id) {
        return repository.findById(id).get();
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        return repository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable long id, @RequestBody Book book) {
        Book bookToUpdate = repository.findById(id).get();
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setDescription(book.getDescription());
        return repository.save(bookToUpdate);
    }

    @DeleteMapping("/books/{id}")
    public boolean deleteBook(@PathVariable long id) {
        repository.deleteById(id);
        return true;
    }

    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return repository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

}
