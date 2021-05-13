/*
 * Motorcycle.java
 * Description: Extends Vehicle object to create Car object
 * allows the car to accellerate and descelerate
 * Author: Joanna Smith
 */


    
public class Motorcycle extends  Vehicle {
    protected int year;
    protected String model;
    protected String make;
    protected int number;
    
    public Motorcycle(RaceSimulator sim)
    {super(sim);
     normalSpeed = 60;}
  
    public void setVariables(int carYear,String carMake, String carModel,int carNumber){
        year = carYear;
        make = carMake;
        model  = carModel;
        number = carNumber;
        name = "Vehicle #" + number + ":" + carYear + "- " + carMake + " - " + carModel ;
    }

}
