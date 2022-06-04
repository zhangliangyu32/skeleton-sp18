package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    private class SearchNode implements Comparable<SearchNode> {
        private WorldState state;
        private int distanceToInitial;
        private int estimatedDistanceToGoal;
        private SearchNode last;
        SearchNode(WorldState state, int distanceToInitial, SearchNode last) {
            this.state = state;
            this.distanceToInitial = distanceToInitial;
            this.last = last;
            this.estimatedDistanceToGoal = state.estimatedDistanceToGoal();
        }
        public int getPriority() {
            return distanceToInitial + estimatedDistanceToGoal;
        }

        public int getDistanceToInitial() {
            return distanceToInitial;
        }
        public WorldState getState() {
            return state;
        }
        public SearchNode getLast() {
            return last;
        }

        @Override
        public int compareTo(SearchNode o) {
            return this.getPriority() - o.getPriority();
        }
    }
    private int moves;
    private List<WorldState> soluts;
    public Solver(WorldState initial) {
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        SearchNode current = new SearchNode(initial, 0, null);
        pq.insert(current);
        while (!pq.isEmpty()) {
            current = pq.delMin();
            if (current.getState().isGoal()) {
                break;
            }
            for (WorldState s : current.getState().neighbors()) {
                if (current.getLast() == null || !s.equals(current.getLast().getState())) {
                    pq.insert(new SearchNode(s, current.getDistanceToInitial() + 1, current));
                }
            }
        }
        moves = current.getDistanceToInitial();
        soluts = new ArrayList<WorldState>();
        while (current != null) {
            soluts.add(current.state);
            current = current.last;
        }
        return;
    }
    public int moves() {
        return moves;
    }
    public Iterable<WorldState> solution() {
        return soluts;
    }
}
