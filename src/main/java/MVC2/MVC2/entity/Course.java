package MVC2.MVC2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "course_table"
//        uniqueConstraints = @UniqueConstraint(
//                name = "course_unique",
//                columnNames = "course_name"
//        )
)

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String courseName;
    private Integer credits;

    public Course(String course_name, Integer credits) {
        this.courseName = course_name;
        this.credits = credits;
    }
}