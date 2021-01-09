package angular.project.carassignment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class CarOwner {

    @Id
    private String id;
    private Date dateAssignation;

    @DBRef
    private Car car;
    @DBRef
    private Owner owner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateAssignation() {
        return dateAssignation;
    }

    public void setDateAssignation(Date dateAssignation) {
        this.dateAssignation = dateAssignation;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString(){
        return String.format("CarAssignment[id='%s', dateAssignation='%s']", id, dateAssignation);
    }
}
