package fer.project.author;

import fer.project.Country.Country;
import fer.project.university.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a ORDER BY a.author_id")
    List<Author> findAllAuthors();

    @Query("SELECT a FROM Author a WHERE a.author_id = ?1 ORDER BY a.author_id")
    List<Author> findAuthorsByAuthorID(Long id);

    @Query("SELECT DISTINCT a FROM Author a JOIN a.books b WHERE LOWER(a.first_name) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(a.last_name) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(a.university.university_name) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(a.university.country.country_name) LIKE LOWER(CONCAT('%', ?1, '%')) ORDER BY a.author_id")
    List<Author> findAuthorsByWildcardString(String filter);

    @Query("SELECT a FROM Author a WHERE LOWER(a.first_name) LIKE LOWER(CONCAT('%', ?1, '%')) ORDER BY a.author_id")
    List<Author> findAuthorsByFirstName(String name);

    @Query("SELECT a FROM Author a WHERE LOWER(a.last_name) LIKE LOWER(CONCAT('%', ?1, '%')) ORDER BY a.author_id")
    List<Author> findAuthorsByLastName(String name);

    @Query("SELECT a FROM Author a WHERE EXTRACT(YEAR FROM a.birth_date) = ?1 ORDER BY a.author_id")
    List<Author> findAuthorsByBirthYear(Integer year);

    @Query("SELECT a FROM Author a WHERE EXTRACT(YEAR FROM a.death_date) = ?1 ORDER BY a.author_id")
    List<Author> findAuthorsByDeathYear(Integer year);

    @Query("SELECT a FROM Author a WHERE a.children_count = ?1 ORDER BY a.author_id")
    List<Author> findAuthorsByChildren(Integer children);

    @Query("SELECT a FROM Author a WHERE LOWER(a.university.university_name) LIKE LOWER(CONCAT('%', ?1, '%')) ORDER BY a.author_id")
    List<Author> findAuthorsByUniversity(String university);

    @Query("SELECT a FROM Author a WHERE LOWER(a.university.country.country_name) LIKE LOWER(CONCAT('%', ?1, '%')) ORDER BY a.author_id")
    List<Author> findAuthorsByLocation(String country);

    @Query("SELECT DISTINCT a FROM Author a JOIN a.books b WHERE b.book_id = ?1 ORDER BY a.author_id")
    List<Author> findAuthorsByBookID(Long id);

    @Query("SELECT DISTINCT a FROM Author a JOIN a.books b WHERE LOWER(b.book_name) LIKE LOWER(CONCAT('%', ?1, '%')) ORDER BY a.author_id")
    List<Author> findAuthorsByBookName(String name);

    @Query("SELECT DISTINCT a FROM Author a JOIN a.books b WHERE b.release_year = ?1 ORDER BY a.author_id")
    List<Author> findAuthorsByReleaseYear(Integer year);

    @Query("SELECT c FROM Country c WHERE c.country_name LIKE ?1")
    List<Country> findCountryByName(String name);

    @Query("SELECT u FROM University u WHERE u.university_name LIKE ?1")
    List<University> findUniversityByName(String name);
}
