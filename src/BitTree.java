import java.io.*;

/**
 * A basic implementation of BitTree where have levels of entry for 1 and 0 so
 * that values can be
 * stored underneath.
 *
 * @author Vivien Yan
 */

class BitTreeNode {
    BitTreeNode left;
    BitTreeNode right;
    String value;
}

class BitTree {
    private BitTreeNode root;
    private int n;

    public BitTree(int n) {
        this.n = n;
    }

    public void set(String bits, String value) {
        if (bits.length() != n || !bits.matches("[01]+")) {
            throw new IllegalArgumentException("Invalid bit string");
        }
        this.root = setHelper(root, bits, value, 0);
    }

    private BitTreeNode setHelper(BitTreeNode root, String bits, String value, int level) {
        if (root == null) {
            root = new BitTreeNode();
        }
        if (level == this.n) {
            root.value = value;
            return root;
        }
        char bit = bits.charAt(level);
        if (bit == '0') {
            level = level + 1;
            root.left = setHelper(root.left, bits, value, level);
        } else {
            level = level + 1;
            root.right = setHelper(root.right, bits, value, level);
        }
        return root;
    }

    public String get(String bits) {
        if (bits.length() != n || !bits.matches("[01]+")) {
            throw new IllegalArgumentException("Invalid bit string");
        }
        return getHelper(root, bits, 0);
    }

    private String getHelper(BitTreeNode root, String bits, int level) {
        if (root == null) {
            throw new IllegalArgumentException("no such path");
        }
        if (level == this.n) {
            return root.value;
        }
        char bit = bits.charAt(level);
        if (bit == '0') {
            level = level + 1;
            return getHelper(root.left, bits, level);
        } else {
            level = level + 1;
            return getHelper(root.right, bits, level);
        }
    }

    public void dump(PrintWriter pen) {
        dumpHelper(root, "", pen);
    }

    private void dumpHelper(BitTreeNode root, String bits, PrintWriter pen) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            pen.println(bits + "," + root.value);
        }
        dumpHelper(root.left, bits + "0", pen);
        dumpHelper(root.right, bits + "1", pen);
    }

    public void load(InputStream source) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(source))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] input = line.split(",");
                set(input[0], input[1]);
            }
        }
        ;
    }
}