#数组
##什么是数组
数组简单来说就是将所有的数据排成一排存放在系统分配的一个内存块上，通过使用特定元素的索引作为数组的下标，可以在常数时间内访问数组元素的这么一个结构；
##为什么能在常数时间内访问数组元素？
为了访问一个数组元素，该元素的内存地址需要计算其距离数组基地址的偏移量。需要用一个乘法计算偏移量，再加上基地址，就可以获得某个元素的内存地址。首先计算元素数据类型的存储大小，然后将它乘以元素在数组中的索引，最后加上基地址，就可以计算出该索引位置元素的地址了；整个过程可以看到需要一次乘法和一次加法就完成了，而这两个运算的执行时间都是常数时间，所以可以认为数组访问操作能在常数时间内完成；

##数组的优点
简单且易用；
访问元素快（常数时间）；
##数组的缺点
 **大小固定：**数组的大小是静态的（在使用前必须制定数组的大小）；

 **分配一个连续空间块：**数组初始分配空间时，有时候无法分配能存储整个数组的内存空间（当数组规模太大时）；

 **基于位置的插入操作实现复杂：**如果要在数组中的给定位置插入元素，那么可能就会需要移动存储在数组中的其他元素，这样才能腾出指定的位置来放插入的新元素；而如果在数组的开始位置插入元素，那么这样的移动操作开销就会很大。
##关于数组的一些问题思考
**1）在索引没有语义的情况下如何表示没有的元素？**

我们创建的数组的索引可以有语义也可以没有语义，比如我现在只是单纯的想存放100，98，96这三个数字，那么它们保存在索引为0，1，2的这几个地方或者其他地方都可以，无论它们之间的顺序怎样我都不关心，因为它们的索引是没有语义的我只是想把它们存起来而已；但是如果它们变成了学号为1，2，3这几个同学对应的成绩，那么它们的索引就有了语义，索引0对应了学号为1的同学的成绩，索引1对应了学号2的同学，索引2对应了学号3的同学，因为数组的最大的优点是访问元素是在常数时间，所以我们使用数组最好就是在索引有语义的情况下；好了，那么如果在索引没有语义的情况下，我们如何表示没有的元素呢？例如上图中，对于用户而言，访问索引为3和4的数组元素是违法的，因为它们根本就不存在，我们如何表示没有的元素呢？

**2）如何添加元素和删除元素呢？**

我们知道，数组的明显缺点是在创建之前需要提前声明好要使用的空间，那么当我们空间满了该如何处理呢？又该如何删除元素呢？在Java中提供给我们的默认数组是不支持这些功能的，我们需要开发属于自己的数组类才行；

##常用方法

    // 获取数组的容量
    public int getCapacity() {
    return data.length;
    }
    
    // 获取数组中的元素个数
    public int getSize() {
    return size;
    }
    
    // 返回数组是否为空
    public boolean isEmpty() {
    return size == 0;
    }
    
    // 在index索引的位置插入一个新元素e
    public void add(int index, E e) {
    
    if (index < 0 || index > size)
    throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
    
    if (size == data.length)
    resize(2 * data.length);
    
    for (int i = size - 1; i >= index; i--)
    data[i + 1] = data[i];
    
    data[index] = e;
    
    size++;
    }
    
    // 向所有元素后添加一个新元素
    public void addLast(E e) {
    add(size, e);
    }
    
    // 在所有元素前添加一个新元素
    public void addFirst(E e) {
    add(0, e);
    }
    
    // 获取index索引位置的元素
    public E get(int index) {
    if (index < 0 || index >= size)
    throw new IllegalArgumentException("Get failed. Index is illegal.");
    return data[index];
    }
    
    // 修改index索引位置的元素为e
    public void set(int index, E e) {
    if (index < 0 || index >= size)
    throw new IllegalArgumentException("Set failed. Index is illegal.");
    data[index] = e;
    }
    
    // 查找数组中是否有元素e
    public boolean contains(E e) {
    for (int i = 0; i < size; i++) {
    if (data[i].equals(e))
    return true;
    }
    return false;
    }
    
    // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e) {
    for (int i = 0; i < size; i++) {
    if (data[i].equals(e))
    return i;
    }
    return -1;
    }
    
    // 从数组中删除index位置的元素, 返回删除的元素
    public E remove(int index) {
    if (index < 0 || index >= size)
    throw new IllegalArgumentException("Remove failed. Index is illegal.");
    
    E ret = data[index];
    for (int i = index + 1; i < size; i++)
    data[i - 1] = data[i];
    size--;
    data[size] = null; // loitering objects != memory leak
    
    if (size == data.length / 4 && data.length / 2 != 0)
    resize(data.length / 2);
    return ret;
    }
    
    // 从数组中删除第一个元素, 返回删除的元素
    public E removeFirst() {
    return remove(0);
    }
    
    // 从数组中删除最后一个元素, 返回删除的元素
    public E removeLast() {
    return remove(size - 1);
    }
    
    // 从数组中删除元素e
    public void removeElement(E e) {
    int index = find(e);
    if (index != -1)
    remove(index);
    }
    
    @Override
    public String toString() {
    
    StringBuilder res = new StringBuilder();
    res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
    res.append('[');
    for (int i = 0; i < size; i++) {
    res.append(data[i]);
    if (i != size - 1)
    res.append(", ");
    }
    res.append(']');
    return res.toString();
    }
    
    // 将数组空间的容量变成newCapacity大小
    private void resize(int newCapacity) {
    
    E[] newData = (E[]) new Object[newCapacity];
    for (int i = 0; i < size; i++)
    newData[i] = data[i];
    data = newData;
    }


#源码分析——Stack

	public class Stack<E> extends Vector<E> 

Stack类继承Vector类

	public E push(E item) {
	        addElement(item);
	        return item;
	    }

将一个元素放入栈顶，并返回这个元素

	public synchronized E pop() {
        E       obj;
        int     len = size();

        obj = peek();
        removeElementAt(len - 1);

        return obj;
    }

弹出栈顶元素

	public synchronized E peek() {
        int     len = size();

        if (len == 0)
            throw new EmptyStackException();
        return elementAt(len - 1);
    }

返回栈顶元素，但不弹出

	public boolean empty() {
        return size() == 0;
    }

判断是否为空
	
	public synchronized int search(Object o) {
        int i = lastIndexOf(o);

        if (i >= 0) {
            return size() - i;
        }
        return -1;
    }




