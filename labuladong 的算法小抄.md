# **labuladong 的算法小抄**

## **写在本书之前**

### **本书约定**

1. **一切以可读性为目标**：Python、C++ 和 Java 混用
2. **最小化语言特性，专注算法思维**：使用内置数据结构

<br>

### **数据结构**

#### **LeetCode**

1. ***二叉树节点 `TreeNode`***
2. ***单链表节点 `ListNode`***

#### **C++**

函数参数默认传值：**`&` 引用容器**

1. ***动态数组 `vector`*** ：避免从其中间或头部增删元素的**低效操作**
2. ***字符串 `string`*** ：直接用 `if(s1 == s2)` 判断相等
3. ***哈希表 `unordered_map`*** ：
    - 键一般为 `int` 或 `string` 类型
    - 方括号 `[]` 访问键时，**若键不存在，则自动创建**
4. ***哈希集合 `unordered_set`***
5. ***队列 `queue`***
6. ***堆栈 `stack`***
    - `pop` 方法是 `void` 类型的，需要先存待删除元素

#### **Java**

1. ***数组*** ：非空检查
2. ***字符串 `string`*** ：
    - 不能直接修改，要用 `toCharArray` 转化成 `char[]` 类型数组再修改
    - `+` 拼接效率低，推荐使用 `StringBuilder` 的 `append` 方法
    - `if(s1.equals(s2))` 判断相等
3. ***动态数组 `ArrayList`***
4. ***双链表 `LinkedList`***
5. ***哈希表 `HashMap`***
6. ***哈希集合 `HashSet`***
7. ***队列 `queue`*** ：`Queue<String> q = new LinkedList<>()`
8. ***堆栈 `stack`***

#### **Python 3**

1. ***列表 `list`*** ：数组、堆栈和队列
2. ***元组 `tuple`***
3. ***字典 `dict`***
    - 动态规划的**备忘录** ：`memo = dict(), memo[(i, j)]`

<br>

## **第 1 章 / 核心套路**

- **算法**：
  - 通过合适的**工具**（***数据结构***）
  - 解决特定问题的**方法**

<br>

### **1.1. 框架思维**

- 从**整体**到**细节**
- 自**顶**向**下**
- 从**抽象**到**具体**

<br>

#### **1.1.1. 数据结构的存储方式**

- 数组（***顺序存储***）
  - **随机**访问
  - **复制**扩容
  - **移动**插入删除
- 链表（***链式存储***）
  - **顺序**访问
  - **插入**扩容
  - **直接**插入删除

||数组|链表|
|:-:|:-:|:-:|
|**队列/栈**|需处理扩容和缩容|需存储节点指针|
|**图**|邻接矩阵|邻接表（稀疏矩阵）|
|**哈希表**（处理哈希冲突）|线性探查法|拉链法|
|**树**|堆（完全二叉树）|二叉搜索树、AVL 树、红黑树、区间树、B 树等|

<br>

#### **1.1.2. 数据结构的基本操作**

**数据结构的使命**：
- 在不同的应用场景下尽可能 ***高效*** 地 ***增、删、查、改***

**遍历 + 访问**：
- **线性**：***for/while 迭代***
  - ***数组迭代*** 遍历框架
  ```
  void traverse(int [] arr) {
      for (int i = 0; i < arr.length; i++) {
          // 迭代遍历 arr[i]
      }
  }
  ```

  - ***链表迭代*** 遍历框架
  ```
  /* 单链表节点 */
  class ListNode {
    int val;
    ListNode next;
  }

  void traverse(ListNode head) {
    for (ListNode p = head; p != null; p = p.next) {
      // 迭代遍历 p.val
    }
  }
  ```
- **非线性**：***递归***
  - ***链表递归*** 遍历框架
  ```
  void traverse(ListNode head) {
    // 前序遍历 head.val
    traverse(head.next);
    // 后序遍历 head.val
  }
  ```

  - ***二叉树递归*** 遍历框架
  ```
  /* 二叉树节点 */
  class TreeNode {
    int val;
    TreeNode left, right;
  }

  void traverse(TreeNode root) {
    // 前序遍历（根左右）
    traverse(root.left);
    // 中序遍历（左根右）
    traverse(root.right);
    // 后序遍历（左右根）
  }
  ```

  - ***N 叉树递归*** 遍历框架 → **图**的遍历（***多个 N 叉树 + 布尔数组 visited***）
  ```
  /* N 叉树节点 */
  class TreeNode {
    int val;
    TreeNode[] children; 
  }

  void traverse(TreeNode root) {
    for (TreeNode child : root.children) {
      traverse(child);
    }
  }
  ```

<br>

#### **1.1.3. 算法刷题指南**

***第一步：二叉树***
- 最容易培养**框架思维**
- 大部分常考算法（**回溯、动态规划、分治**）本质上是树的遍历问题（**递归**）

