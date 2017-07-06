package redis;
import java.util.ArrayList;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

public class TestJedis {
	//用jadis操作redis.set/get
	//操作一个节点
	@Test
	public void redis(){
		//创建连接
		Jedis jedis = new Jedis("192.168.253.16", 6380);
		jedis.set("name", "tony");
		String name = jedis.get("name");
		System.out.println(name);
		//释放资源
		jedis.close();
	}
	//分片
	@Test
	public void shard(){
		ArrayList<JedisShardInfo> infoList = new ArrayList<JedisShardInfo>();
		infoList.add(new JedisShardInfo("192.168.253.16",6380));
		infoList.add(new JedisShardInfo("192.168.253.16",6381));
		infoList.add(new JedisShardInfo("192.168.253.16",6382));
		
	    ShardedJedis jedis =new ShardedJedis(infoList); 
	    for(int i=0;i<10;i++){
			jedis.set("n"+i, "t"+i);
		}
	    System.out.println(jedis.get("n9"));
	    jedis.close();
	}
}
