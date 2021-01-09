package angular.project.carassignment.repository;

import angular.project.carassignment.model.CarStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CarStatusRepository extends MongoRepository<CarStatus, String> {

    @Query(" {'name' : ?0} ")
    CarStatus getCarStatusByName(String name);
}
