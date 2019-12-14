import com.kovaliv.imageHandlers.Filters.WhiteAndBlackFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlackAndWhiteTest {

    @Test
    @DisplayName("BlackAndWhite")
    public void filterTest() {
        Dimension dimension = new Dimension(2, 2);
        BufferedImage image = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
        image.setRGB(0, 0, Color.RED.getRGB());
        image.setRGB(1, 0, Color.WHITE.getRGB());
        image.setRGB(0, 1, Color.BLACK.getRGB());
        image.setRGB(1, 1, Color.WHITE.getRGB());
        WhiteAndBlackFilter whiteAndBlackFilter = new WhiteAndBlackFilter();
        image = whiteAndBlackFilter.filter(image);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (image.getRGB(i, j) != Color.BLACK.getRGB() && image.getRGB(i, j) != Color.BLACK.getRGB()) {
                    assertEquals(1, 0);
                }
            }
        }
        assertEquals(1, 1);
    }
}
