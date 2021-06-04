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
2. ***字符串 `String`*** ：
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

***算法刷题第一步：二叉树***
- 最容易培养**框架思维**
- 大部分常考算法（**回溯、动态规划、分治**）本质上是树的遍历问题（**递归**）

<br>

### **1.2. 动态规划解题套路框架**

- **一般形式**：求最值（如 ***最长*** 递增子序列、***最小*** 编辑距离）

↓

- **核心问题**：穷举
- **动态规划三要素**
  - 存在“**重叠子问题**”：优化穷举效率（备忘录、DP table）
  - 具备“**最优子结构**”：子问题的最值 → 原问题的最值
    - 子问题间**互相独立** → *动态规划*
  - 正确的“**状态转移方程**”
    - 问题的 base case：**最简单情况**
    - “**状态**”：变量
    - “**选择**”：使“状态”改变的行为
    - **dp 数组**：表示“状态”和“选择”

```
# 初始化 base case
dp[0][0][...] = base case
# 进行状态转移
for 状态 1 in 状态 1 的所有取值:
    for 状态 2 in 状态 2 的所有取值:
        for ...
            dp[状态 1][状态 2][...] = 求最值 (选择 1, 选择 2, ...)
```

<br>

#### **1.2.1. 斐波那契数列：重叠子问题**

***斐波那契数列：1, 1, 2, 3, 5, 8, ...***

```
int fib(int N) {
  if (N == 0) return 0;
  if (N == 1 || N == 2) return 1;
  return fib(N - 1) + fib(N - 2);
}
```

1. ***递归算法*** 的**递归树**
   - 时间复杂度 O(2<sup>N</sup>)，空间复杂度 O(1)

