package angular.project.carassignment.service;

import angular.project.carassignment.controller.OwnerController;
import angular.project.carassignment.model.Owner;
import angular.project.carassignment.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public List<Owner> getAllOwners(){
        return ownerRepository.findAll();
    }

    public Owner getOwnerById(String id){
        return ownerRepository.findById(id).orElse(null);
    }

    public Owner save(String id, OwnerController.OwnerDTO ownerDTO){
        Owner owner =  id != null ? getOwnerById(id) : new Owner();

        if( !ownerAlreadyExist(ownerDTO) ){
            if( owner != null ){
                try {
                    owner.setName(ownerDTO.getName());
                    owner.setFirstLastName(ownerDTO.getFirstLastName());
                    owner.setSecondLastName(ownerDTO.getSecondLastName());

                    ownerRepository.save(owner);
                }catch (Exception ex){
                    System.out.println("Error to save Owner");
                }
            }else {
                System.out.println("Owner to update not found.");
            }
        }else {
            System.out.println("Owner is already registered.");
        }

        return owner;
    }

    public boolean delete(String id){
        boolean result = false;

        if( id != null ){
            try {
                ownerRepository.deleteById(id);
                result = true;
            }catch (Exception ex){
                System.out.println("Exception to delete Owner "+ex);
            }
        }else {
            System.out.println("Owner could not found.");
        }

        return result;
    }

    private boolean ownerAlreadyExist(OwnerController.OwnerDTO ownerDTO){
        boolean result = false;
        List<Owner> ownerList = getAllOwners();

        for ( Owner owner: ownerList) {
            if( owner.getName().equals(ownerDTO.getName()) &&
                owner.getFirstLastName().equals(ownerDTO.getFirstLastName()) &&
                owner.getSecondLastName().equals(ownerDTO.getSecondLastName()) ){
                result = true;
                break;
            }
        }

        return result;
    }
}
