package com.example.nettysocketiodemo.config;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;


@Service
@Slf4j
public class TestSocketIOServiceImpl implements TestSocketIOService {

    // 使用ConcurrentMap 存储客户端
    public static ConcurrentMap<String, SocketIOClient> connectMap = new ConcurrentHashMap<>();
    // 发送消息的通道
    public final static String SEND_CHANNEL = "send_channel";

    @Autowired
    private SocketIOMessageEventHandler socketIOMessageEventHandler;


    /**
     * description: 测试通道连接
     * date: 2021年-09月-09日 14:26
     * author: zhanghang
     *
     * @param client
     * @param request
     * @return void
     */
    @OnEvent(value = "test-channel-connetListener")
    public void startOrderDetailChangeListener(SocketIOClient client, AckRequest request, String username) {
        // 数据校验
        if (null == username) {
            return;
        }
        if (username.length() == 0) {
            return;
        }
        String sessionId = client.getSessionId().toString();
        log.info("SocketIO-消息通知-新增连接-sessionId:" + client.getSessionId());
        connectMap.put(username + "~" + sessionId, client);
    }

    /**
     * description: 给容器内所有的客户端发送通知
     * date: 2021年-09月-09日 14:10
     * author: zhanghang
     *
     * @param msg
     * @return void
     */
    @Override
    public void sendMessageToAllUser(Map<String, Object> msg) {
        if (connectMap.isEmpty()) {
            return;
        }
        connectMap.entrySet().forEach(entry -> {
            entry.getValue().sendEvent(SEND_CHANNEL, msg);
        });
    }

    /**
     * description: 给指定用户发送通知
     * date: 2021年-09月-09日 14:10
     * author: zhanghang
     *
     * @param username
     * @param msg
     * @return void
     */
    @Override
    public void sendMessage(String username, Map<String, Object> msg) {
        SocketIOClient socketClient = getSocketClientByUsername(username);
        if (null != socketClient) {
            socketClient.sendEvent(SEND_CHANNEL, msg);
        }
    }

    /**
     * description: 根据用户找到对应的客户端
     * date: 2021年-09月-09日 14:33
     * author: zhanghang
     *
     * @param
     * @return com.corundumstudio.socketio.SocketIOClient
     */
    public SocketIOClient getSocketClientByUsername(String username) {
        SocketIOClient client = null;
        if (null == username) {
            return client;
        }
        if (connectMap.isEmpty()) {
            return client;
        }
        for (String key : connectMap.keySet()) {
            if (username.equals(key.split("~")[0])) {
                client = connectMap.get(key);
            }
        }
        return client;
    }

    /**
     * description: 观察者模式中的通知
     * date: 2021年-09月-09日 14:10
     * author: zhanghang
     *
     * @param o
     * @param arg
     * @return void
     */
    @Override
    public void update(Observable o, Object arg) {
        if (!(o instanceof SocketIOMessageEventHandler)) {
            return;
        }
        Map<String, Object> map = new HashMap<>();
        if (arg instanceof Map) {
            map = (Map<String, Object>) arg;
        }
        log.info("TestSocketIOServiceImpl{}客户端接收到通知：" + map.toString());
//        Map<String, Object> map = (Map<String, Object>) arg;
//		String type = MapUtil.getStr(map, "type");
        Object type = map.get("type");
        if (null == type) {
            return;
        }
        if (type.equals("disconnect")) { // 断开连接
            this.disconnect(map);
        }
    }

    /**
     * description: 断开连接
     * date: 2021年-09月-09日 14:23
     * author: zhanghang
     *
     * @param map
     * @return void
     */
    private void disconnect(Map<String, Object> map) {
//		String sessionId = MapUtil.getStr(map, "sessionId");
        Object sessionId = map.get("sessionId");
        if (null == sessionId) {
            return;
        }

        List<String> keyList = connectMap.keySet().parallelStream().filter(k -> k.split("~")[1].equals(sessionId.toString())).collect(Collectors.toList());

        if (null != keyList && keyList.size() > 0) {
            connectMap.remove(keyList.get(0));
        }
    }

    /**
     * description: 注册进观察者模式
     * date: 2021年-09月-09日 14:10
     * author: zhanghang
     *
     * @param
     * @return void
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // spring 为bean提供了两种初始化Bean的方法，1，在配置文件中指定init-metho方法。2，实现InitializingBean接口，实现afterPropertiesSet()方法
        // 只要实现了 InitializingBean 接口，Spring 就会在类初始化时自动调用该afterPropertiesSet()方法

        // 将当前对象注册进观察者模式中
        socketIOMessageEventHandler.addObserver(this);
    }
}


