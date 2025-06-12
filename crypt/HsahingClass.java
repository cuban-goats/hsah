package crypt;

import java.awt.PrintJob;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.net.ContentHandler;
import java.util.Arrays;
import java.util.function.ToIntFunction;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HsahingClass {
  public static void main(String[] args) {
    // String hash = hsah("ais8dfahdswfi7asfghasdjfz7iasdhfjashfkdusafzhufasjdhf");
  } // end of main

  public static String binary_conversion(String input) {
    StringBuilder result = new StringBuilder();
    char[] chars = input.toCharArray();
    for (char aCHar : chars) {
      result.append(String.format("%8s", Integer.toBinaryString(aCHar)).replaceAll(" ", "0"));
    }
    return result.toString();
  }

  public static String adjust_size(String binary_string) {
    StringBuilder adjusted_binary_string_builder = new StringBuilder().append(binary_string);
    String adjusted_binary_string = adjusted_binary_string_builder.toString();
    if (binary_string.length() < 128) {
      while (adjusted_binary_string_builder.length() < 128) {
        adjusted_binary_string_builder.append("0");
      }
      adjusted_binary_string = adjusted_binary_string_builder.toString();
    } else if (binary_string.length() > 128) {
      while (adjusted_binary_string.length() > 128) {
        adjusted_binary_string = adjusted_binary_string.substring(0, adjusted_binary_string.length() - 1);
      }
    }
    return adjusted_binary_string;
  }

  public static String string_adjustment(String input) {
    StringBuilder input_string_builder = new StringBuilder().append(input);
    while ((input_string_builder.length() % 128) != 0) {
      input_string_builder.append("0");
    }
    String output = input_string_builder.toString();
    return output;
  }

  public static char[][] prepare_size(String input, Boolean print) {
    // adjust size
    StringBuilder input_string_builder = new StringBuilder().append(input);
    while ((input_string_builder.length() % 128) != 0) {
      input_string_builder.append("0");
    }
    String static_input_string = input_string_builder.toString();
    String input_string = static_input_string;

    // first 128 bits of the input
    String base = static_input_string.substring(0, 127);
    char[][] base_chars = init_values(base, false);

    int static_border_indicator = static_input_string.length();
    while (input_string.length() > 128) {

      for (int i = 0; i <= ((static_input_string.length() / 128)); i++) {
        // System.out.println("\nlength of the string: " + input_string.length());
        static_border_indicator = static_input_string.length() - (i * 128);
        int border_indicator = static_input_string.length();
        if (i > 0) {
          border_indicator = static_input_string.length() - (i * 128 - 128);
        } else {
          border_indicator = static_input_string.length() - (i * 128);
        }
        // System.out.println("\ni: " + i + "\nborder inidcator: " +
        // static_border_indicator);

        input_string = static_input_string.substring(0, static_border_indicator);

        String cut_input = static_input_string.substring(static_border_indicator, border_indicator);

        char[][] cut_input_chars = init_values(cut_input, false);
        cut_input_chars = shift(cut_input_chars, 10, false);
        base_chars = shift(base_chars, 10, false);

        base_chars = xor(base_chars, cut_input_chars, 1, false);
      }
    }
    return base_chars;
  }

  public static String[] split(String adjusted) {
    String[] split_values = new String[8];

    for (int i = 0; i < (adjusted.length() / 16); i++) {
      split_values[i] = adjusted.substring(0 + (i * (adjusted.length() / 8)), (adjusted.length() / 8) * (i + 1));
    }
    return split_values;
  }

  public static String[] full_split(String input) {
    int parts = input.length() / 8;
    String[] split_values = new String[parts];

    int array_index = 0;

    for (int i = 0; i < parts; i++) {
      split_values[array_index++] = input.substring(i * 8, (i * 8) + 8);
    }

    // for (int i = 0; i < parts; i++) {
    // System.out.print(split_values[i] + " ");
    // }
    return split_values;
  }

  public static char[][] convert(String[] input) {
    char[][] new_input = new char[input.length][input[0].length()];

    for (int k = 0; k < new_input.length; k++) {
      for (int l = 0; l < new_input[k].length; l++) {
        new_input[k][l] = input[k].charAt(l);
      }
    }
    return new_input;

  }

  public static int[] decimal_conversion(char[][] binary, Boolean print) {
    String[] binary_string = new String[binary.length];
    for (int i = 0; i < binary.length; i++) {
      binary_string[i] = new String(binary[i]);
    }

    int[] decimal = new int[binary_string.length];

    for (int j = 0; j < binary_string.length; j++) {
      decimal[j] = Integer.parseInt(binary_string[j], 2);
    }

    if (print == true) {
      System.out.println("decimal value:");
      for (int k = 0; k < decimal.length; k++) {
        System.out.print(decimal[k] + " ");
      }
      System.out.println("\n");
    }
    return decimal;
  }

  public static String string_conversion(int[] integer_array) {
    String string_value = Arrays.toString(integer_array);
    string_value = string_value.replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
    // System.out.println("String value: \n" + string_value + "\n");
    return string_value;
  }

  public static String char_array_to_string_conversion(char[][] char_array) {
    StringBuilder sb = new StringBuilder();

    for (char[] row : char_array) {
      for (char c : row) {
        sb.append(c);
      }
    }
    String string_value = sb.toString();
    // System.out.println(string_value);
    return string_value;
  }

  public static char[][] init_binary_string_values(String data) {
    String input = binary_conversion(data);
    // System.out.println("\nunprepared input after binary conversion:\n");
    // System.out.println(input);
    char[][] output_chars = prepare_size(input, false);
    // System.out.println("\n output_chars from prepare_size: \n");
    for (int i = 0; i < output_chars.length; i++) {
      // System.out.println(output_chars[i]);
    }
    return output_chars;
  }

  public static char[][] init_values(String data, Boolean print) {
    String output = binary_conversion(data);
    if (print == true) {
      System.out.println("\nunadjusted_string:\n" + output + "\n");
    }
    output = adjust_size(output);

    if (print == true) {
      System.out.println("adjusted_binary_string\n" + output + "\nLength:\n" + output.length() + "\n");
    }
    String[] output_list = split(output);

    if (print == true) {
      for (int k = 0; k < output_list.length; k++) {
        System.out.println(output_list[k]);
      }
      System.out.println("\n");
    }
    char[][] input = convert(output_list);

    if (print == true) {
      System.out.print("\n");
      for (int i = 0; i < input.length; i++) {
        for (int j = 0; j < input[i].length; j++) {
          System.out.print(input[i][j]);
        }
      }
      System.out.print("\n");
    }
    return input;
  }

  public static char[][] xor(char[][] input_one, char[][] input_two, int number, Boolean print) {
    char[][] result = new char[input_one.length][input_one[0].length];
    if (print == true) {
      System.out.print("\nXOR result: \n");
    }

    if (input_one.length == input_two.length) {
      for (int k = 0; k < number; k++) {
        for (int i = 0; i < input_one.length; i++) {
          for (int j = 0; j < input_one[i].length; j++) {
            if (input_one[i][j] == '0' && input_two[i][j] == '0') {
              result[i][j] = '0';
            } else if (input_one[i][j] == '1' && input_two[i][j] == '1') {
              result[i][j] = '0';
            } else if (input_one[i][j] == '0' && input_two[i][j] == '1') {
              result[i][j] = '1';
            } else if (input_one[i][j] == '1' && input_two[i][j] == '0') {
              result[i][j] = '1';
            }
            if (print == true) {
              System.out.print(result[i][j]);
            }
          }
        }
        input_one = result;
      }
    }
    if (print == true) {
      System.out.print("\n");
    }
    return result;
  }

  public static char[][] shift(char[][] input, int number, Boolean print) {
    char[][] output = new char[input.length][input[0].length];
    char save;
    // shiftcounter
    for (int i = 0; i < number; i++) {
      for (int j = 0; j < input.length; j++) {
        save = input[j][0];
        for (int k = 0; k < input[j].length; k++) {
          if (k < ((input[j].length) - 1)) {
            output[j][k] = input[j][k + 1];
          } else {
            output[j][k] = save;
          }
        }
      }
      input = output;
    }

    if (print == true) {
      System.out.print("\n Shift result:\n");
      for (int l = 0; l < output.length; l++) {
        System.out.println(output[l]);
      }
      System.out.print("\n");
    }

    return output;
  }

  public static int[] add(int[] input_one, int[] input_two, Boolean print) {
    int[] result = new int[input_one.length];
    for (int i = 0; i < input_one.length; i++) {
      result[i] = input_one[i] + input_two[i];
    }

    if (print == true) {
      System.out.println("added value:");
      for (int j = 0; j < input_one.length; j++) {
        System.out.print(result[j] + " ");
      }
      System.out.println("\n");
    }

    return result;
  }

  public static int[] modified_modulo(int[] input_one, int[] input_two, int number, Boolean print) {
    int[] result = new int[input_one.length];

    for (int i = 0; i < input_one.length; i++) {
      if ((input_one[i] != 0) && (input_two[i] != 0)) {
        result[i] = (input_one[i] % input_two[i]) + number;
      } else {
        input_one[i] = input_one[i] + number;
        input_two[i] = input_two[i] + number;
        result[i] = (input_one[i] % input_two[i]) + number;
      }
    }

    if (print == true) {
      System.out.println("modified modulo value:");
      for (int j = 0; j < result.length; j++) {
        System.out.print(result[j] + " ");
      }
      System.out.println("\n");
    }

    return result;

  }

  public static int[] string_to_int_array_conversion(String string_value) {
    String input = binary_conversion(string_value);
    input = string_adjustment(input);
    String[] full_split_input = full_split(input);
    char[][] input_chars = convert(full_split_input);
    int[] decimal_values = decimal_conversion(input_chars, false);

    return decimal_values;
  }

  public static int[] partial_addition(String data, int[] integer_array) {
    int[] decimal_values = string_to_int_array_conversion(data);
    int part_length = 8;
    int full_length = decimal_values.length;
    int[] numeric_result = Arrays.copyOf(integer_array, integer_array.length);
    for (int i = full_length; i > 0; i -= part_length) {
      int start = Math.max(i - part_length, 0);
      int end = i;
      int[] part = Arrays.copyOfRange(decimal_values, start, end);
      for (int j = 0; j < 1; j++) {
        numeric_result = add(part, numeric_result, false);
      }
      numeric_result = add(part, numeric_result, false);
    }
    return numeric_result;
  }

  public static String integer_size_increase(String input) {
    BigInteger number = new BigInteger(input);
    String snipped_string = input.substring(input.length() - Math.round(input.length() / 20), input.length());
    BigInteger snipped_biginteger = new BigInteger(snipped_string);
    BigInteger limit = BigInteger.valueOf(2).pow(1024);
    while (number.compareTo(limit) < 0) {
      number = number.add(number).add(snipped_biginteger);
    }
    String number_string = number.toString();
    number_string = number_string.substring(number_string.length() - 309, number_string.length());
    return number.toString();
  }

  public static String increase_loop(String input, String data) {
    int rounds = data.length();
    String output = input.substring(input.length() - 64, input.length());
    for (int i = 0; i < rounds; i++) {
      output = input.substring(output.length() - 64, output.length());
      output = integer_size_increase(output);
    }
    return output;
  }

  public static void write_to_file(String file, String data) {
    try {
      BufferedWriter output_file = new BufferedWriter(new FileWriter(file, true));
      output_file.write(data + "\n");
      output_file.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public static String hsah(String output_file, String data) {
    Boolean print_inputs = false;
    Boolean print_xor = false;
    Boolean print_shift = false;
    Boolean print_decimals = false;
    Boolean print_added_results = false;
    Boolean print_modulo = false;
    // first set of data to be hashed
    char[][] input_one = init_binary_string_values(data);

    String input_one_decimal_string = string_conversion(decimal_conversion(input_one, false));
    input_one_decimal_string = integer_size_increase(input_one_decimal_string);
    input_one_decimal_string = increase_loop(input_one_decimal_string, data);
    input_one = init_binary_string_values(input_one_decimal_string);

    // for (int i = 0; i < input_one.length; i ++) {
    // System.out.println(input_one[i]);
    // }

    // static hsahing dataset
    char[][] input_two = init_values("823b4erhdsa8ufnb", print_inputs);

    char[][] xor_result = xor(input_one, input_two, 1, print_xor);

    if (print_xor == true) {
      for (int m = 0; m < xor_result.length; m++) {
        System.out.println(xor_result[m]);
      }
      System.out.print("\n");
    }

    char[][] shift_result = shift(xor_result, 8, print_shift);

    int[] input_numeric_value = decimal_conversion(shift_result, print_decimals);
    int[] input_numeric_value_two = decimal_conversion(init_values("ds7basdjfgh7d", print_inputs),
        print_decimals);

    int[] added_values = add(input_numeric_value, input_numeric_value_two, print_added_results);

    int[] modulo_values = modified_modulo(added_values, input_numeric_value, 10, print_modulo);

    int[] added_values_two = add(decimal_conversion(input_one, false), modulo_values, print_added_results);

    added_values_two = partial_addition(data, added_values_two);

    String string_mod_values = string_conversion(added_values_two);

    char[][] shifting_values = init_binary_string_values(string_mod_values);

    String result = string_conversion(decimal_conversion(shifting_values, false));
    System.out.println("\nResult values: \n");
    result = integer_size_increase(result);
    result = increase_loop(result, data);

    write_to_file(output_file, result);
    System.out.println(result);
    return result;
  };
}
