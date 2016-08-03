
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.*;

public class SwingControl {

   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;

   public SwingControl(){
      prepareGUI();
   }
      
   private void prepareGUI(){
      mainFrame = new JFrame("Java SWING Examples");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 1));

      headerLabel = new JLabel("",JLabel.CENTER );
      statusLabel = new JLabel("",JLabel.CENTER);        

      statusLabel.setSize(350,100);
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
	        System.exit(0);
         }        
      });    
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }

   public void showEventDemo(){
      headerLabel.setText("Control in action: Button"); 

      JButton loadButton = new JButton("Load");
      JButton simButton = new JButton("Simulate");

      
      loadButton.setActionCommand("Load");
      simButton.setActionCommand("Simulate");

      
      loadButton.addActionListener(new ButtonClickListener()); 
      simButton.addActionListener(new ButtonClickListener()); 

      
      controlPanel.add(loadButton);
      controlPanel.add(simButton);       

      mainFrame.setVisible(true);  
   }

   private class ButtonClickListener implements ActionListener{
      public void actionPerformed(ActionEvent e) {
         String command = e.getActionCommand();  
         if( command.equals( "Simulate" ))  {
        	 Simulator sim = new Simulator();
        	 try {
				sim.run();
				sim.saveMap();
        	 }
        	 catch (Exception eTwo){
        		 System.out.println("Simulating did not work");
        	 }
         } 	
         else  {
        	LoadSave a = new LoadSave();
        	ArrayList<int[][]> c = a.load("SavedRun.txt");
     		try {
				Tester b = new Tester(c);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
         }  	
      }		
   }
}
