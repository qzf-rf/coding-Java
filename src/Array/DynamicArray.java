package Array;

import java.util.Scanner;

/**
 * 支持动态扩容的无序数组，按索引增删改查
 * <p>
 * 成员变量：
 * 存储数据 data
 * 数组容量 capacity
 * 数组实际元素个数 count
 * <p>
 * 有参构造函数：创建初始容量 n 的数组
 * 无参构造函数：默认容量 10
 * <p>
 * 插入函数 insert：
 * 1. 判断数组是否已满，已满则扩容
 * 2. 移动后续元素，插入元素
 * <p>
 * 删除函数 delete：
 * 1. 移动后续元素，删除 index 位置上的元素
 * 2. 若元素不满数组 1/4，则缩容为 1/2
 * <p>
 * 扩容/缩容函数 resize：复制数组元素到新数组
 * <p>
 * 查找函数 search：查找 index 位置上的元素值
 * <p>
 * 修改函数 ppdate：修改 index 位置上的元素
 * <p>
 * 输出函数 printAll：输出数组中全部元素
 */

public class DynamicArray {

    // 定义整型数组data存储数据
    public int[] data;
    // 定义数组容量
    private int capacity;
    // 定义数组实际元素个数
    private int count;


    // 获取容量
    public int getCapacity() {
        return capacity;
    }

    // 获取数组元素个数
    public int getCount() {
        return count;
    }


    // 有参构造函数，创建容量为 n 的数组
    public DynamicArray(int n) {
        this.data = new int[n];
        this.capacity = n;
        // 空数组
        this.count = 0;
    }

    // 无参构造函数，调用有参构造函数创建默认容量为 10 的数组
    public DynamicArray() {
        this(10);
    }


    // 插入函数，将数据插入 index 位置
    // 时间复杂度为 O(1)
    public boolean insert(int index, int num) {

        // 插入位置不合法
        if (index < 0 || index > count) {
            System.out.println("插入位置不合法");
            return false;
        }

        // 若数组空间已满，则扩容
        if (count == capacity) {
            resize(capacity * 2);
        }

        // 移动后续元素，插入元素
        for (int i = count - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = num;
        count++;
        return true;
    }


    // 删除函数，将数组中匹配值的第一个元素删除
    // 时间复杂度为 O(1)
    public boolean delete(int index) {
        // 删除位置不合法
        if (index < 0 || index >= count) {
            System.out.println("删除位置不合法");
            return false;
        }

        // 删除 index 位置上的元素
        for (int i = index + 1; i < count; i++) {
            data[i - 1] = data[i];
        }
        count--;

        // 数组元素不足1/4，缩容为1/2
        if (count < capacity / 4) {
            resize(capacity / 2);
        }

        return true;
    }


    // 扩容/缩容函数，新建数组，复制数组元素
    // 时间复杂度为 O(n)
    public void resize(int newCapacity) {
        int[] newData = new int[newCapacity];

        for (int i = 0; i < count; i++) {
            newData[i] = data[i];
        }

        data = newData;
        capacity = newCapacity;
    }


    // 查找函数，查找数组中 index 位置上的元素
    // 时间复杂度为 O(1)
    public int search(int index) {
        // 查找位置不合法
        if (index < 0 || index >= count) {
            System.out.println("查找位置不合法");
            return -1;
        }

        return data[index];
    }


    // 修改函数，将 index 位置的元素修改为新值
    // 时间复杂度为 O(1)
    public boolean update(int index, int later) {
        if (index < 0 || index >= count) {
            System.out.println("修改位置不合法");
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

        DynamicArray array = new DynamicArray(capacity);
        array.printAll();
        array.insert(1, 7);
        for (int i = 0; i < 10; i++) {
            array.insert(i, i + 1);
        }
        System.out.println("capacity:" + array.getCapacity());

        array.insert(4, 7);
        array.printAll();
        System.out.println("capacity:" + array.getCapacity());

        for (int i = 0; i < 10; i++) {
            array.delete(10 - i);
        }
        array.printAll();
        System.out.println("capacity:" + array.getCapacity());

        array.search(7);
        array.search(0);
        array.printAll();

        array.update(0, 12);
        array.printAll();
    }

}
