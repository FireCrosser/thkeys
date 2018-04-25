package ua.edu.ukma.thkeys.enums;

public enum ScheduleEntryJsonField {
    subject_name("subject_name"),
    auditorium("auditorium"),
    speciality_and_year("speciality and year"),
    time("time"),
    weeks("weeks"),
    day("day"),
    group("group");

    private final String value;

    ScheduleEntryJsonField(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
