package fer.project.education;

import fer.project.author.Author;
import fer.project.university.University;
import jakarta.persistence.*;

@Entity
@Table(name = "education")
public class Education {

    @EmbeddedId
    private EducationId education_id;

    @OneToOne
    @MapsId("authorId")
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @MapsId("universityId")
    @JoinColumn(name = "university_id")
    private University university;

    public Education() {

    }

    public Education(EducationId education_id, Author author, University university) {
        this.education_id = education_id;
        this.author = author;
        this.university = university;
    }

    public EducationId getEducation_id() {
        return education_id;
    }

    public void setEducation_id(EducationId education_id) {
        this.education_id = education_id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "Education{" +
                "education_id=" + education_id +
                ", author=" + author +
                ", university=" + university +
                '}';
    }
}
