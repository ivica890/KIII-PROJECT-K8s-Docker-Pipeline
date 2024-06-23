package MVC2.MVC2.service;

import MVC2.MVC2.entity.Course;
import MVC2.MVC2.entity.Student;
import MVC2.MVC2.entity.StudentCity;
import MVC2.MVC2.repository.CourseRepository;
import MVC2.MVC2.repository.StudentCityRepository;
import MVC2.MVC2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    private final StudentCityRepository studentCityRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, StudentCityRepository studentCityRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentCityRepository = studentCityRepository;
    }

    public List<Student> getAllStudents() {
        List<Student> studentsList = studentRepository.findAll();
        return studentsList;
    }

    public void saveStudent(Student student) {
        Optional<Student> studentEmail =
                studentRepository.findStudentByEmail(student.getEmail());
        if (studentEmail.isPresent()) {
            throw new IllegalStateException(
                    "email taken"
            );
        }

        studentRepository.save(student);
    }

    public void saveStudentAndCourse(Student student, Course course, StudentCity studentCity) {
//        boolean existsStudent = studentRepository.existsById(student.getStudentId());
//        if (!existsStudent) {
//            studentRepository.save(student);
//        } else {
//
//            throw new IllegalStateException(
//                    "Student: " + student.getFirstName() + student.getLastName() +
//                            "already exists");
//        }
//
//        boolean existCourse = courseRepository.existsById(course.getCourse_id());
//        if (!existCourse) {
//            courseRepository.save(course);
//        } else {
//            throw new IllegalStateException(
//                    "Course: " + course.getCourse_name() +
//                            "already exists");
//        }
//
//        boolean existStudentCity = studentCityRepository.existsById(studentCity.getCityId());
//        if (!existStudentCity) {
//            studentCityRepository.save(studentCity);
//        } else {
//            throw new IllegalStateException(
//                    "City: " + studentCity.getCityName() +
//                            "already exists");
//        }


        Optional<StudentCity> cityCode =
                studentCityRepository.findByCityCode(studentCity.getCityCode());
        if (cityCode.isPresent()) {
            throw new IllegalStateException(
                    "city is taken"
            );
        } else {
            studentCityRepository.save(studentCity);
        }

        Optional<Course> courseName =
                courseRepository.findCourseByCourseName(course.getCourseName());
        if (courseName.isPresent()) {
            throw new IllegalStateException(
                    "course is taken"
            );
        } else {
            courseRepository.save(course);
        }

        Optional<Student> studentEmail =
                studentRepository.findStudentByEmail(student.getEmail());
        if (studentEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        } else {
            studentRepository.save(student);
        }

        courseRepository.save(course);

    }

    public Student getStudentById(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(
                    "This student already exists");
        }
        return studentRepository.findById(id).get();
    }

    public Student updateStudent(Student student) {
        student = studentRepository.findById(student.getStudentId())
                .orElseThrow(() -> new IllegalStateException("student with does not exists"));
        if (student.getFirstName() != null
                && student.getLastName() != null
                && student.getFirstName().length() > 0
                && student.getLastName().length() > 0)
            student.setFirstName(student.getFirstName());
        student.setLastName(student.getLastName());
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {

        boolean exists = studentRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException(
                    "Student with id: " + id + " does not exists"
            );
        }


        studentRepository.deleteById(id);
    }
}
