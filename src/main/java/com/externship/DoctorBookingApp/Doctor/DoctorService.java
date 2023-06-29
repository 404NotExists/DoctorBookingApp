package com.externship.DoctorBookingApp.Doctor;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getDoctor() {
        return doctorRepository.findAll();
    }

    public void addNewDoctor(Doctor doctor) {
        Optional<Doctor> foundDoctor = doctorRepository.findDoctorByEmail(doctor.getEmail());

        if(foundDoctor.isPresent()) {
            throw new IllegalArgumentException("Email Taken");
        }
        doctorRepository.save(doctor);
    }

    public void deleteDoctor(Integer doctorId) {
        boolean exists = doctorRepository.existsById(doctorId);
        if(!exists) {
            throw new IllegalArgumentException("Doctor with ID " + doctorId + " does not exits");
        }
        doctorRepository.deleteById(doctorId);
    }

    @Transactional
    public void updateDoctor(Integer doctorId, String doctorName, String doctorEmail) {
        Doctor doctor = doctorRepository.findById(doctorId)
                                        .orElseThrow(() -> new IllegalArgumentException(
                                                "Doctor with ID " + doctorId + " does not exits"
                                        ));

        if (doctorName != null && doctorName.length() > 0 &&
            !Objects.equals(doctor.getName(), doctorName)) {
            doctor.setName(doctorName);
        }

        if (doctorEmail != null && doctorEmail.length() > 0 &&
                !Objects.equals(doctor.getEmail(), doctorEmail)) {

            Optional<Doctor> foundDoctor = doctorRepository.findDoctorByEmail(doctor.getEmail());

            if(foundDoctor.isPresent()) {
                throw new IllegalArgumentException("Email Taken");
            }
            doctor.setEmail(doctorEmail);
        }

        }
}
