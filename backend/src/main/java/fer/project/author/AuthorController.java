package fer.project.author;

import fer.project.book.Book;
import fer.project.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<Response> getAuthors() {
        return authorService.getAuthors();
    }

    @RequestMapping(value = "/filter_by/all/{filter}", method = RequestMethod.GET)
    public ResponseEntity<Response> getAuthorsByWildcard(@PathVariable("filter") String filter) {
        return authorService.getAuthorsByWildcard(filter);
    }

    @RequestMapping(value = "/filter_by/author_id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response> getAuthorsByAuthorID(@PathVariable("id") Long id) {
        return authorService.getAuthorsByAuthorID(id);
    }

    @RequestMapping(value = "/filter_by/first_name/{name}", method = RequestMethod.GET)
    public ResponseEntity<Response> getAuthorsByFirstName(@PathVariable("name") String name) {
        return authorService.getAuthorsByFirstName(name);
    }

    @RequestMapping(value = "/filter_by/last_name/{name}", method = RequestMethod.GET)
    public ResponseEntity<Response> getAuthorsByLastName(@PathVariable("name") String name) {
        return authorService.getAuthorsByLastName(name);
    }

    @RequestMapping(value = "/filter_by/birth_year/{year}", method = RequestMethod.GET)
    public ResponseEntity<Response> getAuthorsByBirthYear(@PathVariable("year") Integer year) {
        return authorService.getAuthorsByBirthYear(year);
    }

    @RequestMapping(value = "/filter_by/death_year/{year}", method = RequestMethod.GET)
    public ResponseEntity<Response> getAuthorsByDeathYear(@PathVariable("year") Integer year) {
        return authorService.getAuthorsByDeathYear(year);
    }

    @RequestMapping(value = "/filter_by/number_of_children/{children}", method = RequestMethod.GET)
    public ResponseEntity<Response> getAuthorsByChildren(@PathVariable("children") Integer children) {
        return authorService.getAuthorsByChildren(children);
    }

    @RequestMapping(value = "/filter_by/university/{university}", method = RequestMethod.GET)
    public ResponseEntity<Response> getAuthorsByUniversity(@PathVariable("university") String university) {
        return authorService.getAuthorsByUniversity(university);
    }

    @RequestMapping(value = "/filter_by/location/{location}", method = RequestMethod.GET)
    public ResponseEntity<Response> getAuthorsByLocation(@PathVariable("location") String location) {
        return authorService.getAuthorsByLocation(location);
    }

    @RequestMapping(value = "/filter_by/book_id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response> getAuthorsByBookID(@PathVariable("id") Long id) {
        return authorService.getAuthorsByBookID(id);
    }

    @RequestMapping(value = "/filter_by/book_name/{name}", method = RequestMethod.GET)
    public ResponseEntity<Response> getAuthorsByBookName(@PathVariable("name") String name) {
        return authorService.getAuthorsByBookName(name);
    }

    @RequestMapping(value = "/filter_by/release_year/{year}", method = RequestMethod.GET)
    public ResponseEntity<Response> getAuthorsByReleaseYear(@PathVariable("year") Integer year) {
        return authorService.getAuthorsByReleaseYear(year);
    }

    /*@RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Response> getAuthorsByWildcard() {
        return authorService.getAuthors();
    }*/

    @PostMapping
    public ResponseEntity<Response> addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> deleteAuthor(@PathVariable("id") Long id) {
        return authorService.deleteAuthor(id);
    }

    @RequestMapping(value = "/update_book/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Response> updateBook(@RequestBody Book book, @PathVariable("id") Long id) {
        return authorService.updateAuthor(book, id);
    }
}
