package MVC2.MVC2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "students_table"
//        uniqueConstraints = @UniqueConstraint(
//                name = "email_unique",
//                columnNames = "email"
//        )
)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne(
            cascade = CascadeType.MERGE,
            targetEntity = Course.class
    )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course;
    @OneToOne(
            cascade = CascadeType.MERGE,
            targetEntity = StudentCity.class
    )
    @JoinColumn(
            name = "city_id",
            referencedColumnName = "cityId"
    )
    private StudentCity studentCity;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Student(String firstName, String lastName, String email, Course course, StudentCity studentCity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.course = course;
        this.studentCity = studentCity;
    }


}
