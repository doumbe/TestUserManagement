package fr.doumbe.usermanagement.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_user")
    @SequenceGenerator(name = "s_user")
    private Long id;

    @Size(min = 2)
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Size(min = 2)
    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "phoneNumber")
    private Long phoneNumber;

    @Size(min = 1)
    @Column(name = "genre")
    private String genre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPhoneNumber(String s, long setPhoneNumber) {
    }
}
