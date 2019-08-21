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

//    // number of tiles out of place
//    public int hamming()
//
//    // sum of Manhattan distances between tiles and goal
//    public int manhattan()
//
//    // is this board the goal board?
//    public boolean isGoal()
//
//    // does this board equal y?
//    public boolean equals(Object y)
//
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
        System.out.println(b.toString());

    }

}