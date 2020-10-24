public class Sudoku {
  /*
   *
   * L2 - PO, TP n 2
   * Auteur : Mael KERICHARD
   *
   */
  static final int n = 3;    // taille des regions
  /*
   * Terminologie
   *
   * m est un plateau (de sudoku) si
   * 	- m est un int [][] ne contenant que des entiers compris entre 0 et 9
   * 	- m.length = n^2
   *  - m[i].length = n^2 pour tous les i de 0 a n^2-1
   *
   */

  static String enClair(int[][] m) {
    /*
     * Prerequis : m est un plateau de sudoku
     * Resultat : une chaine dont l'affichage permet de visualiser m
     *
     */
    String r = "";
    for (int i = 0; i < n * n; i++) {
      for (int j = 0; j < n * n; j++) {
        r = r + m[i][j] + " ";
        if (j % n == n - 1) {
          r = r + "  ";
        }
      }
      if (i % n == n - 1) {
        r = r + "\n";
      }
      r = r + "\n";
    }
    r = r + " ";
    return r;
  } // enClair

  static int[][] aPartirDe(String s) {
    /*
     * Prerequis : s est une chaine contenant au moins n^4 chiffres decimaux
     * Resultat : un plateau de sudoku initialise avec les n^4 premiers chiffres
     * decimaux de s (les chiffres sont consideres comme ranges par lignes).
     */
    int[][] m = new int[n * n][n * n];
    int k = 0;
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m[i].length; j++) {
        while ("0123456789".indexOf(s.charAt(k)) == -1) {
          k++;
        }
        m[i][j] = (int) s.charAt(k) - (int) '0';
        k++;
      }
    }
    return m;

  } // aPartirDe

  /**
   * @param m un plateau de sudoku
   * @param v un entier compris entre 1 et n^2
   * @param i un entier compris entre 0 et n^2-1
   * @return true ssi dans m, v est present dans la ligne i
   */
  static boolean presentLigne(int[][] m, int v, int i) {
    for (int inLine : m[i]) {
      if (v == inLine) return true;
    }
    return false;
  } // presentLigne

  /**
   * @param m un plateau de sudoku
   * @param v un entier compris entre 1 et n^2
   * @param j un entier compris entre 0 et n^2-1
   * @return true ssi dans m, v est present dans la colonne j
   */
  static boolean presentColonne(int[][] m, int v, int j) {
    for (int[] lines : m) {
      if (lines[j] == v) return true;
    }
    return false;
  } // presentColonne

  /**
   * @param m un plateau de sudoku
   * @param v un entier compris entre 1 et n^2
   * @param i un entier compris entre 0 et n^2-1
   * @param j un entier compris entre 0 et n^2-1
   * @return true ssi dans m, v est present dans la region contenant la case <i, j>
   */
  static boolean presentRegion(int[][] m, int v, int i, int j) {
    for (int iLoop = (i / n) * n; iLoop < (i / n) * n + n; iLoop++) {
      for (int jLoop = (j / n) * n; jLoop < ((j / n) * n + 3); jLoop++) {
        if (m[iLoop][jLoop] == v) return true;
      }
    }
    return false;
  } // presentRegion

  /**
   * @param m un plateau de sudoku avec m[i][j] = 0
   * @param i un entier compris entre 0 et n^2-1
   * @param j un entier compris entre 0 et n^2-1
   * @return un tableau r de longueur n^2+1 tel que, dans la tranche r[1..n^2] r[v] indique si v peut etre place en <i, j>
   */
  static boolean[] lesPossiblesEn(int[][] m, int i, int j) {
    boolean[] result = new boolean[n * n + 1];
    for (int boolIndex = 1; boolIndex < result.length; boolIndex++) {
      if (!presentLigne(m, boolIndex, i) && !presentColonne(m, boolIndex, j) && !presentRegion(m, boolIndex, i, j))
        result[boolIndex] = true;
    }
    return result;
  } // lesPossiblesEn

  static String enClair(boolean[] t) {
    /*
     * Prerequis : t.length != 0
     * Resultat :
     * une chaine contenant tous les indices i de la tranche [1..t.length-1] tels
     * que t[i] soit vrai
     */
    String r = "{";
    for (int i = 1; i < t.length; i++) {
      if (t[i]) {
        r = r + i + ", ";
      }
    }
    if (r.length() != 1) {
      r = r.substring(0, r.length() - 2);
    }
    return r + "}";
  } // enClair

  /**
   * @param m un plateau de sudoku
   * @param i un entier compris entre 0 et n^2-1
   * @param j un entier compris entre 0 et n^2-1
   * @return v si la seule valeur possible pour <i, j> est v ; -1 	dans les autres cas
   */
  static int toutSeul(int[][] m, int i, int j) {
    boolean[] possibleValues = lesPossiblesEn(m, i, j);
    int index = -1;
    int count = 0;
    for (int i1 = 1; i1 < possibleValues.length; i1++) {
      if (possibleValues[i1]) {
        if (count > 1) {
          return -1;
        } else {
          index = i1;
          count++;
        }
      }
    }
    return index;
  } // toutSeul

  static void essais(String grille) {
    /*
     * Prerequis : grille represente une grille de sudoku
     * (les chiffres sont consideres comme ranges par lignes)
     *
     * Effet :
     * 1) affiche en clair la grille
     * 2) affecte, tant que faire se peut, toutes les cases pour lesquelles il n'y
     *    a qu'une seule possibilite
     * 3) affiche en clair le resultat final
     */
    int[][] m = aPartirDe(grille);
    System.out.println("Probleme\n\n" + enClair(m));

    int count;
    do {
      count = 0;
      for (int i = 0; i < m.length; i++) {
        for (int j = 0; j < m[i].length; j++) {
          if (m[i][j] == 0) {
            int index = toutSeul(m, i, j);
            if (index >= 0) {
              m[i][j] = index;
              System.out.println("(" + i + ", " + j + ") --> " + index);
              count++;
            }
          }

        }
      }
    } while (count > 0);
    System.out.println("Il se peut qu'on ait avance\n\n" + enClair(m));
  } // essais


  public static void main(String[] args) {
    String grille1 =
        "040 001 006 \n" +
            "007 900 800 \n" +
            "190 086 074 \n" +
            "            \n" +
            "200 690 010 \n" +
            "030 405 090 \n" +
            "060 017 003 \n" +
            "            \n" +
            "910 750 042 \n" +
            "008 002 700 \n" +
            "400 300 080   ";
    String grille2 =
        "030 000 006 \n" +
            "000 702 300 \n" +
            "104 038 000 \n" +
            "            \n" +
            "300 020 810 \n" +
            "918 000 265 \n" +
            "062 050 007 \n" +
            "            \n" +
            "000 140 708 \n" +
            "001 209 000 \n" +
            "800 000 020   ";

    essais(grille1);
//    essais(grille2);
  }

}