package fer.project.author;

import fer.project.Country.Country;
import fer.project.Country.CountryRepository;
import fer.project.bibliography.Bibliography;
import fer.project.book.Book;
import fer.project.database.DatabaseService;
import fer.project.response.Response;
import fer.project.university.University;
import fer.project.university.UniversityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final UniversityRepository universityRepository;
    private final CountryRepository countryRepository;
    private final DatabaseService databaseService;
    private static final int YEAR_OFFSET = 1900;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, UniversityRepository universityRepository, CountryRepository countryRepository, DatabaseService databaseService) {
        this.authorRepository = authorRepository;
        this.universityRepository = universityRepository;
        this.countryRepository = countryRepository;
        this.databaseService = databaseService;
    }

    public ResponseEntity<Response> getAuthors() {
        List<Author> authors = authorRepository.findAllAuthors();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        if(authors.size() > 0) {
            String message = "Elements were successfully fetched.";
            return new ResponseEntity<>
                    (new Response(200, message, authors), responseHeaders, HttpStatus.OK);
        } else {
            String message = "No elements were found.";
            return new ResponseEntity<>
                    (new Response(404, message, null),
                            responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Response> getAuthorsByWildcard(String filter) {
        Long filter_int = null;
        List<Author> all_authors = authorRepository.findAllAuthors();
        List<Author> authors = authorRepository.findAuthorsByWildcardString(filter);
        all_authors.removeAll(authors);

        try {
            filter_int = Long.parseLong(filter);
            for(Author author : all_authors) {
                int birth_year = author.getBirth_date().getYear() + YEAR_OFFSET;
                int death_year = author.getDeath_date().getYear() + YEAR_OFFSET;
                if(author.getAuthor_id().equals(filter_int) ||
                        birth_year == filter_int ||
                        death_year == filter_int ||
                        author.getChildren_count() == filter_int) {
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

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        if(authors.size() > 0) {
            String message = "Elements were successfully fetched for the given value.";
            return new ResponseEntity<>
                    (new Response(200, message, authors), responseHeaders, HttpStatus.OK);
        } else {
            String message = "No elements containing the given value were found.";
            return new ResponseEntity<>
                    (new Response(404, message, null),
                            responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Response> getAuthorsByAuthorID(Long id) {
        List<Author> authors = authorRepository.findAuthorsByAuthorID(id);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        if(authors.size() > 0) {
            String message = "Elements were successfully fetched for the given 'Author ID'.";
            return new ResponseEntity<>
                    (new Response(200, message, authors), responseHeaders, HttpStatus.OK);
        } else {
            String message = "No elements for the given 'Author ID' were found.";
            return new ResponseEntity<>
                    (new Response(404, message, null),
                            responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Response> getAuthorsByFirstName(String name) {
        List<Author> authors = authorRepository.findAuthorsByFirstName(name);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        if(authors.size() > 0) {
            String message = "Elements were successfully fetched for the given 'First name'.";
            return new ResponseEntity<>
                    (new Response(200, message, authors), responseHeaders, HttpStatus.OK);
        } else {
            String message = "No elements for the given 'First name' were found.";
            return new ResponseEntity<>
                    (new Response(404, message, null),
                            responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Response> getAuthorsByLastName(String name) {
        List<Author> authors = authorRepository.findAuthorsByLastName(name);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        if(authors.size() > 0) {
            String message = "Elements were successfully fetched for the given 'Last name'.";
            return new ResponseEntity<>
                    (new Response(200, message, authors), responseHeaders, HttpStatus.OK);
        } else {
            String message = "No elements for the given 'Last name' were found.";
            return new ResponseEntity<>
                    (new Response(404, message, null),
                            responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Response> getAuthorsByBirthYear(Integer year) {
        List<Author> authors = authorRepository.findAuthorsByBirthYear(year);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        if(authors.size() > 0) {
            String message = "Elements were successfully fetched for the given 'Birth year'.";
            return new ResponseEntity<>
                    (new Response(200, message, authors), responseHeaders, HttpStatus.OK);
        } else {
            String message = "No elements for the given 'Birth year' were found.";
            return new ResponseEntity<>
                    (new Response(404, message, null),
                            responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Response> getAuthorsByDeathYear(Integer year) {
        List<Author> authors = authorRepository.findAuthorsByDeathYear(year);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        if(authors.size() > 0) {
            String message = "Elements were successfully fetched for the given 'Death year'.";
            return new ResponseEntity<>
                    (new Response(200, message, authors), responseHeaders, HttpStatus.OK);
        } else {
            String message = "No elements for the given 'Death year' were found.";
            return new ResponseEntity<>
                    (new Response(404, message, null),
                            responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Response> getAuthorsByChildren(Integer children) {
        List<Author> authors = authorRepository.findAuthorsByChildren(children);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        if(authors.size() > 0) {
            String message = "Elements were successfully fetched for the given 'Number of children'.";
            return new ResponseEntity<>
                    (new Response(200, message, authors), responseHeaders, HttpStatus.OK);
        } else {
            String message = "No elements for the given 'Number of children' were found.";
            return new ResponseEntity<>
                    (new Response(404, message, null),
                            responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Response> getAuthorsByUniversity(String university) {
        List<Author> authors = authorRepository.findAuthorsByUniversity(university);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        if(authors.size() > 0) {
            String message = "Elements were successfully fetched for the given 'University'.";
            return new ResponseEntity<>
                    (new Response(200, message, authors), responseHeaders, HttpStatus.OK);
        } else {
            String message = "No elements for the given 'University' were found.";
            return new ResponseEntity<>
                    (new Response(404, message, null),
                            responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Response> getAuthorsByLocation(String location) {
        List<Author> authors = authorRepository.findAuthorsByLocation(location);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        if(authors.size() > 0) {
            String message = "Elements were successfully fetched for the given 'Location'.";
            return new ResponseEntity<>
                    (new Response(200, message, authors), responseHeaders, HttpStatus.OK);
        } else {
            String message = "No elements for the given 'Location' were found.";
            return new ResponseEntity<>
                    (new Response(404, message, null),
                            responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Response> getAuthorsByBookID(Long id) {
        List<Author> authors = authorRepository.findAuthorsByBookID(id);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        if(authors.size() > 0) {
            Book correctBook = null;
            for(Book book : authors.get(0).getBooks()) {
                if(book.getBook_id().equals(id)) {
                    correctBook = new Book(book);
                }
            }
            authors.get(0).setBooks(new HashSet<>());
            authors.get(0).getBooks().add(correctBook);

            String message = "Elements were successfully fetched for the given 'Book ID'.";
            return new ResponseEntity<>
                    (new Response(200, message, authors), responseHeaders, HttpStatus.OK);
        } else {
            String message = "No elements for the given 'Book ID' were found.";
            return new ResponseEntity<>
                    (new Response(404, message, null),
                            responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Response> getAuthorsByBookName(String name) {
        List<Author> authors = authorRepository.findAuthorsByBookName(name);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        if(authors.size() > 0) {
            for(Author author : authors) {
                Set<Book> correctBooks = new HashSet<>();
                for (Book book : author.getBooks()) {
                    if (book.getBook_name().toLowerCase().contains(name.toLowerCase())) {
                        correctBooks.add(new Book(book));
                    }
                }
                author.setBooks(correctBooks);
            }

            String message = "Elements were successfully fetched for the given 'Book name'.";
            return new ResponseEntity<>
                    (new Response(200, message, authors), responseHeaders, HttpStatus.OK);
        } else {
            String message = "No elements for the given 'Book name' were found.";
            return new ResponseEntity<>
                    (new Response(404, message, null),
                            responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Response> getAuthorsByReleaseYear(Integer year) {
        List<Author> authors = authorRepository.findAuthorsByReleaseYear(year);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        if(authors.size() > 0) {
            for(Author author : authors) {
                Set<Book> correctBooks = new HashSet<>();
                for (Book book : author.getBooks()) {
                    if (book.getRelease_year() == year) {
                        correctBooks.add(new Book(book));
                    }
                }
                author.setBooks(correctBooks);
            }

            String message = "Elements were successfully fetched for the given 'Release year'.";
            return new ResponseEntity<>
                    (new Response(200, message, authors), responseHeaders, HttpStatus.OK);
        } else {
            String message = "No elements for the given 'Release year' were found.";
            return new ResponseEntity<>
                    (new Response(404, message, null),
                            responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Response> addAuthor(Author author) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        // check for required parameters
        boolean first_name_present = true;
        boolean last_name_present = true;
        boolean birth_date_present = true;
        boolean country_present = true;
        boolean book_name_present = true;
        boolean release_year_present = true;

        if(author.getFirst_name() == null || author.getFirst_name().strip().length() == 0) {
            first_name_present = false;
        } else if(author.getLast_name() == null || author.getLast_name().strip().length() == 0) {
            last_name_present = false;
        } else if(author.getBirth_date() == null) {
            birth_date_present = false;
        }

        University req_university = author.getUniversity();
        if(req_university != null) {
            Country req_country = req_university.getCountry();
            if(req_country == null || req_country.getCountry_name() == null) {
                country_present = false;
            }
        }

        Set<Book> req_books = author.getBooks();
        if(req_books != null && req_books.size() > 0) {
            for(Book book : req_books) {
                if(book.getBook_name() == null) {
                    book_name_present = false;
                    break;
                } else if(book.getRelease_year() == 0) {
                    release_year_present = false;
                    break;
                }
            }
        }

        if(!first_name_present || !last_name_present || !birth_date_present ||
                !country_present || !book_name_present || !release_year_present) {
            String message = "Invalid request! ";
            if(!first_name_present) {
                message += "Parameter 'First_Name' is required.";
            } else if(!last_name_present) {
                message += "Parameter 'Last_Name' is required.";
            } else if(!birth_date_present) {
                message += "Parameter 'Birth_Date' is required.";
            } else if(!country_present) {
                message += "Parameter 'Country_Name' is required if 'University' is present.";
            } else if(!book_name_present) {
                message += "Parameter 'Book_Name' is required for each book.";
            } else {
                message += "Parameter 'Release_Year' is required for each book.";
            }

            return new ResponseEntity<>
                    (new Response(400, message, null),
                            responseHeaders, HttpStatus.BAD_REQUEST);
        }

        // check for forbidden parameters
        boolean author_id_present = author.getAuthor_id() != null;
        boolean university_id_present = false;
        boolean country_id_present = false;
        boolean book_id_present = false;

        if(req_university != null) {
            if(req_university.getUniversity_id() != 0) {
                university_id_present = true;
            }
            Country req_country = req_university.getCountry();
            if(req_country != null) {
                if(req_country.getCountry_id() != 0) {
                    country_id_present = true;
                }
            }
        }
        if(req_books != null && req_books.size() > 0) {
            for (Book book : req_books) {
                if (book.getBook_id() != null) {
                    book_id_present = true;
                    break;
                }
            }
        }

        // if any of forbidden parameters is present
        if(author_id_present || university_id_present || country_id_present || book_id_present) {
            String message = "Invalid request! ";
            if(author_id_present) {
                message += "Parameter 'Author_ID' is not allowed.";
            } else if(university_id_present) {
                message += "Parameter 'University_ID' is not allowed.";
            } else if(country_id_present) {
                message += "Parameter 'Country_ID' is not allowed.";
            } else {
                message += "Parameter 'Book_ID' is not allowed.";
            }

            return new ResponseEntity<>
                    (new Response(400, message, null),
                            responseHeaders, HttpStatus.BAD_REQUEST);
        }

        databaseService.resetAuthorIdSequence();
        databaseService.resetBookIdSequence();

        // check if university already exists
        University university = author.getUniversity();
        String country_name = (author.getUniversity().getCountry().getCountry_name());
        if(university != null) {
            List<University> universities = authorRepository.findUniversityByName(university.getUniversity_name());
            if(universities.size() > 0) {
                if(!university.getCountry().getCountry_name().equals(universities.get(0).getCountry().getCountry_name())) {
                    String message = "Same university can't be located in different countries.";
                    return new ResponseEntity<>
                            (new Response(400, message, null), responseHeaders, HttpStatus.BAD_REQUEST);
                }
                university.setUniversity_id(universities.get(0).getUniversity_id());
            } else {
                databaseService.resetUniversityIdSequence();
            }

            // check if country already exists
            Country country = university.getCountry();
            List<Country> countries = authorRepository.findCountryByName(country.getCountry_name());
            if(countries.size() > 0) {
                country.setCountry_id(countries.get(0).getCountry_id());
            } else {
                databaseService.resetCountryIdSequence();
            }
            country = countryRepository.save(country);
            university.setCountry(country);
            university = universityRepository.save(university);
            author.setUniversity(university);
        }

        authorRepository.save(author);

        String message = "Passed 'Author' was successfully added.";
        List<Author> addedAuthor = new LinkedList<>();
        addedAuthor.add(author);
        return new ResponseEntity<>
                (new Response(200, message, addedAuthor), responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Response> deleteAuthor(Long id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        if(id == null) {
            String message = "No 'Author_ID' parameter was found in path.";
            return new ResponseEntity<>(new Response(400, message, null), responseHeaders, HttpStatus.BAD_REQUEST);
        }

        boolean authorExists = authorRepository.existsById(id);
        if(authorExists) {
            List<Author> author = authorRepository.findAuthorsByAuthorID(id);
            authorRepository.deleteById(id);
            String message = "Element with 'Author_ID' = " + id + " was deleted successfully.";
            return new ResponseEntity<>(new Response(200, message, author), responseHeaders, HttpStatus.OK);
        } else {
            String message = "No elements with 'Author_ID' = " + id + " were found.";
            return new ResponseEntity<>(new Response(404, message, null), responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<Response> updateAuthor(Book book, Long id) {
        // check if resource exists for the given id
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        // check if author with given id exists
        if(id == null) {
            String message = "No 'Book_ID' parameter was found in path.";
            return new ResponseEntity<>(new Response(400, message, null), responseHeaders, HttpStatus.BAD_REQUEST);
        }

        List<Author> authors = authorRepository.findAuthorsByBookID(id);
        if(authors.size() > 0) {
            Author author = authors.get(0);

            Set<Book> books = author.getBooks();
            Iterator<Book> iterator = books.iterator();
            Book book_db = null;
            while(iterator.hasNext()) {
                book_db = iterator.next();
                if(book_db.getBook_id().equals(id)) {
                    break;
                }
            }

            // check if new book contains book id
            if(book.getBook_id() != null) {
                String message = "Invalid request. Parameter 'Book_ID' can't be modified.";

                return new ResponseEntity<>
                    (new Response(400, message, null),
                            responseHeaders, HttpStatus.BAD_REQUEST);
            }

            boolean book_updated = false;
            if(book.getBook_name() != null) {
                book_db.setBook_name(book.getBook_name());
                book_updated = true;
            }
            if(book.getRelease_year() != 0) {
                book_db.setRelease_year(book.getRelease_year());
                book_updated = true;
            }

            if(!book_updated) {
                String message = "No valid values were passed for 'Book_ID' = " + id + ". Check your parameters.";
                return new ResponseEntity<>(new Response(400, message, null), responseHeaders, HttpStatus.BAD_REQUEST);
            }

            String message = "Element with 'Book_ID' = " + id + " was updated successfully.";
            return new ResponseEntity<>(new Response(200, message, authors), responseHeaders, HttpStatus.OK);
        } else {
            String message = "No elements with 'Book_ID' = " + id + " were found.";
            return new ResponseEntity<>(new Response(404, message, null), responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    public boolean validNameCheck(String name)
    {
        if(name == null) return false;
        String forbiddenCharacters=" !#$%&'()*+,-./:;<=>?@[]^_`{|}~0123456789";
        String nameArray[] = name.split("");
        for (String character : nameArray)
        {
            if (forbiddenCharacters.contains(character))
            {
                return false;
            }
        }
        return true;
    }
}
