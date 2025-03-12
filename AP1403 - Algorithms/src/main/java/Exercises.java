public class Exercises {
    
    public int[] productIndices(int[] values, int target) {
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                if (values[i] * values[j] == target) {
                    return new int[]{i, j}; // i < j, So it is already in ascending order
                }
            }
        }
        return new int[]{-1, -1};
    }

    public int[] spiralTraversal(int[][] values, int rows, int cols) {
        int[] res = new int[rows * cols];
        int index = 0;
        int top = 0, bottom = rows - 1, left = 0, right = cols - 1;

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++)
                res[index++] = values[top][i]; // right
            top++;
            for (int i = top; i <= bottom; i++)
                res[index++] = values[i][right]; // down
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left; i--)
                    res[index++] = values[bottom][i]; // left
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--)
                    res[index++] = values[i][left]; // up
                left++;
            }
        }

        return res;
    }

    public int[][] intPartitions(int n) {
        int max_partitions = countPartitions(n, n);
        int[][] res = new int[max_partitions][];
        int count = 0;
        int[] current = new int[n];

        generatePartitions(n, n, 0, res, current, count);
        return res;
    }

    // Private function to calculate the maximum possible number of partitions
    private int countPartitions(int n, int max) {
        if (n == 0)
            return 1;
        int count = 0;
        for (int i = Math.min(n, max); i >= 1; i--) {
            count += countPartitions(n - i, i);
        }
        return count;
    }

    // Private function to genarate all possible partitions
    private int generatePartitions(int n, int max, int index, int[][] res, int[] current, int count) {
        if (n == 0) {
            int[] partition = new int[index];
            for (int i = 0; i < index; i++) {
                partition[i] = current[i];
            }
            res[count] = partition;
            return count + 1;
        }
        for (int i = Math.min(n, max); i >= 1; i--) {
            current[index] = i;
            count = generatePartitions(n - i, i, index + 1, res, current, count);
        }
        return count;
    }

    public static void main(String[] args) {
        Exercises exercises = new Exercises();

        // Test productIndices
        int[] productResult = exercises.productIndices(new int[]{1, 2, 3, 4}, 8);
        System.out.println("Product Indices: {" + productResult[0] + ", " + productResult[1] + "}");

        // Test spiralTraversal
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[] spiralResult = exercises.spiralTraversal(matrix, 3, 3);
        System.out.print("Spiral Traversal: {");
        for (int i = 0; i < spiralResult.length; i++) {
            System.out.print(spiralResult[i] + (i < spiralResult.length - 1 ? ", " : ""));
        }
        System.out.println("}");

        // Test intPartitions
        int[][] partitions = exercises.intPartitions(4);
        System.out.println("Integer Partitions of 4:");
        for (int i = 0; i < partitions.length; i++) {
            System.out.print("{");
            for (int j = 0; j < partitions[i].length; j++) {
                System.out.print(partitions[i][j] + (j < partitions[i].length - 1 ? ", " : ""));
            }
            System.out.println("}");
        }
    }
}