package soa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tourists")
public class Tourist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tourist_id")
    private Long touristId;

    @Column(name = "tourist_name")
    private String touristName;

    @Column(name = "email")
    private String email;

    @Column(name = "destination")
    private String destination;

    @Column(name = "country")
    private String country;

    public Tourist() {
    }

    public Tourist(String touristName, String email, String destination, String country) {
        this.touristName = touristName;
        this.email = email;
        this.destination = destination;
        this.country = country;
    }

    public Long getTouristId() { return touristId; }
    public void setTouristId(Long touristId) { this.touristId = touristId; }

    public String getTouristName() { return touristName; }
    public void setTouristName(String touristName) { this.touristName = touristName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
