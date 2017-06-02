import alarmClock.model.ConfigReader;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by joni on 01/06/17.
 */
public class ConfigReaderTest {
    /**
     * Since we did not need getters in the ConfigReader, we did not implement them, since Code that was not written
     * can't contain bugs.
     * But we have to test if the hardcoded config, returns what was hardcoded in them. If you change the configs, you
     * the according test will fail and you need to adjust the tests.
     */

    @Test
    public void getColorSchemeTest() {
        ConfigReader configReader = new ConfigReader();
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
