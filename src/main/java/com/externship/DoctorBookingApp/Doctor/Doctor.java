package com.externship.DoctorBookingApp.Doctor;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Doctor {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer empid;
    private String name;
    private String email;
    private LocalDate dob;
    @Transient
    private Integer age;
    private String speciality;

    public Doctor() {
    }

    public Doctor(String name, String email, LocalDate dob, String speciality) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.speciality = speciality;
    }

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "empid=" + empid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                ", speciality='" + speciality + '\'' +
                '}';
    }
}
