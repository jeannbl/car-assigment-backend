package angular.project.carassignment.repository;

import angular.project.carassignment.model.Owner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OwnerRepository extends MongoRepository<Owner, String > {
}
