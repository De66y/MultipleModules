package nl.marktplaats.gedeeld.helper;

import lombok.extern.log4j.Log4j2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Log4j2
public class DocumentLezer {

    public void lees() {
        File file = new File(this.getClass().getResource("/Reglement.txt").getFile());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }

        } catch (IOException e) {
            log.warn(e.getClass().getSimpleName() + " : " + e.getMessage());
        }
    }
}