/*
 * File: RaceSimulator.java
 * Description: Create a thread where a car can go through a course
 * record the start/end times and print out checkpoints
*  Author: Joanna Smith
 */

import java.awt.ComponentOrientation;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Date;
import java.time.Duration;
import java.time.LocalTime;
import java.text.DateFormat; 
import java.text.SimpleDateFormat;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.Border;
import java.io.*;
import java.util.Random;

public class RaceSimulator  {

    protected int currentSpeed;
    protected int speedLimit;
    public Boolean winner=false;
    public Long timeElapsed;
    public Long startTime;
    public Long endTime;
    public String raceTime;
    protected int position=0;
    protected Random rand=new Random();
    protected int nextTurn=0;
    

    public void race(String name,int normalSpeed, int SpeedLimit)
    {
      currentSpeed = normalSpeed;
      speedLimit = SpeedLimit;
      FileWriter fw=null;
      PrintWriter out=null;// = new PrintWriter(f);
      String message="";
      try
      {
        try {
            //File root = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
           File file = new File( "./RaceSimulator.txt");

           if (!file.exists()) {
              file.createNewFile();
           } 
           fw = new FileWriter(file.getAbsoluteFile());
           out = new PrintWriter(fw);

     //set the start time   
     startTime =   System.currentTimeMillis();
    
        //set the track length
        int trackLength = 2000;
        while (position <= trackLength && !winner)
        {                     
      
            try{
                Thread.sleep((long)(Math.random()*1000));//thread goes to sleep
            }catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
            //determine if a straight away or turn to accelerate or decelerate
            NextTurn();
            message =name +" checkpoint:"+ position + " @ " + currentSpeed + "mph";
           out.write("\n" + message);
           out.flush();
           System.out.println(message);
           position+=currentSpeed;
           //record the winner

           //stop if the vehicle will reach the end
           if((position>=trackLength) &&!winner)
           {
             winner=true;
             
        //record the end time of the car
        endTime =  System.currentTimeMillis();
        //calculate the time elapsed and divide by 1000 to get seconds
        timeElapsed = (endTime - startTime)/1000;
        //print the results
        
        message=name + " Finished!";
        System.out.println(message);
        out.write("\n" + message);
        message=name + " Time:" +  timeElapsed ;
        out.write("\n" + message);
        System.out.println(message);
       
        if(winner)
        {
            message = name + " is the WINNER!";
            out.write("\n" + message);
            System.out.println(message);
        }
            out.flush();
        
            break;
           }
           if(winner)break;
        }

        
         } catch (IOException e) { e.printStackTrace(); } 
        finally{fw.close();out.close();}
      }catch (IOException e) { e.printStackTrace();  }
     }
    private void LogMessage(String message, Boolean last)
    {
      FileWriter fw=null;
      try
      {
      try {
          //File root = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
         File file = new File( "./RaceSimulator.txt");
        
         if (!file.exists()) {
            file.createNewFile();
         } 
         fw = new FileWriter(file.getAbsoluteFile());
         fw.write(message);
         fw.flush();
      } catch (IOException e) {
         e.printStackTrace();
      } 
      finally{
         if(last)
             fw.close();
        }
      }catch (IOException e) {
        e.printStackTrace();
      }
    }
 
    protected void accelerate()
    {
       int nextSpeed = rand.nextInt(10);
       if((currentSpeed+nextSpeed)<=speedLimit)
            currentSpeed += nextSpeed;
    }
  protected void decelerate()
    {
       int nextSpeed = rand.nextInt(10);
       if((currentSpeed-nextSpeed)>45)
          currentSpeed -= nextSpeed;  
    }
  protected void NextTurn()
  {
      switch(nextTurn)
      {
          //straight away
          case 0: {accelerate();}
          //left turn
          case 1: {decelerate();}
          //straight away
          case 2: {accelerate();}
          //left turn
          case 3: {decelerate();nextTurn=0;break;}  
          
      }
      nextTurn++;
  }
}
