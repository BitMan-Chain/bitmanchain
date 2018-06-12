package test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
// 整合
@ContextConfiguration(locations = "classpath:spring-redis.xml")
// 加载配置
public class TestSDK extends AbstractJUnit4SpringContextTests {
	@SuppressWarnings("rawtypes")
	@Autowired
	RedisTemplate redisTemplate;
	@Test 
	public void testUserInfo() {
		
		redisTemplate.execute(new RedisCallback<Integer>() {
			//这里返回值是个上面的RedisCallback<Integer> 中的泛型一直，
			public Integer doInRedis(RedisConnection connection) {
				int i = 0;
				for (; i < 100; i++) {
					byte[] key = ("key:" + i).getBytes();
					byte[] value = ("value:" + i).getBytes();
					connection.set(key, value);
				}
				//这里返回值是个上面的RedisCallback<Integer> 中的泛型一直，
				return i;
				
			}
		});
		
	}
}