package sample.ChannelsPackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Channels {
    private static Channels channels = new Channels();
    private ObservableList<ChannelData> allChannels;

    private Channels() {}

    public static Channels getChannels() {
        return channels;
    }

    public void addData(int page, int itemsPerPage, String name) {
        allChannels = FXCollections.observableArrayList(ChannelsDatabaseHandler.getInstance().getChannels(page, itemsPerPage, name));
    }

    ObservableList<ChannelData> getAllChannels() {
        return allChannels;
    }

    public int getSize() {
        return allChannels.size();
    }
}
