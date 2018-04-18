package vo;

public class TeacherSubjectInfo {
	
	private String dayName;
	private String time;
	private String subjectName;
	private String teacher;
	private String group;
	private String weeks;
	private String auditorium;
	private String spec_year;
	
	
	public String getSpec_year() {
		return spec_year;
	}

	public void setSpec_year(String spec_year) {
		this.spec_year = spec_year;
	}

	public String getDayName() {
		return dayName;
	}
	
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getSubjectName() {
		return subjectName;
	}
	
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public String getTeacher() {
		return teacher;
	}
	
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	
	public String getGroup() {
		return group;
	}
	
	public void setGroup(String group) {
		this.group = group;
	}
	
	public String getWeeks() {
		return weeks;
	}
	
	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}
	
	public String getAuditorium() {
		return auditorium;
	}
	
	public void setAuditorium(String auditorium) {
		this.auditorium = auditorium;
	}

	@Override
	public String toString() {
		return "TeacherSubjectInfo [dayName=" + dayName + ", time=" + time
				+ ", subjectName=" + subjectName + ", teacher=" + teacher
				+ ", group=" + group + ", weeks=" + weeks + ", auditorium="
				+ auditorium + ", spec_year=" + spec_year + "]";
	}
	
	

}
