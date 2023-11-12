package fer.project.university;

import fer.project.Country.Country;
import jakarta.persistence.*;

@Entity
@Table(name = "university")
public class University {

    @Id
    private long university_id;
    private String university_name;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    private Country country;

    public University() {

    }

    public University(long university_id, String university_name, Country country) {
        this.university_id = university_id;
        this.university_name = university_name;
        this.country = country;
    }

    public long getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(long university_id) {
        this.university_id = university_id;
    }

    public String getUniversity_name() {
        return university_name;
    }

    public void setUniversity_name(String university_name) {
        this.university_name = university_name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "University{" +
                "university_id=" + university_id +
                ", university_name='" + university_name + '\'' +
                ", country=" + country +
                '}';
    }
}
