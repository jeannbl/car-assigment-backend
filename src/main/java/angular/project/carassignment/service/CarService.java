package angular.project.carassignment.service;

import angular.project.carassignment.controller.CarController;
import angular.project.carassignment.model.Car;
import angular.project.carassignment.model.CarStatus;
import angular.project.carassignment.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarStatusService carStatusService;

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Car getCarById(String id){
        return carRepository.findById(id).orElse(null);
    }

    public List<Car> getAvailableCars(){
        String carStatusId = carStatusService.getCarStatusByName("Available").getId();
        List<Car> cars = getAllCars();
        List<Car> result = new ArrayList<>();

        for (Car car:cars) {
            if (car.getCarStatus().getId().equals(carStatusId)){
                result.add(car);
            }
        }
        return result;
    }

    public Car save(String id, CarController.CarDTO carDTO){
        Car car = ( id != null ) ? getCarById(id) : new Car();

        if( !carAlreadyExist(carDTO) ){
            try {
                car.setBrand(carDTO.getBrand());
                car.setModel(carDTO.getModel());
                car.setColor(carDTO.getColor());
                car.setYear(carDTO.getYear());
                car.setPrice(carDTO.getPrice());

                //CarStatus carStatus =  id != null ? carStatusService.getCarStatusById(carDTO.getCarStatusId()): carStatusService.getCarStatusByName("Available");
                CarStatus carStatus =  id != null ? car.getCarStatus(): carStatusService.getCarStatusByName("Available");
                car.setCarStatus(carStatus);

                carRepository.save(car);
            }catch (Exception ex){
                System.out.println("Exception to save Car.");
            }
        }else {
            System.out.println("Car already exist.");
        }

        return car;
    }

    public boolean delete(String id){
        boolean result = false;
        try {
            carRepository.deleteById(id);
            result = true;
        }catch (Exception ex){
            System.out.println("Error to delete Car "+ex);
        }

        return result;
    }

    public boolean updateCarStatusId(String carId){
        boolean result = false;
        CarStatus carStatus = carStatusService.getCarStatusByName("Occupied");
        try {
            Car car = getCarById(carId);
            car.setCarStatus(carStatus);

            carRepository.save(car);
            result = true;
        }catch (Exception ex){
            System.out.println("Error to update Car Status id.");
        }
        return result;
    }

    private boolean carAlreadyExist(CarController.CarDTO carDTO){
        boolean result = false;
        List<Car> carList = getAllCars();

        if ( carList != null ){
            for (Car car: carList) {
                if (car.getBrand().equals(carDTO.getBrand()) && car.getModel().equals(carDTO.getModel()) &&
                        car.getYear() == carDTO.getYear() && car.getPrice() == carDTO.getPrice() &&
                        car.getColor() == carDTO.getColor()){
                    result = true;
                    break;
                }
            }
        }

        return result;
    }
}
