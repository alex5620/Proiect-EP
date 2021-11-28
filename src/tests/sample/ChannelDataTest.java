package sample;

import org.junit.Test;
import sample.ChannelsPackage.ChannelData;

import static org.junit.Assert.assertEquals;

public class ChannelDataTest {
    @Test
    public void differentChannelsAreComparedAccordingly()
    {
        ChannelData channel1 = new ChannelData();
        ChannelData channel2 = new ChannelData();
        channel1.setName("channel1");
        channel2.setName("channel2");
        assertEquals(-1, channel1.compareTo(channel2));
    }

    @Test
    public void equalChannelsAreComparedAccordingly()
    {
        ChannelData channel1 = new ChannelData();
        ChannelData channel2 = new ChannelData();
        channel1.setName("channel1");
        channel2.setName("channel1");
        assertEquals(0, channel1.compareTo(channel2));
    }

    @Test(expected = NullPointerException.class)
    public void nullChannelIsCompared()
    {
        ChannelData channel1 = new ChannelData();
        channel1.setName("channel1");
        assertEquals(0, channel1.compareTo(null));
    }
}