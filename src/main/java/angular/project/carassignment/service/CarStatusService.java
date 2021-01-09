package angular.project.carassignment.service;

import angular.project.carassignment.controller.CarStatusController;
import angular.project.carassignment.model.CarStatus;
import angular.project.carassignment.repository.CarStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarStatusService {

    @Autowired
    private CarStatusRepository carStatusRepository;

    public List<CarStatus> getAllCarStatus(){
        return carStatusRepository.findAll();
    }

    public CarStatus getCarStatusById(String id){
        return  carStatusRepository.findById(id).orElse(null);
    }

    public CarStatus getCarStatusByName(String name){
        return carStatusRepository.getCarStatusByName(name);
    }

    public CarStatus save(String id, CarStatusController.CarStatusDTO carStatusDTO){
        CarStatus carStatus = (id != null) ? getCarStatusById(id): new CarStatus();

        if( !carStatusAlreadyExist(carStatusDTO) ){
            try {
                carStatus.setName(carStatusDTO.getName());
                if( carStatusDTO.getDescription() != null){
                    carStatus.setDescription(carStatusDTO.getDescription());
                }

                carStatusRepository.save(carStatus);
            }catch (Exception ex){
                System.out.println("Error to save new Car Status.");
            }
        }else {
            System.out.println("Car Status is already registered.");
        }

        return  carStatus;
    }

    public boolean delete(String id){
        boolean result = false;

        try {
            carStatusRepository.deleteById(id);
            result = true;
        }catch (Exception ex){
            System.out.println("Exception to delete Car Status.");
        }

        return  result;
    }

    private boolean carStatusAlreadyExist(CarStatusController.CarStatusDTO carStatusDTO){
        List<CarStatus> carStatusList = getAllCarStatus();
        boolean result = false;

        if (carStatusList != null){
            for (CarStatus carStatus: carStatusList) {
                if( carStatus.getName().equals(carStatusDTO.getName()) ){
                    result = true;
                    break;
                }
            }
        }

        return result;
    }
}
