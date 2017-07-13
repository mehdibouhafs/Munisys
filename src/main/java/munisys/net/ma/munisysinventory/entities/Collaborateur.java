package munisys.net.ma.munisysinventory.entities;

import java.io.Serializable;

/**
 * Created by mehdibouhafs on 04/07/2017.
 */

public class Collaborateur implements Serializable {

    private int id;
    private String nomCollaborateur;


    public Collaborateur(int id, String nomCollaborateur) {
        this.id = id;
        this.nomCollaborateur = nomCollaborateur;
    }

    public Collaborateur() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCollaborateur() {
        return nomCollaborateur;
    }

    public void setNomCollaborateur(String nomCollaborateur) {
        this.nomCollaborateur = nomCollaborateur;
    }

    @Override
    public String toString() {
        return "Collaborateur{" +
                "id=" + id +
                ", nomCollaborateur='" + nomCollaborateur + '\'' +
                '}';
    }
}
