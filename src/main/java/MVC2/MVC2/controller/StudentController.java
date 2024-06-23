package MVC2.MVC2.controller;

import MVC2.MVC2.entity.Course;
import MVC2.MVC2.entity.Student;
import MVC2.MVC2.entity.StudentCity;
import MVC2.MVC2.service.CourseService;
import MVC2.MVC2.service.StudentCityService;
import MVC2.MVC2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    private final StudentCityService studentCityService;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService, StudentCityService studentCityService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.studentCityService = studentCityService;
    }

    @GetMapping("/students")
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "student/students";
    }


    @GetMapping("/students/new")
    public String createStudent(Model model) {
        StudentCity studentCity = new StudentCity();
        model.addAttribute("studentCity", studentCity);
        Course course = new Course();
        model.addAttribute("course", course);
        Student student = new Student();
        model.addAttribute("student", student);
        return "student/create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student,
                              @ModelAttribute("course") Course course,
                              @ModelAttribute("studentCity") StudentCity studentCity) {
        student.setFirstName(student.getFirstName());
        student.setLastName(student.getLastName());
        student.setEmail(student.getEmail());
        student.setCourse(course);
        student.setStudentCity(studentCity);
        studentService.saveStudentAndCourse(student, course, studentCity);
        return "redirect:/students";
    }


    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "student/edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") Student student,
                                Model model) {
        //get student from database by id
        Student existingStudent = studentService.getStudentById(id);
        //update student
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        //save updated student object
        studentService.updateStudent(existingStudent);
        //redirect to view
        return "redirect:/students";
    }

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }


}
