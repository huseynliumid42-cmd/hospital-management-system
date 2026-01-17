package az.umid.hospitalmanagementsystem.Controller;

import az.umid.hospitalmanagementsystem.Dto.DoctorRequestDto;
import az.umid.hospitalmanagementsystem.Dto.DoctorResponseDto;
import az.umid.hospitalmanagementsystem.Service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    
    @PostMapping
    public ResponseEntity<DoctorResponseDto> create(@RequestBody DoctorRequestDto dto) {
        return ResponseEntity.ok(doctorService.create(dto));
    }

    
    @GetMapping
    public ResponseEntity<Page<DoctorResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(doctorService.getAll(pageable));
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getById(id));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> update(
            @PathVariable Long id,
            @RequestBody DoctorRequestDto dto
    ) {
        return ResponseEntity.ok(doctorService.update(id, dto));
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
