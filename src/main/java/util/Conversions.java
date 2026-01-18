package util;

import model.Status;

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
    public static Status makeStatus(String s) {

        if (s.equals("WRITING")) return Status.WRITING;
        else if (s.equals("REVIEWING")) return Status.REVIEWING;
        else if (s.equals("EDITING")) return Status.EDITING;
        else if (s.equals("PUBLISHED")) return Status.PUBLISHED;
        else if (s.equals("PUBLISHED_AND_BORROWED")) return Status.PUBLISHED_AND_BORROWED;
        else return Status.NULL;

    }
    public static  String makeStatusString(Status s) {
        if (s == Status.WRITING) return "WRITING";
        else if (s == Status.REVIEWING) return "REVIEWING";
        else if (s == Status.EDITING) return "EDITING";
        else if (s == Status.PUBLISHED) return "PUBLISHED";
        else if (s == Status.PUBLISHED_AND_BORROWED) return "PUBLISHED_AND_BORROWED";
        else return "NULL";
    }
}
