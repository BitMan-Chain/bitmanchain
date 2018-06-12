package com.shoufubang.model.util;

public class DecideUtils {
	
	public static boolean is_number(String number) {  
        if(number==null) return false;  
        return number.matches("[+-]?[1-9]+[0-9]*(\\.[0-9]+)?");      
    }  
      
    public static boolean is_alpha(String alpha) {  
        if(alpha==null) return false;  
        return alpha.matches("[a-zA-Z]+");      
    }  
      
    public static boolean is_chinese(String chineseContent) {  
        if(chineseContent==null) return false;  
        return chineseContent.matches("[\u4e00-\u9fa5]");  
    }  

}
