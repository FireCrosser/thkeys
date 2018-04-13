import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import parsers.AuditoriesParser;
import dao.ClassroomDAO;
import dao.LaborantDAO;
import dao.TeacherDAO;
import redis.clients.jedis.Jedis;
import vo.Auditorium;

public class RedisJava { 
   public static void main(String[] args) throws IOException { 
      //Connecting to Redis server on localhost 
      //Jedis jedis = new Jedis("localhost"); 
      //System.out.println("Connection to server sucessfully"); 
      //check whether server is running or not 
      //System.out.println("Server is running: "+jedis.ping());
      
	  //Parsing auditories data
	  AuditoriesParser parser = new AuditoriesParser();
	  ArrayList<Auditorium> auds = parser.parseFile("files_to_parse/auditories.html");
	   
	  ClassroomDAO clDao = new ClassroomDAO();
	  
	  //inserting in Redis parsed information
	  clDao.insertClassroomsData(auds);
      
	  Set<String> classrooms_all = clDao.getAllClassrooms();
      
      for(String s : classrooms_all) {
    	  System.out.println(s);
      }
	  
      System.out.println("*******************");
      
      
      Set<String> classrooms_camp = clDao.getClassroomsOfTheCampus(3);
      
      for(String s : classrooms_camp) {
    	  System.out.println(s);
      }
      
      System.out.println("*******************");
      
      Set<String> classrooms_comp = clDao.getComputerClassrooms();
      
      for(String s : classrooms_comp) {
    	  System.out.println(s);
      }
      
      System.out.println("*******************");
      
      //Групування: день, пара, аудиторія. Інформація: викладач, тижні
      /*LaborantDAO labDao = new LaborantDAO();
      Map<String, String> dets = labDao.getTeacherRoomDetails(4, 1, 10, 2);
      
      for (Map.Entry<String, String> entry : dets.entrySet()) {
          System.out.println(entry.getKey() + ": " + entry.getValue());
      }*/
      
      
      //Групування: день, пара, час. Інформація: аудиторія, предмет,тип, спеціальність, курс, група, тижні
      /*TeacherDAO teachDao = new TeacherDAO();
      Map<String, String> dets = teachDao.getSubjectDetails(5, 3, 11, 40);
      
      for (Map.Entry<String, String> entry : dets.entrySet()) {
          System.out.println(entry.getKey() + ": " + entry.getValue());
      }*/
      
   } 
}