package com.example.coding;

import lombok.Data;

@Data
public class Frame {
    private Integer len;
    private String dataStr;
    private byte[] data;
    private Integer rssi;
    private String mac;
}
