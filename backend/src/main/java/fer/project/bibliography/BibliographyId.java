package fer.project.bibliography;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BibliographyId implements Serializable {

    private Long authorId;
    private Long bookId;

    public BibliographyId() {}

    public BibliographyId(Long authorId, Long bookId) {
        this.authorId = authorId;
        this.bookId = bookId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BibliographyId that = (BibliographyId) o;
        return Objects.equals(authorId, that.authorId) && Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, bookId);
    }
}