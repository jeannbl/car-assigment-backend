package angular.project.carassignment.controller;

import angular.project.carassignment.model.Car;
import angular.project.carassignment.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/getAll")
    public List<Car> getAllCars(){
        return carService.getAllCars();
    }

    @GetMapping("/getById/{id}")
    public Car getCarById(@PathVariable String id){
        return carService.getCarById(id);
    }

    @GetMapping("/availableCars")
    public List<Car> getAvailableCars(){
        return carService.getAvailableCars();
    }

    @PostMapping("/new")
    public Car newCar(@RequestBody CarDTO newCar){
        return carService.save(null, newCar);
    }

    @PutMapping("/update/{id}")
    public Car updateCar(@PathVariable String id, @RequestBody CarDTO carDTO){
        return carService.save(id, carDTO);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id){
        return carService.delete(id);
    }

    public static class CarDTO{
        private String brand;
        private String model;
        private String color;
        private int year;
        private double price;
        private String carStatusId;

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getCarStatusId() {
            return carStatusId;
        }

        public void setCarStatusId(String carStatusId) {
            this.carStatusId = carStatusId;
        }
    }
}
