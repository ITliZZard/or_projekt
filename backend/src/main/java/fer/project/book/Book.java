package fer.project.book;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_generator")
    @SequenceGenerator(name="book_id_generator", sequenceName = "book_book_id_seq", allocationSize=1)
    private Long book_id;
    private String book_name;
    private int release_year;

    public Book() {
    }

    public Book(Book book) {
        this.book_id = book.book_id;
        this.book_name = book.book_name;
        this.release_year = book.release_year;
    }

    public Book(Long book_id, String book_name, int release_year) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.release_year = release_year;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", book_name='" + book_name + '\'' +
                ", release_year=" + release_year +
                '}';
    }
}
