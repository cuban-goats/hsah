import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import crypt.HsahingClass;

public class TrngClass {
  public static void main(String[] args) {
    // System.out.println("random\n\n");
    // System.out.println(random_hash);
  }

  public static int[] extract_data(String image) {
    try {
      BufferedImage buffered_image = ImageIO.read(new File(image));
      int width = buffered_image.getWidth();
      int height = buffered_image.getHeight();
      int[] pixels = new int[width * height];
      pixels = buffered_image.getRGB(0, 0, width, height, pixels, 0, width);
      for (int i = 0; i < pixels.length; i++) {
        // System.out.print(pixels[i]);
      }
      return pixels;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String[] int_to_string_array(int[] array) {
    String[] random_number_strings = new String[array.length];
    for (int i = 0; i < random_number_strings.length; i++) {
      random_number_strings[i] = Integer.toString(array[i]);
    }
    return random_number_strings;
  }

  public static String random_hash_generator(int[] random) {
    String[] hashed_value = int_to_string_array(random);
    StringBuilder string_builder = new StringBuilder().append("random");
    for (int j = 0; j < hashed_value.length; j++) {
      string_builder = string_builder.append(hashed_value[j]);
    }
    String input_value = string_builder.toString();
    input_value = input_value.replace("random[", "").replace("random]", "").replace("random,", "").replace("random ", "").replace("random-", "");
    String output_hash = HsahingClass.hsah("trng_hashes.txt", input_value);
    return output_hash;
  }
}
