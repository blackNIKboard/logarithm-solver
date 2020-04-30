package FindLogarithm;

import java.math.BigInteger;
import java.util.Scanner;

public class FindLogarithm {
    public static void print(final int i, final boolean enter) {
        if (enter == true)
            System.out.println(i);
        else
            System.out.print(i);
    }
    public static void print(final long i, final boolean enter) {
        if (enter == true)
            System.out.println(i);
        else
            System.out.print(i);
    }
    public static void print(final String s, final boolean enter) {
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
        BigInteger a;
        BigInteger b = BigInteger.valueOf(0);
        BigInteger[] data = new BigInteger[3];
        for(int i = 0; i < 3; i++){
            data[i] = BigInteger.valueOf(srcData[i]);
        }
        while (true) {
            if (x > srcData[2])
                throw new RuntimeException("Not exist or error in calculations");
            a = BigInteger.valueOf(srcData[0]);
            for (int k = 0; k < x; k++) {
                a = a.multiply(data[0]);         
                b = (a.mod(data[2]));
                steps++;
            }
            // print(a.toString(), true);
            // print(b.toString(), true);
            // print("\n", true);
            x++;
            if (b.equals(data[1]))
                break;
        }
        return ("Result: " + x + " with exponential complexity of " + steps);
    }

    public static void main(final String[] args) throws Exception {
        final Scanner in = new Scanner(System.in);
        final int[] source = new int[3];
        String selectedWay = "NONE";
        final String[] ways = { "Brute Force" };

        print("Enter all integers from left to right: ", false);
        // ---input
        for (int i = 0; i < 3; i++) {
            source[i] = in.nextInt();
        }
        print("SELECT METHOD FROM LIST BELOW", true);
        for (int i = 0; i < ways.length; i++) {
            print(i + 1, false);
            print(" - " + ways[i], true);
        }
        print("Method: ", false);
        selectedWay = ways[in.nextInt() - 1];
        in.close();

        // ---output
        // brute force method
        if (selectedWay == "Brute Force") {
            print(bruteForce(source), true);
        } else
            throw new RuntimeException("Error in checking of method");
    }
}