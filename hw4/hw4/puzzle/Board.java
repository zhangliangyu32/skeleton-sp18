package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;
public class Board implements WorldState {

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    private int[][] tiles;
    private int size;
    public Board(int[][] tiles) {
        this.size = tiles[0].length;
        this.tiles = new int[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }

    }
    public int size() {
        return size;
    }
    public int tileAt(int i, int j) {
        if (i >= size || j >= size || i < 0 || j < 0) {
            throw new IndexOutOfBoundsException("Index should lie in [0, N-1].");
        }
        return tiles[i][j];
    }
    @Override
    public int hashCode() {
        return 0;
    }
    @Override
    public boolean equals(Object y) {
        if (!(y instanceof Board)) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tileAt(i, j) != ((Board) y).tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
    public int hamming() {
        int sum = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tileAt(i, j) != i * size() + j + 1 && tileAt(i, j) != 0) {
                    sum += 1;
                }
            }
        }
        return sum;
    }
    public int manhattan() {
        int sum = 0;
        int expectedi = 0;
        int expectedj = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tileAt(i, j) == 0) {
                    continue;
                }
                expectedi = (tileAt(i, j) - 1) / size();
                expectedj = (tileAt(i, j) - 1) % size();
                sum = sum + Math.abs(expectedi - i) + Math.abs(expectedj - j);
            }
        }
        return sum;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == 0) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = 0;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = 0;
                }
            }
        }
        return neighbors;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
