package SpringTest.Student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp(){
        mapper = new StudentMapper();
    }


    @Test
    public void shouldMapStudentDtoToStudent(){
        StudentDto dto = new StudentDto(
                "John",
                "Dou",
                "john@wp.pl",
                1
        );
        Student student = mapper.toStudent(dto);
        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());
    }


    @Test
    public void should_throw_NullPointerException_when_studentDto_is_null(){
        var exp = assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        assertEquals("The studentDto should not be null", exp.getMessage());
    }

    @Test
    public void ShouldMapToStudentResponseDto(){
        Student student = new Student(
                "John",
                "Dou",
                "john@wp.pl",
                20
        );
        StudentResponseDto response = mapper.toStudentResponseDto(student);
        assertEquals(response.firstname(), student.getFirstname());
        assertEquals(response.lastname(), student.getLastname());
        assertEquals(response.email(), student.getEmail());
    }

}