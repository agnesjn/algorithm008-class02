# 递归与分治

程序调用自身的编程技巧称为递归（ recursion）。 
递归做为一种算法在程序设计语言中广泛应用。 一个过程或函数在其定义或说明中有直接或间接调用自身的一种方法，它通常把一个大型复杂的问题层层转化为一个与原问题相似的规模
较小的问题来求解，递归策略只需少量的程序就可描述出解题过程所需要的多次重复计算，大大地减少了程序的代码量。

## 递归范型

	public void recur(int level, int param) { 
	  // 递归终结条件
	  if (level > MAX_LEVEL) { 
	    // 对结果进行处理
	    return; 
	  }
	  // 处理逻辑与运算
	  process(level, param); 
	  // 下一层递归
	  recur( level: level + 1, newParam); 
	  // 恢复当前层状态
	 
	}

回溯法是一种选优搜索法，按选优条件向前搜索，以达到目标。但当探索到某一步时，发现原先选择并不优或达不到目标，就退回一步重新选择，这种走不通就退回再走的技术为回溯法。

回溯的思路基本如下：当前局面下，我们有若干种选择，所以我们对每一种选择进行尝试。如果发现某种选择违反了某些限定条件，此时 return；如果尝试某种选择到了最后，发现该选择是正确解，那么就将其加入到解集中。
在这种思想下，我们需要清晰的找出三个要素：选择 (Options)，限制 (Restraints)，结束条件 (Termination)。



## 分治范型

	def divide_conquer(problem, param1, param2, ...): 
	  # 递归终结条件
	  if problem is None: 
		print_result 
		return 
	
	  # 预处理数据
	  data = prepare_data(problem) 
	  subproblems = split_problem(problem, data) 
	
	  # 分割并调用处理子问题的自身函数
	  subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
	  subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
	  subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
	  …
	
	  # 生成最终结果
	  result = process_result(subresult1, subresult2, subresult3, …)
		
	  # 恢复当前层状态
  
  
  分治策略是：对于一个规模为n的问题，若该问题可以容易地解决（比如说规模n较小）则直接解决，否则将其分解为k个规模较小的子问题，这些子问题互相独立且与原问题形式相同，递归地解这些子问题，然后将各子问题的解合并得到原问题的解。这种算法设计策略叫做分治法。

   如果原问题可分割成k个子问题，1 < k ≤ n，且这些子问题都可解并可利用这些子问题的解求出原问题的解，那么这种分治法就是可行的。由分治法产生的子问题往往是与原问题解决方式相同，但规模更小的子问题，这就为使用递归技术提供了方便。在这种情况下，反复应用分治手段，可以使子问题与原问题类型一致而其规模却不断缩小，最终使子问题缩小到很容易直接求出其解。这自然导致递归过程的产生。分治与递归像一对孪生兄弟，经常同时应用在算法设计之中，并由此产生许多高效算法。
  
  
  
  
  