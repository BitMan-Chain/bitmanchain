package com.shoufubang.model.redis;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 读取缓存配置文件
 */
public class RedisConfig {

	private static final Log log = LogFactory.getLog(RedisConfig.class);

	/**
	 * redis配置key
	 */

	public static final String CLUSTER_POOL = "cluster.pool";
	public static final String CLUSTER_PORT = "cluster.port";
	public static final String CLUSTER_PASSWORD = "cluster.password";

	/**
	 * 配置文件名称
	 */
	private static String fileName = "redis.properties";

	private static JedisPoolConfig pool_Config = new JedisPoolConfig();
	private static Properties prop = new Properties();

	private static String getString(String key){
		Object value = prop.get(key);
		if(value!=null){
			return value.toString();
		}
		return null;
	}

	private static String[] getProperties(String key) {
		Object value = prop.get(key);
		if(value!=null){
			return value.toString().split(",");
		}
		return null;
	}

	/**
	 * 根据配置文件 初始化redis cluster集群
	 * @return
     */
	public  static JedisCluster initJedisCluster(){
		try {
			prop = new Properties();
			InputStream is = RedisConfig.class.getClassLoader().getResourceAsStream("redis.properties");//假设当前这个方法是在CommonUtils类下面
			prop.load(is);
			//最大活动的对象个数
			pool_Config.setMaxTotal(Integer.parseInt(getString("config.maxtotal")));
			//对象最大空闲时间
			pool_Config.setMaxIdle(Integer.parseInt(getString("config.maxidle")));
			//获取对象时最大等待时间
			pool_Config.setMaxWaitMillis(Integer.parseInt(getString("config.maxwaitmillis")));
			//当调用borrow Object方法时，是否进行有效性检查
			pool_Config.setTestOnBorrow(true);
			Set<HostAndPort> redis = new HashSet<HostAndPort>();
			String[] strArray = getProperties(CLUSTER_POOL);
			log.info("initJedisCluster--CLUSTER_POOL--"+Arrays.toString(strArray));
			String[] portArray = getProperties(CLUSTER_PORT);
			log.info("initJedisCluster--CLUSTER_PORT--"+Arrays.toString(portArray));
			for (int i = 0; i < strArray.length; i++) {
				redis.add(new HostAndPort(strArray[i], Integer.parseInt(portArray[i])));
			}
			//注意：这里超时时间不要太短，他会有超时重试机制。而且其他像httpclient、dubbo等RPC框架也要注意这点
			return  new JedisCluster(redis, 1000, 1000, 1, prop.getProperty(CLUSTER_PASSWORD), pool_Config);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("initJedisCluster--error--"+e.toString());
		}
		return null;
	}


}