package sample;

import org.junit.Test;
import sample.ChannelsPackage.ChannelData;
import sample.ChannelsPackage.ChannelsDatabaseErrorChecker;
import sample.ChannelsPackage.ChannelsDatabaseHandler;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {
    @Test
    public void numOfRowsEntriesReturnedIsTheOneGiven() {
        int numberOfItems = 18;
        List<ChannelData> listOfChannels = ChannelsDatabaseHandler.getInstance().
                getChannels(1, numberOfItems, "");
        assertEquals(numberOfItems, listOfChannels.size());
    }

    @Test
    public void createChannelWithImproperType() {
        ChannelData channel = new ChannelData();
        channel.setId(1000001);
        channel.setName("nume_canal");
        channel.setChannel(998);
        channel.setType("TIP_ALEATORIU");
        channel.setFrequency(7777.6);
        channel.setStartDate("22.05.2012");
        channel.setEndDate("23.06.2013");
        ChannelsDatabaseHandler.getInstance().addChannel(channel);
        assertEquals(true, ChannelsDatabaseErrorChecker.getInstance().getErrorFound());
    }

    @Test
    public void createChannelWithNullName() {
        ChannelData channel = new ChannelData();
        channel.setId(1000001);
        channel.setChannel(998);
        channel.setType("A");
        channel.setFrequency(7777.6);
        channel.setStartDate("22.05.2012");
        channel.setEndDate("23.06.2013");
        ChannelsDatabaseHandler.getInstance().addChannel(channel);
        assertEquals(true, ChannelsDatabaseErrorChecker.getInstance().getErrorFound());
    }

    @Test
    public void createChannelWithNullStartDate() {
        ChannelData channel = new ChannelData();
        channel.setName("nume_canal");
        channel.setId(1000001);
        channel.setChannel(998);
        channel.setType("A");
        channel.setFrequency(7777.6);
        channel.setEndDate("23.06.2013");
        ChannelsDatabaseHandler.getInstance().addChannel(channel);
        assertEquals(true, ChannelsDatabaseErrorChecker.getInstance().getErrorFound());
    }

    @Test
    public void channelNameIsNotLongEnough() {
        ChannelData channel = new ChannelData();
        channel.setName("a");
        channel.setId(1000001);
        channel.setChannel(998);
        channel.setType("A");
        channel.setFrequency(7777.6);
        channel.setStartDate("23.06.2013");
        channel.setEndDate("23.06.2013");
        ChannelsDatabaseHandler.getInstance().addChannel(channel);
        assertEquals(true, ChannelsDatabaseErrorChecker.getInstance().
                getErrorMessage().contains("Campul denumire trebuie sa aiba intre 2 si 25 de caractere."));
    }
}
