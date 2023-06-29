package com.externship.DoctorBookingApp.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    @Query("SELECT s FROM Doctor s WHERE s.email = ?1")
    Optional<Doctor> findDoctorByEmail(String email);
}
