/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF uf;
    boolean[] openSites;
    int n;
    int top;
    int bot;
    int openSitesCnt;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        uf = new WeightedQuickUnionUF(n * n + 2);
        openSites = new boolean[n*n];
        this.n = n;
        top = n*n+1;
        bot = n*n+2;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int idx = coordinatesToIdx(row, col);

        if(isOpen(row,col)) {
            return;
        }

        openSites[idx] = true;

        if(row == 1) {
            uf.union(idx, top);
        }

        if(row == n) {
            uf.union(idx, bot);
        }

        //check top
        if(row > 1 && isOpen(row-1, col)) {
            uf.union(idx, coordinatesToIdx(row-1, col));
        }
        //check bot
        if(row < n && isOpen(row+1, col)) {
            uf.union(idx, coordinatesToIdx(row+1, col));
        }
        //check left
        if(col > 1 && isOpen(row, col-1)) {
            uf.union(idx, coordinatesToIdx(row, col-1));
        }
        //check right
        if(col < n && isOpen(row, col+1)) {
            uf.union(idx, coordinatesToIdx(row, col+1));
        }
    }

    public void validate(int row, int  col) {
        if (row < 1 ||
                row > this.n ||
                col < 1 ||
                col > this.n
        ) {
            throw new IllegalArgumentException("coordinates " + row + "," + col + " is not between 1 and " + n);
        }
    }

    private int coordinatesToIdx(int row, int col) {
            return (n*(row-1)+col-1);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return openSites[coordinatesToIdx(row,col)];
    }
    //
    // // is the site (row, col) full?
    // public boolean isFull(int row, int col){
    //
    // }
    //
    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSitesCnt;
    }
    //
    // // does the system percolate?
    // public boolean percolates(){
    //
    // }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        //System.out.println("3,3- " + p.coordinatesToIdx(3,3));

    }
}

