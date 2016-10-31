import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    static TreeNode createFromArray(Integer[] array) {
        if (array.length == 0) return null;
        TreeNode root = new TreeNode(array[0]);
        List<TreeNode> layer = new ArrayList<>();
        layer.add(root);
        int i = 1, c = 1;
        while(i < array.length) {
            int len = (int)Math.pow(2, c);
            List<TreeNode> newLayer = new ArrayList<>();
            for(int j = 0; j <  len; j = j + 2) {
                Integer leftVal = array[j + i];
                Integer rightVal = array[j + i + 1];
                TreeNode father = layer.get(j / 2);
                TreeNode left = leftVal != null ? new TreeNode(leftVal) : null;
                TreeNode right = rightVal != null ? new TreeNode(rightVal) : null;
                father.left = left;
                father.right = right;
                newLayer.add(left);
                newLayer.add(right);
            }
            layer = newLayer;
            i += len; c++;
        }
        return root;
    }

    @Override
    public String toString() {
        String s = this.val + "";
        if (this.left == null && this.right == null) return s;
        s += " -> [";
        s += this.left != null ? this.left.toString() : "null";
        s += ", ";
        s += this.right != null ? this.right.toString() : "null";
        return s + "]";
    }
}

