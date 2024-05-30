package week1_percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    boolean[] flatBooleanGrid;
    private int n;
    private int vt;
    private int vb;
    private final int[] flatGrid;
    private final int gridSize;
    private int openSitesCounter;
    WeightedQuickUnionUF uf;

    public void checkArgument(int row, int col) {
        if ((row < 1 || row > n) || (col < 1 || col > n)) {
            throw new IllegalArgumentException();
        }
    }

    //    WeightedQuickUnionUF uf = new WeightedQuickUnionUF();
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 0) throw new IllegalArgumentException();
        this.n = n;
        vt = 0;
        vb = n * n + 1;
        gridSize = n * n + 2;

        flatGrid = new int[gridSize];
        flatBooleanGrid = new boolean[gridSize];
        //Открываем виртуальные верх и низ
        flatBooleanGrid[vt] = true;
        flatBooleanGrid[vb] = true;

        //Перевод в плоскую сетку
        int counter2 = 0;
        for (int i = 0; i < flatGrid.length; i++) {
            flatGrid[i] = counter2++;
        }
        //Создание UF
        uf = new WeightedQuickUnionUF(gridSize);
    }

    private int toFlat(int row, int col) {
        return (row - 1) * n + col;
    }

    // opens the site (row, col) if it is not open already
    // Когда открывается site, надо проверить открыт ли соседний site слева, справа, сверху или снизу, если открыт, то необходимо их объединить uf.union
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            checkArgument(row, col);
            flatBooleanGrid[toFlat(row, col)] = true;
            checkAndUnion(row, col);
            openSitesCounter++;
        }
    }

    private void checkAndUnion(int row, int col) {
        checkLeftAndUnion(row, col);
        checkRightAndUnion(row, col);
        checkUpAndUnion(row, col);
        checkDownAndUnion(row, col);
    }

    //Проверить site слева и объединить в uf, если он открыт
    private void checkLeftAndUnion(int row, int col) {
        if (col - 1 > 0) {
            if (isOpen(row, col - 1))
                uf.union(flatGrid[toFlat(row, col)], flatGrid[toFlat(row, col - 1)]);
        }
    }

    //Проверить site справа и объединить в uf, если он открыт
    private void checkRightAndUnion(int row, int col) {
        if (col + 1 <= n) {
            if (isOpen(row, col + 1))
                uf.union(flatGrid[toFlat(row, col)], flatGrid[toFlat(row, col + 1)]);
        }
    }

    //Проверить site сверху и объединить в uf, если он открыт
    private void checkUpAndUnion(int row, int col) {
        if (row - 1 == 0) { //vt case
            uf.union(flatGrid[toFlat(1, col)], flatGrid[0]);
        } else if (row - 1 > 0) {
            if (isOpen(row - 1, col)) {
                uf.union(flatGrid[toFlat(row, col)], flatGrid[toFlat(row - 1, col)]);
            }
        }
    }

    //Проверить site снизу и объединить в uf, если он открыт
    private void checkDownAndUnion(int row, int col) {
        if (row == n) { //vb case
            uf.union(flatGrid[toFlat(n, col)], flatGrid[n * n + 1]);
        } else if (row + 1 <= n) {
            if (isOpen(row + 1, col)) {
                uf.union(flatGrid[toFlat(row, col)], flatGrid[toFlat(row + 1, col)]);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkArgument(row, col);
        return flatBooleanGrid[toFlat(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkArgument(row, col);
        return uf.connected(flatGrid[toFlat(row, col)], flatGrid[0]);
    }


    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSitesCounter;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(flatGrid[vb], flatGrid[vt]);
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}
