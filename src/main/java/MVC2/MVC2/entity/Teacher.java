package MVC2.MVC2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "teacher_table"
)

public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;
    private String teacherName;
    private String teacherLastName;

    @OneToMany(cascade = CascadeType.MERGE)

    @JoinColumn(name = "teacher_id",
            referencedColumnName = "teacherId")
    private List<Course> courses;


    public Teacher(String teacherName, String teacherLastName) {
        this.teacherName = teacherName;
        this.teacherLastName = teacherLastName;
    }

    public Teacher(String teacherName, String teacherLastName, List<Course> courses) {
        this.teacherName = teacherName;
        this.teacherLastName = teacherLastName;
        this.courses = courses;
    }


}
