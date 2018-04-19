package ua.edu.ukma.thkeys.dao;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

@Repository
public class StudentDAO {
	
    @Autowired
    public Jedis jedis;
    
	public Set<String> getSpecialities() {
		Set<String> res = jedis.smembers("specialities_years");
		
		return res;
	}

}
