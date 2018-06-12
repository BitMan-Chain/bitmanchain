package com.shoufubang.model.util;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.shoufubang.model.redis.RedisConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.JedisClusterCRC16;

/**
 * redis cluster集群 工具类
 */
public class RedisUtil {

    private static final Log log = LogFactory.getLog(RedisUtil.class);

    private static JedisCluster jedisCluster;
    private static final int DEFAULT_SINGLE_EXPIRE_TIME = 3;

    static {
        try {
            log.info("JedisClientCluster--static--go--");
            init();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("JedisCluster--init--error--" + e.toString());
        }
    }

    public static String init() {
        log.info("JedisClientCluster--init--start--");
        jedisCluster = RedisConfig.initJedisCluster();
        log.info("JedisClientCluster--init--end--" + jedisCluster);
        if (jedisCluster == null) return "";
        return "ok";
    }


    public static String setex(String key, int seconds, String value) {
        try {
            if (key == null || value == null)
                return null;
            if (seconds <= 0) {
                return jedisCluster.set(key, value);
            } else {
                return jedisCluster.setex(key, seconds, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("---JedisClientCluster.setex---" + e.toString());
        }
        return null;
    }
    
    public static String setBySeconds(String key, String value,int seconds) {
        try {
            if (key == null || value == null)
                return null;
            if (seconds <= 0) {
                return jedisCluster.set(key, value);
            } else {
                return jedisCluster.setex(key, seconds, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("---JedisClientCluster.setex---" + e.toString());
        }
        return null;
    }
    
    public static Boolean setOnSeconds(String key, String value, int seconds)
    {
    	 try {
             if (key == null || value == null)
                 return false;
             if (seconds <= 0) {
            	 jedisCluster.set(key, value);
                 return true;
             } else {
            	 jedisCluster.setex(key, seconds, value);
                 return true;
             }
         } catch (Exception e) {
             e.printStackTrace();
             log.error("---JedisClientCluster.setex---" + e.toString());
         }
    	 return false;
    }
    
    
    public static String get(String key) {
        try {
            if (key == null)
                return null;
            return jedisCluster.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("---JedisClientCluster.get---" + e.toString());
        }
        return null;
    }

    public static <T> T get(String key, Class<T> clazz)
    {
    	 try {
        if (StringUtils.empty(key))
        {
            return null;
        }
        String value = jedisCluster.get(key);
        if (StringUtils.empty(value))
        {
            return null;
        }
        return JsonUtil.jsonToObj(value, clazz);
    	 } catch (Exception e) {
             e.printStackTrace();
             log.error("---JedisClientCluster.get---" + e.toString());
         }
         return null;
    }
    
    
    public static String set(String key, String value) {
        try {
            if (key == null || value == null)
                return null;
            return jedisCluster.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("---JedisClientCluster.set---" + e.toString());
        }
        return null;
    }

    public static boolean setObject(String key, int seconds, Object value) {
        try {
            if (key == null || value == null)
                return false;
            String objectJson = JSON.toJSONString(value);
            if (seconds <= 0) {
                jedisCluster.set(key, objectJson);
            } else {
                jedisCluster.setex(key, seconds, objectJson);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("---JedisClientCluster.setObject---" + e.toString());
        }
        return false;
    }

    public static boolean exists(String key) {
        return jedisCluster.exists(key);
    }
    
    
    /**
     * 将 key 的值设为 value ，当且仅当 key 不存在。
     * 设置成功，返回 true 。
     * 设置失败，返回 false 。
     */
    public static Boolean setnx(String key,String value)
    {
        if (StringUtils.empty(key))
        {
            return false;
        }
        String v = StringUtils.isBlank(value) ? String.valueOf(System.currentTimeMillis()) : value;
        Long result = jedisCluster.setnx(key, String.valueOf(v));
        boolean isSuccess = result == 1;

        jedisCluster.expire(key, 3);//3秒钟后过期

//        jedis.close();
        return isSuccess;
    }


    public static <T> T getObject(String key, Class<T> clazz) {
        try {
            String value = jedisCluster.get(key);
            if (value != null || !"".equals(value)) {
                return JSON.parseObject(value, clazz);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("---JedisClientCluster.getObject---" + e.toString());
        }
        return null;
    }


    /**
     * @param key
     * @return 0 key值不存在，1：删除成功，-1删除失败
     */
    public static long del(String key) {
        long ret = 0L;
        if (key == null) {
            return ret;
        }
        try {
            ret = jedisCluster.del(key).longValue();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("---JedisClientCluster.del---" + e.toString());
            ret = -1L;
        }
        return ret;
    }

    /**
     * 删除缓存
     *
     * @param key
     * @return
     */
    public static Boolean delete(String key)
    {
        if (StringUtils.empty(key))
        {
            return false;
        }
        long  ret = jedisCluster.del(key).longValue();
        if (ret <= 0)
        {
            //log.error("redis delete error,key:" + key);
            return false;
        }
        return true;
    }
    
    
    /**
     * @param key
     * @return
     */
    public static Long incr(String key) {
        long ret = 0L;
        if (key == null) {
            return ret;
        }
        try {
            ret = jedisCluster.incr(key);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("---JedisClientCluster.incr---" + e.toString());
            ret = -1L;
        }
        return ret;
    }

    public static Long decr(String key) {
        long ret = 0L;
        if (key == null) {
            return ret;
        }
        try {
            ret = jedisCluster.decr(key);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("---JedisClientCluster.decr---" + e.toString());
            ret = -1L;
        }
        return ret;
    }

    public static Long expire(String key, int second) {
        long ret = 0L;
        if (key == null) {
            return ret;
        }
        try {
            ret = jedisCluster.expire(key, second);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("---JedisClientCluster.expire---" + e.toString());
            ret = -1L;
        }
        return ret;
    }

    public static Long ttl(String key) {
        long ret = 0L;
        if (key == null) {
            return ret;
        }
        try {
            ret = jedisCluster.ttl(key);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("---JedisClientCluster.ttl---" + e.toString());
            ret = -1L;
        }
        return ret;
    }

    public static Long hset(String key, String item, String value) {
        long ret = 0L;
        if (key == null) {
            return ret;
        }
        try {
            ret = jedisCluster.hset(key, item, value);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("---JedisClientCluster.hset---" + e.toString());
            ret = -1L;
        }
        return ret;
    }

    public static String hget(String key, String item) {
        String ret = null;
        if (key == null) {
            return ret;
        }
        try {
            ret = jedisCluster.hget(key, item);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("---JedisClientCluster.hget---" + e.toString());
        }
        return ret;
    }

    public static Long hdel(String key, String item) {
        long ret = 0L;
        if (key == null) {
            return ret;
        }
        try {
            ret = jedisCluster.hdel(key, item);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("---JedisClientCluster.hdel---" + e.toString());
            ret = -1L;
        }
        return ret;
    }


    public static TreeSet<String> keys(String pattern) {
        try {
            TreeSet<String> keys = new TreeSet<String>();
            //获取所有的节点
            Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
            //遍历节点 获取所有符合条件的KEY
            for (String k : clusterNodes.keySet()) {
                JedisPool jp = clusterNodes.get(k);
                Jedis connection = jp.getResource();
                try {
                    keys.addAll(connection.keys(pattern));
                } catch (Exception e) {

                } finally {
                    connection.close();//用完一定要close这个链接！！！
                }
            }
            return keys;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("---JedisClientCluster.keys---" + e.toString());
        }
        return null;
    }

    public static long flushAll(TreeSet<String> keys) {
        long result = 0;
        try {
            if (keys != null) {
                for (String key : keys) {
                    result += jedisCluster.del(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("---JedisClientCluster.keys---" + e.toString());
        }
        return result;
    }

    /**
     * 从缓存中获取对象
     *
     * @param key 唯一标识
     * @param ref 格式映射 </BR> 使用说明:</BR> 如果对象类型为User,则传new
     *            TypeReference<User>(){}</BR>
     *            如果对象类型为List,则传"new TypeReference<List>(){}"</BR>
     *            如果对象类型为List<User>,则传"new TypeReference<List<User>>(){}"</BR>
     *            如果对象类型为Map,则传"new TypeReference<Map>(){}"
     * @return obj or null
     */
    public static Object getObject(String key, TypeReference ref) {
        try {
            String value = jedisCluster.get(key);
            if (value != null || !"".equals(value)) {
                return JSONObject.parseObject(value, ref);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("---JedisClusterUtil.getObject---" + e.toString());
        }
        return null;
    }


    public static <T> void setList(String key ,List<T> list){  
        try {  
        	jedisCluster.set(key.getBytes(),ObjectTranscoder.serialize(list));  
        } catch (Exception e) {  
            System.out.println("Set key error : "+e);  
        }  
    }  
    /** 
     * 获取list 
     * @param <T> 
     * @param key 
     * @return list 
     */  
    public static <T> List<T> getList(String key){  
        if(jedisCluster == null || !jedisCluster.exists(key.getBytes())){  
            return null;  
        }  
        byte[] in = jedisCluster.get(key.getBytes());    
        List<T> list = (List<T>) ObjectTranscoder.deserialize(in);    
        return list;  
    }  
    /** 
     * 设置 map 
     * @param <T> 
     * @param key 
     * @param value 
     */  
    public static <T> void setMap(String key ,Map<String,T> map){  
        try {  
            jedisCluster.set(key.getBytes(),ObjectTranscoder.serialize(map));  
        } catch (Exception e) {  
        	 System.out.println("Set key error : "+e);  
        }  
    }  
    /** 
     * 获取list 
     * @param <T> 
     * @param key 
     * @return list 
     */  
    public static <T> Map<String,T> getMap(String key){  
        if(jedisCluster == null || !jedisCluster.exists(key.getBytes())){  
            return null;  
        }  
        byte[] in = jedisCluster.get(key.getBytes());    
        Map<String,T> map = (Map<String, T>) ObjectTranscoder.deserialize(in);    
        return map;  
    }  
    
    
    /**
     * 获取锁 如果锁可用   立即返回true，  否则立即返回false，作为非阻塞式锁使用
     * @param key
     * @param value
     * @return
     */
    public static boolean tryLock(String key , String value)
    {
        try
        {
            return tryLock(key, value, 0L, null);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 锁在给定的等待时间内空闲，则获取锁成功 返回true， 否则返回false，作为阻塞式锁使用
     * @param key 锁键
     * @param value 被谁锁定
     * @param timeout 尝试获取锁时长，建议传递500,结合实践单位，则可表示500毫秒
     * @param unit，建议传递TimeUnit.MILLISECONDS
     * @return
     * @throws InterruptedException
     */
    public static boolean tryLock(String key , String value , long timeout , TimeUnit unit) throws InterruptedException
    {
        //纳秒
        long begin = System.nanoTime();
        do
        {
            Long i = jedisCluster.setnx(key, value);
            if (i == 1)
            {
                jedisCluster.expire(key, DEFAULT_SINGLE_EXPIRE_TIME);
                log.info(value+"成功获取" +key+ "的锁,设置锁过期时间为"+DEFAULT_SINGLE_EXPIRE_TIME+"秒 ");
                return true;
            }
            if (timeout == 0)
            {
                break;
            }
            //在其睡眠的期间，锁可能被解，也可能又被他人占用，但会尝试继续获取锁直到指定的时间
            Thread.sleep(100);
        }
        while ((System.nanoTime() - begin) < unit.toNanos(timeout));
        //因超时没有获得锁
        return false;
    }

    /**
     * 释放单个锁
     * @param key 锁键
     * @param value 被谁释放
     */
    public static void unLock(String key , String value)
    {
        try
        {
            jedisCluster.del(key);
            log.info(key+"锁被"+ value +"释放 .");
        }
        catch (JedisConnectionException je)
        {

        }
        catch (Exception e)
        {

        }
    }
   
    
   public static void batchDel(String keyIndex){  
	   flushAll(RedisUtil.keys(keyIndex));
    }
   
   public static void main(String[] args) {
		/* //setBySeconds("GenesisTime", "GenesisTime", 60 * 60 * 24 * 30);  代表30天失效 
		//现在时间加上多少毫秒等于最后时间 。。后面失效时间是秒     
		//setBySeconds("GenesisTime", TimeUtil1.dateTime2Add(new Date(),(long)(60 * 60  * 24 * 7 * 1000)), 60 * 60 * 24 * 7);
	    setBySeconds("GenesisTime", TimeUtil1.dateTime2Add(new Date(),(long)(60 * 60 * 3 * 1000)), 60 * 60 * 3 );
		//设置 开始人数
		set("BEGIN_USER", "10");
		//设置 创世纪开关
		set("IS_Genesis", "1");
		
		//设置 产生矿次数在配置文件里面   把 1 改成  4     MineServiceImpl里面把方法改成小时相减
		
		//set("total_stress", "200000");
		//RedisUtil.set("BCDN", "200000");
		//RedisUtil.set("GTC", "200000");
		batchDel("mine*");
		batchDel("wakuang*");
		batchDel("storageNumb*");
		batchDel("storage*");
		//删除总算力
		del("total_stress");
		del("GenesisDate");
		del("isSameDate");
		
		System.out.println("--------创世结束时间："+ get("GenesisTime")+"  创世累计人数： "+ get("BEGIN_USER")+"--------");
		System.out.println("测试删除："+get("wakuang18600547802"));*/
	    set("updateUserFileSign", "0");
	    set("testSign", "0");
	}
   
}
