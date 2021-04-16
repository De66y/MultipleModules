package jdbc;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {
    private int id;
    private String title;
    private String author;

    @Override
    public String toString() {
        return String.format("Id: %s  ||  Title: %s  ||  Author: %s", this.id, this.title, this.author);
    }

}
