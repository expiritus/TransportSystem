package com.belhard.misha.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class StringUtils {

    private StringUtils(){
        throw new InstantiationError("Class contains static methods only. You should not instantiate it!");
    }

    public static boolean isEmpty(String str){
        if(str != null){
            return str.equals("");
        }
        return true;
    }

    public static boolean isBlank(String str){
        if(!isEmpty(str)){
            return str.trim().equals("");
        }

        return true;
    }

    public static String MD5(String password) {
        String str = "";
        try {
            MessageDigest ms = MessageDigest.getInstance("MD5");
            ms.reset();
            ms.update(password.getBytes());
            byte[] msDigest = ms.digest();
            BigInteger number = new BigInteger(1, msDigest);
            str = number.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    }
}
