package hw2;
public class TestPercolationStats {
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(4, 10, new PercolationFactory());
        System.out.println(ps.mean());
        System.out.println(ps.stddev());
    }
}
