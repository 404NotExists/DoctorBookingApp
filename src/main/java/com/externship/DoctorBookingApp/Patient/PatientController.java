package com.externship.DoctorBookingApp.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getPatient() {
        return patientService.getPatient();
    }

    @PostMapping
    public void registerNewPatient(@RequestBody Patient patient) {
        patientService.addNewPatient(patient);
    }

    @DeleteMapping("/{patientId}")
    public void deletePatient(@PathVariable("patientId") Integer patientId) {
        patientService.deletePatient(patientId);
    }

    @PutMapping("/{patientId}")
    public void updatePatient(@PathVariable("patientId") Integer patientId,
                              @RequestParam(required = false) String patientName,
                              @RequestParam(required = false) String patientEmail) {
        patientService.updatePatient(patientId, patientName, patientEmail);
    }
}
