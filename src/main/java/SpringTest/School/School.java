package SpringTest.School;


import SpringTest.Student.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class School {

    public School(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany(
            mappedBy = "school"
    )
    @JsonManagedReference
    private List<Student> students;
}
