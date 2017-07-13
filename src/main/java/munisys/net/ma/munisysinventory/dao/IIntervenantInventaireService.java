package munisys.net.ma.munisysinventory.dao;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.IntervenantInventaire;

/**
 * Created by mehdibouhafs on 08/07/2017.
 */

public interface IIntervenantInventaireService {

    public void insererIntervenantInventaire(int idInventaire,int idIntervenant);
    public void deleteIntervenantInventaire(int idIventaire);

    public ArrayList<IntervenantInventaire> getAllIntervenantInventaire(int idInventaire);

    public ArrayList<Intervenant> getIntervenantsInventaire(int idInventaire);
}
