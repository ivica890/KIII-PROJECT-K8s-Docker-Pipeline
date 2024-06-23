package MVC2.MVC2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "city_table"
//        uniqueConstraints = @UniqueConstraint(
//                name = "city_uniq",
//                columnNames = "cityName"
//        )
)
public class StudentCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;
    private String cityName;
    private Integer cityCode;

    public StudentCity(String cityName, Integer cityCode) {
        this.cityName = cityName;
        this.cityCode = cityCode;
    }
}
