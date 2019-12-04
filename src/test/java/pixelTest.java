import com.kovaliv.imageHandlers.Pixel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class pixelTest {

    @Test
    @DisplayName("My Test")
    public void testSumBlack() {
        Pixel pixel = new Pixel(Color.BLACK.getRGB());
        assertEquals(pixel.getSum(), 0);
    }


}
