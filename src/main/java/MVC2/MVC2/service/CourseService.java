package MVC2.MVC2.service;

import MVC2.MVC2.entity.Course;
import MVC2.MVC2.exceptions.NullValueException;
import MVC2.MVC2.repository.CourseRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> showCourses() {
//        List<Course> findAllList = courseRepository.findAll();
//        int isEmpty = findAllList.size();
//        if(isEmpty == 0){
//            throw new IllegalStateException(
//                    "Null value"
//            );
//        }
        return courseRepository.findAll();


    }

    public void saveCourse(Course course) {
        Optional<Course> courseName =
                courseRepository.findCourseByCourseName(course.getCourseName());
        if (courseName.isPresent()) {
            throw new IllegalStateException(
                    "course is taken"
            );
        }
        courseRepository.save(course);
    }

    public Course findCourseById(Long id) {
        boolean exists = courseRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(
                    "This course already exists");
        }
        return courseRepository.findById(id).get();
    }

    public void deleteCourse(Long id) {
        boolean exists = courseRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(
                    "Course with id: " + id + " does not exists"
            );
        }
        courseRepository.deleteById(id);
    }
}
