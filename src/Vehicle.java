
/*
 * Vehicle.java
 * Description: Base class for the race
 * 
 * Author: Joanna Smith
 */



public abstract class Vehicle implements Runnable {
 
    protected String name;
    protected Double distanceTraveled=0.0;
    protected int speedLimit = 70;
    protected RaceSimulator race;
    protected int normalSpeed;
    protected static boolean winner = false;
    
    public Vehicle(RaceSimulator sim)
    { race=sim;}
   
  public void run()
{
    race.race(name, normalSpeed, speedLimit);
} 
}