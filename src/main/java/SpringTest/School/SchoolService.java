package SpringTest.School;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class SchoolService {

    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepository;

    public SchoolDto create(SchoolDto dto){
        var school = schoolMapper.toSchool(dto);
        var savedSchool = schoolRepository.save(school);
        return dto;
    }

    public List<SchoolDto> findAll() {
        return schoolRepository.findAll().stream()
                .map(schoolMapper::toSchoolDto)
                .collect(Collectors.toList());
    }
}
