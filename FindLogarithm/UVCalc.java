package FindLogarithm;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class UVCalc {
    List<MyPair> UV;
    List<Integer> zList;
    int module = 0;
    double l, ml, mh, h;
    BigInteger a;
    BigInteger b;

    public UVCalc(int[] srcData) {
        module = srcData[2];
        UV = new ArrayList<MyPair>();
        zList = new ArrayList<Integer>();
        UV.add(0, new MyPair(0, 0));
        zList.add(0, 1);
        a = BigInteger.valueOf(srcData[0]);
        b = BigInteger.valueOf(srcData[1]);
        l = (double) 0;
        ml = (double) module / 3;
        mh = (double) module * 2 / 3;
        h = (double) module;
    }

    public int calculateU(int i) {
        if (i < 1)
            throw new RuntimeException("Invalid index");
        int result = 0;
        double z = (double) (zList.get(i - 1));
        FindLogarithm.print("Calculating u_" + i + ": ", false);
        if (z > l && z < ml) {
            // FindLogarithm.print("0 < z0 < 1/3p; ", false);
            result = UV.get(i - 1).getU() + 1;
            FindLogarithm.print("u_" + i + " = " + "u_(" + i + "-1) + 1 = " + result, true);
        } else if (z > ml && z < mh) {
            // FindLogarithm.print("1/3p < z0 < 2/3p; ", false);
            result = UV.get(i - 1).getU() * 2;
            FindLogarithm.print("u_" + i + " = " + "u_(" + i + "-1) * 2 = " + result, true);
        } else if (z > mh && z < h) {
            // FindLogarithm.print("2/3p < z0 < p; ", false);
            result = UV.get(i - 1).getU();
            FindLogarithm.print("u_" + i + " = " + "u_(" + i + "-1) = " + result, true);

        } else {
            throw new RuntimeException("Error in u calculations");
        }
        return result;
    }

    public int calculateV(int i) {
        if (i < 1)
            throw new RuntimeException("Invalid index");
        int result = 0;
        int z = (int) (zList.get(i - 1));
        FindLogarithm.print("Calculating v_" + i + ": ", false);
        if (z > l && z < ml) {
            // FindLogarithm.print("0 < z0 < 1/3p; ", true);
            result = UV.get(i - 1).getV();
            FindLogarithm.print("v_" + i + " = " + "v_(" + i + "-1) = " + result, true);
        } else if (z > ml && z < mh) {
            // FindLogarithm.print("1/3p < z0 < 2/3p; ", true);
            result = UV.get(i - 1).getV() * 2;
            FindLogarithm.print("v_" + i + " = " + "v_(" + i + "-1) * 2 = " + result, true);
        } else if (z > module * 2 / 3 && z < module) {
            // FindLogarithm.print("2/3p < z0 < p; ", true);
            result = UV.get(i - 1).getV() + 1;
            FindLogarithm.print("v_" + i + " = " + "v_(" + i + "-1) + 1 = " + result, true);
        } else {
            throw new RuntimeException("Error in v calculations");
        }
        return result;
    }

    public int iterate(int i) {
        UV.add(i, new MyPair(calculateU(i), calculateV(i)));
        int newZ = calculateZ(i);
        if (findLoop(newZ)) {
            return zList.indexOf(newZ);
        } else {
            zList.add(i, newZ);
            return -1;
        }
    }

    public int calculateZ(int i) {
        if (i < 1)
            throw new RuntimeException("Invalid index");
        int result = 0;
        int z = zList.get(i - 1);
        FindLogarithm.print("Calculating z_" + i + ": ", false);
        if (z > l && z < ml) {
            // FindLogarithm.print("0 < z0 < 1/3p; ", true);
            result = (z * a.intValue()) % module;
            FindLogarithm.print("z_" + i + " = " + "z_(" + i + "-1) * a = " + result, true);
        } else if (z > ml && z < mh) {
            // FindLogarithm.print("1/3p < z0 < 2/3p; ", true);
            result = ((int) Math.pow(z, 2)) % module;
            FindLogarithm.print("z_" + i + " = " + "z_(" + i + "-1)^2 = " + result, true);
        } else if (z > mh && z < h) {
            // FindLogarithm.print("2/3p < z0 < p; ", true);
            result = (z * b.intValue()) % module;
            FindLogarithm.print("z_" + i + " = " + "z_(" + i + "-1) * b = " + result, true);
        } else {
            throw new RuntimeException("Error in z calculations");
        }
        FindLogarithm.print("", true);

        return result;
    }

    public Boolean findLoop(int newZ) {
        for (int i = 1; i < module; i++) {
            if (zList.contains(newZ)) {
                return true;
            }
        }
        return false;
    }

    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        int x = a % b;
        return gcd(b, x);
    }

    public static int invert(int n, int module) {
        double result = n;
        if (gcd(n, module) != 1) {
            throw new RuntimeException(n + " not invertible in modulo " + module);
        }
        int i = 0;
        while ((result % 1) != 0) {
            if (i == module)
                throw new RuntimeException("Error in calculations");
            result = (1 + module) / n % module;
            i++;
        }
        //FindLogarithm.print("invert"+result, true);
        return (int) result;
    }

    public static int moduleAbs(int n, int module) {
        while (n < 0) {
            n += module;
        }
        //FindLogarithm.print("moduleABS"+n, true);
        return n;
    }

    public int solve() {
        int xZero = 0;
        int i, up, down, j = -1;
        FindLogarithm.print("z_0 = 1, u_0 = 0, v_0 = 0", true);
        for (i = 1; i <= module; i++) {
            if (i == module)
                throw new RuntimeException("No solution: out of bounds");
            j = iterate(i);
            if (j != -1)
                break;
        }
        up = UV.get(i).getU() - UV.get(j).getU();
        down = UV.get(j).getV() - UV.get(i).getV();
        // FindLogarithm.print("TEST SCUKO " +
        // a.pow(UV.get(i).getU()).mod(BigInteger.valueOf(module)) + " * "
        // + b.pow(UV.get(i).getV()).mod(BigInteger.valueOf(module)) + " = "
        // + a.pow(UV.get(j).getU()).mod(BigInteger.valueOf(module)) + " * "
        // + b.pow(UV.get(j).getV()).mod(BigInteger.valueOf(module)), true);
        xZero = (moduleAbs(up, module - 1) * invert(moduleAbs(down, module - 1), module - 1)) % (module - 1);
        FindLogarithm.print("z_" + i + " = " + "z_" + j, true);
        FindLogarithm.print("x = (" + UV.get(i).getU() + " - " + UV.get(j).getU() + ") / (" + UV.get(j).getV() + " - "
                + UV.get(i).getV() + ") = " + xZero + " mod(" + (module - 1) + ")", true);
        return xZero;
    }
}