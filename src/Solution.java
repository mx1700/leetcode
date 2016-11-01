import java.util.*;

public class Solution {

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> points = new HashMap<>(nums.length);
        for(int i = 0; i < nums.length; i++) {
            int v = nums[i];
            int t = target - v;
            if (points.containsKey(t)) {
                return new int[] { points.get(t), i };
            } else {
                points.put(v, i);
            }
        }
        return new int[0];
    }

    public String addBinary(String a, String b) {
        String _long, _short;
        int aL, bL, lL, sL;
        aL = a.length();
        bL = b.length();

        if (aL >= bL) {
            _long = a;
            _short = b;
            lL = aL;
            sL = bL;
        } else {
            _long = b;
            _short = a;
            lL = bL;
            sL = aL;
        }
        StringBuilder sb = new StringBuilder(lL);
        int carry = 0;
        for(int iL = lL - 1, iS = sL - 1; iL >= 0; iL--, iS--) {
            int v1 = _long.charAt(iL) == '1' ? 1 : 0;
            int v2 = iS >= 0 ? (_short.charAt(iS) == '1' ? 1 : 0) : 0;
            int v = v1 + v2 + carry;
            switch (v) {
                case 0:
                    carry = 0;
                    sb.append("0");
                    break;
                case 1:
                    carry = 0;
                    sb.append("1");
                    break;
                case 2:
                    carry = 1;
                    sb.append("0");
                    break;
                case 3:
                    carry = 1;
                    sb.append("1");
                    break;
            }
        }
        if (carry == 1) {
            sb.append('1');
        }

        return sb.reverse().toString();
    }

    public int addDigits(int num) {
        if (num == 0) return 0;
        int r = num % 9;
        return r == 0 ? 9 : r;
    }

    public String addStrings(String num1, String num2) {
        String _long, _short;
        int aL, bL, lL, sL;
        aL = num1.length();
        bL = num2.length();

        if (aL >= bL) {
            _long = num1;
            _short = num2;
            lL = aL;
            sL = bL;
        } else {
            _long = num2;
            _short = num1;
            lL = bL;
            sL = aL;
        }
        StringBuilder sb = new StringBuilder(lL);
        int carry = 0;
        for(int iL = lL - 1, iS = sL - 1; iL >= 0; iL--, iS--) {
            int v1 = _long.charAt(iL) - 48;
            int v2 = iS >= 0 ? (_short.charAt(iS) - 48) : 0;
            int v = v1 + v2 + carry;
            carry = v / 10;
            sb.append(v - carry * 10);
        }
        if (carry > 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();
    }

    public String reverseString(String s) {
        //TODO:性能不行，看到一个用 while 循环，从两头开始交换的，可以减少循环次数
        //return new StringBuffer(s).reverse().toString();
//        StringBuffer sb = new StringBuffer(s.length());
//        for(int i = s.length() - 1; i >= 0; i--) {
//            sb.append(s.charAt(i));
//        }
//        return sb.toString();
        int len = s.length() - 1;
        char[] r = new char[len + 1];
        for(int i = len; i >= 0; i--) {
            r[len - i] = s.charAt(i);
        }
        return String.valueOf(r);
    }

    public List<String> fizzBuzz(int n) {
        ArrayList<String> result = new ArrayList<>(n);
        int threeIndex = 0, fiveIndex = 0;
        for(int i = 1; i <= n; i++) {
            threeIndex++;
            fiveIndex++;
            if (threeIndex == 3 && fiveIndex == 5) {
                threeIndex = 0;
                fiveIndex = 0;
                result.add("FizzBuzz");
            } else if (threeIndex == 3) {
                threeIndex = 0;
                result.add("Fizz");
            } else if (fiveIndex == 5) {
                fiveIndex = 0;
                result.add("Buzz");
            } else {
                result.add(Integer.toString(i));
            }
        }

        return result;
    }

    public int singleNumber(int[] nums) {
        int res = 0;
        for(int i : nums) {
            res ^= i;
        }
        return res;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return _maxDepth(root, 0);
    }

    private int _maxDepth(TreeNode root, int depth) {
        depth++;
        int left = depth, right = depth;
        if (root.left != null) {
            left = _maxDepth(root.left, depth);
        }
        if (root.right != null) {
            right = _maxDepth(root.right, depth);
        }
        return Math.max(left, right);
    }

    public char findTheDifference(String s, String t) {
        int len = s.length();
        int ret = 0;
        for(int i = 0; i < len; i++) {
            ret = ret - s.charAt(i) + t.charAt(i);
        }
        ret = ret + t.charAt(len);
        return (char)ret;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode t = new TreeNode(root.val);
        t.left = invertTree(root.right);
        t.right = invertTree(root.left);
        return t;
    }

    public void moveZeroes(int[] nums) {
        int len = nums.length, z = 0;
        for(int i = 0; i < len; i++) {
            int v = nums[i];
            if (v != 0) {
                if (z < i) {
                    nums[z] = v;
                }
                z++;
            }
        }
        for(int i = z; i < len; i++) {
            nums[i] = 0;
        }
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        int left = 0, right = 0;
        if (root.right != null) {
            right = sumOfLeftLeaves(root.right);
        }
        if(root.left != null) {
            if(root.left.left == null && root.left.right == null) {
                left = root.left.val;
            } else {
                left = sumOfLeftLeaves(root.left);
            }
        }
        return left + right;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }

    public int titleToNumber(String s) {
        int r = 0;
        s = new StringBuffer(s).reverse().toString();
        int len = s.length();
        for(int i = 0; i < len; i++) {
            int v = s.charAt(i) - 64;
            r += v * Math.pow(26, i);
        }
        return r;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] table = new int[123]; //z 是 122
        for(char i : magazine.toCharArray()) {
            table[i]++;
        }
        for (char i : ransomNote.toCharArray()) {
            if (table[i] == 0) {
                return false;
            }
            table[i]--;
        }
        return true;
    }
