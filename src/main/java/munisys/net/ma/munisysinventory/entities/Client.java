package munisys.net.ma.munisysinventory.entities;

import java.io.Serializable;

/**
 * Created by mehdibouhafs on 04/07/2017.
 */

public class Client implements Serializable {

    private int id;
    private String client;

    public Client() {
    }

    public Client(int id, String client) {
        this.id = id;
        this.client = client;
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

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", client='" + client + '\'' +
                '}';
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
