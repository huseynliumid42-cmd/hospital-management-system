package az.umid.hospitalmanagementsystem.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import az.umid.hospitalmanagementsystem.Dto.PatientRequestDto;
import az.umid.hospitalmanagementsystem.Dto.PatientResponseDto;
import az.umid.hospitalmanagementsystem.Entity.Patient;
import az.umid.hospitalmanagementsystem.Repository.AppointmentRepository;
import az.umid.hospitalmanagementsystem.Repository.PatientRepository;
import az.umid.hospitalmanagementsystem.exception.DoctorNotFoundException;
import az.umid.hospitalmanagementsystem.exception.PatientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service 
@RequiredArgsConstructor 
public class PatientService {

    private final PatientRepository patientRepository;


    private final AppointmentRepository appointmentRepository;


    public PatientResponseDto create(PatientRequestDto dto) {
    

        Patient patient = new Patient();
      

        patient.setFullName(dto.getFullName());
       

        patient.setEmail(dto.getEmail());
       

        patient.setPhoneNumber(dto.getPhoneNumber());
     

        Patient saved = patientRepository.save(patient);
        

        return new PatientResponseDto(
                saved.getId(),           
                saved.getFullName(),     
                saved.getEmail(),       
                saved.getPhoneNumber(),  
                null                     
        );
        
    }

    public Page<PatientResponseDto> getAll(Pageable pageable) {
        

        return patientRepository.findAll(pageable)
                
                .map(p -> new PatientResponseDto(
                        p.getId(),        
                        p.getFullName(),   
                        p.getEmail(),      
                        p.getPhoneNumber(),  
                        null                
                ));
        
    }

    public PatientResponseDto getById(Long id) {
        

        Patient p = patientRepository.findById(id)
                
                .orElseThrow(() -> new PatientNotFoundException(
                        "Patient tapılmadı id = " + id));
        

        return new PatientResponseDto(
                p.getId(), p.getFullName(), p.getEmail(), p.getPhoneNumber(), null
        );
        
    }

    public PatientResponseDto update(Long id, PatientRequestDto dto) {
        

        Patient p = patientRepository.findById(id)
                
                .orElseThrow(() -> new PatientNotFoundException(
                        "Patient tapılmadı id = " + id));

        p.setFullName(dto.getFullName());
        

        p.setEmail(dto.getEmail());
        

        p.setPhoneNumber(dto.getPhoneNumber());
        

        Patient saved = patientRepository.save(p);
    

        return new PatientResponseDto(
                saved.getId(), saved.getFullName(), saved.getEmail(), saved.getPhoneNumber(), null
        );
        
    }

    public void delete(Long id) {
        

        if (!patientRepository.existsById(id)) {
            

            throw new PatientNotFoundException("Patient tapılmadı id = " + id);
            
        }

        patientRepository.deleteById(id);
        
    }

    public List<PatientResponseDto> getPatientsByDoctorId(Long doctorId) {
        

        List<Patient> patients =
                appointmentRepository.findPatientsByDoctorId(doctorId);
        

        if (patients.isEmpty()) {
            

            throw new DoctorNotFoundException(
                    "Bu hekimə aid xəstələr tapılmadı: " + doctorId);
        }

        return patients.stream()
                .map(p -> new PatientResponseDto(
                        p.getId(), p.getFullName(), p.getEmail(), p.getPhoneNumber(), doctorId
                ))
                

                .toList();
    
    }
}
