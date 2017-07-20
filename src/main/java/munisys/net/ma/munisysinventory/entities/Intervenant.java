package munisys.net.ma.munisysinventory.entities;

import java.io.Serializable;

/**
 * Created by mehdibouhafs on 04/07/2017.
 */

public class Intervenant implements Serializable{

    private int id;
    private String nomIntervenant;

    public Intervenant() {
    }

    public Intervenant(String nomIntervenant) {
    this.nomIntervenant = nomIntervenant;
    }

    public Intervenant(int id, String nomIntervenant) {
        this.id = id;
        this.nomIntervenant = nomIntervenant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomIntervenant() {
        return nomIntervenant;
    }

    public void setNomIntervenant(String nomIntervenant) {
        this.nomIntervenant = nomIntervenant;
    }

    @Override
    public String toString() {
        return "Intervenant{" +
                "id=" + id +
                ", nomIntervenant='" + nomIntervenant + '\'' +
                '}';
    }
}
