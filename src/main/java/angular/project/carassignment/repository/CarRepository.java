package angular.project.carassignment.repository;

import angular.project.carassignment.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car, String> {
}
