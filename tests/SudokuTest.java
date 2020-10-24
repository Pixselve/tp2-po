import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {

  @Test
  void presentLigne() {
    assertTrue(Sudoku.presentLigne(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}}, 1, 0));
    assertTrue(Sudoku.presentLigne(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}}, 2, 0));
    assertTrue(Sudoku.presentLigne(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}}, 3, 0));

    assertFalse(Sudoku.presentLigne(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}}, 4, 0));
    assertFalse(Sudoku.presentLigne(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}}, 5, 0));
    assertFalse(Sudoku.presentLigne(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}}, 6, 0));

    assertTrue(Sudoku.presentLigne(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}}, 4, 1));
    assertTrue(Sudoku.presentLigne(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}}, 5, 1));
    assertTrue(Sudoku.presentLigne(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}}, 6, 1));
  }

  @Test
  void presentColonne() {
    assertTrue(Sudoku.presentColonne(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}}, 1, 0));
    assertTrue(Sudoku.presentColonne(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}}, 4, 0));
    assertTrue(Sudoku.presentColonne(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}}, 7, 0));

    assertFalse(Sudoku.presentColonne(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}}, 2, 0));
    assertFalse(Sudoku.presentColonne(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}}, 5, 0));
    assertFalse(Sudoku.presentColonne(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}}, 8, 0));

    assertTrue(Sudoku.presentColonne(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}}, 2, 1));
    assertTrue(Sudoku.presentColonne(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}}, 5, 1));
    assertTrue(Sudoku.presentColonne(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}}, 8, 1));
  }

  @Test
  void presentRegion() {
    int[][] grid3 = new int[][]{
        new int[]{1, 2, 3},
        new int[]{4, 5, 6},
        new int[]{7, 8, 0}
    };
    int[][] grid6 = new int[][]{
        new int[]{1, 0, 0, 0, 0, 0},
        new int[]{0, 2, 0, 0, 0, 0},
        new int[]{0, 0, 3, 0, 0, 0},

        new int[]{0, 0, 0, 4, 0, 0},
        new int[]{0, 0, 0, 0, 5, 0},
        new int[]{0, 0, 0, 0, 0, 6},

    };


    assertTrue(Sudoku.presentRegion(grid3, 1, 0, 0));
    assertTrue(Sudoku.presentRegion(grid3, 1, 1, 1));
    assertTrue(Sudoku.presentRegion(grid3, 1, 2, 2));

    assertFalse(Sudoku.presentRegion(grid3, 9, 0, 0));
    assertFalse(Sudoku.presentRegion(grid3, 9, 1, 1));
    assertFalse(Sudoku.presentRegion(grid3, 9, 2, 2));


    assertTrue(Sudoku.presentRegion(grid6, 1, 0, 0));
    assertTrue(Sudoku.presentRegion(grid6, 2, 1, 1));
    assertTrue(Sudoku.presentRegion(grid6, 3, 2, 2));
    assertTrue(Sudoku.presentRegion(grid6, 4, 3, 3));
    assertTrue(Sudoku.presentRegion(grid6, 5, 4, 4));
    assertTrue(Sudoku.presentRegion(grid6, 6, 5, 5));

    assertFalse(Sudoku.presentRegion(grid6, 4, 0, 3));
    assertFalse(Sudoku.presentRegion(grid6, 5, 1, 4));
    assertFalse(Sudoku.presentRegion(grid6, 6, 2, 5));

  }

}