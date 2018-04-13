package vo;

public class Auditorium {
	
	private String room_no;
	private String campus_no;
	private boolean isCompRoom;
	
	public String getRoom_no() {
		return room_no;
	}
	
	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}
	
	public String getCampus_no() {
		return campus_no;
	}
	
	public void setCampus_no(String campus_no) {
		this.campus_no = campus_no;
	}
	
	public boolean isCompRoom() {
		return isCompRoom;
	}
	
	public void setCompRoom(boolean isCompRoom) {
		this.isCompRoom = isCompRoom;
	}
	
	public String toString() {
		return "Room no: "+getRoom_no()+"\n"+
				"Campus no: "+getCampus_no()+"\n"+
				"Is Comp room: "+isCompRoom();
	}

}
