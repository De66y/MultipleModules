package nl.marktplaats.gedeeld.helper;

import java.util.Random;

public class Wachtwoordgenerator {

    public String maakEenRandomWachtwoord() {
        int vanafA = 97;
        int totEnMetZ = 122;
        int wachtwoordLengte = 8;
        Random random = new Random();

        return random.ints(vanafA, totEnMetZ + 1)
                .limit(wachtwoordLengte)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
