package fer.project.bibliography;

import fer.project.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="bibliography")
public class BibliographyController {

    private final BibliographyServices bibliographyServices;

    @Autowired
    public BibliographyController(BibliographyServices bibliographyServices) {
        this.bibliographyServices = bibliographyServices;
    }

    @GetMapping
    public List<Bibliography> getBibliography() {
        return bibliographyServices.getBibliography();
    }
}
