import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private int[][] board;
    private int dimensions;
    private int moves;

    public Board (int[][] tiles) {
        this(tiles, 0);
    }

    public Board(int[][] tiles, int moves) {
        this.moves = moves;
        dimensions = tiles.length;
        board = new int[dimensions][dimensions];
        IntStream.range(0,tiles.length).forEach(row-> {
            IntStream.range(0,tiles.length).forEach(col->{
                board[row][col] = tiles[row][col];
            });
        });
    }

    public String toString() {
        StringBuffer res = new StringBuffer(board.length + "\n");
        IntStream.range(0,board.length).forEach(row-> {
            IntStream.range(0,board.length).forEach(col->{
                res.append(board[row][col] + " ");
            });
            res.append("\n");
        });
        return res.toString();
    }

    public int dimension() {
        return dimensions;
    }

    // number of tiles out of place
    public int hamming() {
        final int[] res = {0};
        IntStream.range(0,board.length).forEach(row-> {
            IntStream.range(0,board.length).forEach(col->{
                if(board[row][col]!=row*dimensions+col+1) {
                    res[0]++;
                }
            });
        });
        return res[0];
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        final int[] res = {0};
        IntStream.range(0,board.length).forEach(row-> {
            IntStream.range(0,board.length).forEach(col->{
                int i = board[row][col];
                int dx, dy;
                if(i==0) {
                    dx = dimensions-1;
                    dy = dimensions-1;
                } else {
                    dx = (i-1)/dimensions;
                    dy = (i-1)-dx*dimensions;
                }
                res[0] = res[0] + Math.abs(dx-row)+Math.abs(dy-col);
            });
        });
        return res[0];
    }

    // is this board the goal board?
    public boolean isGoal() {
        final boolean[] ret = {true};
        LinkedList<Integer> queue = IntStream.range(1, dimensions*dimensions).boxed()
                .collect(Collectors.toCollection(LinkedList::new));
        queue.addLast(0);

        IntStream.range(0, dimensions).forEach(x-> {
            IntStream.range(0, dimensions).forEach(y->{
                if (board[x][y] != queue.poll()) {
                    ret[0] = false;
                }
            });
        });
        return ret[0];
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }

        if (this == y) {
            return true;
        }

        if (this.getClass() != y.getClass()) {
            return false;
        }

        Board that = (Board) y;

        if (this.dimension() != that.dimension()) {
            return false;
        }

        final boolean[] res = {true};
        IntStream.range(0,board.length).forEach(row-> {
            IntStream.range(0,board.length).forEach(col->{
                if( board[row][col] != that.board[row][col] ) {
                    res[0] = false;
                }
            });
        });

        return res[0];
    }

//    // all neighboring boards
//    public Iterable<Board> neighbors()
//
//    // a board that is obtained by exchanging any pair of tiles
//    public Board twin()

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] input = new int[3][3];
        input[0] = new int[] {0, 1, 3};
        input[1] = new int[] {4, 2, 5};
        input[2] = new int[] {7, 8, 6};
        Board b = new Board(input);
        assert(b.hamming() == 5);
        assert(b.manhattan() == 8);
        assert(b.isGoal() != true);

        int[][] goodInput = new int[3][3];
        goodInput[0] = new int[] {1, 2, 3};
        goodInput[1] = new int[] {4, 5, 6};
        goodInput[2] = new int[] {7, 8, 0};
        Board goodB = new Board(goodInput);
        assert(goodB.isGoal() == true);

    }

}