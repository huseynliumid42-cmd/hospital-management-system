package az.umid.hospitalmanagementsystem.Service;

import az.umid.hospitalmanagementsystem.Dto.AppointmentResponseDto;
import az.umid.hospitalmanagementsystem.Entity.Appointment;
import az.umid.hospitalmanagementsystem.Entity.Doctor;
import az.umid.hospitalmanagementsystem.Entity.Patient;
import az.umid.hospitalmanagementsystem.Repository.AppointmentRepository;
import az.umid.hospitalmanagementsystem.Repository.DoctorRepository;
import az.umid.hospitalmanagementsystem.Repository.PatientRepository;
import az.umid.hospitalmanagementsystem.exception.AppointmentAlreadyExistsException;
import az.umid.hospitalmanagementsystem.exception.DoctorNotAvailableException;
import az.umid.hospitalmanagementsystem.exception.DoctorNotFoundException;
import az.umid.hospitalmanagementsystem.exception.PatientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentResponseDto create(Long patientId, Long doctorId, LocalDate date) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(
                        "Patient tapılmadı id = " + patientId));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException(
                        "Doctor tapılmadı id = " + doctorId));

        if (!doctor.isAvailable()) {
            throw new DoctorNotAvailableException("Həkim hazır deyil");
        }

        boolean exists =
                appointmentRepository.existsSameAppointment(patientId, doctorId, date);

        if (exists) {
            throw new AppointmentAlreadyExistsException("Bu görüş artıq mövcuddur");
        }

        Appointment a = new Appointment();
        a.setAppointmentDate(date);
        a.setPatient(patient);
        a.setDoctor(doctor);

        Appointment saved = appointmentRepository.save(a);

        return new AppointmentResponseDto(
                saved.getId(),
                saved.getAppointmentDate(),
                saved.getPatient().getId(),
                saved.getDoctor().getId()
        );
    }

    public Page<AppointmentResponseDto> getAll(Pageable pageable) {

        return appointmentRepository.findAll(pageable)
                .map(a -> new AppointmentResponseDto(
                        a.getId(),
                        a.getAppointmentDate(),
                        a.getPatient().getId(),
                        a.getDoctor().getId()
                ));
    }

    public void delete(Long id) {

        if (!appointmentRepository.existsById(id)) {
            throw new AppointmentAlreadyExistsException(
                    "Appointment tapılmadı id=" + id);
        }

        appointmentRepository.deleteById(id);
    }
}
