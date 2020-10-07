package Array;

// 重塑矩阵
public class ReshapeMatrix {
    // 时间复杂度 O(r * c)，空间复杂度 O(r * c)
    // 思路：重构过程中行遍历的元素位置不变
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int m = nums.length, n = nums[0].length;

        // 转换不可行，输出原矩阵
        if (m * n != r * c) {
            return nums;
        }

        // 将 index 设为原数组 nums 行遍历的第 index 个元素（index < m×n），则重构后其依然为第 index 个元素
        int index = 0;
        int[][] reshapedNums = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                reshapedNums[i][j] = nums[index / n][index % n];
                index++;
            }
        }
        return reshapedNums;
    }
}
