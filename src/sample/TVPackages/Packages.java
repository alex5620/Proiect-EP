package sample.TVPackages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Packages {
    private static Packages packages = new Packages();
    private ObservableList<TVPackageData> allPackages;

    private Packages() {}

    void addData(int page, int itemsPerPage, String name) {
        allPackages = FXCollections.observableArrayList(PackagesDatabaseHandler.getInstance().getPackages(page, itemsPerPage, name));
    }

    static Packages getPackages() {
        return packages;
    }

    public int getSize() {
        return allPackages.size();
    }

    ObservableList<TVPackageData> getAllPackages() {
        return allPackages;
    }
}
