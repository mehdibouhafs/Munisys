package munisys.net.ma.munisysinventory.dao;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.Produit;

/**
 * Created by mehdibouhafs on 08/07/2017.
 */

public interface IIntervenantService {
    public void insererIntervenant(String nomIntervenant);
    public void deleteIntervenant(int id);
    public void majIntervenant(int id, String nomIntervenant);
    public Intervenant getIntervenant(int id);
    public ArrayList<Intervenant> getAllIntervenants();
}
