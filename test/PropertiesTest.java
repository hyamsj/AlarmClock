import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by joni on 14/03/17.
 */
public class PropertiesTest {

    @Test
    public void PropertiesAddValue(){
        SimpleIntegerProperty num = new SimpleIntegerProperty(0);
        assertEquals(0, num.get());
//        System.out.println(num.add(1).getValue());
        NumberBinding sum = num.add(1);
//        num.add(1).getValue();
        assertEquals(1, sum.getValue());
    }
}
