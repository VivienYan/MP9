/**
 * A basic implementation of the main class where arguements can be taken in as
 * type to converting and
 * the info want to decode.
 *
 * @author Vivien Yan
 */

public class BrailleASCII {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("invalid input");
            return;
        }
        String type = args[0];
        String text = args[1];

        String output;
        switch (type) {
            case "braille":
                output = toBrailleString(text);
                break;
            case "ascii":
                output = toASCIIString(text);
                break;
            case "unicode":
                output = toUnicodeString(text);
                break;
            default:
                output = "Invalid target character set";
        }
        System.out.println(output);
    }

    private static String toBrailleString(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append(BrailleASCIITables.toBraille(c));
        }
        return result.toString();
    }

    private static String toASCIIString(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= text.length() - 6; i += 6) {
            String part = text.substring(i, i + 6);
            result.append(BrailleASCIITables.toASCII(part));
        }
        return result.toString();
    }

    private static String toUnicodeString(String text) {
        if (!Character.isDigit(text.charAt(0))) {
            text = toBrailleString(text);
        }
        String add = "000000";
        text = add + text;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length() - 6; i += 6) {
            String part = text.substring(i, i + 6);
            result.append(BrailleASCIITables.toUnicode(part));
        }
        return result.toString();
    }
}
