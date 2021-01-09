package angular.project.carassignment.controller;

import angular.project.carassignment.model.Owner;
import angular.project.carassignment.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/getAll")
    public List<Owner> getAllOwners(){
        return ownerService.getAllOwners();
    }

    @GetMapping("/getById/{id}")
    public Owner getOwnerById(@PathVariable String id){
        return ownerService.getOwnerById(id);
    }

    @PostMapping("/new")
    public Owner newOwner(@RequestBody OwnerDTO newOwner){
        return ownerService.save(null, newOwner);
    }

    @PutMapping("/update/{id}")
    public Owner updateOwner(@PathVariable String id, @RequestBody OwnerDTO updateOwner){
        return ownerService.save(id, updateOwner);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteOwner(@PathVariable String id){
        return ownerService.delete(id);
    }

    public static class OwnerDTO{
        private String name;
        private String firstLastName;
        private String secondLastName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirstLastName() {
            return firstLastName;
        }

        public void setFirstLastName(String firstLastName) {
            this.firstLastName = firstLastName;
        }

        public String getSecondLastName() {
            return secondLastName;
        }

        public void setSecondLastName(String secondLastName) {
            this.secondLastName = secondLastName;
        }
    }
}
