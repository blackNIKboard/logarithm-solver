package FindLogarithm;

public class MyPair {
    private final int u;
    private final int v;
    private long resultX = 0;

    public MyPair(int uValue, int vValue) {
        u = uValue;
        v = vValue;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    public void setResult(long result) {
        resultX = result;
    }

    public long getResult() {
        return resultX;
    }
}