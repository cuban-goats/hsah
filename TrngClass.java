import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import crypt.HsahingClass;

public class TrngClass {
  public static void main(String[] args) {
    System.out.println("Soon a trng");
    extract_data("converted_image.png");
  }

  public static int[] extract_data(String image) {
    try {
      BufferedImage buffered_image = ImageIO.read(new File(image));
      int width = buffered_image.getWidth();
      int height = buffered_image.getHeight();
      int[] pixels = new int[width * height];
      pixels = buffered_image.getRGB(0, 0, width, height, pixels, 0, width);
      for (int i = 0; i < pixels.length; i++) {
        System.out.print(pixels[i]);
      }
      return pixels;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
  public static void convert_int_array(int[] array) {
    
  }
}
