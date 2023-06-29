package com.externship.DoctorBookingApp;

import com.externship.DoctorBookingApp.Doctor.Doctor;
import com.externship.DoctorBookingApp.Doctor.DoctorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/doctor")
public class DoctorBookingAppApplication {

	private final DoctorRepository doctorRepository;

	public DoctorBookingAppApplication(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DoctorBookingAppApplication.class, args);
	}

	@GetMapping
	public List<Doctor> getDoctors(){
		return doctorRepository.findAll();
	}

	record NewDoctorRequest(String Name,
							Date dob,
							String empId,
							String speciality
							){}

	@PostMapping
	public void addDoctor(@RequestBody NewDoctorRequest newDoctorRequest) {

	}

}
