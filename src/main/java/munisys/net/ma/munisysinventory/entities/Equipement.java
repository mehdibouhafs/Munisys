package munisys.net.ma.munisysinventory.entities;

/**
 * Created by mehdibouhafs on 17/07/2017.
 */

public class Equipement {

    private int id;
    private String equipement;


    public Equipement() {
    }

    public Equipement(int id, String equipement) {
        this.id = id;
        this.equipement = equipement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipement() {
        return equipement;
    }

    public void setEquipement(String equipement) {
        this.equipement = equipement;
    }

    @Override
    public String toString() {
        return equipement;
    }
}
