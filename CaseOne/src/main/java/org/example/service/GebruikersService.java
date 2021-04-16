package org.example.service;

import org.example.data.dao.KlantDao;

public class GebruikersService {
    private KlantDao klantDao;

    public GebruikersService(KlantDao klantDao) {
        this.klantDao = klantDao;
    }

    //@TODO
    public boolean usernameExist(String username) {
        return klantDao.getKlanten().stream()
                .anyMatch(klant -> klant.getUsername().equals(username));

    }

    //@TODO
    public boolean usernameAndPasswordExist (String username, String password) {
        return klantDao.getKlanten().stream()
                .anyMatch(klant -> klant.getUsername().equals(username)
                && klant.getPassword().equals(password));
    }
}
