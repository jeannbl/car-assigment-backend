package angular.project.carassignment.controller;

import angular.project.carassignment.model.CarStatus;
import angular.project.carassignment.service.CarStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/carstatus")
@RestController
public class CarStatusController {

    @Autowired
    private CarStatusService carStatusService;

    @GetMapping("/getAll")
    public List<CarStatus> getAllCarStatus(){
        return carStatusService.getAllCarStatus();
    }

    @GetMapping("/getById/{id}")
    public CarStatus getCarStatusById(@PathVariable String id){
        return carStatusService.getCarStatusById(id);
    }

    @PostMapping("/save")
    public CarStatus newCarStatus(@RequestBody CarStatusDTO newCarStatus){
        return carStatusService.save(null, newCarStatus);
    }

    @PutMapping("/update/{id}")
    public CarStatus updateCarStatus(@PathVariable String id, @RequestBody CarStatusDTO updateCarStatus){
        return carStatusService.save(id, updateCarStatus);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteCarStatus(@PathVariable String id){
        return carStatusService.delete(id);
    }

    public static class CarStatusDTO{
        private String name;
        private String description;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
