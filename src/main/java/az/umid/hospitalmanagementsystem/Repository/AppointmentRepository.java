package az.umid.hospitalmanagementsystem.Repository;

import az.umid.hospitalmanagementsystem.Entity.Appointment;
import az.umid.hospitalmanagementsystem.Entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


    @Query("SELECT DISTINCT a.patient FROM Appointment a WHERE a.doctor.id = :doctorId")
    List<Patient> findPatientsByDoctorId(@Param("doctorId") Long doctorId);


    @Query("""
    SELECT COUNT(a) > 0 FROM Appointment a
    WHERE a.patient.id = :patientId
    AND a.doctor.id = :doctorId
    AND a.appointmentDate = :date
    """)
    boolean existsSameAppointment(@Param("patientId") Long patientId,
                                  @Param("doctorId") Long doctorId,
                                  @Param("date") LocalDate date);


    Page<Appointment> findAll(Pageable pageable);
}
