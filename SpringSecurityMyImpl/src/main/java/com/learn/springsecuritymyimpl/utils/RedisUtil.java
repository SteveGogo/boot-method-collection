package com.learn.springsecuritymyimpl.utils;

import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.domain.geo.Metrics;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 */
@Component
public final class RedisUtil {
    @Resource
    public RedisTemplate<String, Object> redisTemplate;

    // =============================common============================

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }
    }
    // ============================String=============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将值 value 关联到 key ，并将 key 的过期时间设为 timeout
     *
     * @param timeout 过期时间
     * @param unit    时间单位, 天:TimeUnit.DAYS 小时:TimeUnit.HOURS 分钟:TimeUnit.MINUTES
     *                秒:TimeUnit.SECONDS 毫秒:TimeUnit.MILLISECONDS
     */
    public void setEx(String key, String value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    // ================================Map=================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */

    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }
    // ============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    // ===============================list=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {

            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 一个个增加地理位置
     *
     * @param key       键值
     * @param longitude 经度
     * @param latitude  纬度
     * @param member    成员
     * @return 返回添加的行数 为 -1 则添加失败
     */
    public long geoAdd(String key, Double longitude, Double latitude, Object member) {
        if (key == null || "".equals(key)) {
            throw new RuntimeException("key值不能为空！");
        }
        if (member == null || "".equals(member)) {
            throw new RuntimeException("成员不能为空！");
        }
        if (longitude == null || latitude == null) {
            throw new RuntimeException("经纬度不能为空！");
        }

        try {
            Point point = new Point(longitude, latitude);
            System.out.println(point);
            Long l = redisTemplate.opsForGeo().add(key, point, member);
            if (l == null) {
                return -1;
            } else {
                return l;
            }
        } catch (RedisSystemException e) {
            System.out.println("经纬度有误！");
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 一个个增加地理位置
     *
     * @param key         键值
     * @param geoLocation 存放成员 member 和 经纬度的 point
     * @return 返回添加的行数 为 -1 则添加失败
     */
    public long geoAdd(String key, RedisGeoCommands.GeoLocation<Object> geoLocation) {
        if (key == null || "".equals(key)) {
            throw new RuntimeException("key值不能为空！");
        }

        try {
            Long l = redisTemplate.opsForGeo().add(key, geoLocation);
            if (l == null) {
                return -1;
            } else {
                return l;
            }
        } catch (RedisSystemException e) {
            System.out.println("经纬度有误！");
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 一次性添加多个位置信息
     *
     * @param key                 键值
     * @param memberCoordinateMap 存放位置信息
     * @return 添加的行数 -1 为全部都添加失败
     */
    public long geoAdd(String key, Map<Object, Point> memberCoordinateMap) {
        if (key == null || "".equals(key)) {
            throw new RuntimeException("key值不能为空！");
        }
        try {
            Long l = redisTemplate.opsForGeo().add(key, memberCoordinateMap);
            if (l == null) {
                return -1;
            } else {
                return l;
            }
        } catch (RedisSystemException e) {
            System.out.println("经纬度有误！");
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取两个地点之间的距离
     *
     * @param key     键值
     * @param member1 成员 1
     * @param member2 成员 2
     * @return 两个之间的距离, 单位为米（M/m） 如果成员不存在则返回{@literal null}
     */
    public Distance geoDistance(String key, Object member1, Object member2) {
        if (key == null || "".equals(key)) {
            throw new RuntimeException("key值不能为空！");
        }
        try {
            return redisTemplate.opsForGeo().distance(key, member1, member2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取两个地点之间的距离
     *
     * @param key     键值
     * @param member1 成员 1
     * @param member2 成员 2
     * @param metrics 指定单位（默认为米） 千米(km)  英里(mi)
     * @return 两个之间的距离, 单位为米（M/m） 如果成员不存在则返回{@literal null}
     */
    public Distance geoDistance(String key, Object member1, Object member2, Metrics metrics) {
        if (key == null || "".equals(key)) {
            throw new RuntimeException("key值不能为空！");
        }
        try {
            return redisTemplate.opsForGeo().distance(key, member1, member2, metrics);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回geohash geohash：基本原理是将地球理解为一个二维平面，将平面递归分解成更小的子块，
     * 每个子块在一定经纬度范围内拥有相同的编码
     *
     * @param key     键值
     * @param members 成员值
     * @return 返回成员对应的geohash 如果成员不存在则geohash为null
     */
    public List<String> geoHash(String key, Object... members) {
        if (key == null || "".equals(key)) {
            throw new RuntimeException("key值不能为空！");
        }
        return redisTemplate.opsForGeo().hash(key, members);
    }

    /**
     * 返回地点的经纬度
     *
     * @param key     键值
     * @param members 成员值
     * @return 返回成员对应的Point(经纬度) 如果成员不存在则为null
     */
    public List<Point> geoPosition(String key, Object... members) {
        if (key == null || "".equals(key)) {
            throw new RuntimeException("key值不能为空！");
        }
        return redisTemplate.opsForGeo().position(key, members);
    }

    /**
     * 返回指定点 指定范围内存在的成员
     *
     * @param key    键值
     * @param within Circle 对象 Point（center） 中心点经纬度，Distance（radius）查找的范围
     * @return GeoResults对象
     */
    public GeoResults<RedisGeoCommands.GeoLocation<Object>> geoRadius(String key, Circle within) {
        if (key == null || "".equals(key)) {
            throw new RuntimeException("key值不能为空！");
        }
        return redisTemplate.opsForGeo().radius(key, within);
    }

    /**
     * 返回指定点 指定范围内存在的成员,并指定最多显示多少个成员
     *
     * @param key    键值
     * @param within Circle 对象 Point（center） 中心点经纬度，Distance（radius）查找的范围
     * @param args   includeCoordinates() 返回成员的坐标 includeDistance() 返回距离中心点的距离 同时可以设置最多显示的个数 和升序还是降序排列
     * @return GeoResults对象 可以使用GeoRadiusCommandArgs对象进行设计显示坐标和距离
     */
    public GeoResults<RedisGeoCommands.GeoLocation<Object>> geoRadius(String key, Circle within, RedisGeoCommands.GeoRadiusCommandArgs args) {
        if (key == null || "".equals(key)) {
            throw new RuntimeException("key值不能为空！");
        }
        return redisTemplate.opsForGeo().radius(key, within, args);
    }


    /**
     * 返回改成员指定范围内中存在的其他成员 包含自身
     *
     * @param key    键值
     * @param member 成员
     * @param radius 距离 默认米
     * @return GeoResults对象
     */
    public GeoResults<RedisGeoCommands.GeoLocation<Object>> geoRadius(String key, Object member, double radius) {
        if (key == null || "".equals(key)) {
            throw new RuntimeException("key值不能为空！");
        }
        return redisTemplate.opsForGeo().radius(key, member, radius);
    }

    /**
     * 返回改成员指定范围内中存在的其他成员 包含自身
     *
     * @param key      键值
     * @param member   成员
     * @param distance 距离和指定单位
     * @return GeoResults对象 包含自身
     */
    public GeoResults<RedisGeoCommands.GeoLocation<Object>> geoRadius(String key, Object member, Distance distance) {
        if (key == null || "".equals(key)) {
            throw new RuntimeException("key值不能为空！");
        }
        return redisTemplate.opsForGeo().radius(key, member, distance);
    }

    /**
     * 返回改成员指定范围内中存在的其他成员 包含自身
     *
     * @param key      键值
     * @param member   成员
     * @param distance 距离和指定单位
     * @param args   includeCoordinates() 返回成员的坐标 includeDistance() 返回距离中心点的距离 同时可以设置最多显示的个数 和升序还是降序排列
     * @return GeoResults对象 包含自身
     */
    public GeoResults<RedisGeoCommands.GeoLocation<Object>> geoRadius(String key, Object member, Distance distance, RedisGeoCommands.GeoRadiusCommandArgs args) {
        if (key == null || "".equals(key)) {
            throw new RuntimeException("key值不能为空！");
        }
        return redisTemplate.opsForGeo().radius(key, member, distance, args);
    }

    /**
     * 删除成员
     * @param key 键值
     * @param members 成员 可变参数
     * @return 返回删除的个数 -1为删除失败
     */
    public long geoRemove(String key, Object... members) {
        if (key == null || "".equals(key)) {
            throw new RuntimeException("key值不能为空！");
        }
        Long remove = redisTemplate.opsForGeo().remove(key, members);
        if (remove == null) {
            return -1;
        } else {
            return remove;
        }
    }
}