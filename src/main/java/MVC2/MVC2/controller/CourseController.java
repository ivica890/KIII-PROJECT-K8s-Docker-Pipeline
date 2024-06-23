package MVC2.MVC2.controller;

import MVC2.MVC2.entity.Course;
import MVC2.MVC2.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public String showCourses(Model model) {
        model.addAttribute("courses", courseService.showCourses());
        return "course/courses";
    }

//    @GetMapping("courses/new")
//    public String createCourse(Model model){
//        Course course = new Course();
//        model.addAttribute("course",course);
//        return "create_course";
//    }
//
//    @PostMapping("/courses")
//    public String saveCourse(@ModelAttribute("courses") Course course){
//        courseService.saveCourse(course);
//        return "redirect:/courses";
//    }

    @GetMapping("/courses/edit/{id}")
    public String findCourseById(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.findCourseById(id));
        return "course/edit_course";
    }

    @PostMapping("/courses/{id}")
    public String updateCourse(@PathVariable Long id,
                               @ModelAttribute Course course,
                               Model model) {
        Course existingCourse = courseService.findCourseById(id);
        existingCourse.setCourseName(course.getCourseName());
        existingCourse.setCredits(course.getCredits());
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/courses/{id}")
    public String deleteStudent(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }
}
