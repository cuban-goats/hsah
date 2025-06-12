import crypt.HsahingClass;
import java.security.SecureRandom;

public class PrngClass {
  public static void main(String[] args) {
    String seed = seed_generator();
    String[] random_numbers = linear_congruential_generator(seed);
    String[] random_hash = random_hash_generator(random_numbers);
  }

  public static String byte_to_seed_string(byte[] byte_array) {
    String[] string_array = new String[byte_array.length];
    String string = "";
    StringBuilder string_builder = new StringBuilder().append(string);
    for (int i = 0; i < byte_array.length; i++) {
      string_array[i] = Byte.toString(byte_array[i]);
      string_builder = string_builder.append(string_array[i]);
      string = string_builder.toString();
    }
    string = string.replace("[", "").replace("]", "").replace(",", "").replace(" ", "").replace("-", "");
    return string;
  }

  public static String seed_generator() {
    // generate a random random Seed
    byte[] byte_seed = SecureRandom.getSeed(64);
    String seed = byte_to_seed_string(byte_seed);
    String increased_seed = HsahingClass.integer_size_increase(seed);
    return increased_seed;
  }

  public static int int_array_flattening(int[] int_array) {
    int flattened_int = 0;
    for (int i = 0; i < int_array.length; i++) {
      flattened_int = flattened_int + int_array[i];
    }
    return flattened_int;
  }

  public static String[] linear_congruential_generator(String seed) {
    int[] seed_int_array = HsahingClass.string_to_int_array_conversion(seed);
    int seed_int = int_array_flattening(seed_int_array);

    int a = 29384;
    int c = 88382;
    int m = 12323;

    int[] pseudo_random_numbers = new int[1000];
    pseudo_random_numbers[0] = (a * seed_int + c) % m;
    for (int i = 0; i < pseudo_random_numbers.length - 1; i++) {
      if (i > 0) {
        pseudo_random_numbers[i + 1] = (a * pseudo_random_numbers[i] + c) % m;
      }
      if (pseudo_random_numbers[i] == 0) {
        pseudo_random_numbers[i] = pseudo_random_numbers[i] + 1;
      }
    }
    String[] pseudo_random_number_strings = new String[pseudo_random_numbers.length];
    for (int j = 0; j < pseudo_random_number_strings.length; j++) {
      pseudo_random_number_strings[j] = Integer.toString(pseudo_random_numbers[j]);
    }
    return pseudo_random_number_strings;
  }

  public static String[] random_hash_generator(String[] random) {
    String[] hashed_value = random;
    for (int i = 0; i < hashed_value.length; i++) {
      System.out.println(hashed_value[i]);
      hashed_value[i] = HsahingClass.hsah("prng_hashes.txt", hashed_value[i]);
    }
    return hashed_value;
  }
}
