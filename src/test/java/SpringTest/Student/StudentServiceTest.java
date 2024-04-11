package SpringTest.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository repository;
    @Mock
    private StudentMapper studentMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_succesfully_save_a_student() {
        //given
        StudentDto dto = new StudentDto(
                "John",
                "Dou",
                "john@wp.pl",
                1);

        Student student = new Student(
                "John",
                "Dou",
                "john@wp.pl",
                20);

        Student savedStudent = new Student(
                "John",
                "Dou",
                "john@wp.pl",
                20);
        savedStudent.setId(1);

        //Mock the calls
        when(studentMapper.toStudent(dto)).thenReturn(student);
        when(repository.save(student)).thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDto(savedStudent))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Dou",
                        "john@wp.pl")
                );
        // when
        StudentResponseDto responseDto = studentService.saveStudent(dto);
        //then
        assertEquals(dto.firstname(), responseDto.firstname());
        assertEquals(dto.lastname(), responseDto.lastname());
        assertEquals(dto.email(), responseDto.email());

        verify(studentMapper, times(1)).toStudent(dto);
        verify(repository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentResponseDto(savedStudent);
    }


    @Test
    public void should_return_all_students() {
        //given
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "John",
                "Dou",
                "john@wp.pl",
                20));

        // mock the calls
        when(repository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Dou",
                        "john@wp.pl"
                ));

        List<StudentResponseDto> responseDtos = studentService.findAllStudents();

        //then
        assertEquals(students.size(), responseDtos.size());

        verify(repository, times(1)).findAll();

    }

    @Test
    public void shouldReturnStudentById() {
        //given
        Integer studentId = 1;
        Student student = new Student(
                "John",
                "Dou",
                "john@wp.pl",
                20
        );

        //mockTheCalls
        when(repository.findById(studentId))
                .thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Dou",
                        "john@wp.pl"
                ));

        StudentResponseDto dto = studentService.findStudentById(studentId);

        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());

        verify(repository, times(1)).findById(studentId);

    }

    @Test
    public void shouldReturnStudentByName(){
        //given
        String studentName = "John";

        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "John",
                "Dou",
                "john@wp.pl",
                20));

        // mock the calls
        when(repository.findAllByFirstnameContaining(studentName)).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Dou",
                        "john@wp.pl"
                ));

        //when
        var responseDto= studentService.findStudentByName(studentName);

        //then
        assertEquals(students.size(), responseDto.size());

        verify(repository, times(1))
                .findAllByFirstnameContaining(studentName);

    }
    @Test
    public void shouldDeleteStudentById() {
        //given
        Integer studentId = 1;
        Student student = new Student(
                "John",
                "Dou",
                "john@wp.pl",
                20
        );

        //mockTheCalls
        when(repository.findById(studentId)).thenReturn(Optional.of(student));

        //when
        studentService.delete(studentId);

        //then
        verify(repository, times(1)).deleteById(studentId);
    }

}