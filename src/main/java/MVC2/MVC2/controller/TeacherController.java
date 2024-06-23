package MVC2.MVC2.controller;

import MVC2.MVC2.entity.Course;
import MVC2.MVC2.entity.Teacher;
import MVC2.MVC2.service.CourseService;
import MVC2.MVC2.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TeacherController {
    public final TeacherService teacherService;
    public final CourseService courseService;

    @Autowired
    public TeacherController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @GetMapping("/teachers")
    public String getAllTeachers(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeachers());

        return "teacher/teachers";
    }


    @GetMapping("/teachers/new")
    public String addNewTeacher(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "teacher/create_teacher";
    }

    @PostMapping("/teachers")
    public String saveTeacher(@ModelAttribute("course") Course course,
                              @ModelAttribute("teacher") Teacher teacher) {
        teacher.setTeacherName(teacher.getTeacherName());
        teacher.setTeacherLastName(teacher.getTeacherLastName());
        teacher.setCourses(List.of(course));
        teacherService.saveTeacherAndCourse(course, teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/teachers/edit/{id}")
    public String findTeacher(@PathVariable Long id, Model model) {
        model.addAttribute("teacher", teacherService.getTeacherById(id));
        return "teacher/edit_teacher";
    }

    @PostMapping("/teachers/{id}")
    public String updateTeacherById(
            @PathVariable Long id,
            @ModelAttribute Teacher teacher) {
        Teacher existingTeacher = teacherService.getTeacherById(id);

        existingTeacher.setTeacherName(teacher.getTeacherName());
        existingTeacher.setTeacherLastName(teacher.getTeacherLastName());

        teacherService.saveTeacher(existingTeacher);

        return "redirect:/teachers";
    }

    @GetMapping("/teachers/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return "redirect:/teachers";
    }
}