//
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;
        if (s.length() == 1) return 0;
        int[] table = new int[123];
        int r = 0, len = s.length();
        char cr = s.charAt(0);

        for(int i = 0; i < len; i++) {
            char v = s.charAt(i);
            table[v]++;
            if (v == cr || table[cr] > 1) {
                while (r < i) {
                    if (table[cr] > 1) {
                        r++;
                        cr = s.charAt(r);
                    } else {
                        break;
                    }
                }
            }
            System.out.println(i);
        }
        return table[cr] > 1 ? -1 : r;
    }

    public boolean isAnagram(String s, String t) {
        int sl = s.length(), tl = t.length();
        if (sl != tl) return false;

        int[] sTable = new int[123];
        int[] tTable = new int[123];

        for(char c : s.toCharArray()) {
            sTable[c]++;
        }

        for(char c : t.toCharArray()) {
            tTable[c]++;
        }

        for (int i = 'a'; i <= 'z'; i++) {
            if (sTable[i] != tTable[i]) return false;
        }
        return true;
    }

    public int longestPalindrome(String s) {
        int[] table = new int[123];
        for(char c : s.toCharArray()) {
            table[c]++;
        }
        int r = 0;
        boolean jishu = false;
        for(int i : table) {
            if (i % 2 == 1) {
                jishu = true;
                r += (i - 1);
            } else {
                r += i;
            }
        }
        return r + (jishu ? 1 : 0);
    }

    public int majorityElement(int[] nums) {
        //看答案了
        int count = 1, num = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if (nums[i] == num) {
                count++;
            } else {
                if (count > 1) {
                    count--;
                } else {
                    i++;
                    num = nums[i];
                }
            }
        }
        return num;
    }

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hs = new HashSet<>(nums.length * 2);
        for(int i : nums) {
            if(hs.contains(i)) {
                return true;
            } else {
                hs.add(i);
            }
        }
        return false;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode previous = head, cur = head.next, next;
        head.next = null;
        while(cur != null) {
            next = cur.next;
            cur.next = previous;
            previous = cur;
            cur = next;
        }
        return previous;
    }

    public String toHex(int num) {
        if (num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 8; i++) {
            long v = (num >> 28);
            num = num << 4;

            if (v >= 10) {
                sb.append((char)(v + 87));
            } else if(v > 0) {
                sb.append((char)(v + 48));
            } else {
                if (sb.length() > 0) sb.append('0');
            }
        }
        return sb.toString();
    }

    public boolean isPowerOfThree(int n) {
        //参考的答案
        return ( n>0 &&  1162261467%n==0);
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode node = head;
        while(node.next != null) {
            if(node.val == node.next.val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return head;
    }

    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int l = prices[0] ,h = prices[0], r = 0;
        int sl = l, sh = 0, sr = 0;
        for (int i: prices) {
            if (i > h) {
                h = i;
                r = h - l;
            } else if (i < l && i < sl) {
                sl = sh = i;
                sr = 0;
            }
            if (i > sh) {
                sh = i;
                sr = sh - sl;
            }
            if (sr > r) {
                l = sl;
                h = sh;
                r = sr;
            }
        }
        return r;
    }

    public int hammingWeight(int n) {
        int r = 0;
        for(int i = 0; i < 32; i++) {
            r += n & 1;
            n = n >> 1;
        }
        return r;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val > l2.val) {
            ListNode a = l1;
            l1 = l2;
            l2 = a;
        }
        ListNode r = l1;
        while(l2 != null) {
            if (l1.next == null || l2.val < l1.next.val) {
                ListNode next = l1.next;
                ListNode insert = l2;
                ListNode next_l2 = l2.next;
                l1.next = insert;
                insert.next = next;
                l1 = insert;
                l2 = next_l2;
            } else {
                l1 = l1.next;
            }
        }
        return r;
    }

    public boolean isPowerOfFour(int num) {
        //2^31 = 1073741824
        return num > 0 && 1073741824 % Math.sqrt(num) == 0;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        //性能太差，可以用二维数组加循环实现
        if (root == null) return new LinkedList<>();
        List<List<Integer>> left = levelOrderBottom(root.left),
                right = levelOrderBottom(root.right);
        List<List<Integer>> r = new LinkedList<>();
        if (left != null && right != null) {
            //两个数组合并入 r
            int lLen = left.size(), rLen = right.size();
            boolean leftLonger = lLen >= rLen;
            for(int il = leftLonger ? 0 : lLen - rLen, ir = leftLonger ? rLen - lLen : 0; il < lLen || ir < rLen; il++, ir++) {
                List<Integer> lList = il >= 0 && il < lLen ? left.get(il) : null;
                List<Integer> rList = ir >= 0 && ir < rLen ? right.get(ir) : null;
                if (lList != null && rList != null) {
                    lList.addAll(rList);
                    r.add(lList);
                } else if (lList != null) {
                    r.add(lList);
                } else if (rList != null) {
                    r.add(rList);
                }
            }
        } else if(left != null) {
            r.addAll(left);
        } else if (right != null) {
            r.addAll(right);
        }
        List<Integer> self = new LinkedList<>();
        self.add(root.val);
        r.add(self);
        return r;
    }

    public int treeDeep(TreeNode node) {
        if (node == null) return 0;
        int deep = 0;
        if (node.left == null && node.right == null) {
            deep = 1;
        } else {
            int deepLeft = treeDeep(node.left);
            int deepRight = treeDeep(node.right);
            deep = Math.max(deepLeft, deepRight) + 1;
        }

        return deep;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>(nums1.length);
        for(int i : nums1) {
            set.add(i);
        }
        Set<Integer> rSet = new HashSet<>();
        for (int i : nums2) {
            if (set.contains(i)) {
                rSet.add(i);
            }
        }
        int[] r = new int[rSet.size()];
        int i = 0;
        for(Integer v : rSet) {
            r[i] = v;
            i++;
        }
        return r;
    }

    static  int[] result = new int[100];
    public int climbStairs(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (result[n] != 0) {
            return result[n];
        }
        int r = climbStairs(n - 1) + climbStairs(n - 2);
        if (r > 2) {
            result[n] = r;
        }
        return r;
    }

    public boolean isPowerOfTwo(int n) {
        return n > 0 && Math.pow(2, 31) % n == 0;
    }

    public int arrangeCoins(int n) {
        //计算 1-x 的和等于n，求解 x
        return (int)((Math.sqrt(1 + 8 * n) - 1) / 2);
    }

    public List<String> readBinaryWatch(int num) {
        List<String> r = new ArrayList<>();
        for(int i = 0; i < 10 - num; i++) {
            for(int j = i; j < num; j++) {
                for(int k = j; k < 10; k++) {
                    System.out.println(i + "-" + j + "-" + k);
                }
            }
        }

//        for(int n = 0; n < num; n++) {
//            for(int i = 0; i < 10 && i > 10 - num; i++) {
//                for(int j = i + 1; j < 10; j++) {
//
//                }
//            }
//        }

        return r;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : nums1) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for(int i : nums2) {
            if (map.containsKey(i)) {
                list.add(i);
                int count = map.get(i) - 1;
                if (count > 0) {
                    map.put(i, count);
                } else {
                    map.remove(i);
                }
            }
        }
        int[] r = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            r[i] = list.get(i);
        }
        return r;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode r = head.next;
        head.next = r.next;
        r.next = head;

        head = r.next;
        while(head.next != null && head.next.next != null) {
            ListNode item1 = head.next, item2 = head.next.next;
            ListNode next = item2.next;
            head.next = item2;
            item2.next = item1;
            item1.next = next;
            head = item1;
        }
        return r;
    }

    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int i = 0; int j = chars.length - 1;
        while ( i < j) {
            char l = chars[i], r = chars[j];
            boolean lv = isVowels(l), rv = isVowels(r);
            if (lv && rv) {
                chars[i] = r;
                chars[j] = l;
                i++; j--;
            } else if(lv) {
                j--;
            } else {
                i++;
            }
        }
        return new String(chars);
    }

    private boolean isVowels(char v) {
        switch (v) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
                return true;
            default:
                return false;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return _isSymmetric(root.left, root.right);
    }

    private boolean _isSymmetric(TreeNode left, TreeNode right) {
        if (left == right) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return _isSymmetric(left.left, right.right) && _isSymmetric(left.right, right.left);
    }

    public boolean hasCycle(ListNode head) {
        //反转链表，如果头等于尾，则有环
        if (head == null || head.next == null) return false;
        ListNode pre = null, cur = head;
        while(cur != null) {
            ListNode newPre = cur;
            ListNode newCur = cur.next;
            cur.next = pre;
            pre = newPre;
            cur = newCur;
        }
        return pre != head;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> r = new LinkedList<>();
        if (numRows == 0) return r;
        List<Integer> r1 = new LinkedList<>();
        r1.add(1);
        r.add(r1);
        for(int i = 1; i < numRows; i++) {
            List<Integer> row = new LinkedList<>();
            List<Integer> pre = r.get(i - 1);

            row.add(1);
            for(int j = 0; j <= pre.size() - 2; j++) {
                row.add(pre.get(j) + pre.get(j + 1));
            }
            row.add(1);

            r.add(row);
        }

        return r;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new LinkedList<>();
        List<List<Integer>> left = levelOrder(root.left),
                right = levelOrder(root.right);
        List<List<Integer>> r = new LinkedList<>();
        List<Integer> self = new LinkedList<>();
        self.add(root.val);
        r.add(self);
        if (left != null && right != null) {
            //两个数组合并入 r
            int lLen = left.size(), rLen = right.size();
            for(int il = 0, ir = 0; il < lLen || ir < rLen; il++, ir++) {
                List<Integer> lList = il < lLen ? left.get(il) : null;
                List<Integer> rList = ir < rLen ? right.get(ir) : null;
                if (lList != null && rList != null) {
                    lList.addAll(rList);
                    r.add(lList);
                } else if (lList != null) {
                    r.add(lList);
                } else if (rList != null) {
                    r.add(rList);
                }
            }
        } else if(left != null) {
            r.addAll(left);
        } else if (right != null) {
            r.addAll(right);
        }
        return r;
    }

//    public boolean isBalanced(TreeNode root) {
//
//    }

//    public int removeElement(int[] nums, int val) {
//        int i = 0, j = nums.length - 1;
//        int r = 0;
//        while(i < j) {
//            int v = nums[i];
//            int end = nums[j];
//            if (v == val) {
//                if (end != val) {
//                    nums[i] = end;
//                    nums[j] = val;
//                    i++;
//                    j--;
//                    r++;
//                } else {
//                    j--;
//                    r++;
//                }
//            } else {
//                i++;
//            }
//        }
//        return r;
//    }

}
