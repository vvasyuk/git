/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private boolean[] openSites;
    private int n;
    private int top;
    private int bot;
    private int openSitesCnt;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        uf = new WeightedQuickUnionUF(n * n + 2);
        openSites = new boolean[n*n];
        this.n = n;
        top = coordinatesToIdx(n, n) + 1;
        bot = coordinatesToIdx(n, n) + 2;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int idx = coordinatesToIdx(row, col);

        if (isOpen(row, col)) {
            return;
        }

        openSites[idx] = true;
        openSitesCnt++;

        if (row == 1) {
            uf.union(idx, top);
        }

        if (row == n) {
            uf.union(idx, bot);
        }

        // check top
        if (row > 1 && isOpen(row-1, col)) {
            uf.union(idx, coordinatesToIdx(row-1, col));
        }
        // check bot
        if (row < n && isOpen(row+1, col)) {
            uf.union(idx, coordinatesToIdx(row+1, col));
        }
        // check left
        if (col > 1 && isOpen(row, col-1)) {
            uf.union(idx, coordinatesToIdx(row, col-1));
        }
        // check right
        if (col < n && isOpen(row, col+1)) {
            uf.union(idx, coordinatesToIdx(row, col+1));
        }
    }

    private void validate(int row, int  col) {
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
        return openSites[coordinatesToIdx(row, col)];
    }

     // is the site (row, col) full?
     public boolean isFull(int row, int col) {
        validate(row, col);
        int idx = coordinatesToIdx(row, col);
        return uf.connected(top, idx);
     }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSitesCnt;
    }

     // does the system percolate?
     public boolean percolates() {
        return uf.connected(top, bot);
     }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(1, 1);
        System.out.println("open 1,1: " + p.isOpen(1, 1));
        System.out.println("full 1,1: " + p.isFull(1, 1));

        p.open(1, 3);
        System.out.println("open 1,3: " + p.isOpen(1, 3));
        System.out.println("full 1,3: " + p.isFull(1, 3));

        p.open(1, 2);
        System.out.println("open 1,2: " + p.isOpen(1, 2));
        System.out.println("full 1,2: " + p.isFull(1, 2));

        p.open(2, 2);
        System.out.println("open 2,2: " + p.isOpen(2, 2));
        System.out.println("full 2,2: " + p.isFull(2, 2));

        p.open(3, 2);
        System.out.println("open 3,2: " + p.isOpen(3, 2));
        System.out.println("full 3,2: " + p.isFull(3, 2));
        System.out.println(p.percolates());
    }
}

