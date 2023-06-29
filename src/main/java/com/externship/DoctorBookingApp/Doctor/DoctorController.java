package com.externship.DoctorBookingApp.Doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("doctor")
public class DoctorController {

    private final DoctorService doctorService;
    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Doctor> getDoctor() {
        return doctorService.getDoctor();
    }

    @PostMapping
    public void registerNewDoctor(@RequestBody Doctor doctor) {
        doctorService.addNewDoctor(doctor);
    }

    @DeleteMapping("/{doctorId}")
    public void deleteDoctor(@PathVariable("doctorId") Integer doctorId) {
        doctorService.deleteDoctor(doctorId);
    }

    @PutMapping("/{doctorId}")
    public void updateDoctor(@PathVariable("doctorId") Integer doctorId,
                             @RequestParam(required = false) String doctorName,
                             @RequestParam(required = false) String doctorEmail) {
        doctorService.updateDoctor(doctorId, doctorName, doctorEmail);
    }
}
