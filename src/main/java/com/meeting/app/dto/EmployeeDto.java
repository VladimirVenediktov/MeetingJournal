package com.meeting.app.dto;



public class EmployeeDto {

    private String name;
    private int age;
    private String division;

    public EmployeeDto() {
    }

    public EmployeeDto(String name, int age, String division) {
        this.name = name;
        this.age = age;
        this.division = division;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDivision() {
        return division;
    }


}
