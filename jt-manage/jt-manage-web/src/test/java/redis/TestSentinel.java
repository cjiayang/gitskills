package redis;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class TestSentinel {
	public void test(){
		Set<String> set = new HashSet<String>();
		set.add(new HostAndPort("192.168.253.16", 26380).toString());
		set.add(new HostAndPort("192.168.253.16", 26381).toString());
		
		JedisSentinelPool pool = new JedisSentinelPool("mymaster",set);
		
		Jedis jedis = pool.getResource();
		
	}

}
