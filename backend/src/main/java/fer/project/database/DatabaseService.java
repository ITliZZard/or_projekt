package fer.project.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DatabaseService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void resetAuthorIdSequence() {
        String sql = "SELECT setval('author_author_id_seq', COALESCE((SELECT MAX(author_id) + 1 FROM author), 1), false)";
        jdbcTemplate.execute(sql);
    }

    @Transactional
    public void resetBookIdSequence() {
        String sql = "SELECT setval('book_book_id_seq', COALESCE((SELECT MAX(book_id) + 1 FROM book), 1), false)";
        jdbcTemplate.execute(sql);
    }

    @Transactional
    public void resetUniversityIdSequence() {
        String sql = "SELECT setval('university_university_id_seq', COALESCE((SELECT MAX(university_id) + 1 FROM university), 1), false)";
        jdbcTemplate.execute(sql);
    }

    @Transactional
    public void resetCountryIdSequence() {
        String sql = "SELECT setval('country_country_id_seq', COALESCE((SELECT MAX(country_id) + 1 FROM country), 1), false)";
        jdbcTemplate.execute(sql);
    }
}
