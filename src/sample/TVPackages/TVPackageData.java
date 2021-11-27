package sample.TVPackages;

import javafx.beans.property.*;
import sample.ChannelsPackage.ChannelData;

import java.util.ArrayList;

public class TVPackageData {
    private IntegerProperty ID;
    private StringProperty name;
    private StringProperty startDate;
    private StringProperty endDate;
    private DoubleProperty price;
    private ArrayList<ChannelData> availableChannels;

    TVPackageData() {
        ID = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        startDate = new SimpleStringProperty();
        endDate = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        availableChannels = new ArrayList<>();
    }

    void updateInfo(TVPackageData newPackage) {
        setName(newPackage.getNameProperty().getValue());
        setStartDate(newPackage.getStartDateProperty().getValue());
        setEndDate(newPackage.getEndDateProperty().getValue());
        setPrice(newPackage.getPriceProperty().getValue());
    }

    public IntegerProperty getIdProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    StringProperty getStartDateProperty() {
        return startDate;
    }

    void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }

    StringProperty getEndDateProperty() {
        return endDate;
    }

    void setEndDate(String endDate) {
        this.endDate.set(endDate);
    }

    DoubleProperty getPriceProperty() {
        return price;
    }

    void setPrice(double price) {
        this.price.set(price);
    }

    void addChannel(ChannelData channel) {
        availableChannels.add(channel);
    }

    void removeChannel(ChannelData channel) {
        availableChannels.remove(channel);
    }

    ArrayList<ChannelData> getAvailableChannels() {
        return availableChannels;
    }
}
