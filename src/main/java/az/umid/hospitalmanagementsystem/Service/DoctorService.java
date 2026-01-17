package az.umid.hospitalmanagementsystem.Service;

import az.umid.hospitalmanagementsystem.Dto.DoctorRequestDto;
import az.umid.hospitalmanagementsystem.Dto.DoctorResponseDto;
import az.umid.hospitalmanagementsystem.Entity.Doctor;
import az.umid.hospitalmanagementsystem.Repository.DoctorRepository;
import az.umid.hospitalmanagementsystem.exception.DoctorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorResponseDto create(DoctorRequestDto dto) {

        Doctor d = new Doctor();

        d.setFullName(dto.getFullName());
        d.setSpecialization(dto.getSpecialization());
        d.setAvailable(dto.isAvailable());

        Doctor saved = doctorRepository.save(d);

        return new DoctorResponseDto(
                saved.getId(),
                saved.getFullName(),
                saved.getSpecialization(),
                saved.isAvailable()
        );
    }

    public Page<DoctorResponseDto> getAll(Pageable pageable) {

        return doctorRepository.findAll(pageable)
                .map(d -> new DoctorResponseDto(
                        d.getId(),
                        d.getFullName(),
                        d.getSpecialization(),
                        d.isAvailable()
                ));
    }

    public DoctorResponseDto getById(Long id) {

        Doctor d = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor tapılmadı id = " + id));

        return new DoctorResponseDto(
                d.getId(),
                d.getFullName(),
                d.getSpecialization(),
                d.isAvailable()
        );
    }

    public DoctorResponseDto update(Long id, DoctorRequestDto dto) {

        Doctor d = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor tapılmadı id = " + id));

        d.setFullName(dto.getFullName());
        d.setSpecialization(dto.getSpecialization());
        d.setAvailable(dto.isAvailable());

        Doctor saved = doctorRepository.save(d);

        return new DoctorResponseDto(
                saved.getId(),
                saved.getFullName(),
                saved.getSpecialization(),
                saved.isAvailable()
        );
    }

    public void delete(Long id) {

        if (!doctorRepository.existsById(id)) {
            throw new DoctorNotFoundException("Doctor tapılmadı id = " + id);
        }

        doctorRepository.deleteById(id);
    }
}
