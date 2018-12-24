package com.neo.utility;

import java.util.Arrays;
import java.util.List;

public class StringOperations {

    public static List removeBlankValue(List list){
        list.removeAll(Arrays.asList("", null));
        return list;
    }

    public static boolean isValueAvailableInList(String val, List<String> list){
        for (int i=0;i<list.size();i++){
            if(val.equals(list.get(i))){
                return true;
            }
        }
        return false;
    }

    public static int getIndexOfDataInList(String val, List<String> list){
        return list.indexOf(val);
    }
}
