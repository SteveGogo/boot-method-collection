package com.example.coding;


/**
 * @author zhangqianwei
 * @date 2021/10/21 20:47
 */
public class HexToASCToBytes {
    //十六进制字符串与byte数组相互转换，常用于socket通信
    public static void main(String[] args) {
        String s = "3003010000001A39250E0948573830372D3033333935373102010603030D1807FF00000F006400E670168EBC19CF373A";
        byte[] bytes = hexStringToByteArray(s);
        System.out.println("十六进制字符串转byte数组："+bytes);
        String hex = ByteArrayToHexString(bytes);
        System.out.println("byte数组转十六进制字符串："+hex);
        String ASC = convertHexToASC(s);
        System.out.println("十六进制字符串转ASCII码："+ASC);
        String hex2 = ASCToHex(ASC);
        System.out.println("ASCII码转十六进制字符串："+hex2);
    }

    //十六进制字符串转byte数组
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] ba = new byte[len / 2];

        for(int i = 0; i < ba.length; ++i) {
            int j = i * 2;
            int t = Integer.parseInt(s.substring(j, j + 2), 16);
            byte b = (byte)(t & 255);
            ba[i] = b;
        }

        return ba;
    }

    //byte数组转十六进制字符串以小写字母形式显示
    public static String ByteArrayToHexString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    //ASCII码转十六进制字符串
    public static String ASCToHex(String str){
        char[] chars = str.toCharArray();
        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < chars.length; i++){
            hex.append(Integer.toHexString((int)chars[i]));
        }
        return hex.toString();
    }

    //16进制字符串转ASCII码
    public static String convertHexToASC(String hex){
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for( int i=0; i<hex.length()-1; i+=2 ){
            String output = hex.substring(i, (i + 2));
            int decimal = Integer.parseInt(output, 16);
            sb.append((char)decimal);
            temp.append(decimal);
        }
        return sb.toString();
    }
}