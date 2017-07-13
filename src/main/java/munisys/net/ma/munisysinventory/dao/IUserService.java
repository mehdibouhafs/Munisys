package munisys.net.ma.munisysinventory.dao;

import java.util.List;

import munisys.net.ma.munisysinventory.entities.User;

/**
 * Created by mehdibouhafs on 12/07/2017.
 */

public interface IUserService {

    public boolean insererUser(String nom, String email,String password);
    public void deleteUser(int id);
    public void majUser(int id, String nom, String email, String password);
    public User getUser(String email,String password);
    public Boolean getUserBoolean(String email,String password);
    public Boolean getUserBoolean(String email);
    public Boolean getUserBooleanEmail(String email);
    public List<User>  getALLUser();
}
