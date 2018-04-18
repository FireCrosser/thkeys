package dao;

import java.util.ArrayList;
import java.util.Set;

import redis.clients.jedis.Jedis;
import vo.Auditorium;

public class ClassroomDAO {
	
	public void insertClassroomsData(ArrayList<Auditorium> auds) {
		Jedis jedis = new Jedis("localhost"); 
	    //System.out.println("Connection to server sucessfully");
	    
	    for(Auditorium aud : auds) {
	    	//Заповнюємо колекцію всіх аудиторій
	    	jedis.sadd("classrooms", aud.getRoom_no());
	    	
	    	//Заповнюємо колекцію аудиторій певного корпусу
	    	jedis.sadd("classrooms:"+aud.getCampus_no(), aud.getRoom_no());
	    	
	    	//Заповнюємо колекцію комп'ютерних аудиторій
	    	if(aud.isCompRoom() == true)
	    		jedis.sadd("classrooms_comp", aud.getRoom_no());
	    }
	    
	    jedis.close();
	}
	
	//Getting classrooms
	public Set<String> getAllClassrooms() {
		Jedis jedis = new Jedis("localhost"); 
	    //System.out.println("Connection to server sucessfully");
	    
		Set<String> classrooms = jedis.smembers("classrooms");
		
		jedis.close();
		
		return classrooms;
	}
	
	//Getting classrooms of the campus
	public Set<String> getClassroomsOfTheCampus(int campusNo) {
		Jedis jedis = new Jedis("localhost"); 
	    //System.out.println("Connection to server sucessfully");
	    
		Set<String> classrooms = jedis.smembers("classrooms:"+campusNo);
		
		jedis.close();
		
		return classrooms;
	}
	
	//Getting classrooms with computers
	public Set<String> getComputerClassrooms() {
		Jedis jedis = new Jedis("localhost"); 
	    //System.out.println("Connection to server sucessfully");
	    
		Set<String> classrooms = jedis.smembers("classrooms_comp");
		
		jedis.close();
		
		return classrooms;
	}

}
