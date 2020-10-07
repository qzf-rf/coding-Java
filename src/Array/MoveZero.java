package Array;

// 移动零
public class MoveZero {
    // 时间复杂度 O(n)，空间复杂度 O(1)
    // 思路：遍历数组，跳过 0 元素，即前移非 0 元素，最后根据 index 位置在末尾补 0
    // 移动 0 至末尾 → 移动非 0 至前面
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[index++] = num;
            }
        }
        while (index < nums.length) {
            nums[index++] = 0;
        }
    }
}
