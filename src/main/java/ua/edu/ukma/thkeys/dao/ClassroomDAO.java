package ua.edu.ukma.thkeys.dao;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;
import ua.edu.ukma.thkeys.vo.Auditorium;

@Repository
public class ClassroomDAO {
    
    @Autowired
    private Jedis jedis;
	
	public void insertClassroomsData(final List<Auditorium> auds) {
	    //System.out.println("Connection to server sucessfully");
	    
	    for(Auditorium aud : auds) {
	    	//���������� �������� ��� ��������
	    	jedis.sadd("classrooms", aud.getRoom_no());
	    	
	    	//���������� �������� �������� ������� �������
	    	jedis.sadd("classrooms:"+aud.getCampus_no(), aud.getRoom_no());
	    	
	    	//���������� �������� ����'������� ��������
	    	if(aud.isCompRoom() == true)
	    		jedis.sadd("classrooms_comp", aud.getRoom_no());
	    }
	}
	
	//Getting classrooms
	public Set<String> getAllClassrooms() {
	    //System.out.println("Connection to server sucessfully");
	    
		Set<String> classrooms = jedis.smembers("classrooms");
		
		return classrooms;
	}
	
	//Getting classrooms of the campus
	public Set<String> getClassroomsOfTheCampus(int campusNo) {
	    //System.out.println("Connection to server sucessfully");
	    
		Set<String> classrooms = jedis.smembers("classrooms:"+campusNo);
		return classrooms;
	}
	
	//Getting classrooms with computers
	public Set<String> getComputerClassrooms() {
	    //System.out.println("Connection to server sucessfully");
	    
		Set<String> classrooms = jedis.smembers("classrooms_comp");
		return classrooms;
	}

}
