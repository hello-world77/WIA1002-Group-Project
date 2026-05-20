# Large Number Arithmetic Using Doubly Linked List

本项目使用 Java 实现「使用双向链表的大数运算」。程序可以处理超过 Java 内置整数范围的大整数，并支持加法、减法、乘法和除法。

每一个数字位都会被存储在自定义双向链表的一个节点中，所有运算都按位处理，不使用 `BigInteger` 或 `BigDecimal` 完成计算。

## 功能

- 支持正整数、负整数和 `0`
- 支持任意长度整数输入
- 支持四则运算：
  - addition
  - subtraction
  - multiplication
  - division
- 除法使用长除法，默认最多输出 20 位小数
- 自动移除结果中的前导 `0`
- `-0` 会显示为 `0`
- 输入验证会拒绝字母、小数、空输入和单独的符号
- 除数为 `0` 时不会崩溃，会显示 `undefined (division by zero)`

## 项目结构

```text
src/
  app/
    App.java                  程序入口，负责菜单、输入和输出

  largenumber/
    Arithmetic.java           四则运算接口
    Addition.java             加法实现
    Subtraction.java          减法实现
    Multiplication.java       乘法实现
    Division.java             除法实现
    LargeNumber.java          大整数表示、解析和辅助方法
    DigitList.java            存储数字位的双向链表
    DigitNode.java            双向链表节点
```

## Package 设计

程序使用两个 package：

```java
package app;
package largenumber;
```

`app` package 只负责用户交互。`largenumber` package 负责大数结构和运算逻辑。

四则运算类都实现同一个接口：

```java
public interface Arithmetic {
    String calculate(LargeNumber left, LargeNumber right);
}
```

`App` 中固定调用四个实现类：

```java
private static final Arithmetic ADDITION = new Addition();
private static final Arithmetic SUBTRACTION = new Subtraction();
private static final Arithmetic MULTIPLICATION = new Multiplication();
private static final Arithmetic DIVISION = new Division(20);
```

## 双向链表设计

`LargeNumber` 使用两个部分表示一个大整数：

- `boolean negative`：记录是否为负数
- `DigitList digits`：用双向链表存储每一位数字

例如：

```text
12345
```

会被存储为：

```text
1 <-> 2 <-> 3 <-> 4 <-> 5
```

双向链表同时支持：

- 从尾部向前遍历：适合加法、减法、乘法
- 从头部向后遍历：适合除法

## 编译与运行

在项目根目录执行：

```powershell
javac -d bin src\app\App.java src\largenumber\*.java
```

运行：

```powershell
java -cp bin app.App
```

## 使用示例

输入：

```text
m = 55
n = 2
```

输出：

```text
addition = 57
subtraction = 53
multiplication = 110
division = 27.5
```

## 输入规则

允许：

```text
123
0
-987654321
000123
```

不允许：

```text
abc
4.5
-
```

## 建议测试用例

```text
55 and 2
-50 and 8
50 and -8
-50 and -8
0 and 12345
000123 and -0
1 and 3
123 and 0
```

`GUIDELINE.md` 中的大数测试：

```text
m = 123456789123456789123456789123456789123456789123456789
n = 456789123456789123456789123456789123456789123456789
```

关键输出：

```text
addition = 123913578246913578246913578246913578246913578246913578
subtraction = 123000000000000000000000000000000000000000000000000000
division = 270.27085975512599935528
```

## 运算方法说明

- 加法：从链表尾部开始逐位相加，并处理进位。
- 减法：通过比较绝对值后逐位相减，并处理借位。
- 乘法：逐位相乘，生成部分乘积，移位后累加。
- 除法：使用长除法，每次寻找当前余数可容纳的最大商位。

## 复杂度简述

设两个输入数字长度分别为 `m` 和 `n`：

- 加法：`O(max(m, n))`
- 减法：`O(max(m, n))`
- 乘法：`O(m * n)`
- 除法：约为 `O(m * n)`，并额外受小数位数影响
