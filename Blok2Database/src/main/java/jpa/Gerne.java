package jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Gerne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String omschrijving;

    public Gerne(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public int getId() {
        return id;
    }
    public String getOmschrijving() {
        return omschrijving;
    }

    @Override
    public String toString() {
        return String.format("Id: %s  ||  %s", this.id, this.omschrijving);
    }
}
