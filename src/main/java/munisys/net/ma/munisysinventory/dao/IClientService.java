package munisys.net.ma.munisysinventory.dao;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Intervenant;

/**
 * Created by mehdibouhafs on 08/07/2017.
 */

public interface IClientService {
    public void insererClient(String client,String site,String direction,String ville,String burreauEtage,
                              String ServiceCentre,String telephone,String contact);
    public void deleteClient(int id);
    public void majClient(int id, String client,String site,String direction,String ville,String burreauEtage,
                          String ServiceCentre,String telephone,String contact);
    public Client getClient(int id);
    public ArrayList<Client> getAllClients();
    public int insererClient2(String client,String site,String ville,String telephone,String contact);
    public int getClient(String client);


}
