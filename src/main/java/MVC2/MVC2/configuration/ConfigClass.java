package MVC2.MVC2.configuration;

import MVC2.MVC2.entity.Course;
import MVC2.MVC2.entity.Student;
import MVC2.MVC2.entity.StudentCity;
import MVC2.MVC2.entity.Teacher;
import MVC2.MVC2.repository.CourseRepository;
import MVC2.MVC2.repository.StudentCityRepository;
import MVC2.MVC2.repository.StudentRepository;
import MVC2.MVC2.repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.util.List;

@Configuration
public class ConfigClass {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository,
                                        TeacherRepository teacherRepository,
                                        CourseRepository courseRepository,
                                        StudentCityRepository studentCityRepository){
        return args -> {
            Course course1 = new Course("AOK",6);
            courseRepository.save(course1);
            Course course2 = new Course("KMiB",6);
            courseRepository.save(course2);
            Course course3 = new Course("OOP",6);
            courseRepository.save(course3);

            StudentCity studentCity1 = new StudentCity("Bitola",7000);
            studentCityRepository.save(studentCity1);
            StudentCity studentCity2 = new StudentCity("Ohrid",4000);
            studentCityRepository.save(studentCity2);
            StudentCity studentCity3 = new StudentCity("Skopje",1000);
            studentCityRepository.save(studentCity3);

            Student student1 = new Student("Ivica","Cickoski","icickoski@gmail.com",course1,studentCity1);
            studentRepository.save(student1);
            Student student2 = new Student("Luka","Cickoski","lcickoski@gmail.com",course3,studentCity2);
            studentRepository.save(student2);
            Student student3 = new Student("Tony","Montana","tony@gmail.com",course2,studentCity3);
            studentRepository.save(student3);

            Teacher teacher1 = new Teacher("Mende","Mende", List.of(course1,course2));
            teacherRepository.save(teacher1);
            Teacher teacher2 = new Teacher("Cane","Pelister",List.of(course3));
            teacherRepository.save(teacher2);
            Teacher teacher3 = new Teacher("Ronaldo","SIIIIU",List.of(course2));
            teacherRepository.save(teacher3);

            Course course4 = new Course("SP",6);
            courseRepository.save(course4);
        };
    }
}
