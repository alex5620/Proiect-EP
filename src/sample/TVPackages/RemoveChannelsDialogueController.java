package sample.TVPackages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import sample.ChannelsPackage.ChannelData;

import java.util.List;

public class RemoveChannelsDialogueController {
    @FXML
    ListView listView;
    private TVPackageData tvPackage;

    void addChannels(TVPackageData tvPackage) {
        List<ChannelData> list = tvPackage.getAvailableChannels();
        ChannelsSorter.insertionSort(list);
        listView.setCellFactory(param -> new TableCell());
        listView.setItems(FXCollections.observableArrayList(list));
        this.tvPackage = tvPackage;
    }

    class TableCell extends ListCell<ChannelData> {
        HBox hbox = new HBox();
        Label label = new Label("");
        Pane pane = new Pane();
        Button button = new Button("Delete");

        TableCell() {
            hbox.getChildren().addAll(label, pane, button);
            button.setOnAction(event ->
            {
                PackagesDatabaseHandler.getInstance().removeChannelFromPackage(tvPackage.getIdProperty().getValue(), getItem().getIdProperty().getValue());
                tvPackage.removeChannel(getItem());
                getListView().getItems().remove(getItem());
            });
            HBox.setHgrow(pane, Priority.ALWAYS);
        }

        @Override
        protected void updateItem(ChannelData item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);
            if (item != null && !empty) {
                String name = item.getNameProperty().getValue();
                String type = item.getTypeProperty().getValue();
                String frequency = Double.toString(item.getFrequencyProperty().getValue());
                String channel = Integer.toString(item.getChannelProperty().getValue());
                label.setText("Denumire: " + name + ",   tip: " + type + ",   frecven??a: " + frequency + ",   canal: " + channel + ".");
                setGraphic(hbox);
            }
        }
    }
}
