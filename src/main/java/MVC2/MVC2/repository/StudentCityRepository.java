package MVC2.MVC2.repository;

import MVC2.MVC2.entity.StudentCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentCityRepository extends JpaRepository<StudentCity, Long> {
    Optional<StudentCity> findByCityCode(Integer code);
}
