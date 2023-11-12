package fer.project.author;

import com.fasterxml.jackson.annotation.JsonFormat;
import fer.project.book.Book;
import fer.project.university.University;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "author")
public class Author implements Comparable {
    @Id
    private Long author_id;
    private String first_name;
    private String last_name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birth_date;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date death_date;
    private int children_count;

    ///@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Bibliography> bibliographies;

    @OneToMany
    @JoinTable(
            name = "bibliography",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books;

    @ManyToOne(optional = true)
    @JoinTable(
            name = "education",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "university_id")
    )
    private University university;

    public Author() {
    }

    public Author(Long author_id, String first_name, String last_name, Date birth_date, Date death_date, int children_count) {
        this.author_id = author_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.death_date = death_date;
        this.children_count = children_count;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Date getDeath_date() {
        return death_date;
    }

    public void setDeath_date(Date death_date) {
        this.death_date = death_date;
    }

    public int getChildren_count() {
        return children_count;
    }

    public void setChildren_count(int children_count) {
        this.children_count = children_count;
    }

    //public List<Bibliography> getBibliographies() {
        //return bibliographies;
    //}

    //public void setBibliographies(List<Bibliography> bibliographies) {
        //this.bibliographies = bibliographies;
    //}


    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "Author{" +
                "author_id=" + author_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", birth_date=" + birth_date +
                ", death_date=" + death_date +
                ", children_count=" + children_count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(author_id, author.author_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author_id);
    }


    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(Object o) {
        return this.author_id.compareTo(((Author) o).author_id);
    }
}
