# java.util.Map 源码分析

HashMap基于哈希表的Map接口实现，是以key-value键值对存储形式存在。在 JDK1.8 中，HashMap 是由 数组+链表+红黑树构成，新增了红黑树作为底层数据结构，结构变得复杂了，但是效率也变的更高效。当一个值中要存储到Map的时候会根据Key的值来计算出他的hashcode，通过哈希来确认到数组的位置，如果发生哈希碰撞就以链表的形式存储 在Object源码分析中解释过，但是这样如果链表过长来的话，HashMap会把这个链表转换成红黑树来存储。

![text]("E:\algorithm008-class02\Week_02\hashmap.PNG")

![avatar](https://github.com/agnesjn/algorithm008-class02/blob/master/Week_02/hashmap.PNG)

## 属性

初始化容量(必须是二的n次幂)：

	static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
 
集合最大容量(必须是二的幂)：

	static final int MAXIMUM_CAPACITY = 1 << 30;

负载因子，默认的0.75。负载因子是和扩容机制有关的，意思是如果当前容器的容量，达到了我们设定的最大值，就要开始执行扩容操作。

	static final float DEFAULT_LOAD_FACTOR = 0.75f;

当tab链表的长度超过8则会转红黑树(1.8新增)

    static final int TREEIFY_THRESHOLD = 8;

当tab链表的长度小于6则会从红黑树转回链表

    static final int UNTREEIFY_THRESHOLD = 6;

Node为HashMap中的一个内部类，带有哈希值、键值对以及只想下一个Node的指针。HashMap的结构为这种Node类型元素组成的数组。

    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

红黑树节点：

	static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
	        TreeNode<K,V> parent;  
	        TreeNode<K,V> left;
	        TreeNode<K,V> right;
	        TreeNode<K,V> prev;    
	        boolean red;
	        TreeNode(int hash, K key, V val, Node<K,V> next) {
	            super(hash, key, val, next);
	        }
	}

插入操作：

首先判断tab是否等于空或者为零，如果是就进行初始化。注意到resize()方法就只是进行初始化而并没有分配空间。

	final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
	                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
		
		//首先判断tab是否等于空或者为零，如果是就进行初始化。注意到resize()方法就只是进行初始化而并没有分配空间。
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        
		//对Hash码进行取模运算
		//i = (n - 1) & hash  对值的位置进行确定
		/如果tab[i]为null就新增一个元素
		if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
			//如果tab[i]不等于空就表示已经有值了
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
				//判断是否是红黑树
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
				//否则就是链表
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
						// 如果是链表的话就遍历到尾部之后插入
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
			// 如果链表中有重复就直接替换
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
		// 修改次数加一
        ++modCount;
		//判断当前元素数量是否超过threshold阈值，如果超过就调用resize()
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }