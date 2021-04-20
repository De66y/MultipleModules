package service.service;

public interface IControleerGebruikergegevens {
    public boolean emailadresBestaat(String username);
    public boolean usernameAndPasswordExist (String username, String password);


}
