# 四种GC总结

## 串行GC
Serial GC 对年轻代使用 mark-copy(标记-复制) 算法, 对老年代使用 mark-sweep-compact(标记-清除-整理)算法
两者都是单线程的垃圾收集器,不能进行并行处理。两者都会触发全线暂停,停止所有的应用线程。
这种GC算法不能充分利用多核CPU。不管有多少CPU内核, JVM 在垃圾收集时都只能使用单个核心。

## 并行GC
年轻代使用拷贝-复制，老年代使用标记-清除-整理
GC处理时，暂停业务处理，所有线程处理GC垃圾回收。平常运行时，所以线程都去处理业务。因此，吞吐量比较高。

## CMS GC
CMS收集器的主要设计目标是：低应用停顿时间。它通过两种方式实现这一目标：
1，不压缩老年代，而是使用空闲列表来管理回收空间。
2，大部分标记清理工作与应用程序并发执行。

## G1 GC
与CMS相比有几个不同点使得G1成为GC的更好解决方案。
1,G1会压缩空闲内存使之足够紧凑，做法是用regions代替细粒度的空闲列表进行分配，减少内存碎片的产生。
2,G1的STW更可控，G1在停顿时间上添加了预测机制，用户可以指定期望停顿时间。

