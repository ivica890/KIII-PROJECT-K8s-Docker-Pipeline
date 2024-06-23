package MVC2.MVC2.service;

import MVC2.MVC2.entity.Course;
import MVC2.MVC2.entity.Teacher;
import MVC2.MVC2.repository.CourseRepository;
import MVC2.MVC2.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    public final TeacherRepository teacherRepository;
    public final CourseRepository courseRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }


    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public void saveTeacher(Teacher teacher) {
        Optional<Teacher> teacherName =
                teacherRepository.findTeacherByTeacherName(teacher.getTeacherName());
        if (teacherName.isPresent()) {
            throw new IllegalStateException(
                    "teacher is taken"
            );
        }
        teacherRepository.save(teacher);
    }

    public Teacher getTeacherById(Long id) {

        boolean exists = teacherRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(
                    "This teacher already exists");
        }
        return teacherRepository.findById(id).get();


    }

    public void deleteTeacher(Long id) {
        boolean exists = teacherRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException(
                    "Teacher with id: " + id + " does not exists"
            );
        }
        teacherRepository.deleteById(id);
    }

    public void saveTeacherAndCourse(Course course, Teacher teacher) {
        Optional<Course> courseName =
                courseRepository.findCourseByCourseName(course.getCourseName());
        if (courseName.isPresent()) {
            throw new IllegalStateException(
                    "course is taken"
            );
        } else {
            courseRepository.saveAll(List.of(course));
        }


        Optional<Teacher> teacherName =
                teacherRepository.findTeacherByTeacherName(teacher.getTeacherName());
        if (teacherName.isPresent()) {
            throw new IllegalStateException(
                    "teacher is taken"
            );
        } else {
            teacherRepository.save(teacher);
        }
    }
}
