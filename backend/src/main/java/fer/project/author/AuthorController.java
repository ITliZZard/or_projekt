package fer.project.author;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    @RequestMapping(value = "/filter_by/all/{filter}", method = RequestMethod.GET)
    public List<Author> getAuthorsByWildcard(@PathVariable("filter") String filter) {
        return authorService.getAuthorsByWildcard(filter);
    }

    @RequestMapping(value = "/filter_by/author_id/{id}", method = RequestMethod.GET)
    public List<Author> getAuthorsByAuthorID(@PathVariable("id") Long id) {
        return authorService.getAuthorsByAuthorID(id);
    }

    @RequestMapping(value = "/filter_by/first_name/{name}", method = RequestMethod.GET)
    public List<Author> getAuthorsByFirstName(@PathVariable("name") String name) {
        return authorService.getAuthorsByFirstName(name);
    }

    @RequestMapping(value = "/filter_by/last_name/{name}", method = RequestMethod.GET)
    public List<Author> getAuthorsByLastName(@PathVariable("name") String name) {
        return authorService.getAuthorsByLastName(name);
    }

    @RequestMapping(value = "/filter_by/birth_year/{year}", method = RequestMethod.GET)
    public List<Author> getAuthorsByBirthYear(@PathVariable("year") Integer year) {
        return authorService.getAuthorsByBirthYear(year);
    }

    @RequestMapping(value = "/filter_by/death_year/{year}", method = RequestMethod.GET)
    public List<Author> getAuthorsByDeathYear(@PathVariable("year") Integer year) {
        return authorService.getAuthorsByDeathYear(year);
    }

    @RequestMapping(value = "/filter_by/number_of_children/{children}", method = RequestMethod.GET)
    public List<Author> getAuthorsByChildren(@PathVariable("children") Integer children) {
        return authorService.getAuthorsByChildren(children);
    }

    @RequestMapping(value = "/filter_by/university/{university}", method = RequestMethod.GET)
    public List<Author> getAuthorsByUniversity(@PathVariable("university") String university) {
        return authorService.getAuthorsByUniversity(university);
    }

    @RequestMapping(value = "/filter_by/location/{location}", method = RequestMethod.GET)
    public List<Author> getAuthorsByLocation(@PathVariable("location") String location) {
        return authorService.getAuthorsByLocation(location);
    }

    @RequestMapping(value = "/filter_by/book_id/{id}", method = RequestMethod.GET)
    public List<Author> getAuthorsByBookID(@PathVariable("id") Long id) {
        return authorService.getAuthorsByBookID(id);
    }

    @RequestMapping(value = "/filter_by/book_name/{name}", method = RequestMethod.GET)
    public List<Author> getAuthorsByBookName(@PathVariable("name") String name) {
        return authorService.getAuthorsByBookName(name);
    }

    @RequestMapping(value = "/filter_by/release_year/{year}", method = RequestMethod.GET)
    public List<Author> getAuthorsByReleaseYear(@PathVariable("year") Integer year) {
        return authorService.getAuthorsByReleaseYear(year);
    }

    /*
    @GetMapping(value = "/download/json", produces = "application/json")
    public ResponseEntity<String> downloadAuthors() {
        try {
            List<Author> authors = authorService.getAuthors();

            String json = new String();

            for

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setContentDispositionFormData("attachment", "authors.json");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(json);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }*/
}
