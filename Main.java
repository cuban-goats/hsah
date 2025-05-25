public class Main {
  public static void main(String[] args) {
    String input = "This will soon be a hash";
    String result = binary_conversion(input);
    System.out.println(result);
  }

  public static String binary_conversion(String input) {
    StringBuilder result = new StringBuilder();
    char[] chars = input.toCharArray();

    for (char aCHar : chars) {
      result.append(String.format("%8s", Integer.toBinaryString(aCHar)).replaceAll(" ", "0"));
    }
    return result.toString();
  }
}
