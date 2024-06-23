package MVC2.MVC2.repository;

import MVC2.MVC2.entity.Course;
import MVC2.MVC2.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findCourseByCourseName(String name);
}
