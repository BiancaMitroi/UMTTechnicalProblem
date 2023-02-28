public class Solver {
    public static boolean solve(int[] A) {
        int n = A.length;
        if (n == 1) return false; // if A has only 1 element, we cannot split it
        int sum = 0;
        for(int i : A)
            sum += i;
        // The xElemFormSumY tells if x elem of A (first index) can form a sum Y (second index).
        // If yes, then on that position will be true, else false
        boolean[][] xElemFormSumY = new boolean[n / 2 + 1][sum + 1];
        // the matrix has n/2 + 1 lines because this is of interest, the others lines are deduced, they
        // represent the complement of the main subsets
        xElemFormSumY[0][0] = true; // we can form the 0 sum with 0 elements
        // the 0 column will remain false except the element xElemFormSumY[0][0] because we cannot
        // form the sum 0 with at least 1 element
        for (int value : A) {
            for (int j = value; j <= sum; j++) {
                for (int k = 1; k <= n / 2; k++) {
                    xElemFormSumY[k][j] |= xElemFormSumY[k - 1][j - value];
                }
            }
        }
        // The matrix xElemFormSumY will be traveled A arity times, so it will be updated as follows:
        // if the sum y could not be formed with the elements of a subset of A which has arity x and
        // value as an element, we should check if the sum y - value could be formed with x - 1 elements from the
        // same subset without value. If any of these statements is true, then xElemFormSumY[x][y] is true.
        for (int k = 1; k <= n / 2; k++) {
            if ((sum * k) % n == 0) {
                int j = sum * k / n; // the sum that should be formed from k elements from A
                // so that the average of them and from the remained elements can be the same
                if (xElemFormSumY[k][j]) {
                    return true;
                }
            }
        }
        // if the program flow gets here, it means the solve function could not find any partition that
        // satisfies the requirements
        return false;
    }
}