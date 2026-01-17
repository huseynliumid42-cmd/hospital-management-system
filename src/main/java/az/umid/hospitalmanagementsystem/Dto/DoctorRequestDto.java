package az.umid.hospitalmanagementsystem.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequestDto {
    private String fullName;
    private String specialization;
    private Long patientId;
    private boolean available;

}
