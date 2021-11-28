package sample.ClientsPackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.Comparator;

class Clients {
    private static Clients clients = new Clients();
    private ObservableList<ClientData> allClients;

    private Clients() {}

    static Clients getClients() {
        return clients;
    }

    void addDataWhenIdSelected(int page, int itemsPerPage, String id) {
        allClients = FXCollections.observableArrayList(ClientsDatabaseHandler.getInstance().
                getClientsWhenIdSelected(page, itemsPerPage, id));
    }

    void addDataWhenPhoneSelected(int page, int itemsPerPage, String phone) {
        allClients = FXCollections.observableArrayList(ClientsDatabaseHandler.getInstance().
                getClientsWhenPhoneSelected(page, itemsPerPage, phone));
    }

    void addDataWhenNameSelected(int page, int itemsPerPage, String name) {
        allClients = FXCollections.observableArrayList(ClientsDatabaseHandler.getInstance().
                getClientsWhenNameSelected(page, itemsPerPage, name));
    }

    ObservableList<ClientData> getAllClients() {
        return allClients;
    }

    public int getSize() {
        return allClients.size();
    }

}
