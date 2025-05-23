---
title: 查看线程
slug: check-threads
abstract: 如何查看一个应用程序的线程? 可以使用哪些工具获得线程? 线程有哪些属性? 这些值分别代表什么意思?
---

线程​​是程序执行的最小单元，是进程中的一个独立运行路径。一个进程（例如一个 Java 应用程序）可以包含多个线程，这些线程共享进程的资源（如内存、文件句柄等），但每个线程有自己独立的执行栈和程序计数器。

# 线程与进程的区别与联系​​

| ​特性​              | ​进程 | ​线程 |
| :---------------- | :------: | ----: |
| ​​定义        |   操作系统分配资源的基本单位（如内存、CPU时间）。   | 进程内的执行单元，是 CPU 调度的最小单位。 |
| ​​资源占用​​           |   独立的内存空间和系统资源。   | 共享所属进程的内存和资源。 |
| ​切换开销​​    |  切换开销大（需切换内存空间、寄存器等）。   | 切换开销小（共享地址空间，仅需切换栈和寄存器）。 |
| ​​独立性​ |  进程间相互隔离，一个崩溃不影响其他进程。   | 线程共享资源，一个线程崩溃可能导致整个进程终止。 |
| ​​通信方式​    |  需通过管道、消息队列、共享内存等复杂机制。   | 可直接读写进程内的共享变量（需同步机制）。 |
| ​​创建与销毁​ |  速度慢，资源消耗大。   | 速度快，资源消耗小。 |

# 如何创建一个 Java 线程

在 Java 中，线程通过以下方式实现：
1. 继承 Thread 类​​：重写 run() 方法。
    ```java
    class MyThread extends Thread {
        public void run() {
            System.out.println("线程执行");
        }
    }
    ```
2. ​​实现 Runnable 接口​​（更灵活，推荐）：
   ```java
   class MyRunnable implements Runnable {
        public void run() {
            System.out.println("线程执行");
        }
    }
    // 启动线程
    new Thread(new MyRunnable()).start();
   ```

## 为什么用多线程？
​1. ​提高 CPU 利用率​​：在等待 I/O 时（如网络请求），CPU 可以执行其他任务。  
​2. ​提升响应速度​​：例如 GUI 应用中，主线程处理界面交互，后台线程执行耗时操作。  
​​3. 简化复杂逻辑​​：通过多线程分治任务（如并行计算）。  

# 

---
```
This file is located at: {{ page.path }}
```
---



