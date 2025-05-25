public class Main {
  public static void main(String[] args) {
    String converted = binary_conversion("Helloooo");
    String adjusted = adjust_size(converted);
    System.out.println(adjusted + "\n" + adjusted.length());
    String[] split_values = split(adjusted);

    for (int k = 0; k < split_values.length; k++) {
      System.out.println(split_values[k]);
    }
    char[][] new_input1 = convert(split_values);

    xor(new_input1);

  }

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
    char[][] new_input = new char[15][8];

    for (int k = 0; k < input.length; k++) {
      for (int l = 0; l < 7; l++) {
        new_input[k][l] = input[k].charAt(l);
        System.out.print(new_input[k][l]);
      }
    }
    return new_input;

  }

  public static void xor(char[][] input1) {
    char[][] input2 = new char[15][8];
    char[][] result = new char[15][8];

    if (input1.length == input2.length) {
      for (int i = 0; i < input1.length; i++) {
        for (int j = 0; j < 7; j++) {
          if (input1[i][j] == '0' && input2[i][j] == '0') {
            result[i][j] = '0';
          } else if (input1[i][j] == '1' && input2[i][j] == '1') {
            result[i][j] = '0';
          } else if (input1[i][j] == '0' && input2[i][j] == '1') {
            result[i][j] = '1';
          } else if (input1[i][j] == '1' && input2[i][j] == '0') {
            result[i][j] = '1';
          }
        }
      }
    }
  }

  public static void magic(String[] data) {
  };
}
