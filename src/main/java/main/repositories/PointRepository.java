package main.repositories;

import main.models.Point;
import main.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PointRepository extends JpaRepository<Point, Integer> {
    Collection<Point> findByUser(User user);
}
