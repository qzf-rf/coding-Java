package Array;

// 二维数组中的查找
public class NumIn2DArray {
    // 时间复杂度 O(M + N)，空间复杂度 O(1)
    // 思路：选取数组中右上角的数字，缩小查找区间（类似二分法取中值）
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        boolean found = false;

        // 空数组处理
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        // 获取二维数组行和列数
        int rows = matrix.length;
        int columns = matrix[0].length;

        int row = 0;
        // 从右上角即第一行第m列开始查找
        int column = columns - 1;

        while (row < rows && column >= 0) {
            // 右上角=target，查找成功
            if (matrix[row][column] == target) {
                found = true;
                break;
            }
            // 右上角>target，剔除该列
            else if (matrix[row][column] > target) {
                column--;
            }
            // 右上角<target，剔除该行
            else {
                row++;
            }
        }
        return found;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target = 5;
        NumIn2DArray na = new NumIn2DArray();
        boolean found = na.findNumberIn2DArray(matrix, target);
        System.out.println(found);
    }

}

