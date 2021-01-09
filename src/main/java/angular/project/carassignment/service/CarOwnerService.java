package angular.project.carassignment.service;

import angular.project.carassignment.controller.CarOwnerController;
import angular.project.carassignment.model.Car;
import angular.project.carassignment.model.CarOwner;
import angular.project.carassignment.model.CarStatus;
import angular.project.carassignment.model.Owner;
import angular.project.carassignment.repository.CarOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarOwnerService {

    @Autowired
    private CarOwnerRepository carOwnerRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private OwnerService ownerService;

    public List<CarOwner> getAllCarOwners(){
        return carOwnerRepository.findAll();
    }

    public CarOwner getCarOwnerById(String id){
        return carOwnerRepository.findById(id).orElse(null);
    }

    public CarOwner save(String id, CarOwnerController.CarOwnerDTO carOwnerDTO){
        CarOwner carOwner = ( id != null ) ? getCarOwnerById(id) : new CarOwner();

        if( !carOwnerAlreadyExist(carOwnerDTO) ){
            try {
                Car car = carService.getCarById(carOwnerDTO.getCarId());
                Owner owner = ownerService.getOwnerById(carOwnerDTO.getOwnerId());

                carOwner.setDateAssignation(carOwnerDTO.getDateAssignation());
                carOwner.setCar(car);
                carOwner.setOwner(owner);

                carOwnerRepository.save(carOwner);
                //update car status to occupied
                updateCarStatusId(car.getId());
            }catch (Exception ex){
                System.out.println("Error to save Car Owner.");
            }
        }else {
            System.out.println("Car Owner already exist.");
        }

        return carOwner;
    }

    public boolean delete(String id){
        boolean result = false;

        try {
            carOwnerRepository.deleteById(id);
            result = true;
        }catch (Exception ex){
            System.out.println("Error to delete Car Owner Assignation "+ex);
        }

        return result;
    }

    private void updateCarStatusId(String carId){
        carService.updateCarStatusId(carId);
    }

    private boolean carOwnerAlreadyExist(CarOwnerController.CarOwnerDTO carOwnerDTO){
        List<CarOwner> carOwnerList = getAllCarOwners();
        boolean result = false;

        if (carOwnerList != null){
            for (CarOwner carOwner: carOwnerList) {
                if ( carOwner.getCar().getId().equals(carOwnerDTO.getCarId()) ){
                    result = true;
                    break;
                }
            }
        }

        return result;
    }
}
