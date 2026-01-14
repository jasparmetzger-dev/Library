package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Conversions {

    public static List<String> makeStrLst(String s) {
        String[] arr = s.split(",");
        List<String> lst = new ArrayList<>();
        for (String str : arr) {
            lst.add(str.trim().toLowerCase());
        }
        return lst;
    }

    public static List<Integer> makeStrIntLst(String s) {
        String[] arr = s.split(",");
        List<Integer> lst = new ArrayList<>();
        for (String str : arr) {
            lst.add(Integer.parseInt(str.trim()));
        }
        return lst;
    }
    public static String makeIntLstStr(List<Integer> ints) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ints.size(); i++) {
            sb.append(Integer.toString(ints.get(i)));
            if (i < ints.size() - 1) {sb.append(",");}
        }
        return sb.toString();
    }
}
