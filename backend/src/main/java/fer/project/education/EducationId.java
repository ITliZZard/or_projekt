package fer.project.education;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EducationId implements Serializable {

    private long authorId;
    private long universityId;

    public EducationId() {

    }

    public EducationId(long authorId, long universityId) {
        this.authorId = authorId;
        this.universityId = universityId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(long universityId) {
        this.universityId = universityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EducationId that = (EducationId) o;
        return authorId == that.authorId && universityId == that.universityId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, universityId);
    }
}
