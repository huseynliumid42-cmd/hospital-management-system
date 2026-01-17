package az.umid.hospitalmanagementsystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
     private LocalDate  appointmentDate;

     @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

     @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

}
