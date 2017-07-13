package munisys.net.ma.munisysinventory.entities;

import java.io.Serializable;

/**
 * Created by mehdibouhafs on 04/07/2017.
 */

public class Client implements Serializable {

    private int id;
    private String client;
    private String site;
    private String direction;
    private String ville;
    private String burreauEtage;
    private String serviceCentre;
    private String telephone;
    private String contact;


    public Client() {
    }

    public Client(int id, String client, String site, String direction,
                  String ville, String burreauEtage, String serviceCentre,String contact,String telephone) {
        this.id = id;
        this.client = client;
        this.site = site;
        this.direction = direction;
        this.ville = ville;
        this.burreauEtage = burreauEtage;
        this.serviceCentre = serviceCentre;
        this.contact = contact;
        this.telephone = telephone;

    }

    public Client(String client, String site, String ville) {
        this.client = client;
        this.site = site;
        this.ville = ville;
    }

    public Client(String client, String site, String direction, String ville, String burreauEtage, String serviceCentre,String contact, String telephone) {
        this.client = client;
        this.site = site;
        this.direction = direction;
        this.ville = ville;
        this.burreauEtage = burreauEtage;
        this.serviceCentre = serviceCentre;
        this.telephone = telephone;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getBurreauEtage() {
        return burreauEtage;
    }

    public void setBurreauEtage(String burreauEtage) {
        this.burreauEtage = burreauEtage;
    }

    public String getServiceCentre() {
        return serviceCentre;
    }

    public void setServiceCentre(String serviceCentre) {
        this.serviceCentre = serviceCentre;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return client;
    }

    /*
    protected Client(Parcel in) {
        id = in.readInt();
        client = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(client);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };*/
}
