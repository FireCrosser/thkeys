package dao;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class StudentDAO {
	
	public Set<String> getSpecialities() {
		Jedis jedis = new Jedis("localhost");
		Set<String> res = jedis.smembers("specialities_years");
		
		jedis.close();
		
		return res;
	}

}
