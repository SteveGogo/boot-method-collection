package com.example.coding;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.HexUtil;

import java.util.*;

public class testMain {
    public static void main(String[] args) {
        String message = "3003010000001A39250E0948573830372D3033333935373102010603030D1807FF000000006400EC70168EBC19CF23A0";
        System.out.println(message);
        byte[] packet = HexUtil.decodeHex(message);
        System.out.println(packet);
        int length = packet.length;
        if (HexUtil.hexToInt(HexUtil.encodeHexStr(Arrays.copyOfRange(packet, 0, 1))) != length) {
            System.out.println("packet数据长度有问题");
            return;
        }

        int indexOfLen = 0;
        int indexOfCmdID = 1;
        int indexOfInfo_version = 2;
        int indexOfInfo_Reserved = 3;
        int hubIdStart = 4;
        int hubIdEnd = 8;
        int CRC16_start = length - 2;
        int CRC16_end = length;

        int dada_start = 8;
        int data_end = length - 2;


        byte len = packet[indexOfLen];
        byte cmdId = packet[indexOfCmdID];
        byte version = packet[indexOfInfo_version];
        byte reserved = packet[indexOfInfo_Reserved];
        byte[] hubBytes = Arrays.copyOfRange(packet, hubIdStart, hubIdEnd);
        //接收器hub设备ID
        String hubIDHex = HexUtil.encodeHexStr(hubBytes, false);
        int hubId = HexUtil.hexToInt(hubIDHex);

        byte[] crc16Bytes = Arrays.copyOfRange(packet, CRC16_start, CRC16_end);
        String crcStr = HexUtil.encodeHexStr(crc16Bytes, false);

        byte[] dataBytes = Arrays.copyOfRange(packet, dada_start, data_end);
        String dataStr = HexUtil.encodeHexStr(dataBytes, false);
        System.out.println(dataStr);
        //蓝牙广播数据
        if (cmdId == 3) {
            System.out.println("蓝牙广播数据");
            List<byte[]> frameList = new ArrayList<>();
            int i = 0;
            byte[] temp = dataBytes;
            while (temp.length > 0) {
                byte frameLen = temp[0];
                byte[] frame = Arrays.copyOfRange(temp, 0, frameLen + 1);
                frameList.add(frame);
                temp = Arrays.copyOfRange(temp, frameLen + 1, temp.length);
            }
            if (CollectionUtil.isNotEmpty(frameList)) {
                for (byte[] frameBytes : frameList) {
                    Frame frame = new Frame();
                    int frameLen = frameBytes.length;
                    frame.setLen(frameLen);
                    byte[] advertisingData = Arrays.copyOfRange(frameBytes, 1, frameLen - 7);
                    byte rssibyte = frameBytes[frameLen - 7];
                    byte[] macBytes = Arrays.copyOfRange(frameBytes, frameLen - 6, frameLen);
                    int index = 0;
                    Map<String, String> partMap = new HashMap<>();
                    while (index < advertisingData.length) {
                        byte partLen = advertisingData[index];
                        if (partLen > 0) {
                            byte[] partType = Arrays.copyOfRange(advertisingData, index + 1, index + 2);
                            String partTypeHexStr = HexUtil.encodeHexStr(partType);
                            byte[] partData = Arrays.copyOfRange(advertisingData, index + 2, index + 1 + partLen);
                            partMap.put(partTypeHexStr, HexUtil.encodeHexStr(partData));
                        }
                        index += partLen + 1;
                    }
                    System.out.println(partMap);
                }
            }
        }
        //电量心跳包
        if (cmdId == 2) {
            System.out.println("电量心跳包:" + message);
            //解析心跳包数据
            System.out.println("hubId:" + hubId + " 电量:" + packet[8]);
        }

    }


}
