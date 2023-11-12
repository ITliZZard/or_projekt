package fer.project.bibliography;

import fer.project.author.Author;
import fer.project.author.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BibliographyServices {
    private final BibliographyRepository bibliographyRepository;

    @Autowired
    public BibliographyServices(BibliographyRepository bibliographyRepository) {
        this.bibliographyRepository = bibliographyRepository;
    }

    public List<Bibliography> getBibliography() {
        return bibliographyRepository.findAll();
    }
}
