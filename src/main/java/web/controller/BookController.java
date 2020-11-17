package web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import web.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.Book;

import java.util.List;

@RestController
public class BookController {

    private BookDao bookDao;
    @Autowired
    public BookController(BookDao bookDao) {this.bookDao = bookDao;}

    @GetMapping(value = "/title",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getOrderByTitle() {

        List<Book> list = bookDao.getBooksOrderByTitle();
        return (list.size() > 0 )
                ? ResponseEntity.ok(list)
                : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/author",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> BookGroupByAuthor(@RequestParam(value = "author",
            required = false) String author) {

        List<Book> list = bookDao.getBooksGroupByAuthor(author);
        return (list.size() > 0)
                ? ResponseEntity.ok(list)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/add_book")
    public String savbook (@RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "author", required = false) String author,
                           @RequestParam(value = "description", required = false) String description){
        if (bookDao.saveBook(title, author, description)) { return "book save";}
        return "error, book not save";
    }

}
