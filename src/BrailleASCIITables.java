import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A basic implementation of BrailleASCIITables which import the info about the
 * translation
 * And creat methods to decode the info from one type to another.
 *
 * @author Vivien Yan
 */

public class BrailleASCIITables {
    private static BitTree brailleToASCII;
    private static BitTree brailleToUnicode;
    private static BitTree asciiToBraille;

    static {
        brailleToASCII = new BitTree(6);
        brailleToUnicode = new BitTree(6);
        asciiToBraille = new BitTree(8);

        File source1 = new File("/Users/yiweiyan/Desktop/csc207/mp9/src/BtoA.txt");
        File source2 = new File("/Users/yiweiyan/Desktop/csc207/mp9/src/BtoU.txt");
        File source3 = new File("/Users/yiweiyan/Desktop/csc207/mp9/src/AtoB.txt");

        try {
            InputStream BtoA = new FileInputStream(source1);
            brailleToASCII.load(BtoA);
            BtoA.close();

            InputStream BtoU = new FileInputStream(source2);
            brailleToUnicode.load(BtoU);
            BtoU.close();

            InputStream AtoB = new FileInputStream(source3);
            asciiToBraille.load(AtoB);
            AtoB.close();
        } catch (IOException e) {
        }
    }

    public static String toBraille(char letter) {
        String binary = Integer.toBinaryString(letter);
        if (binary.length() != 7) {
            throw new IllegalArgumentException("invalid ACSII");
        }
        int add = 0;
        binary = add + binary;
        return asciiToBraille.get(binary);
    }

    public static String toASCII(String bits) {
        if (bits.length() != 6) {
            throw new IllegalArgumentException("invalid braille");
        }
        return brailleToASCII.get(bits);
    }

    public static String toUnicode(String bits) {
        if (bits.length() != 6) {
            throw new IllegalArgumentException("invalid braille");
        }
        return brailleToUnicode.get(bits);
    }
}