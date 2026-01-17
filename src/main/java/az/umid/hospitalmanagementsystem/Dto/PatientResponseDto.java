package az.umid.hospitalmanagementsystem.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Long doctorId;

}
