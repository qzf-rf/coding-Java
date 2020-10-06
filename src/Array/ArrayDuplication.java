package Array;

// 数组中重复的数字
public class ArrayDuplication {
    // 时间复杂度 O(n)，空间复杂度 O(1)
    // 思路：将值为 i 的元素调整到第 i 个位置上进行求解（类Hash）
    public int findRepeatNumber(int[] nums) {
        int arrayLength = nums.length;
        int duplication;
        if (arrayLength <= 0) {
            return -1;
        }

        for (int i = 0; i < arrayLength; i++) {
            // 若值为 i 的元素已调整到第 i 位，则跳过交换
            while (nums[i] != i) {
                // 若第 nums[i] 位已有值为 nums[i] 的元素，则找到重复数字
                if (nums[i] == nums[nums[i]]) {
                    duplication = nums[i];
                    return duplication;
                }
                // 将值为 nums[i] 的元素调整到 nums[i] 位上
                swap(nums, i, nums[i]);
            }
        }
        return -1;
    }

    // 交换 i 位和 j 位元素
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        ArrayDuplication ad = new ArrayDuplication();
        int duplication = ad.findRepeatNumber(nums);
        System.out.println(duplication);
    }
}

