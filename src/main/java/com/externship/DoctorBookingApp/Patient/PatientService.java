package com.externship.DoctorBookingApp.Patient;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientService {
    
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatient() {
        return patientRepository.findAll();
    }

    public void addNewPatient(Patient patient) {
        Optional<Patient> foundPatient = patientRepository.findPatientByEmail(patient.getEmail());
        
        if(foundPatient.isPresent()) {
            throw new IllegalArgumentException("Email Taken");
        }
        patientRepository.save(patient);
    }

    public void deletePatient(Integer patientId) {
        boolean exists = patientRepository.existsById(patientId);
        
        if(!exists) {
            throw new IllegalArgumentException("Patient with ID " + patientId + " does not exits");
        }
        
        patientRepository.deleteById(patientId);
    }
    @Transactional
    public void updatePatient(Integer patientId, String patientName, String patientEmail) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Patient with ID " + patientId + " does not exits"
                ));

        if (patientName != null && patientName.length() > 0 &&
                !Objects.equals(patient.getName(), patientName)) {
            patient.setName(patientName);
        }

        if (patientEmail != null && patientEmail.length() > 0 &&
                !Objects.equals(patient.getEmail(), patientEmail)) {

            Optional<Patient> foundPatient = patientRepository.findPatientByEmail(patient.getEmail());

            if(foundPatient.isPresent()) {
                throw new IllegalArgumentException("Email Taken");
            }
            patient.setEmail(patientEmail);
        }
    }
}
