package LeetCode.round3;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

import java.util.*;

/**
 * 240804 二叉树的最近公共祖先
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q
 * as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 *
 * Example 3:
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 * Constraints:
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q will exist in the tree.
 */
public class P236_LowestCommonAncestorOfBinaryTree {

    /**
     * AC: 44ms Beats 5.03%, Memory 48.86MB Beats 5.61%
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //构建每层节点的map
        Map<Integer, Set<TreeNode>> levelMap = new LinkedHashMap<>();
        Set<TreeNode> levelNodes = new HashSet<>();
        levelNodes.add(root);
        boolean pFind = false, qFind = false;
        int level = 1;
        levelMap.put(level, levelNodes);
        while (!levelNodes.isEmpty()){
            Set<TreeNode> newLevelNodes = new HashSet<>();
            for (TreeNode node :levelNodes) {
                if(node.val == p.val)
                    pFind = true;
                if(node.val == q.val)
                    qFind = true;
                if(node.left != null)
                    newLevelNodes.add(node.left);
                if(node.right != null)
                    newLevelNodes.add(node.right);
            }
            if(pFind && qFind)
                break;
            levelNodes = newLevelNodes;
            levelMap.put(++ level, levelNodes);
        }

        //寻找P、Q的祖先
        List<TreeNode> pAncestors = findAncestors(p, levelMap);
        List<TreeNode> qAncestors = findAncestors(q, levelMap);

        //根据P、Q的祖先，寻找最低公共祖先
        Iterator<TreeNode> pIterator = pAncestors.iterator(), qIterator = qAncestors.iterator();
        TreeNode res = new TreeNode();
        while(pIterator.hasNext() && qIterator.hasNext()){
            TreeNode pNode = pIterator.next(), qNode = qIterator.next();
            if(pNode == qNode) {
                res = pNode;
            }
        }
        return res;
    }

    /**
     * 寻找node的祖先
     * @param node 寻找node的祖先
     * @param levelMap 每层包含节点情况的map
     * @return node祖先链条（从下到上），可以包括自己
     */
    private List<TreeNode> findAncestors(TreeNode node, Map<Integer, Set<TreeNode>> levelMap){
        List<TreeNode> res = new ArrayList<>();
        int level = 0;
        //先找到节点所在level
        for(Integer curLevel: levelMap.keySet()){
            Set<TreeNode> levelNodes = levelMap.get(curLevel);
            if(levelNodes.contains(node)){
                level = curLevel;
                res.add(0, node);   //自己也是自己的祖先
                break;
            }
        }
        //从当前level往上，依次找父节点
        for (int i = level - 1; i > 0 ; i--) {
            Set<TreeNode> parentNodes = levelMap.get(i);
            TreeNode son = res.get(0);
            for(TreeNode parent: parentNodes){
                if(parent.left == son || parent.right == son){
                    res.add(0, parent);
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        P236_LowestCommonAncestorOfBinaryTree ps = new P236_LowestCommonAncestorOfBinaryTree();
        TreeNode root = BuildTreeFromArrayUtil.build(new Integer[]{1,2,null});
        TreeNode p = root, q = root.left;
        System.out.println(ps.lowestCommonAncestor(root, p, q));
    }
}
