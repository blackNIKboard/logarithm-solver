package FindLogarithm;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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

    public static void bruteForce(final int[] srcData) {
        checkData(srcData);
        int x = 1;
        int steps = 0;
        BigInteger a;
        BigInteger b = BigInteger.valueOf(0);
        BigInteger[] data = new BigInteger[3];
        for (int i = 0; i < 3; i++) {
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
        print(("Result: " + x + " with complexity of " + steps), true);
    }

    public static List<MyPair> getIntersection(int[] arr1, int[] arr2) {
        List<MyPair> list = new ArrayList<MyPair>();
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] == arr2[j]) {
                    MyPair p = new MyPair(i, j);
                    list.add(p);
                }
            }
        }
        return list;
    }

    public static void comparison(final int[] srcData) {
        checkData(srcData);
        int steps = 0;
        int H = (int) Math.sqrt(srcData[2]) + 1; // first step
        print("H = " + "sqrt(" + srcData[2] + ")" + " + " + "1" + " = " + H, true);
        int[] cu = new int[H + 1];
        int[] bav = new int[H + 1];

        int c = (int) Math.pow(srcData[0], H) % srcData[2]; // second step
        print("c = " + srcData[0] + " ^ " + H + " = " + c, true);
        print(" ", true);
        cu[1] = c; // third step
        print("c" + "[" + 1 + "] = " + cu[1] + " mod(" + srcData[2] + ")", true);
        for (int u = 2; u <= H; u++) {
            cu[u] = cu[u - 1] * c % srcData[2];
            steps++;
            print("c" + "[" + u + "] = " + cu[u - 1] + " * " + c + " mod(" + srcData[2] + ") = " + cu[u], true);
        }
        print(" ", true);
        for (int v = 0; v <= H; v++) { // forth step
            bav[v] = (srcData[1] * (int) Math.pow(srcData[0], v)) % srcData[2];
            steps++;
            print("v" + "[" + v + "] = " + srcData[1] + " * " + srcData[0] + " ^ " + v + " mod(" + srcData[2] + ") = "
                    + bav[v], true);
        }
        print(" ", true);
        List<MyPair> inter = (List<MyPair>) getIntersection(cu, bav); // 5th & 6th steps
        for (int i = 0; i < inter.size(); i++) {
            MyPair cur = inter.get(i);
            cur.setResult((H * cur.getU() - cur.getV()) % (srcData[2] - 1));
            steps++;
            print("x_" + (i + 1) + " = " + H + " * " + cur.getU() + " - " + cur.getV() + " mod(" + (srcData[2] - 1)
                    + ")" + " = " + cur.getResult(), true);
        }
        print("Complexity = " + steps, true);
    }

    public static void main(final String[] args) throws Exception {
        final Scanner in = new Scanner(System.in);
        final int[] source = new int[3];
        String selectedWay = "NONE";
        final String[] ways = { "Brute Force", "Comparison" };

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
            bruteForce(source);
        } else if (selectedWay == "Comparison") {
            comparison(source);
        } else
            throw new RuntimeException("Error in checking of method");
    }
}