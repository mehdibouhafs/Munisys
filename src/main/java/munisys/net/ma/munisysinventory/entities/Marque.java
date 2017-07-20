package munisys.net.ma.munisysinventory.entities;

/**
 * Created by mehdibouhafs on 17/07/2017.
 */

public class Marque {
    private int id;
    private String marque;

    public Marque(int id, String marque) {
        this.id = id;
        this.marque = marque;
    }

    public Marque(String marque) {
        this.marque = marque;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Override
    public String toString() {
        return marque;
    }
}
