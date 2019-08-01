package com.huqi.qs.main;

import com.sun.xml.internal.ws.api.message.Attachment;
import redis.clients.jedis.Jedis;

import javax.management.RuntimeErrorException;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.stream.Collectors;

/**
 * @author huqi
 */
public class MainRedis {
    private static Jedis connect() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        jedis.auth("123456");
        //查看服务是否运行
        System.out.println("连接成功: " + jedis.ping());
        return jedis;
    }

    private static void setContent190125(Jedis jedis) {
        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");

        //存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
    }

    private static void getContent190125(Jedis jedis) {
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: " + jedis.get("runoobkey"));

        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0, 2);
        for (String i : list) {
            System.out.println("列表项为: " + i);
        }

        // 获取数据并输出
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
    }

    private static void getContent190727(Jedis jedis) {
        // 数据是否存在
        System.out.println("runoobkey 是否存在: " + jedis.exists("runoobkey"));
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: " + jedis.get("runoobkey"));
        // 删除数据
        System.out.println("删除成功：" + jedis.del("runoobkey"));
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: " + jedis.get("runoobkey"));

        // 批量读写
        System.out.println("批量写入：" + jedis.mset("runoobkey", "new value", "key001", "value001"));
        System.out.println("批量读取：" + jedis.mget("runoobkey", "key001", "key002", "key003"));

        // 设置过期时间
        System.out.println("设置过期时间：" + jedis.expire("key001", 3));
        System.out.println("写入数据并设置超时时间：" + jedis.setex("key004", 3, "value004"));
        System.out.println("超时前：" + jedis.mget("key001", "key004"));
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("超时后：" + jedis.mget("key001", "key004"));

        // key不存在则创建，存在则跳过
        System.out.println(jedis.setnx("key005", "value005"));
        System.out.println(jedis.get("key005"));
        System.out.println(jedis.setnx("key005", "new value005"));
        System.out.println(jedis.get("key005"));

        // 计数
        System.out.println(jedis.set("key006", "6"));
        System.out.println(jedis.incr("key006"));
        System.out.println(jedis.incrBy("key006", 5));
        System.out.println(jedis.get("key006"));
        System.out.println(jedis.set("key007", Long.MAX_VALUE + ""));
        System.out.println(jedis.get("key007"));
        // 整数型的value，自增范围是signed long的最大最小值
        // System.out.println(jedis.incr("key007"));

        // list
        System.out.println(jedis.rpush("list001", "value001", "value002", "value003"));
        System.out.println(jedis.llen("list001"));
        System.out.println(jedis.lpop("list001"));
        System.out.println(jedis.rpop("list001"));
        System.out.println(jedis.lpop("list001"));
        System.out.println(jedis.rpop("list001"));
    }

    public static void main(String[] args) {
        Jedis jedis = connect();
        getContent190727(jedis);
    }
}
