package fer.project.bibliography;

import fer.project.author.Author;
import fer.project.book.Book;
import jakarta.persistence.*;

@Entity
@Table(name = "bibliography")
public class Bibliography {

    @EmbeddedId
    private BibliographyId id;

    @ManyToOne
    @MapsId("authorId")
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    public Bibliography() {}

    public Bibliography(BibliographyId id, Author author, Book book) {
        this.id = id;
        this.author = author;
        this.book = book;
    }

    public BibliographyId getId() {
        return id;
    }

    public void setId(BibliographyId id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Bibliography{" +
                "id=" + id +
                ", author=" + author +
                ", book=" + book +
                '}';
    }
}
