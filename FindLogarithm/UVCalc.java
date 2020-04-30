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
        if (z > l && z < ml) {
            result = UV.get(i - 1).getU() + 1;
        } else if (z > ml && z < mh) {
            result = UV.get(i - 1).getU() * 2;
        } else if (z > mh && z < h) {
            result = UV.get(i - 1).getU();
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
        if (z > l && z < ml) {
            result = UV.get(i - 1).getV();
        } else if (z > ml && z < mh) {
            result = UV.get(i - 1).getV() * 2;
        } else if (z > module * 2 / 3 && z < module) {
            result = UV.get(i - 1).getV() + 1;
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
        if (z > l && z < ml) {
            result = (z * a.intValue()) % module;
        } else if (z > ml && z < mh) {
            result = ((int) Math.pow(z, 2)) % module;
        } else if (z > mh && z < h) {
            result = (z * b.intValue()) % module;
        } else {
            throw new RuntimeException("Error in z calculations");
        }

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

    public int solve() {
        int xZero = 0;
        int i, up, down, j = -1;
        for (i = 1; i <= module; i++) {
            if (i == module)
                throw new RuntimeException("No solution: out of bounds");
            j = iterate(i);
            if (j != -1)
                break;
        }
        up = UV.get(i).getU() - UV.get(j).getU();
        down = UV.get(j).getV() - UV.get(i).getV();
        xZero = (up / down) % (module - 1);
        while (xZero < 0)
            xZero += (module - 1);
        FindLogarithm.print(xZero, true);
        return xZero;
    }
}