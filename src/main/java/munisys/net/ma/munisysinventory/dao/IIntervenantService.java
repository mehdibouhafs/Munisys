package munisys.net.ma.munisysinventory.dao;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;

import java.util.ArrayList;
import java.util.List;

import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.Produit;

/**
 * Created by mehdibouhafs on 08/07/2017.
 */

public interface IIntervenantService {
    public boolean insererIntervenant(String nomIntervenant);
    public void deleteIntervenant(int id);
    public void majIntervenant(int id, String nomIntervenant);
    public Intervenant getIntervenant(int id);
    public ArrayList<Intervenant> getAllIntervenants();
    //public ArrayList<Intervenant> getIntervenantsInventaire(int idInventaire);
    public Boolean getIntervenantBoolean(String nomIntervenant);
    public List<KeyPairBoolData> getIntervenants(String intervenant);

    public Intervenant getIntervenantByName(String nomIntervenant);
    public void dropTableIntervenant();
}
