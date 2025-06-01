public class Main {
  public static void main(String[] args) {

    hsah("Heellooo");

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

  public static char[][] xor(char[][] input_one, char[][] input_two, int number) {
    char[][] result = new char[input_one.length][input_one[0].length];

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
            System.out.print(result[i][j]);
          }
        }
        System.out.print("\n");
        input_one = result;
      }
    }
    return result;
  }

  public static char[][] shift(char[][] input, int number) {
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

    for (int l = 0; l < output.length; l++) {
      System.out.println(output[l]);
    }

    return output;

  }

  public static char[][] init_values(String data, Boolean a) {
    String output = binary_conversion(data);
    output = adjust_size(output);
    System.out.println(output + "\n" + output.length());
    String[] output_list = split(output);

    if (a == true) {
      for (int k = 0; k < output_list.length; k++) {
        System.out.println(output_list[k]);
      }
    } else {
    }

    char[][] input = convert(output_list);

    if (a = true) {
      for (int i = 0; i < input.length; i++) {
        for (int j = 0; j < input[i].length; j++) {
          // System.out.print(input[i][j]);
        }
      }
    } else {
    }

    return input;
  }

  public static void hsah(String data) {
    // first set of data to be hashed
    char[][] input_one = init_values(data, false);

    // space
    System.out.println("\n");

    // second set of data
    char[][] input_two = init_values("823b4erhdsa8ufnb", false);

    // space
    System.out.println("\n");

    char[][] xor_result = xor(input_one, input_two, 1);

    // space
    System.out.println("\n");

    for (int m = 0; m < xor_result.length; m++) {
      System.out.println(xor_result[m]);
    }

    // space
    System.out.println("\n");

    char[][] shift_result = shift(xor_result, 1);
  };
}
