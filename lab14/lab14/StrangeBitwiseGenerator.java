package lab14;

import edu.princeton.cs.algs4.StdAudio;
import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;

    public StrangeBitwiseGenerator(int period) {
        state = 0;
        this.period = period;
    }
    @Override
    public double next() {
        state = (state + 1);
        int weirdState = state & (state >> 3) & (state >> 8) % period;
        return 2 * ((double) (weirdState) / period - 0.5);
    }
}


