package FindLogarithm;

public class MyPair {
    private final int u;
    private final int v;
    private int resultX = 0;

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

    public void setResult(int result) {
        resultX = result;
    }

    public int getResult() {
        return resultX;
    }
}