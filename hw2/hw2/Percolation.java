package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF sitesUF;
    WeightedQuickUnionUF sitesUFWithoutBottom;
    int [][] sites;
    int N;
    int numOfOpenSites;
    public int xyTo1D(int row, int col) {
        return row * N + col + 1;
    }
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N need to be positive.");
        }
        this.N = N;
        this.sites = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sites[i][j] = 0;
            }
        }
        this.numOfOpenSites = 0;
        sitesUF = new WeightedQuickUnionUF(N * N + 2); //two virtual sites
        sitesUFWithoutBottom = new WeightedQuickUnionUF(N * N + 1); //only one virtual site
        for (int i = 0; i < N; i++) {
            sitesUF.union(0, xyTo1D(0, i));
            sitesUF.union(N * N + 1, xyTo1D(N - 1, i));
            sitesUFWithoutBottom.union(0, xyTo1D(0, i));
        }
    } // create N-by-N grid, with all sites initially blocked
    public void open(int row, int col) {
        if (row >= N || col >= N) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        if (!isOpen(row, col)) {
            numOfOpenSites += 1;
            sites[row][col] = 1;
            if (row >= 1 && isOpen(row - 1, col)) {
                sitesUF.union(xyTo1D(row, col), xyTo1D(row - 1, col));
                sitesUFWithoutBottom.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            }
            if (col <= N - 2 && isOpen(row, col + 1)) {
                sitesUF.union(xyTo1D(row, col), xyTo1D(row, col + 1));
                sitesUFWithoutBottom.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            }
            if (col >= 1 && isOpen(row, col - 1)) {
                sitesUF.union(xyTo1D(row, col), xyTo1D(row, col - 1));
                sitesUFWithoutBottom.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            }
            if (row <= N - 2 && isOpen(row + 1, col)) {
                sitesUF.union(xyTo1D(row, col), xyTo1D(row + 1, col));
                sitesUFWithoutBottom.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            }
        }
    }      // open the site (row, col) if it is not open already
    public boolean isOpen(int row, int col) {
        if (row >= N || col >= N) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        return (sites[row][col] == 1);
    }  // is the site (row, col) open?
    public boolean isFull(int row, int col) {
        if (row >= N || col >= N) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        return sitesUFWithoutBottom.connected(0, xyTo1D(row, col));
    } // is the site (row, col) full?
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }           // number of open sites
    public boolean percolates() {
        return sitesUF.connected(0, N * N + 1);
    }              // does the system percolate?
//    public static void main(String[] args)   // use for unit testing (not required)

}
