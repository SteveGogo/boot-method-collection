package com.example.coding;

import cn.hutool.core.util.HexUtil;

public class main2 {
    public static void main(String[] args) {
        //48573830372d
        String s="48573830372d";
        byte[] bytes= HexUtil.decodeHex(s);
        String s1 = bytesToHexString(bytes);
        System.out.println(s1);

    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
