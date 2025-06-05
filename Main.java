public class Main {
  public static void main(String[] args) {
    hsah("HsahhsahHsahhsah", false, false, false, false, true, true);
  } // end of main

  public static String binary_conversion(String input) {
    StringBuilder result = new StringBuilder();
    char[] chars = input.toCharArray();
    for (char aCHar : chars) {
      result.append(String.format("%8s", Integer.toBinaryString(aCHar)).replaceAll(" ", "0"));
    }
    return result.toString();
  }

  public static String adjust_size(String converted) {
    StringBuilder adjusted = new StringBuilder().append(converted);
    if (converted.length() < 128) {
      while (adjusted.length() < 128) {
        adjusted.append("0");
      }
    }
    return adjusted.toString();
  }

  public static String[] split(String adjusted) {
    String[] split_values = new String[8];

    for (int i = 0; i < 8; i++) {
      split_values[i] = adjusted.substring(0 + (i * 16), 16 * (i + 1));
    }
    return split_values;
  }

  public static char[][] convert(String[] input) {
    char[][] new_input = new char[input.length][16];

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

  public static char[][] xor(char[][] input_one, char[][] input_two, int number, Boolean print) {
    char[][] result = new char[input_one.length][input_one[0].length];
    if (print == true) {
      System.out.print("\n XOR result: \n");
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

  public static char[][] init_values(String data, Boolean print) {
    String output = binary_conversion(data);
    output = adjust_size(output);

    if (print == true) {
      System.out.println(output + "\n" + "Length:" + "\n" + output.length());
      System.out.println("\n");
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
    }

    if (print == true) {
      for (int i = 0; i < input.length; i++) {
        for (int j = 0; j < input[i].length; j++) {
          System.out.print(input[i][j]);
        }
      }
      System.out.print("\n");
    }
    return input;
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

  public static void hsah(String data, Boolean print_inputs, Boolean print_xor, Boolean print_shift,
      Boolean print_decimals, Boolean print_added_results, Boolean print_modulo) {
    // first set of data to be hashed
    char[][] input_one = init_values(data, print_inputs);

    // static hsahing datasets
    char[][] input_two = init_values("823b4erhdsa8ufnb", print_inputs);
    char[][] input_three = init_values("lköwrienheß", print_inputs);
    char[][] input_four = init_values("jkasdhh2ndin", print_inputs);

    char[][] xor_result = xor(input_one, input_two, 1, print_xor);
    xor_result = xor(xor_result, input_three, 1, print_xor);
    xor_result = xor(xor_result, input_four, 1, print_xor);

    if (print_xor == true) {
      for (int m = 0; m < xor_result.length; m++) {
        System.out.println(xor_result[m]);
      }
      System.out.print("\n");
    }

    char[][] shift_result = shift(xor_result, 8, print_shift);

    int[] input_numeric_value = decimal_conversion(shift_result, print_decimals);
    int[] input_numeric_value_two = decimal_conversion(init_values("ds7basdjfgh7d", print_inputs), print_decimals);
    int[] input_numeric_value_three = decimal_conversion(init_values("982hejhdsabfn", print_inputs), print_decimals);
    int[] input_numeric_value_four = decimal_conversion(init_values("897h32jk1n0d*", print_inputs), print_decimals);
    int[] input_numeric_value_five = decimal_conversion(xor_result, print_decimals);

    int[] added_values = add(input_numeric_value, input_numeric_value_two, print_added_results);
    added_values = add(added_values, input_numeric_value_three, print_added_results);
    added_values = add(added_values, input_numeric_value_four, print_added_results);
    added_values = add(added_values, input_numeric_value_five, print_added_results);

    int[] modulo_values = modified_modulo(input_numeric_value, input_numeric_value_two, 999, print_modulo);
    modulo_values = modified_modulo(modulo_values, input_numeric_value_two, 999, print_modulo);
    modulo_values = modified_modulo(modulo_values, input_numeric_value_three, 999, print_modulo);
    modulo_values = modified_modulo(modulo_values, input_numeric_value_four, 999, print_modulo);
    modulo_values = modified_modulo(modulo_values, input_numeric_value_five, 999, print_modulo);
    modulo_values = modified_modulo(modulo_values, added_values, 999, print_modulo);
  };
}