![递归算法的递归树](https://upload-images.jianshu.io/upload_images/24313937-8355c19361b8dd0d.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

↓“剪枝”

2. ***带“备忘录”的递归算法（自顶向下）*** 的**递归图**
   - 时间复杂度 O(N)，空间复杂度 O(N)

![带“备忘录”的递归算法的递归图](https://upload-images.jianshu.io/upload_images/24313937-b54b543391fb85f4.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
int fib(int N) {
  if (N == 0) return 0;
  // 初始化备忘录
  int[] memo = new int[N + 1];
  Arrays.fill(memo, 0);
  // 进行带备忘录的递归
  return helper(memo, N);
}

int helper(int[] memo, int n) {
  // base case
  if (n == 1 || n == 2) return 1;
  // 已经计算过
  if (memo[n] != 0) return memo[n];
  memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
  return memo[n];
}
```

↓

3. ***dp 数组的迭代算法（自底向上）*** 的 **DP table** 图
   - 时间复杂度 O(N)，空间复杂度 O(N)

![DP table](https://upload-images.jianshu.io/upload_images/24313937-be6fe141cd85c57a.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


```
int fib(int N) {
  if (N == 0) return 0;
  if (N == 1 || N == 2) return 1;
  int[] dp = new int[N + 1];
  Arrays.fill(dp, 0);
  // base case
  dp[1] = dp[2] = 1;
  for (int i = 3; i <= N; i++) {
    dp[i] = dp[i - 1] + dp[i - 2];
  }
  return dp[N];
}
```

**状态转移方程**：
![状态转移方程](https://upload-images.jianshu.io/upload_images/24313937-1e23383516273843.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

↓

4. ***状态压缩*** 的**dp 数组的迭代算法**
   - 时间复杂度 O(N)，空间复杂度 O(1)

```
int fib(int N) {
  if (N == 0) return 0;
  if (N == 1 || N == 2) return 1;
  int pre2 = 1, pre1 = 1;
  for (int i = 3; i <= N; i++) {
    int cur = pre2 + pre1;
    pre2 = pre1;
    pre1 = cur;
  }
  return pre1;
}
```

<br>

#### **1.2.2. 凑零钱问题：状态转移方程**

***k 种面值的硬币：c1, c2, ..., ck，以最少的硬币数凑出总金额 amount***

**算法的函数签名**：
```
// coins 可选硬币面值，amount 目标金额
int coinChange(int[] coins, int amount);
```

1. **暴力递归**：确定状态转移方程
   - 时间复杂度 O(kn<sup>k</sup>)，空间复杂度 O(1)
    1. 确定 **base case**：amount 为 0 时，算法返回 0
    2. 确定“**状态**”：amount
    3. 确定“**选择**”：coins
    4. 确定 **dp 函数/数组**的定义：输入 amount，输出凑出 amount 的最少硬币数量

```
int coinChange(int[] coins, int amount) {
  return dp(amount, coins);
}

int dp(int n, int[] coins) {
  // base case
  if (n == 0) return 0;
  if (n < 0) return -1;

  int res = Integer.MAX_VALUE;
  for (int coin : coins) {
    int subproblem = dp(n - coin, coins);
    // 子问题不能凑出，跳过这一面值
    if (subproblem == -1) continue;
    // 取子问题中各面值的最少硬币数
    res = Math.min(res, 1 + subproblem);
  }
  // 原问题不能凑出，则返回 -1
  return (res != Integer.MAX_VALUE) ? res : -1;
}
```

***结果：递归超时***


2. **带“备忘录”的递归**：自顶向下，消除重叠子问题
   - 时间复杂度 O(kn)，空间复杂度 O(n)
```
int coinChange(int[] coins, int amount) {
  int[] memo = new int[amount + 1];
  Arrays.fill(memo, 0);
  return helper(memo, amount, coins);
}

int helper(int[] memo, int n, int[] coins) {
  // base case
  if (n == 0) return 0;
  if (n < 0) return -1;

  if (memo[n] != 0) return memo[n];

  int res = Integer.MAX_VALUE;
  for (int coin : coins) {
    int subproblem = helper(memo, n - coin, coins);
    // 子问题不能凑出，则跳过这一面值
    if (subproblem == -1) continue;
    // 取子问题中各面值的最少硬币数
    res = Math.min(res, 1 + subproblem);
  }
  // 原问题不能凑出，则设置备忘录值为 -1
  memo[n] = (res != Integer.MAX_VALUE) ? res : -1;
  return memo[n];
}
```

3. **dp 数组的迭代**：自底向上
   - 时间复杂度 O(kn)，空间复杂度 O(n)

```
int coinChange(int[] coins, int amount) {
  if (amount == 0) return 0;
  if (amount < 0) return -1;
  
  int[] dp = new int[amount + 1];
  // 初始化为 amount + 1，最多用 amount + 1 枚硬币凑出
  Arrays.fill(dp, amount + 1);

  // base case
  dp[0] = 0;
  for (int i = 1; i <= amount; i++) {
    for (int coin : coins) {
      // 子问题超出范围，跳过
      if (i - coin < 0) continue;
      dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
    }
  }
  return (dp[amount] != amount + 1) ? dp[amount] : -1;
}
```

<br>

### **1.3. 回溯算法**

- **回溯问题**：***决策树*** 的遍历过程，**纯暴力枚举**
  1. **路径**：***已做出*** 的选择
  2. **选择列表**：***当前能做*** 的选择
  3. **结束条件**：***无法再做*** 选择的条件

```
result = []
def backtrack( 路径, 选择列表 ):
  if 满足结束条件:
    result.add( 路径 )
    return

  for 选择 in 选择列表:
    做选择
    backtrack( 路径, 选择列表 )
    撤销选择
```

<br>

#### **1.3.1. 全排列问题**

***n 个不重复的数的全排列共有 n! 个***
  1. **路径**：[2]
  2. **选择列表**：[1, 3]
  3. **结束条件**：选择列表为空

![全排列问题的回溯树](https://upload-images.jianshu.io/upload_images/24313937-75373086b40f7cd3.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


- 时间复杂度 O(n * n!)，***递归总次数 * 每次递归中的操作次数***
- 空间复杂度 O(n)，***递归深度 * 每次递归的辅助空间***
```
// 全排列链表
List<List<Integer>> res = new LinkedList<>();

/* 主函数，输入一组不重复的数字，返回它们的全排列 */
List<List<Integer>> permute(int[] nums) {
  // 记录 “路径”
  LinkedList<Interger> track = new LinkedList<>();
  backtrack(nums, track);
  return res;
}

// 路径：记录在 track 中
// 选择列表：nums 中不存在于 track 中的元素
// 结束条件：nums 中元素全部都在 track 中
void backtrack(int[] nums, LinkedList<Integer> track) {
  // 触发结束条件
  if (track.size() == nums.length) {
    res.add(new LinkedList(track));
    return;
  }

  for (int i = 0; i < nums.length; i++) {
    // 排除不合法的选择
    if (track.contains(nums[i])) {
      continue;
    }
    // 做选择
    track.add(nums[i]);
    // 进入下一层决策树
    backtrack(nums, track);
    // 撤销选择
    track.removeLast();
  }
}
```

<br>

#### **1.3.2. N 皇后问题**

***N × N 的棋盘，放置 N 个皇后，使它们不能从八个方向互相攻击***

```
List<List<String>> res = new LinkedList<>();

/* 输入棋盘边长 n，返回所有合法的放置方法*/
List<List<String>> solveNQueens(int n) {
  // '.' 表示空，'Q' 表示皇后，初始化空棋盘
  List<String> board = new LinkedList<>();
  for (int i = 0; i < n; i++) {
    char[] row = new char[n];
    Arrays.fill(row, '.');
    board.add(new String(row));
  }
  backtrack(board, 0);
  return res;
}

// 路径：board 中小于 row 的行已经成功放置皇后
// 选择列表：第 row 行所有列都是放置皇后的选择
// 结束条件：row 大于 board 的最后一行，说明棋盘已满
void backtrack(List<String> board, int row) {
  // 触发结束条件
  if (board.size() == row) {
    res.add(new LinkedList(board));
    return;
  }

  int n = board.get(row).length();
  for (int col = 0; col < n; col++) {
    // 排除不合法选择
    if (!isValid(board, row, col)) {
      continue;
    }
    // 做选择
    char[] arr = new char[n];
    Arrays.fill(arr, '.');
    arr[col] = 'Q';
    board.set(row, new String(arr));
    // 进入下一行决策
    backtrack(board, row + 1);
    // 撤销选择
    arr[col] = '.';
    board.set(row, new String(arr));
  }
}

/* 检查 board.get(row).charAt(col) 处是否可以放置皇后 */
boolean isValid(List<String> board, int row, int col) {
  int n = board.get(row).length();
  // 检查列中是否有皇后冲突
  for (int i = 0; i < row; i++) {
    if (board.get(i).charAt(col) == 'Q') {
      return false;
    }
  }

  // 检查左上方是否有皇后冲突
  for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
    if (board.get(i).charAt(j) == 'Q') {
      return false;
    }
  }

  // 检查右上方是否有皇后冲突
  for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
    if (board.get(i).charAt(j) == 'Q') {
      return false;
    }
  }

  return true;
}
```

- 注意此算法每次递归都需要开辟新数组，导致空间复杂度大，原因是 **String 无法直接修改元素**

<br>

### **1.4. BFS 算法**

- **DFS 算法**：深度优先
  - 利用 ***递归***，遍历**决策树**
  - 全排列 / 可行路径
  - 时间复杂度大
- **BFS 算法**：广度优先
  - 利用 ***队列***，**图**的起点到终点
  - **最短**路径
  - 空间复杂度大

<br>

- **核心数据结构**：Queue
- **避免走回头路（图）**：visited

```
// 计算从起点 start 到终点 target 的最短距离
int BFS( Node start, Node target) {
  Queue<Node> q; // 核心数据结构
  Set<Node> visited; // 避免走回头路

  q.offer(start); // 将起点加入队列
  visited.add(start);
  int step = 0; // 记录步数

  while (q not empty) {
    int sz = q.size();
    /* 将当前队列中的所有节点向四周扩散 */
    for (int i = 0; i < sz; i++) {
      Node cur = q.poll();
      /* 判断是否到达终点 */
      if (cur is target) {
        return step;
      }
      /* 若未访问过，则将 cur 的相邻节点加入队列 */
      for (Node x : cur.adj()) {
        if (x not in visited) {
          q.offer(x);
          visited.add(x);
        }
      }
    }
    /* 更新步数 */
    step++;
  }
}
```

<br>

#### **1.4.1. 二叉树的最小高度**

***输入一棵二叉树，计算从根节点到叶子节点的最短距离***

**叶子节点**：左右子节点都为空<br>
`if (cur.left == null && cur.right == null)`

```
int minDepth(TreeNode root) {
  if (root == null) return 0;
  Queue<TreeNode> q = new LinkedList<>();
  q.offer(root);
  int depth = 1;

  while (!q.isEmpty()) {
    int sz = q.size();
    /* 将当前队列中的所有节点向四周扩散 */
    for (int i = 0; i < sz; i++) {
      TreeNode cur = q.poll();
      /* 判断是否到达终点，即叶子节点 */
      if (cur.left == null && cur.right == null) {
        return depth;
      }
      /* 将 cur 的子节点加入队列 */
      if (cur.left != null) {
        q.offer(cur.left);
      }
      if (cur.right != null) {
        q.offer(cur.right);
      }
    }
    /* 更新高度 */
    depth++;
  }
  return depth;
}
```

<br>

#### **1.4.2. 解开密码锁的最小次数**

***带有四个圆形拨轮的转盘锁，每次旋转只能将一个拨轮旋转一次，计算从初始状态 `"0000"` 拨出 `target` 的最少次数，同时避免拨出 `deadends`***

```
int openLock(String[] deadends, String[] target) {
  // 记录需要避免的死亡密码
  Set<String> deads = new HashSet<>();
  for (String s : deadends) {
    deads.add(s);
  }
  // 记录已经穷举过的密码，避免走回头路
  Set<String> visited = new HashSet<>();
  Queue<String> q = new LinkedList<>();
  // 从初始状态开始启动 BFS 算法
  int step = 0;
  q.offer("0000");
  visited.add("0000");

  while (!q.isEmpty()) {
    int sz = q.size();
    // 将当前队列中的所有节点向周围扩散
    for (int i = 0; i < sz; i++) {
      String cur = q.poll();
      // 判断密码是否是死亡密码
      if (deads.contains(cur)) {
        continue;
      }
      // 判断密码是否到达终点
      if (cur.equals(target)) {
        return step;
      }

      // 将四个拨轮未遍历的相邻节点加入队列
      for (int j = 0; j < 4; j++) {
        String up = plusOne(cur, j);
        if (!visited.contains(up)) {
          q.offer(up);
          visited.add(up);
        }
        String down = minusOne(cur, j);
        if (!visited.contains(down)) {
          q.offer(down);
          visited.add(down);
        }
      }
    }
    // 增加步数
    step++;
  }
  // 找不到 target 密码，返回 -1
  return -1;
}

// 将 s[j] 向上拨动一次
String plusOne(String s, int j) {
  char[] ch = s.toCharArray();
  if (ch[j] == '9') {
    ch[j] = '0';
  }
  else {
    ch[j] += 1;
  }
  return new String(ch);
}

// 将 s[j] 向下拨动一次
String minusOne(String s, int j) {
  char[] ch = s.toCharArray();
  if (ch[j] == '0') {
    ch[j] = '9';
  }
  else {
    ch[j] -= 1;
  }
  return new String(ch);
}
```

**注意：遍历与否在加入相邻节点时检查**
- 时间复杂度 O(N * A<sup>N</sup>)，遍历全部密码需要 O(A<sup>N</sup>)，每个密码拨动每一位需要 O(N)
- 空间复杂度 O(A<sup>N</sup>)
- A 为数字个数，N 为密码位数

<br>

### **1.5. 双指针技巧套路框架**

- **快、慢指针**：解决**链表**问题
  - 判定链表中**是否含有环**
  - 返回**环的起始位置**
  - 寻找无环**单链表的中点**
  - 寻找单链表的**倒数第 `k` 个元素**

#### **1.5.1. 快、慢指针常用算法**

1. **判定链表中是否含有环**

- `fast` 指针每次前进 **2 步**，`slow` 指针每次前进 **1 步**
- **若不含有环**，`fast` 指针遇到 **`null`**
- **若含有环**，`fast` 指针最终超 `slow` 指针 1 圈，**两指针相遇**

```
boolean hasCycle(ListNode head) {
  ListNode fast, slow;
  // 初始化两指针指向头节点
  fast = slow = head;
  while (fast != null && fast.next != null) {
    // 快指针每次前进 2 步
    fast = fast.next.next;
    // 慢指针每次前进 1 步
    slow = slow.next;
    // 若含有环，两指针必然相遇
    if (fast == slow) {
      return true;
    }
  }
  return false;
}
```

<br>

2. **返回环的起始位置**

- `fast` 指针每次前进 **2 步**，`slow` 指针每次前进 **1 步**，**若两指针未相遇，则无环，若两指针相遇**
  - `slow` 指针走了 **`k`** 步，则 `fast` 指针走了 **`2k`** 步，**环长度为 `k` 的整数倍**
  - 设相遇点与环起点的距离为 **`m`**，则环起点与头节点距离为 **`k-m`**
- **将 `slow` 指针重新指向 `head`，这次 `fast` 和 `slow` 指针同速前进，再次相遇即为环起点**
  - `slow` 指针从 `head` 走 `k-m` 步到达环起点
  - `fast` 指针从相遇点走 `k-m` 步到达环起点

```
ListNode detectCycle(ListNode head) {
  ListNode fast, slow;
  // 初始化两指针指向头节点
  fast = slow = head;
  while (fast != null && fast.next != null) {
    // 快指针每次前进 2 步
    fast = fast.next.next;
    // 慢指针每次前进 1 步
    slow = slow.next;
    // 有环
    if (fast == slow) {
      // 将慢指针重新指向头节点
      slow = head;
      // 双指针同速前进，再次相遇即为环起点
      while (slow != fast) {
        fast = fast.next;
        slow = slow.next;
      }
      return slow;
    }
  }
  // 无环
  return null;
}
```