package ua.edu.ukma.thkeys.domain;

import ua.edu.ukma.thkeys.enums.ScheduleEntryType;
import ua.edu.ukma.thkeys.enums.Weekday;

public class ScheduleEntry {

    private Weekday weekday;
    private String time;
    private String subject;
    private String teacher;
    private ScheduleEntryType group;
    private String week;
    private String classroom;
    private String studentYear;

    public Weekday getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday weekday) {
        this.weekday = weekday;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public ScheduleEntryType getGroup() {
        return group;
    }

    public void setGroup(ScheduleEntryType group) {
        this.group = group;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getStudentYear() {
        return studentYear;
    }

    public void setStudentYear(String studentYear) {
        this.studentYear = studentYear;
    }

    public boolean isCorrect() {
        return this.weekday != null
                && this.time != null
                && this.subject != null
                && this.teacher != null
                && this.group != null
                && this.week != null
                && this.classroom != null;
    }

    @Override
    public String toString() {
        return "ScheduleEntry{" + "weekday=" + weekday + ", time=" + time + ", subject=" + subject + ", teacher=" + teacher + ", group=" + group + ", week=" + week + ", classroom=" + classroom + '}';
    }
}
