package munisys.net.ma.munisysinventory.entities;

import java.io.Serializable;

/**
 * Created by mehdibouhafs on 13/07/2017.
 */

public class IntervenantInventaire implements Serializable {

    private int intervenantId;
    private int inventaireId;

    public IntervenantInventaire(int inventaireId, int intervenantId) {
        this.intervenantId = intervenantId;
        this.inventaireId = inventaireId;
    }

    public int getIntervenantId() {
        return intervenantId;
    }

    public void setIntervenantId(int intervenantId) {
        this.intervenantId = intervenantId;
    }

    public int getInventaireId() {
        return inventaireId;
    }

    public void setInventaireId(int inventaireId) {
        this.inventaireId = inventaireId;
    }
}
