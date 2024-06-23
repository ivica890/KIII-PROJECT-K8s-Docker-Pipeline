package MVC2.MVC2.service;

import MVC2.MVC2.entity.StudentCity;
import MVC2.MVC2.entity.Teacher;
import MVC2.MVC2.repository.StudentCityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentCityService {

    private final StudentCityRepository studentCityRepository;

    @Autowired
    public StudentCityService(StudentCityRepository studentCityRepository) {
        this.studentCityRepository = studentCityRepository;
    }

    public List<StudentCity> getAllCities() {
        return studentCityRepository.findAll();
    }

    public StudentCity getCityById(Long id) {

        boolean exists = studentCityRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(
                    "This city already exists");
        }

        return studentCityRepository.findById(id).get();
    }

    public void saveCity(StudentCity city) {
        Optional<StudentCity> cityCode =
                studentCityRepository.findByCityCode(city.getCityCode());
        if (cityCode.isPresent()) {
            throw new IllegalStateException(
                    "city is taken"
            );
        }
        studentCityRepository.save(city);
    }

    public void deleteCityById(Long id) {
        boolean exists = studentCityRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("City with id: " + id + " does not exists");
        }
        studentCityRepository.deleteById(id);
    }
}
