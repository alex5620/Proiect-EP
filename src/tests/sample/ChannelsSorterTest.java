package sample;

import org.junit.Test;
import sample.ChannelsPackage.ChannelData;
import sample.TVPackages.ChannelsSorter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChannelsSorterTest {
    private List<ChannelData> listOfChannels;

    private void addTestingData1() {
        listOfChannels = new ArrayList<>();
        ChannelData channel1 = new ChannelData();
        ChannelData channel2 = new ChannelData();
        ChannelData channel3 = new ChannelData();
        ChannelData channel4 = new ChannelData();
        channel1.setName("c");
        channel2.setName("z");
        channel3.setName("a");
        channel4.setName("B");
        listOfChannels.add(channel1);
        listOfChannels.add(channel2);
        listOfChannels.add(channel3);
        listOfChannels.add(channel4);
        ChannelsSorter.insertionSort(listOfChannels);
    }

    @Test
    public void firstElementIsCorrect() {
        addTestingData1();
        assertEquals("a", listOfChannels.get(0).getNameProperty().getValue());
    }

    @Test
    public void lastElementIsCorrect() {
        addTestingData1();
        assertEquals("z", listOfChannels.get(listOfChannels.size() - 1).getNameProperty().getValue());
    }

    @Test
    public void allElementsAreCorrect() {
        addTestingData1();
        assertEquals("a", listOfChannels.get(0).getNameProperty().getValue());
        assertEquals("B", listOfChannels.get(1).getNameProperty().getValue());
        assertEquals("c", listOfChannels.get(2).getNameProperty().getValue());
        assertEquals("z", listOfChannels.get(3).getNameProperty().getValue());
    }


    @Test
    public void numberOfElementsIsCorrect() {
        addTestingData1();
        assertEquals(4, listOfChannels.size());
    }

    @Test
    public void listIsNull() {
        ChannelsSorter.insertionSort(listOfChannels);
    }

    @Test
    public void listIsEmpty() {
        listOfChannels = new ArrayList<>();
        ChannelsSorter.insertionSort(listOfChannels);
    }

    private void addTestingData2() {
        listOfChannels = new ArrayList<>();
        ChannelData channel1 = new ChannelData();
        ChannelData channel2 = new ChannelData();
        ChannelData channel3 = new ChannelData();
        channel1.setName("4Life");
        channel2.setName("Digi Sport 1");
        channel3.setName("Disney Channel");
        listOfChannels.add(channel1);
        listOfChannels.add(channel2);
        listOfChannels.add(channel3);
        ChannelsSorter.insertionSort(listOfChannels);
    }

    @Test
    public void channelNameStartsWithNumber() {
        addTestingData2();
        assertEquals("4Life", listOfChannels.get(0).getNameProperty().getValue());
    }

    private void addTestingData3() {
        listOfChannels = new ArrayList<>();
        ChannelData channel1 = new ChannelData();
        ChannelData channel2 = new ChannelData();
        ChannelData channel3 = new ChannelData();
        channel1.setName("Digi Sport 1");
        channel2.setName("Digi Sport 1");
        channel3.setName("Disney Channel");
        listOfChannels.add(channel1);
        listOfChannels.add(channel2);
        listOfChannels.add(channel3);
        ChannelsSorter.insertionSort(listOfChannels);
    }

    @Test
    public void duplicateDataIsReturnedAccordingly() {
        addTestingData3();
        assertEquals("Digi Sport 1", listOfChannels.get(0).getNameProperty().getValue());
        assertEquals("Digi Sport 1", listOfChannels.get(1).getNameProperty().getValue());
    }
}