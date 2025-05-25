public class Main {
  public static void main(String[] args) {
    String converted = binary_conversion("Hello");
    String adjusted = adjust_size(converted);
    System.out.println(adjusted);

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
    if (converted.length() < 64) {
      while (adjusted.length() < 64) {
        adjusted.append("0");
      }

    }
    return adjusted.toString();
  }
}
