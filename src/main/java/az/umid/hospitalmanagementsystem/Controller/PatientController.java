package az.umid.hospitalmanagementsystem.Controller;

import az.umid.hospitalmanagementsystem.Dto.PatientRequestDto;
import az.umid.hospitalmanagementsystem.Dto.PatientResponseDto;
import az.umid.hospitalmanagementsystem.Service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;


    @PostMapping
    public ResponseEntity<PatientResponseDto> create(@RequestBody PatientRequestDto dto) {
        return ResponseEntity.ok(patientService.create(dto));
    }


    @GetMapping
    public ResponseEntity<Page<PatientResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(patientService.getAll(pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getById(id));
    }


    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<PatientResponseDto>> getByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(patientService.getPatientsByDoctorId(doctorId));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
