package FindLogarithm;

import java.util.Scanner;

public class FindLogarithm {
    public static void printInteger(final int i, final boolean enter) {
        if (enter == true)
            System.out.println(i);
        else
            System.out.print(i);
    }

    public static void printString(final String s, final boolean enter) {
        if (enter == true)
            System.out.println(s);
        else
            System.out.print(s);
    }

    public static void checkData(final int[] srcData) { // TODO
        // throw new RuntimeException("Error in data");
    }

    public static String bruteForce(final int[] srcData) {
        checkData(srcData);
        int x = 1;
        int steps = 0;
        while (true) {
            if (x > 10000)
                throw new RuntimeException("Out of bounds");
            int a = srcData[0];
            int b = -1;
            for (int k = 0; k < x; k++) {
                a *= srcData[0];
                b = a % srcData[2];
                steps++;
            }
            x++;
            if (b == srcData[1])
                break;
        }
        return ("Result: " + x + " with exponential complexity of " + steps);
    }

    public static void main(final String[] args) throws Exception {
        final Scanner in = new Scanner(System.in);
        final int[] source = new int[3];
        String selectedWay = "NONE";
        final String[] ways = { "Brute Force" };

        printString("Enter all integers from left to right: ", false);
        // ---input
        for (int i = 0; i < 3; i++) {
            source[i] = in.nextInt();
        }
        printString("SELECT METHOD FROM LIST BELOW", true);
        for (int i = 0; i < ways.length; i++) {
            printInteger(i + 1, false);
            printString(" - " + ways[i], true);
        }
        selectedWay = ways[in.nextInt() - 1];
        in.close();

        // ---output
        // brute force method
        if (selectedWay == "Brute Force") {
            printString(bruteForce(source), true);
        } else
            throw new RuntimeException("Error in checking of method");
    }
}