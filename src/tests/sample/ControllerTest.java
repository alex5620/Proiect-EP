package sample;

import org.junit.Test;
import sample.ChannelsPackage.ChannelsController;

import static org.junit.Assert.assertEquals;

public class ControllerTest {
    @Test
    public void numOfRowsIsZero() {
        ChannelsController controller = new ChannelsController();
        assertEquals(0, controller.getProperPageNumber(0));
    }

    @Test
    public void numOfRowsIsLessThanOnePage() {
        ChannelsController controller = new ChannelsController();
        assertEquals(1, controller.getProperPageNumber(5));
    }

    @Test
    public void numOfRowsIsExactlyOnePage() {
        ChannelsController controller = new ChannelsController();
        assertEquals(1, controller.getProperPageNumber(18));
    }

    @Test
    public void numOfRowsIsOnePagePlusOneElement() {
        ChannelsController controller = new ChannelsController();
        assertEquals(2, controller.getProperPageNumber(19));
    }
}
