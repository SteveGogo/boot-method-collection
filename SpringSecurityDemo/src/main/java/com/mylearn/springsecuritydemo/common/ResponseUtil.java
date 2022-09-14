package com.mylearn.springsecuritydemo.common;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson2.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ResponseUtil {
    private static final Logger log = LoggerFactory.getLogger(ResponseUtil.class);

    public ResponseUtil() {
    }

    public static void out(Result result) {
        out(200, result, (Map) null);
    }

    public static void out(int statusCode, Result result) {
        out(statusCode, result, (Map) null);
    }

    public static void outWithHeader(int statusCode, Result result, Map<String, String> map) {
        out(statusCode, result, map);
    }

    public static void out(int statusCode, Result result, Map<String, String> header) {
        ServletOutputStream out = null;
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (servletRequestAttributes != null) {
                HttpServletResponse response = servletRequestAttributes.getResponse();
                if (response != null && !response.isCommitted()) {
                    //如果发现乱码,这里注释
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json;charset=UTF-8");
                    response.setStatus(statusCode);
                    if (MapUtil.isNotEmpty(header)) {
                        header.forEach(response::setHeader);
                    }
                    out = response.getOutputStream();
                    out.write(JSON.toJSONString(result).getBytes());
                }
            }
        } catch (Exception var14) {
            log.error("[ResponseUtil] 响应出错 ", var14);
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException var13) {
                    log.error("关闭流出错 ", var13);
                }
            }

        }

    }
}