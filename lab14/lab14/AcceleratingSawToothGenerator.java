package lab14;

import edu.princeton.cs.algs4.StdAudio;
import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private int state;
    private double factor;

    public AcceleratingSawToothGenerator(int period, double factor) {
        state = 0;
        this.period = period;
        this.factor = factor;
    }
    @Override
    public double next() {
        state = (state + 1);
        if (state % period == 0) {
            period = (int) (period * factor);
            state = 0;
        }
        return 2 * ((double) (state % period) / period - 0.5);
    }
}


