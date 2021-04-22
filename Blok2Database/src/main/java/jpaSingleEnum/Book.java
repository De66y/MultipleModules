package jpaSingleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String Titel;

    @Enumerated //(EnumType.ORDINAL)
    private Classification classification;


    public Book(String Titel) {
        this.Titel = Titel;

    }

    public int getId() {
        return id;
    }
    public String getTitel() {
        return Titel;
    }
    public void setTitel(String titel) {
        this.Titel = titel;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    @Override
    public String toString() {
        return String.format("Id: %s  ||  Title: %s  ||  \n", this.id, this.Titel);
    }
}
