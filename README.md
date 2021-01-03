# **学代码，从最简单的开始**

## **代码规范**

### **1. 代码命名规范**

|类型|示例|
|:--:|:--------:|
|类名|ThisIsClass|
|变量名|thisIsValue|
|函数名|thisIsValue|
|常量名|THIS_IS_CONSTANT|

<br>

### **2. 代码书写规范**

#### 1. 合并判断条件，减少 if 语句

```
if (sortType == SortType.descend)
```
优化为
```
if (num > data[i] && sortType == SortType.descend)
```

<br>

#### 2. 简化注释，尽量通过代码阐述

> 若编程语言足够有表达力，就不需要注释，尽量通过代码来阐述
>
> **去掉下面复杂的注释，只需要创建一个与注释所言同一事物的函数即可**
>
> ```
> // check to see if the employee is eligible for full benefits
> if ((employee.flags & HOURLY_FLAG) && (employee.age > 65))
> ```
> 应替换为
> ```
> if (employee.isEligibleForFullBenefits())
> ```

<br>

#### 3. **Java 5** 中引入主要用于**数组**的**增强型 for 循环**

```
for (int num : nums)
```
等价于
```
for (int i = 0; i < nums.length; i++) {
    nums[i] ...
}
```

<br>

## **总结思路**

#### 1. 谨慎处理边界问题（***空数组***等）

#### 2. 从变化中设计**不变**的量
- **题目 283 移动零**：后移 0 → 前移非 0
- **题目 566 重塑矩阵**：index 表示重构矩阵前后的第 index 个元素

#### 3. **类比**经典算法
- **题目 03 数组中重复的数字**：n 个元素，取值范围为 0~n-1 → **类 Hash** 寻找元素冲突
- **题目 04 二维数组中的查找**：行和列均递增 → **类二分法**缩小查找范围
 
<br>

## **数据结构与算法之美**

### **1. 数组**

> #### **题目 1：大小固定的有序数组，支持动态增删改**

- **错误 1**：插入函数搜索插入位置时 index 初值为 -1，***未考虑空数组的情况，导致数组越界***


```
// 考虑空数组，因此 index 设为 0
if (count == 0) {
    index = 0;
}
```

- **错误 2**：降序数组只有元素 7 时，插入 4 的index值为 0，反而插入到了 7 前面，***未考虑数组元素比较结束，插入数组末尾的情况***
```
// 考虑插入数组末尾的情况
if (i == count) {
    index = count;
}
```
***查找成功后没有 break 循环***，导致此判断条件失效

- **总结**：**未考虑插入首位和末位的特殊情况（即*边界*）**

<br>

> #### **题目 2：支持动态扩容的无序数组，按索引增删改查**

1. 无参构造函数**调用**有参构造函数
2. 数组已满则扩容，不足1/4则缩容为1/2，扩容和缩容调用同一函数

<br>

> #### **题目 3：两个有序数组合并为一个有序数组**

- **错误**：数组中有 count 个元素，但最后一个元素的位置是 count-1

<br>

## **剑指Offer**

> #### **题目 03：数组中重复的数字**
> 在一个长度为 n 的数组 nums 里的所有数字都在 0~n-1 的范围内

- **思路1（×）**：HashSet的add方法，元素重复则返回false

- **思路2（√）**：将值为 i 的元素调整到第 i 个位置上进行求解（***类Hash***）
> 时间复杂度 O(n)，空间复杂度 O(1)

- **注意事项**：
1. 某处缺少返回值
2. 先初始化类，才能在main函数中调用

<br>

> #### **题目 04：二维数组中的查找**
> 行和列均递增

|a[i][1]|a[i][2]|a[i][3]|a[i][4]|
|:-:|:-:|:-:|:-:|
|1|2|8|9|
|2|4|9|12|
|4|7|10|13|
|6|8|11|15|
 
- **思路**：选取数组中右上角的数字，缩小查找区间（***类似二分法取中值***）
> 时间复杂度 O(M + N)，空间复杂度 O(1)
1. `if` 该数字=要查找的数字，查找过程结束
2. `else if` 该数字>要查找的数字，剔除这个数字所在的列
3. `else if` 该数字<要查找的数字，剔除这个数字所在的行

- **注意事项**：
1. **空数组**求长度越界
2. 判断空数组的条件写在一起
```
if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
```

<br>

## **LeetCode**

### **1. 数组与矩阵**

> #### **题目 283：移动零**
> 将数组 nums 中的 0 移动至数组末尾

- **思路**：遍历数组，跳过 0 元素，即前移非 0 元素，最后根据 index 位置在末尾补 0（***移动 0 至末尾 → 移动非 0 至前面***）
> 时间复杂度 O(n)，空间复杂度 O(1)

<br>

> #### **题目 566：重塑矩阵**
> 将 m×n 的矩阵 nums 重构为 r×c 的矩阵 reshapedNums

- **思路**：重构过程中**行遍历**的元素位置不变，将 index 设为原数组 nums 行遍历的第 index 个元素（index < m×n），则重构后其依然为第 index 个元素
1. 2×2 → 1×4：reshapedNums[i][j] = nums[index / 2][index % 2]
2. 1×6 → 2×3：reshapedNums[i][j] = nums[index / 6][index % 1]
3. m×n → r×c：reshapedNums[i][j] = nums[index / n][index % n]

> 时间复杂度 O(r * c)，空间复杂度 O(r * c)

<br>

> #### **题目 485：最大连续 1 的个数**
> 数组中只包含 0 和 1

- **思路**：遍历数组，若元素为 1 则计数加 1，若元素为 0 则重新计数，**记录计数的最大值**

- **注意事项**：`count = num == 1 ? count + 1 : 0` 中运算符优先级顺序为 `+` > `==` > `?:` > `=`，因此**不需加括号**

> 时间复杂度 O(n)，空间复杂度 O(1)