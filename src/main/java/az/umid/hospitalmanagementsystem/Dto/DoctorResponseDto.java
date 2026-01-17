package az.umid.hospitalmanagementsystem.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponseDto {
   private Long id;
   private String fullName;
   private String specialization;
   private boolean available;


}
