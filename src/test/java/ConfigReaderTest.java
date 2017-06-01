import alarmClock.model.ConfigReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by joni on 01/06/17.
 */
public class ConfigReaderTest {

    @Test
    public void getColorSchemeTest() {
        ConfigReader configReader = new ConfigReader();
        assertEquals("nightmode", configReader.getColorScheme());
        assertTrue(configReader.isEnableDarkMode());

    }

    @Test
    public void isEnablePastRemindersTest() {
        ConfigReader configReader = new ConfigReader();
        assertTrue(configReader.isEnablePastReminders());
    }

    @Test
    public void isEnableImminentRemindersTest() {
        ConfigReader configReader = new ConfigReader();
        assertTrue(configReader.isEnableImminentReminders());
    }

    @Test
    public void isEnableRemindersThisMonthTest() {
        ConfigReader configReader = new ConfigReader();
        assertTrue(configReader.isEnableRemindersThisMonth());
    }


}
