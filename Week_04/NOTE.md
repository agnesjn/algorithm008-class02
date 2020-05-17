## 思考题

使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方

	if nums[left] < nums[right]
		
		return right	
	
	while left < right
	
		if nums[mid] == nums[left]
		
			return left
			
		if nums[mid] < nums[left]
			
			right = mid - 1
			
			continue
		
		if nums[mid] > nums[left]
		
			left = mid + 1
			
			continue
	
	return left



## 贪心算法

什么是贪心算法呢？所谓贪心算法是指，在对问题求解时，总是做出在当前看来最好的选择。也就是说，不从整体最优解出发来考虑，它所做出的仅是在某种意义上的局部最优解。

贪心算法不是对所有问题都能得到整体最优解，但对范围相当广泛的许多问题都能产生整体最优解或整体最优解的近似解。

	在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
	顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
	每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
	注意，一开始你手头没有任何零钱。
	如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
	
这个问题中，不论以何种策略进行找零，手中钱的总和是一样的，因此从眼前的最优选择上考虑，应该尽可能多地留下面额较小（5元）的钱币。因此，当顾客购买10元或20元的柠檬水（既需要找零时），应优先使用10元钱币找零，若无剩余再使用5元钱币。

一种可行的实现如下：

	class Solution {
	    public boolean lemonadeChange(int[] bills) {
	        int[] cash = new int[2];
	        for (int i = 0; i < bills.length; i++) {
	            if (bills[i] == 5) {
	                cash[0]++;
	                continue;
	            }
	            if (bills[i] == 10) {
	                cash[1]++;
	                cash[0]--;
	                if (cash[0] < 0) return false;
	            }
	            else {
	                if (cash[1] > 0 && cash[0] > 0) {
	                    cash[1]--;
	                    cash[0]--;
	                    continue;
	                }
	                if (cash[0] >= 3) {
	                    cash[0] -= 3;
	                    continue;
	                }
	                return false;
	            }
	        }
	        return true;
	    }
	}
	

## 深度优先&广度优先搜索

深度优先搜索（缩写DFS）有点类似广度优先搜索，也是对一个连通图进行遍历的算法。它的思想是从一个顶点V0开始，沿着一条路一直走到底，如果发现不能到达目标解，那就返回到上一个节点，然后从另一条路开始走到底，这种尽量往深处走的概念即是深度优先的概念。

深度优先搜索分递归与非递归两种方式

### 递归实现

	（1）访问顶点v；visited[v]=1；//算法执行前visited[n]=0
	
	（2）w=顶点v的第一个邻接点；
	
	（3）while（w存在）  

           if（w未被访问）

                   从顶点w出发递归执行该算法；     
                           
                   w=顶点v的下一个邻接点；

### 非递归实现（利用栈）

	（1）栈S初始化；visited[n]=0；
	
	 （2）访问顶点v；visited[v]=1；顶点v入栈S
	
	 （3）while(栈S非空)

            x=栈S的顶元素(不出栈)；

            if(存在并找到未被访问的x的邻接点w)

                    访问w；visited[w]=1；

                    w进栈;

            else

                     x出栈；
                     
图的广度优先遍历BFS算法是一个分层搜索的过程，和树的层序遍历算法类同，它也需要一个队列以保持遍历过的顶点顺序，以便按出队的顺序再去访问这些顶点的邻接顶点。 

使用队列的实现：

	（1）初始化队列Q；visited[n]=0；
	
	（2）访问顶点v；visited[v]=1；顶点v入队列Q；
	
	（3） while（队列Q非空）   

              v=队列Q的对头元素出队；

              w=顶点v的第一个邻接点；

             while（w存在） 

                     如果w未访问，则访问顶点w；

                     visited[w]=1；

                     顶点w入队列Q；

                     w=顶点v的下一个邻接点。
                     
              