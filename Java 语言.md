<!-- TOC -->

- [**1. Java 语言基础**](#1-java-语言基础)
  - [**1.1. Java 基础概念**](#11-java-基础概念)
    - [**1.1.1. Java 虚拟机（JVM）**](#111-java-虚拟机jvm)
    - [**1.1.2. 优化 .class 文件 -> 机器码**](#112-优化-class-文件---机器码)
    - [**1.1.3. Java 与 C++ 的区别**](#113-java-与-c-的区别)
  - [**1.2. 数据类型**](#12-数据类型)
    - [**1.2.1. 数据类型分类**](#121-数据类型分类)
    - [**1.2.2. 各数据类型所占内存**](#122-各数据类型所占内存)
  - [**1.3. 运算符**](#13-运算符)
    - [**1.3.1. 运算符优先级**](#131-运算符优先级)
  - [**1.4. 方法（函数）**](#14-方法函数)
    - [**1.4.1. == 和 equals()**](#141--和-equals)
    - [**1.4.2. hashCode() 与 equals()**](#142-hashcode-与-equals)
- [**2. Java 面向对象**](#2-java-面向对象)
  - [**2.1. 对象**](#21-对象)
  - [**2.2. 面向对象三大特征**](#22-面向对象三大特征)
- [**Java 泛型（generics）**](#java-泛型generics)
- [**参考资料**：](#参考资料)

<!-- /TOC -->

<br>

## **1. Java 语言基础**

### **1.1. Java 基础概念**

- **Java SE**：**JDK**（Java SDK，创建和编译程序） > **JRE**（运行时环境） > **JVM**

- **编译与解释并存**：先***一次性编译***为字节码（.class 文件），再由 Java 解释器***逐行解释***执行

<br>

#### **1.1.1. Java 虚拟机（JVM）**
1. 实现平台无关性，**针对不同系统，使用相同的字节码**
2. 字节码（.class 文件），**传统解释型语言执行效率低+解释型语言可移植**

![](https://my-blog-to-use.oss-cn-beijing.aliyuncs.com/Java%20%E7%A8%8B%E5%BA%8F%E8%BF%90%E8%A1%8C%E8%BF%87%E7%A8%8B.png)

#### **1.1.2. 优化 .class 文件 -> 机器码**
- **JIT 编译器**：运行时编译，保存热点代码的机器码，提高运行效率
- **AOT 编译器**：JDK9 引入，将字节码直接编译成机器码

<br>

#### **1.1.3. Java 与 C++ 的区别**

||Java|C++|
|:-:|:-:|:-:|
|**相同点**|面向对象的语言，|支持封装、继承和多态|
|**指针访问内存**|无|有|
|**类继承**|单继承<br>接口可多继承|多继承|
|**手动释放内存**|不需要<br>自动内存管理垃圾回收机制(GC)|需要|
|**字符串结束符 '\0'**|无<br>字符串对象有 length 属性，不需标识结束位置|有|

<br>

### **1.2. 数据类型**

#### **1.2.1. 数据类型分类**
- **基本数据类型**：
  - 6种数字类型：byte、short、int、long、float、double
  - 1种字符类型：char
  - 1种布尔型：boolean
- **引用数据类型**：
  - String 类

<br>

#### **1.2.2. 各数据类型所占内存**

||字符型常量 char|字符串常量 String|
|:-:|:-:|:-:|
|**形式**|'c'|"str"|
|**含义**|整型值（ASCII 值）|地址值|
|**占内存大小**|2 bytes|若干字节|

<br>

- **short**：2 bytes
- **int**：4 bytes
- **long**：8 bytes
- **float**：4 bytes
- **double**：8 bytes

<br>

### **1.3. 运算符**

#### **1.3.1. 运算符优先级**

> 口诀：<br>
> **单目算术位关系，逻辑三目后赋值**

1. **单目运算符**：只接受一个操作数
   - \+, -, ++, --, !
2. **算术运算符**：双目运算符
   - [*, /, %] > [+, -] 
3. **位移运算符**：
   - <<, >> 
4. **关系运算符**：关系单目运算符
   - [>, <, >=, <=] > [==, !=] 
5. **逻辑运算符**
   - & > ^ > | > && > || 
6. **三目运算符**：条件运算符
   - A > B ? X : Y 
7. **赋值运算符**
   - =

<br>

### **1.4. 方法（函数）**

#### **1.4.1. == 和 equals()**

|`==`|`equals()`|
|:-:|:-:|
|比较对象的**值**<br>基本数据类型：值<br>引用数据类型：内存地址|`Object` 类 `equals()` 方法：**等价于 `==`**<br>`String` 类 `equals()` 方法：覆盖重写为比较对象的**值**|

<br>

`String` 类 `equals()` 方法：

```
public boolean equals(Object anObject) {
    if (this == anObject) {
        return true;
    }
    if (anObject instanceof String) {
        String anotherString = (String)anObject;
        int n = value.length;
        if (n == anotherString.value.length) {
            char v1[] = value;
            char v2[] = anotherString.value;
            int i = 0;
            while (n-- != 0) {
                if (v1[i] != v2[i])
                    return false;
                i++;
            }
            return true;
        }
    }
    return false;
}
```

***上述方法比较字符串时用到 String 类的属性 value，其构造方法如下***：

```
private final byte[] value;
public String() {
        this.value = "".value;
        this.coder = "".coder;
}
public String(String original) {
        this.value = original.value;
        this.coder = original.coder;
        this.hash = original.hash;
}
```

> "" 和 original 这类**字符串字面量**的初始化过程：<br>
> Java 代码中写入的**字符串初始值** → 编译成**二进制的class文件** → **虚拟机将其初始化为一个 String 对象**，存储在字符串常量池中<br>
> ——[Java String 源码中的属性 value 是如何被赋值的？](https://www.zhihu.com/question/307686247/answer/566358694)

<br>

#### **1.4.2. hashCode() 与 equals()**

<br>

## **2. Java 面向对象**

### **2.1. 对象**

- **对象**：服务提供者
- **问题**：分解为对象集合
- **软件设计的基本原则**：高内聚（每个对象**功能单一且高效**），提高代码的**可复用性**
  - 支票打印模块：同时读取文本格式又能正确识别不同打印机型号
    1. 检查所有排版布局的目录
    2. 识别不同打印机型号，展示通用的打印界面
    3. 组合上述两个服务

<br>

### **2.2. 面向对象三大特征**


## **Java 泛型（generics）**

## **参考资料**：

1. [JavaGuide](https://snailclimb.gitee.io/javaguide/#/?id=java)
2. [HOW2J](https://how2j.cn/)
3. [On Java 8](https://lingcoder.github.io/OnJava8/#/sidebar)