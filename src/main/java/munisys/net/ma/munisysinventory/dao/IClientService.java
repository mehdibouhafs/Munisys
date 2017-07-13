package munisys.net.ma.munisysinventory.dao;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Intervenant;

/**
 * Created by mehdibouhafs on 08/07/2017.
 */

public interface IClientService {
    public boolean insererClient(String client);
    public void deleteClient(int id);
    public void majClient(int id, String client);
    public Client getClient(int id);
    public ArrayList<Client> getAllClients();
    public int insererClient2(String client);
    public int getClient(String client);
    public Boolean getClientBoolean(String client);
    public void dropTableClient();


}
