package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    private double[] thresholds;
    private PercolationFactory pf;
    private int T;
    private int N;
    private double oneExperiment(Percolation p) {
        while (!p.percolates()) {
            int row = StdRandom.uniform(N);
            int col = StdRandom.uniform(N);
            p.open(row, col);
        }
        return p.numberOfOpenSites() / (N * N);
    }
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T need to be positive");
        }
        this.N = N;
        this.T = T;
        this.pf = pf;
        this.thresholds = new double[T];
        for (int i = 0; i < T; i++) {
            thresholds[i] = oneExperiment(pf.make(N));
        }
    }
    public double mean() {
        return StdStats.mean(thresholds);
    }
    public double stddev() {
        return StdStats.stddev(thresholds);
    }
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

}
