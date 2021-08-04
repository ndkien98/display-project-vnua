package com.fita.project.ulti;

import java.util.List;

public final class CommonUtil {


    public static boolean isNull(Object obj){
        if (obj == null) return true;
        return false;
    }

    public static boolean isNullOrEmpty(List<Object> list){
        if (list == null) return true;

        if (list.isEmpty()) return true;

        return false;
    }
}
