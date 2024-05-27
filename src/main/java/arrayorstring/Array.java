package arrayorstring;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author mi11
 * @version 1.0
 * @project algorithm
 * @description
 * @ClassName arrayorstring.Array
 */
public class Array {

    public static void main(String[] args) {
//        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
//        int[][] merge = merge(intervals);
//        for (int[] ints : merge) {
//            System.out.println(ints[0] + ":" + ints[1]);
//        }

        //
        //         {15, 14, 12, 16},
        //         {13, 3, 6, 7},
        //         {2, 4, 8, 10},
        //         {5, 1, 9, 11}

//
//        int[][] matrix = {
//                {5, 1, 9, 11},
//                {2, 4, 8, 10},
//                {13, 3, 6, 7},
//                {15, 14, 12, 16}
//        };
//        rotate(matrix);
        int [][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        findDiagonalOrder(matrix);
    }

    //对角线遍历
    //1）先得出遍历的次数，也就是对角线的条数为i=n+m-1，所以数组遍历条件也就是i<n+m-1。
    //（2）在看图，对角线上的每个元素坐标之和为i,也就是元素的坐标xy与i的关系为：x+y=i
    //（3）如何遍历？看图中，偶数对应的对角线上的元素是从下往上遍历，而奇数对应的对角线上的元素是从上往下遍历，那么只要确定遍历的起始点和结束点就好啦！我们先看偶数对角线的起点和终点，因为奇数对角线和它相反，知道了偶数的，也不难得出奇数的的。
    //
    //当i<n-1时，起始点坐标x=i，如1的x坐标为0，i也为0,结束点的横坐标x=0
    //当i>=n-1时，起始点坐标x=n-1,如2的x坐标为2，i也为2,结束点的纵坐标y=m-1,根据（2）中的关系式,所以得出横坐标x=i-(m-1)
    //所以偶数对角线遍历时起始点的x的坐标为min(i,n-1)，结束点的x坐标为max(0,i-(m-1)),而坐标y就是i-x
    //
    //作者：隔壁黄二狗
    //链接：https://leetcode.cn/leetbook/read/array-and-string/cuxq3/?discussion=A3eqBL
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public static int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[] result = new int[m * n];

        for (int i = 0; i < m + n - 1; i++) {
            if (i %2 == 0){
                for (int x = Math.min(i,m-1); x < Math.max(i,i-n+1) ; x++) {
                    result[i++] = mat[x][i-x];
                }
            }else {
                for (int x = Math.max(i,i-n+1); x < Math.max(i,m-1) ; x++) {
                    result[i++] = mat[x][i-x];
                }
            }
        }
        return result;
    }

    //零矩阵
    public static void setZeroes(int[][] matrix) {
        List<Pair<Integer,Integer>> pairList = new ArrayList<>();
        int row = matrix.length;
        int column = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == 0){
                    pairList.add(new Pair<>(i,j));
                }
            }
        }

        for (Pair<Integer, Integer> pair : pairList){
            Integer i = pair.getKey();
            Integer j = pair.getValue();
            for (int k = 0; k < column; k++) {
                matrix[i][k] = 0;
            }

            for (int k = 0; k < row; k++){
                matrix[k][j] = 0;
            }
        }
    }

    /**
     * 旋转矩阵
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        int length = matrix.length;
        //上下反转
        for (int i = 0; i < length / 2; i++) {
            int[] temp = matrix[i];
            matrix[i] = matrix[length - 1 - i];
            matrix[length - 1 - i] = temp;
        }
        //对角切换
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

    }

    /**
     * 区间查寻
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {


        if (intervals.length == 0) {
            return new int[0][2];
        }
        //根据元素中的第一个值进行排序
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        //定义返回的数组
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            //定义左右边界
            int left = intervals[i][0];
            int right = intervals[i][1];
            //数组中无元素或者数组中最后一个元素的有边界小于当前元素的左边界
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < left) {
                merged.add(new int[]{left, right});
            } else {
                //替换数组中最后一个元素的右边界
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], right);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }


}

