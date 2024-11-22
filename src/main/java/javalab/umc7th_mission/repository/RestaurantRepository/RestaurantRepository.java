package javalab.umc7th_mission.repository.RestaurantRepository;

import javalab.umc7th_mission.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
