package Array;

import java.util.Scanner;


// 枚举变量，决定数组排序方式
enum SortType {
    ascend, descend
}


/**
 * 大小固定的有序数组，支持动态增删改
 * 枚举变量 SortType：默认升序ascend，降序descend
 * <p>
 * 成员变量：
 * 存储数据 data
 * 数组容量 capacity
 * 数组实际元素个数 count
 * <p>
 * 有参构造函数：创建指定容量 n 的数组
 * <p>
 * 合并函数 combine：
 * 1. 创建两个数组容量之和的数组
 * 2. 设置两个指针，有序复制两个数组
 * 3. 创建新的有序数组类
 * <p>
 * 插入函数 insert：
 * 1. 判断数组是否已满，已满则输出提示
 * 2. 通过比较，确定插入元素位置
 * 3. 移动后续元素，插入元素
 * <p>
 * 删除函数 delete：
 * 1. 查找值匹配的元素
 * 2. 移动后续元素，删除元素
 * <p>
 * 查找函数 search：查找值匹配的元素，返回位置
 * <p>
 * 修改函数 update：
 * 1. 查找值匹配的元素
 * 2. 修改该元素
 * <p>
 * 输出函数 printAll：输出数组中全部元素
 */

public class StaticArray {

    // 定义整型数组data存储数据
    public int[] data;
    // 定义数组容量
    private int capacity;
    // 定义数组实际元素个数
    private int count;
    // 定义排序方式
    private SortType sortType;


    // 构造函数，创建容量为 n 的数组
    public StaticArray(int n, SortType sort) {
        this.data = new int[n];
        this.capacity = n;
        // 空数组
        this.count = 0;
        this.sortType = sort;
    }

    // 获取容量
    public int getCapacity() {
        return capacity;
    }

    // 获取数组元素个数
    public int getCount() {
        return count;
    }

    // 设置数组元素个数
    public void setCount(int num) {
        count = num;
    }

    // 合并函数，将两个有序数组合并为新的有序数组
    // 时间复杂度为 O(m+n)
    public StaticArray combine(StaticArray secondArray) {
        int newCapacity = capacity + secondArray.getCapacity();
        int[] newData = new int[newCapacity];

        int pArrayA = 0, pArrayB = 0;
        int newCount = 0;
        while (pArrayA < count && pArrayB < secondArray.getCount()) {
            // 升序数组复制两个数组中的较小值到新数组中
            // 降序数组复制两个数组中的较大值到新数组中
            if ((data[pArrayA] <= secondArray.data[pArrayB] && sortType == SortType.ascend)
                    || (data[pArrayA] >= secondArray.data[pArrayB] && sortType == SortType.descend)) {
                newData[newCount] = data[pArrayA];
                newCount++;
                pArrayA++;
            } else {
                newData[newCount] = secondArray.data[pArrayB];
                newCount++;
                pArrayB++;
            }
        }

        // 复制余下的数组元素
        while (pArrayA < count) {
            newData[newCount] = data[pArrayA];
            newCount++;
            pArrayA++;
        }
        while (pArrayB < secondArray.getCount()) {
            newData[newCount] = data[pArrayB];
            newCount++;
            pArrayB++;
        }

        // 创建新的有序数组类
        StaticArray combineArray = new StaticArray(newCapacity, sortType);
        combineArray.data = newData;
        combineArray.setCount(newCount);
        return combineArray;
    }

    // 插入函数，将数据插入有序数组的合法位置
    // 时间复杂度为 O(n)
    public boolean insert(int num) {
        // 若数组空间已满
        if (count == capacity) {
            System.out.println("没有可插入的位置");
            return false;
        }

        // 与数组内元素挨个比较，确定插入位置
        int index = -1;

        // 考虑空数组，因此 index 设为 0
        if (count == 0) {
            index = 0;
        }

        int i;

        for (i = 0; i < count; i++) {
            // 升序数组的插入位置
            if (num < data[i] && sortType == SortType.ascend) {
                index = i;
                break;
            }
            // 降序数组的插入位置
            else if (num > data[i] && sortType == SortType.descend) {
                index = i;
                break;
            }
        }

        // 考虑插入数组末尾的情况
        if (i == count) {
            index = count;
        }

        // 在 index 位置插入元素
        for (i = count - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = num;
        count++;
        return true;
    }


    // 删除函数，将数组中匹配值的第一个元素删除
    // 时间复杂度为 O(n)
    public boolean delete(int num) {
        // 数组中不存在值匹配的元素
        int index = search(num);
        if (index == -1) {
            System.out.println("数组中不存在该元素");
            return false;
        }

        // 删除 index 位置上的元素
        for (int i = index + 1; i < count; i++) {
            data[i - 1] = data[i];
        }
        count--;
        return true;
    }


    // 查找函数，查找数组中值匹配的元素，返回位置
    // 时间复杂度为 O(n)
    public int search(int num) {
        for (int i = 0; i < count; i++) {
            if (data[i] == num) {
                return i;
            }
        }
        return -1;
    }


    // 修改函数，将值匹配的元素修改为新值
    // 时间复杂度为 O(n)
    public boolean Update(int before, int later) {
        // 数组中不存在值匹配的元素
        int index = search(before);
        if (index == -1) {
            System.out.println("数组中不存在该元素");
            return false;
        }
        data[index] = later;
        return true;
    }

    // 输出函数，输出数组中全部元素
    public void printAll() {
        if (count == 0) {
            System.out.println("数组为空");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(data[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("指定容量：");
        int capacity = scan.nextInt();
        if (capacity <= 0) {
            System.out.println("输入不合法");
            return;
        }

        System.out.println("排序方式：\n1：升序\n2：降序");
        SortType sort;
        int sortType = scan.nextInt();
        if (sortType == 1) {
            sort = SortType.ascend;
        } else if (sortType == 2) {
            sort = SortType.descend;
        } else {
            System.out.println("输入不合法");
            return;
        }

//        StaticArray array = new StaticArray(capacity, sort);
//        array.printAll();
//        array.insert(7);
//        array.insert(4);
//        array.insert(6);
//        array.printAll();
//        array.delete(5);
//        array.delete(7);
//        array.printAll();

        StaticArray arrayA = new StaticArray(capacity, sort);
        arrayA.insert(1);
        arrayA.insert(3);
        arrayA.insert(5);
        arrayA.insert(8);

        StaticArray arrayB = new StaticArray(capacity, sort);
        arrayB.insert(2);
        arrayB.insert(4);
        arrayB.insert(6);
        arrayB.insert(7);

        StaticArray combineArray = arrayA.combine(arrayB);
        System.out.println(combineArray.getCount());
        System.out.println(combineArray.getCapacity());
        combineArray.printAll();
    }

}
