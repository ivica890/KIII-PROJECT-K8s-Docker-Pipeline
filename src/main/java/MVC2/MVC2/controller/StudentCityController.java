package MVC2.MVC2.controller;

import MVC2.MVC2.entity.StudentCity;
import MVC2.MVC2.service.StudentCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentCityController {
    private final StudentCityService service;

    @Autowired
    public StudentCityController(StudentCityService service) {
        this.service = service;
    }

    @GetMapping("/cities")
    public String getAllCities(Model model) {
        model.addAttribute("cities", service.getAllCities());
        return "city/cities";
    }

    @GetMapping("/cities/edit/{id}")
    public String editCity(@PathVariable Long id,
                           Model model) {
        model.addAttribute("city", service.getCityById(id));
        return "city/edit_city";
    }

    @PostMapping("/cities/{id}")
    public String addEditedCity(@PathVariable Long id,
                                @ModelAttribute("city") StudentCity city) {
        StudentCity existingCity = service.getCityById(id);
        existingCity.setCityName(city.getCityName());
        existingCity.setCityCode(city.getCityCode());
        service.saveCity(existingCity);
        return "redirect:/cities";
    }

    @GetMapping("/cities/{id}")
    public String deleteCity(@PathVariable Long id) {
        service.deleteCityById(id);
        return "redirect:/cities";
    }

}
