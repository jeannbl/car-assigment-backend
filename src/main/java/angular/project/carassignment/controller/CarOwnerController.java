package angular.project.carassignment.controller;

import angular.project.carassignment.model.CarOwner;
import angular.project.carassignment.service.CarOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/carowners")
public class CarOwnerController {

    @Autowired
    private CarOwnerService carOwnerService;

    @GetMapping("/getAll")
    public List<CarOwner> getAllCarOwners(){
        return carOwnerService.getAllCarOwners();
    }

    @GetMapping("/getById/{id}")
    private CarOwner getCarOwnerById(@PathVariable String id){
        return carOwnerService.getCarOwnerById(id);
    }

    @PostMapping("/save")
    private CarOwner newCarOwner(@RequestBody CarOwnerDTO newCarOwner){
        return carOwnerService.save(null, newCarOwner);
    }

    @PutMapping("/update/{id}")
    private CarOwner update(@PathVariable String id, @RequestBody CarOwnerDTO carOwnerDTO){
        return carOwnerService.save(id, carOwnerDTO);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(String id){
        return carOwnerService.delete(id);
    }

    public static class CarOwnerDTO{
        private Date dateAssignation;
        private String carId;
        private String ownerId;

        public Date getDateAssignation() {
            return dateAssignation;
        }

        public void setDateAssignation(Date dateAssignation) {
            this.dateAssignation = dateAssignation;
        }

        public String getCarId() {
            return carId;
        }

        public void setCarId(String carId) {
            this.carId = carId;
        }

        public String getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(String ownerId) {
            this.ownerId = ownerId;
        }
    }
}
