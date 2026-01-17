package az.umid.hospitalmanagementsystem.Controller;

import az.umid.hospitalmanagementsystem.Dto.AppointmentResponseDto;
import az.umid.hospitalmanagementsystem.Service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    
    @PostMapping("/{patientId}/{doctorId}")
    public ResponseEntity<AppointmentResponseDto> create(
            @PathVariable Long patientId,
            @PathVariable Long doctorId,
            @RequestParam("date") LocalDate date
    ) {
        return ResponseEntity.ok(
                appointmentService.create(patientId, doctorId, date)
        );
    }

    
    @GetMapping
    public ResponseEntity<Page<AppointmentResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(appointmentService.getAll(pageable));
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
