package lab11.graphs;
import edu.princeton.cs.algs4.Queue;
/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        // Add more variables here!
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(s);
        int current;
        while (!q.isEmpty()) {
            current = q.dequeue();
            marked[current] = true;
            announce();
            if (current == t) {
                break;
            }
            for (int i : maze.adj(current)) {
                if (marked[i]) {
                    continue;
                }
                distTo[i] = distTo[current] + 1;
                edgeTo[i] = current;
                announce();
                q.enqueue(i);
            }
        }


        // TODO Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
    }


    @Override
    public void solve() {
         bfs();
    }
}

