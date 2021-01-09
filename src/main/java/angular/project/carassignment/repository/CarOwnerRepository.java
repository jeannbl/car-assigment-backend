package angular.project.carassignment.repository;

import angular.project.carassignment.model.CarOwner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarOwnerRepository extends MongoRepository<CarOwner, String> {
}
