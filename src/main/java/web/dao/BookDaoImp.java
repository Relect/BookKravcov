package web.dao;

import web.model.BookMapper;
import web.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class BookDaoImp implements BookDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDaoImp(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean saveBook(String title, String author, String description) {
        return
        jdbcTemplate.update("insert into book(title, author, description) values (?,?,?)",
                title, author, description) > 0;
    }

    @Override
    public List<Book> getBooksOrderByTitle() {
        return jdbcTemplate.query(
                "SELECT * FROM book ORDER BY book.title DESC", new BookMapper());
    }

    @Override
    public List<Book> getBooksGroupByAuthor(String author) {
        return jdbcTemplate.query(
                "SELECT * FROM book WHERE book.author = ?", new BookMapper(), author);

    }
}
