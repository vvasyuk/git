import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

public class Solver {
    private final Stack<Board> solutionBoards;
    private boolean isSolvable;


    public Solver(Board initial) {
        if (initial == null) throw new NullPointerException();
        isSolvable = false;
        solutionBoards = new Stack<>();
        MinPQ<SearchNode> searchNodes = new MinPQ<>();

        searchNodes.insert(new SearchNode(initial, null));
        searchNodes.insert(new SearchNode(initial.twin(), null));

        while (!searchNodes.min().board.isGoal()) {
            SearchNode searchNode = searchNodes.delMin();
            for (Board board : searchNode.board.neighbors())
                if (searchNode.prevNode == null || searchNode.prevNode != null && !searchNode.prevNode.board.equals(board))
                    searchNodes.insert(new SearchNode(board, searchNode));
        }

        SearchNode current = searchNodes.min();
        while (current.prevNode != null) {
            solutionBoards.push(current.board);
            current = current.prevNode;
        }
        solutionBoards.push(current.board);

        if (current.board.equals(initial)) isSolvable = true;

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board
    public int moves() {
        if (!isSolvable()) return -1;
        return solutionBoards.size() - 1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        if (isSolvable()) return solutionBoards;
        return null;
    }

    // test client (see below)
    public static void main(String[] args) {
        int[][] input = new int[3][3];
        input[0] = new int[] {0, 1, 3};
        input[1] = new int[] {4, 2, 5};
        input[2] = new int[] {7, 8, 6};
        Board b = new Board(input);

        Solver solver = new Solver(b);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final SearchNode prevNode;
        private int moves;
        private int manhattan;

        public SearchNode(Board board, SearchNode prevNode) {
            this.board = board;
            this.prevNode = prevNode;
            this.manhattan = board.manhattan();
            if (prevNode != null) moves = prevNode.moves + 1;
            else moves = 0;
        }

        @Override
        public int compareTo(SearchNode that) {
            int priorityDiff = (this.manhattan + this.moves) - (that.manhattan + that.moves);
            return  priorityDiff == 0 ? this.manhattan - that.manhattan : priorityDiff;
        }
    }
}