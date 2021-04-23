package nl.marktplaats.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity @Data @Builder @NoArgsConstructor @AllArgsConstructor
public class BezorgwijzeEntity<T extends Bezorglike> {
    @Id
    private int id;
}
