package munisys.net.ma.munisysinventory.dao;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.entities.Collaborateur;
import munisys.net.ma.munisysinventory.entities.Intervenant;

/**
 * Created by mehdibouhafs on 08/07/2017.
 */

public interface ICollaborateurService {
    public void insererCollaborateur(String nomCollaborateur);
    public void deleteCollaborateur(int id);
    public void majCollaborateur(int id, String nomCollaborateur);
    public Collaborateur getCollaborateur(int id);
    public ArrayList<Collaborateur> getAllCollaborateurs();
}
