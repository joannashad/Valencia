/*
 * Race.java
 * Description: Creates a form for user input of Year/Make/Model for 2 vehicles
 * Send both vehicles to the race (RaceSimulator)
 * Author: Joanna Smith
 */


import java .awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.io.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

public class Race  extends JPanel implements ActionListener {
  	protected JTextField txtYear ;
	protected JComboBox cmdMake ;;
	protected JComboBox cmdModel ;
        protected JButton btn;
        protected JButton btnRace ;
        protected JLabel lblRace;
        protected JLabel lbl;
        protected JRadioButton rdoType1;
        protected JRadioButton rdoType2;
        protected JRadioButton rdoType3;
        protected ArrayList<Vehicle> vArray = new ArrayList<Vehicle>() ;
        protected ButtonGroup groupType;
        
        public Race(){
		buildGUI();
        } 
        private void buildGUI()
        {
                setLayout(new GridBagLayout());
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                //this.setPreferredSize(new Dimension(400,500));
		
			
		Border edges = BorderFactory.createEmptyBorder(20,20,20,20);
		setBorder(edges);
		GridBagConstraints gbc = new GridBagConstraints();
        
                //label and radio buttons for type of loan
		lbl = new JLabel();
		lbl.setText("Type of Vehicle");
		lbl.setVisible(true);
		gbc.weightx = 0.5;
		gbc.gridwidth = 1;
		gbc.gridx = 0;//1st column
		gbc.gridy = 0;//1st row
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(lbl,gbc);
		
		rdoType1 = new JRadioButton("Car");
		rdoType1.setActionCommand("0");
		rdoType1.setText("Car");
		rdoType1.setSelected(true);
                rdoType1.addActionListener(this);
                gbc.weightx = 0.5;
		gbc.gridwidth = 1;
		gbc.gridx = 1;//2nd column
		gbc.gridy = 0;//1st row
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(rdoType1,gbc);
		rdoType2 = new JRadioButton("Motorcycle");
		rdoType2.setActionCommand("1");
		rdoType2.setText("Motorcycle");
                rdoType2.addActionListener(this);
		gbc.weightx = 0.5;
		gbc.gridwidth = 1;
		gbc.gridx = 2;//3rd column
		gbc.gridy = 0;//1st row
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(rdoType2,gbc);
		rdoType3 = new JRadioButton("Truck");
		rdoType3.setActionCommand("2");
		rdoType3.setText("Truck");
                rdoType3.addActionListener(this);
		gbc.weightx = 0.5;
		gbc.gridwidth = 1;
		gbc.gridx = 3;//4th column
		gbc.gridy = 0;//1st row
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(rdoType3,gbc);
 		//group the radio buttons
		groupType = new ButtonGroup();
		groupType.add(rdoType1);
		groupType.add(rdoType2);
		groupType.add(rdoType3); 
		//put the radio buttons in a panel

                
		//Text box and label for Year
                gbc = new GridBagConstraints();
		JLabel lbl = new JLabel();
		lbl.setText("Year");
		lbl.setVisible(true);
                lbl.setHorizontalAlignment(JLabel.RIGHT);
                gbc.ipadx=20;
		gbc.weightx = 0.5;
                //gbc.weighty = 0.5;
		gbc.gridwidth = 1;
		gbc.gridx = 0;//1st column
		gbc.gridy = 1;//2nd row
		gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(0,0,0,20);
		add(lbl,gbc);
                

		
                gbc = new GridBagConstraints();
		txtYear = new JTextField();
                txtYear.setActionCommand("Year"); 
		txtYear.addActionListener(this);
		txtYear.setEditable(true); //make it editable
                txtYear.setFocusable(true);
		gbc.weightx = 0.5;
		gbc.gridwidth = 3;
		gbc.gridx = 1;
		gbc.gridy = 1;//2nd row
                gbc.insets = new Insets(0,0,0,0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(txtYear, gbc);//add it to the frame
                
                
		//Text box and label for Make
                gbc = new GridBagConstraints();
		lbl = new JLabel();
		lbl.setText("Make");
		lbl.setVisible(true);
		gbc.weightx = 0.5;
                //gbc.weighty = 0.5;
		gbc.gridwidth = 1;
		gbc.gridx = 0;//1st column
		gbc.gridy = 2;//3rd row
                gbc.ipadx=20;
		gbc.fill = GridBagConstraints.HORIZONTAL;
                lbl.setHorizontalAlignment(JLabel.RIGHT);
                gbc.insets = new Insets(0,0,0,20);
		add(lbl,gbc);
		
			
                gbc = new GridBagConstraints();
		cmdMake = new JComboBox(fillMake());
                cmdMake.setFocusable(true); 
		cmdMake.setEditable(true); //make it editable
		gbc.weightx = 0.5;
                gbc.ipadx=0;
                //gbc.weighty = 0.5;
		gbc.gridwidth = 3;
		gbc.gridx = 1;
		gbc.gridy = 2;//3rd row
                gbc.insets = new Insets(0,0,0,0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(cmdMake, gbc);//add it to the frame
				
                //Text box and label for Model
                gbc = new GridBagConstraints();
		lbl = new JLabel();
		lbl.setText("Model");
		lbl.setVisible(true);
		gbc.weightx = 0.5;
                //gbc.weighty = 0.5;
		gbc.gridwidth = 1;
		gbc.gridx = 0;//1st column
		gbc.gridy = 3;//3rd row
                gbc.ipadx=20;
		gbc.fill = GridBagConstraints.HORIZONTAL;
                lbl.setHorizontalAlignment(JLabel.RIGHT);
                gbc.insets = new Insets(0,0,0,20);
		add(lbl,gbc);
		
                gbc = new GridBagConstraints();
		cmdModel = new JComboBox(fillModel());
                cmdModel.setFocusable(true); 
		cmdModel.setEditable(true); //make it editable
		gbc.weightx = 0.5;
                gbc.ipadx=0;
                //gbc.weighty = 0.5;
		gbc.gridwidth = 3;
		gbc.gridx = 1;
		gbc.gridy = 3;//3rd row
                gbc.insets = new Insets(0,0,0,0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(cmdModel, gbc);//add it to the frame
                
		
                //Action button
                gbc = new GridBagConstraints();
		btn = new JButton();
                btn.setActionCommand("addButton");        
		btn.setText("Add Vehicle");
		btn.setMnemonic(KeyEvent.VK_D);
		btn.addActionListener(this);
                btn.setFocusable(true);
		//gbc.ipady = 20;
		gbc.weightx = 0.5;
                //gbc.weighty = 0.5;
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = 4;//4th row
                //gbc.insets = new Insets(10,10,40,40);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(btn,gbc);
                
                //Action button
                gbc = new GridBagConstraints();
		btnRace = new JButton();
                btnRace.setActionCommand("raceButton");        
		btnRace.setText("Race");
		btnRace.setMnemonic(KeyEvent.VK_D);
		btnRace.addActionListener(this);
                btnRace.setFocusable(true);
                btnRace.setEnabled(false);
		//gbc.ipady = 20;
		gbc.weightx = 0.5;
                //gbc.weighty = 0.5;
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = 5;//4th row
                //gbc.insets = new Insets(10,10,40,40);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(btnRace,gbc);
                
		//label for each car results
                gbc = new GridBagConstraints();
		lblRace = new JLabel("Cars in Race:");
		lblRace.setVisible(true);
                lblRace.setPreferredSize(new Dimension(100, 400));
                lbl.setVerticalAlignment(JLabel.TOP);
		gbc.weightx = 0.5;
                //gbc.weighty = 0.5;   
                gbc.ipady = 0;
                gbc.ipadx=0;
		gbc.gridwidth = 2;
		gbc.gridx = 0;//1st column
		gbc.gridy = 6;//6th row
		gbc.fill = GridBagConstraints.HORIZONTAL;
                //gbc.anchor(GridBagConstraints.FIRST_LINE_START);
		add(lblRace,gbc);
               
               
                //cmdMake.grabFocus();
        }
        private String[] fillMake()
        {  //fill the combo box with make options         
            String makeArray [] = {"","Ford", "Dodge", "Chevrolet", "Honda", "Toyota", "VW", "Hyundai"};
            return makeArray;
        }
       private String[] fillModel()
        {   //fill the combo box with model options        
            String makeModel [] = {"","Mustang", "Corvet", "Prius", "Focus", "Beetle", "Acura", "Siena", "Accord", "Sonata"};
            return makeModel;
            
            
        }
       private String[] fillMotorcycleMake()
        {   //fill the combo box with model options        
            String makeModel [] = {"","Honda", "Harley", "BMW", "Kawasaki", "Indian", "Suzuki", "Yamaha"};
            return makeModel;
            
        }
       private String[] fillMotorcycleModel()
        {   //fill the combo box with model options        
            String makeModel [] = {"","Gold Wing", "Africa Twin", "SuperLow", "Wide Glide", "Night Rod", "Trailmaster-100", "Crux", "XVZ1200 Venture Royale"};
            return makeModel;
            
        }
       private String[] fillTruckMake()
        {   //fill the combo box with model options        
            String makeModel [] = {"","Ram", "Ford", "Honda", "GMC", "Toyota", "Chevrolet"};
            return makeModel;
        }
       private String[] fillTruckModel()
        {   //fill the combo box with model options        
            String makeModel [] = {"","F250 Super Duty", "F150 Regular Cab", "Quad Cab", "Mega Cab", "Tacoma Access Cab", "Tundra CrewMax"};
            return makeModel;
            
        }
       private void fillVehicles(int Type)
       {String make[] = null;
       String Model[] = null;
           switch(Type) 
            {
               case 0: 
                   make =fillMake();  
                   Model = fillModel();
                   break;
               case 1: 
                   make =fillMotorcycleMake(); 
                   Model = fillMotorcycleModel();
                   break;
               case 2: 
                   make =fillTruckMake(); 
                   Model = fillTruckModel();
                   break;
               
            }
           if(make!=null && Model !=null)
           {
               cmdMake.removeAllItems();
               cmdModel.removeAllItems();
                   for (String s : make) {
                     cmdMake.insertItemAt(s,cmdMake.getItemCount());
                   }             
                   for (String s : Model) {
                     cmdModel.insertItemAt(s,cmdModel.getItemCount());
                   }
           }
       }
	public static void main(String args[])
	{//create the panel
            try
            {
		JPanel pnl = new Race();
                //pnl.setAnchor(GridBagConstraints.NORTHWEST);
		JFrame myFrame = new JFrame();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setContentPane(pnl);
		myFrame.pack();
		myFrame.setVisible(true);
            }
            catch(Exception e)
            {
            //ShowMessage(e.getMessage(),JOptionPane.ERROR_MESSAGE, "Error");
            }

	}
    public void actionPerformed(ActionEvent e) 
    {
        String action = e.getActionCommand();
        String strRequired = "Car not added: ";
        try
        {

            switch(action)
            {
                case "addButton" ->                 {
                if(txtYear.getText().length() ==0)
                {
                    ShowMessage(strRequired + "Year is required!",JOptionPane.ERROR_MESSAGE, "Error");
                    return;
                }
                int Year = Integer.valueOf(txtYear.getText());
                if((Year>2021)|| (Year<1910) )
                {
                    ShowMessage("Year must be between 1910 and 2021!",JOptionPane.ERROR_MESSAGE, "Error");                
                    return;
                }
                if(cmdMake.getSelectedItem().toString().length() ==0)
                {
                    ShowMessage(strRequired + "Make is required!",JOptionPane.ERROR_MESSAGE, "Error");
                    return;
                }
                if(cmdModel.getSelectedItem().toString().length() ==0)
                {
                    ShowMessage(strRequired + "Model is required!",JOptionPane.ERROR_MESSAGE, "Error");
                    return;
                }
                //Add the vehicle to the array 
                String strType = groupType.getSelection().getActionCommand();
                int i = 1;
                if(vArray!=null)
                    i=vArray.size()+1;
               int type = Integer.valueOf(strType);
               addVehicle(Integer.valueOf(txtYear.getText()),cmdMake.getSelectedItem().toString(),cmdModel.getSelectedItem().toString(),i,type);
               }           
                
                case "raceButton" -> RaceCars(vArray);
                case "0","1","2" -> fillVehicles(Integer.valueOf(action));
                    }
        }
        catch(NumberFormatException ex)
        {
              ShowMessage(ex.getMessage(),JOptionPane.ERROR_MESSAGE, "Error");
        }
    }
        private void ShowMessage(String strError, int intErrorType,String strErrorType)
    {//Display an error or information pop up box        
        JOptionPane.showMessageDialog(this,
        strError,
        strErrorType,
        intErrorType);
        //JOptionPane.ERROR_MESSAGE);
    }
        private void addVehicle(int Year, String Make, String Model, int Number, int Type)
        {// Add the car to the array
            try 
            {
                RaceSimulator sim = new RaceSimulator();
                switch (Type)
                {
                    case 0 -> {
                        Car car = new Car(sim) ;
                        car.setVariables(Year, Make, Model, Number);
                        vArray.add(car);
                    }
                    case 1 -> {
                        Motorcycle motorcycle = new Motorcycle(sim);
                        motorcycle.setVariables(Year, Make, Model, Number);
                        vArray.add(motorcycle);
                    }
                    case 2 -> {
                        Truck truck = new Truck(sim);
                        truck.setVariables(Year, Make, Model, Number);
                        vArray.add(truck);
                    }
                }             

               
               //add each car in the display box
               String strCar ="<html>";
               for(Vehicle i:vArray)     
                   strCar +=i.name +"<br/>";
               
                strCar +="</html>";
                lblRace.setText(strCar);
                //enable the race button
               if(vArray.size()>1)
                   btnRace.setEnabled(true);
                       
                
            }
            catch (Exception e)
            {
                ShowMessage(e.getMessage(),JOptionPane.ERROR_MESSAGE, "Error");
            }
        }
        private void RaceCars(ArrayList<Vehicle> myArray)
        {//create a thread for the race go through each car
            for(Vehicle vehicle : myArray)
            {
               Thread race = new Thread(vehicle); 
               //make the thread user
                race.setDaemon(false);
               race.start();
            }
            
      }
        
}
