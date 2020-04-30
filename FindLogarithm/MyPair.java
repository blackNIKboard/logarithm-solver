package FindLogarithm;

public class MyPair {
    private int u;
    private int v;
    private long result = 0;

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

    public void setU(int value) {
        u = value;
    }

    public void setV(int value) {
        v = value;
    }

    public void setResult(long res) {
        result = res;
    }

    public long getResult() {
        return result;
    }
}