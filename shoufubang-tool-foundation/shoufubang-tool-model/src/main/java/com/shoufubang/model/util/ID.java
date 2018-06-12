package com.shoufubang.model.util;

import java.util.UUID;

/**
 * Created by 陈蓓 on 2015/11/6.
 */
public class ID
{
    /**
     * 生成一个主键值
     * @return 一个32位字符的十六进制大写主键
     */
    public static synchronized String get()
    {
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }
}
