import java.util.*;

// Minimum depth of binary tree

public class bfs {
    public static void main(String args[]){
        TreeNode root = new TreeNode(2, new TreeNode(1, new TreeNode(1), new TreeNode(1)), new TreeNode(3, new TreeNode(0), new TreeNode(1)));
        System.out.println(findMinimumDepth(root));
    }

    public static int findMinimumDepth(TreeNode root) {
        List<TreeNode> width = new ArrayList<>();
        width.add(root);
        int result = 0;

        while (width.size() > 0) {
            System.out.println(width.size());
            result++;
            for (TreeNode treeNode : width) {
                if(treeNode.left == null && treeNode.right == null){
                    return result;
                }
            }
            width = new ArrayList<>();
            for (TreeNode treeNode : width) {
                if(treeNode.left != null){
                    width.add(treeNode.left);
                }
                if(treeNode.right != null){
                    width.add(treeNode.right);
                }
            }
        }
        System.out.println(width.size());

        return result;
    }
}