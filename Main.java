public class Main {
  public static void main(String[] args) {
    String converted = binary_conversion("Hello");
    String adjusted = adjust_size(converted);
    System.out.println(adjusted + "\n" + adjusted.length());
    String[] split_values = split(adjusted);

    for (int k=0; k<split_values.length; k++) {
      System.out.println(split_values[k]);
    }

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
    if (converted.length() < 63) {
      while (adjusted.length() < 63) {
        adjusted.append("0");
      }

    }
    return adjusted.toString();
  }

  public static String[] split(String adjusted){
    String[] split_values = new String[4];

    for (int i=0 ; i<=3; i++) {
      split_values[i] = adjusted.substring(0+(i*14), 14*(i+1));
    }
    return split_values;
  }
}
