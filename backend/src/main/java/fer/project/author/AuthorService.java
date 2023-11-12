package fer.project.author;

import fer.project.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private static final int YEAR_OFFSET = 1900;


    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> getAuthorsByWildcard(String filter) {
        Long filter_int = null;
        List<Author> all_authors = authorRepository.findAll();
        List<Author> authors = authorRepository.findAuthorsByWildcardString(filter);
        all_authors.removeAll(authors);

        try {
            filter_int = Long.parseLong(filter);
            for(Author author : all_authors) {
                int birth_year = author.getBirth_date().getYear() + YEAR_OFFSET;
                int death_year = author.getDeath_date().getYear() + YEAR_OFFSET;
                if(author.getAuthor_id().equals(filter_int) ||
                        birth_year == filter_int ||
                        death_year == filter_int) {
                    authors.add(author);
                }
            }
            all_authors.removeAll(authors);

        } catch (NumberFormatException e) {
            // ignorable
        }

        // filtering by books
        for(Author author : all_authors) {
            Set<Book> correctBooks = new HashSet<>();
            for (Book book : author.getBooks()) {
                boolean matching_int = false;
                if(filter_int != null) {
                    if(book.getRelease_year() == filter_int || book.getBook_id().equals(filter_int)) {
                        matching_int = true;
                    }
                }
                if (book.getBook_name().toLowerCase().contains(filter.toLowerCase()) || matching_int) {
                    correctBooks.add(new Book(book));
                }
            }
            if(correctBooks.size() > 0) {
                author.setBooks(correctBooks);
                authors.add(author);
            }

        }
        Collections.sort(authors);
        return authors;
    }

    public List<Author> getAuthorsByAuthorID(Long id) {
        return authorRepository.findAuthorsByAuthorID(id);
    }

    public List<Author> getAuthorsByFirstName(String name) {
        return authorRepository.findAuthorsByFirstName(name);
    }

    public List<Author> getAuthorsByLastName(String name) {
        return authorRepository.findAuthorsByLastName(name);
    }

    public List<Author> getAuthorsByBirthYear(Integer year) {
        return authorRepository.findAuthorsByBirthYear(year);
    }

    public List<Author> getAuthorsByDeathYear(Integer year) {
        return authorRepository.findAuthorsByDeathYear(year);
    }

    public List<Author> getAuthorsByChildren(Integer children) {
        return authorRepository.findAuthorsByChildren(children);
    }

    public List<Author> getAuthorsByUniversity(String university) {
        return authorRepository.findAuthorsByUniversity(university);
    }

    public List<Author> getAuthorsByLocation(String location) {
        return authorRepository.findAuthorsByLocation(location);
    }

    public List<Author> getAuthorsByBookID(Long id) {
        List<Author> author = authorRepository.findAuthorsByBookID(id);
        Book correctBook = null;
        for(Book book : author.get(0).getBooks()) {
            if(book.getBook_id().equals(id)) {
                correctBook = new Book(book);
            }
        }
        author.get(0).setBooks(new HashSet<>());
        author.get(0).getBooks().add(correctBook);
        return author;
    }

    public List<Author> getAuthorsByBookName(String name) {
        List<Author> authors = authorRepository.findAuthorsByBookName(name);
        for(Author author : authors) {
            Set<Book> correctBooks = new HashSet<>();
            for (Book book : author.getBooks()) {
                if (book.getBook_name().toLowerCase().contains(name.toLowerCase())) {
                    correctBooks.add(new Book(book));
                }
            }
            author.setBooks(correctBooks);
        }
        return authors;
    }

    public List<Author> getAuthorsByReleaseYear(Integer year) {
        List<Author> authors = authorRepository.findAuthorsByReleaseYear(year);
        for(Author author : authors) {
            Set<Book> correctBooks = new HashSet<>();
            for (Book book : author.getBooks()) {
                if (book.getRelease_year() == year) {
                    correctBooks.add(new Book(book));
                }
            }
            author.setBooks(correctBooks);
        }
        return authors;
    }
}
