package com.agiantii.backend.utils;

import java.util.List;

public class SimpleUtil {
    public static String succcessMessage(List<?> list){
        return String.format("查询成功 共%d 条数据",list.size());
    }

}
