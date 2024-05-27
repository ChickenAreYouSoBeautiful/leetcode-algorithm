package arrayorstring;

/**
 * @author mi11
 * @version 1.0
 * @project algorithm
 * @description 字符串
 * @ClassName Str
 */
public class Str {

    /**
     * 最长公共前缀
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        //获取最短字符串长度
        int minStr = strs[0].length();

        for (int i = 1; i < strs.length; i++) {
            if (minStr > strs[i].length()) {
                minStr = strs[i].length();
            }
        }

        //最小字符串的长度，遍历字符串数组，取它们字符判断
        StringBuilder resStr = new StringBuilder();
        for (int i = 0; i < minStr; i++) {
            char chars = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (chars != strs[j].charAt(i)) {
                    return resStr.toString();
                }
            }
            resStr.append(chars);
        }
        return resStr.toString();
    }
}
