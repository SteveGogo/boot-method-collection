package com.example.qrcodedemo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class QrCodeController {

    private static final String FileFormat = ".png";

    private static final ThreadLocal<SimpleDateFormat> LOCALDATEFORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMddHHmmss"));

    //生成二维码并将其存放于本地目录
    @PostMapping("/v1")
    public String generateV1(String content) {
        try {
            final String fileName = LOCALDATEFORMAT.get().format(new Date());
            String path = QrcodeUtil.createCodeToFile("https://brandgogo.com/", "qr/" + fileName + FileFormat);
            return path;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/v2")
    public void generateV2(String content, HttpServletResponse servletResponse) {
        try {
            ServletOutputStream outputStream = servletResponse.getOutputStream();
            QrcodeUtil.createCodeToOutputStream("https://brandgogo.com/", outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

