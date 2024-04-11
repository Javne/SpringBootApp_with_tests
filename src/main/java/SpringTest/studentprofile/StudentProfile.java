package SpringTest.studentprofile;


import SpringTest.Student.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class StudentProfile {

    public StudentProfile(String bio) {
        this.bio = bio;
    }

    @Id
    @GeneratedValue
    private Integer id;
    private String bio;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

}
