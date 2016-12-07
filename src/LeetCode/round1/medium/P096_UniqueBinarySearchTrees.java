package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 161204 + 1207
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
For example, Given n = 3, there are a total of 5 unique BST's.
   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
   
*/
public class P096_UniqueBinarySearchTrees {

	/**
	 * DP. 161207
	 * AC: 0ms
	 */
	public int numTrees(int n) {
		if(n <= 0)
        	return 0;
		
		int res[] = new int[n + 1];
		res[0] = 1;
		res[1] = 1;
		for (int i = 2; i <= n ; i++) {
			int sum = 0;
			for (int j = 0; j < i;  j++) 
				sum += res[j] * res[i - j - 1];
			res[i] = sum;
		}
		return res[n];
	}
	
	/**
	 * timeout. 161204
	 * @param n
	 * @return
	 */
	public int numTrees_timeout(int n) {
        if(n <= 0)
        	return 0;
        List<int[]> trees = new ArrayList<int[]>();
        trees.add(new int[]{0});	//for n=1
        Set<String> visited = new HashSet<String>();
        for (int i = 0; i < n - 1; i++) {
        	List<int[]> newtrees = new ArrayList<int[]>();
        	for (int[] tree: trees) {
        		for (int j = tree.length - 1; j >= 0; j--) {
					int c = tree[j];
					if(c == 0){	//0: left, 1: has left child, 2: has right child, 3: has 2 children
						while(c < 2){
			        		int[] newTree = newInt(tree);
							newTree[j] = ++c;
							String newTreeStr = Arrays.toString(newTree);
							if(!visited.contains(newTreeStr)){
								visited.add(newTreeStr);
								newtrees.add(newTree);
							}
						}
					}else if(c != 3){	//c=1 or 2(1 child). next value can only be 3(2 children).
		        		int[] newTree = newInt(tree);
						newTree[j] = 3;
						String newTreeStr = Arrays.toString(newTree);
						if(!visited.contains(newTreeStr)){
							visited.add(newTreeStr);
							newtrees.add(newTree);
						}
					}
				}
			}
        	visited.clear();
        	trees = newtrees;
		}
        return trees.size();
    }
	private int[] newInt(int[] old){
		int[] newInt = new int[old.length + 1];
		for (int i = 0; i < old.length; i++) {
			newInt[i] = old[i]; 
		}
		return newInt;
	}
	
	public static void main(String[] args) {
		P096_UniqueBinarySearchTrees p = new P096_UniqueBinarySearchTrees();
		System.out.println(p.numTrees(5));
	}

}
