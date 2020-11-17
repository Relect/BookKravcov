package web.dao;

import web.model.Book;

import java.util.List;

public interface BookDao {
    boolean saveBook(String title, String author, String description);
    List<Book> getBooksOrderByTitle();
    List<Book> getBooksGroupByAuthor(String author);
}
