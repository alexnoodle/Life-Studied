
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

class SwingControl
		extends 	JFrame
		implements	ActionListener
{
	private final int	ITEM_PLAIN	=	0;	// Item types
	private final int	ITEM_CHECK	=	1;
	private final int	ITEM_RADIO	=	2;
	final JFileChooser fc;
	private	JPanel		topPanel;
	private	JMenuBar	menuBar;
	private	JMenu		menuDisplay;
	private	JMenu		menuSimulate;
	private	JMenuItem	menuDisplayLoad;
	private	JMenuItem	menuSimulateBuild;
	private	JMenuItem	menuSimulateNew;
	public SaveLoadv2 l;	
	
	public SwingControl()
	{
		setTitle( "Game of Life" );
		setSize( 310, 130 );

		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create the menu bar
		menuBar = new JMenuBar();

		// Set this instance as the application's menu bar
		setJMenuBar( menuBar );
		
		// Build the property sub-menu
		menuDisplay = new JMenu( "Display" );
		menuDisplay.setMnemonic( 'D' );
		menuBar.add(menuDisplay);

		// Create property items
		menuDisplayLoad = CreateMenuItem( menuDisplay, ITEM_PLAIN,
								"Load", null, 'S', "Display the results of a previous simulation" );
		
		
		// Create the file menu
		menuSimulate = new JMenu( "Simulate" );
		menuSimulate.setMnemonic( 'S' );
		menuBar.add( menuSimulate);

		// Create the file menu
		// Build a file menu items
		menuSimulateBuild = CreateMenuItem( menuSimulate, ITEM_PLAIN,
								"Builder File", null, 'N', "Simulate an existing initial state" );
		menuSimulateNew = CreateMenuItem( menuSimulate, ITEM_PLAIN, "New",
								new ImageIcon( "open.gif" ), 'O',
								"Create a new initial state to simulate" );
		
		
		menuDisplayLoad.addActionListener(new ButtonClickListener());
		menuSimulateBuild.addActionListener(new ButtonClickListener());
		menuSimulateNew.addActionListener(new ButtonClickListener());
		//Create a file chooser
		fc = new JFileChooser();
		l = new SaveLoadv2();
	}

	public JMenuItem CreateMenuItem( JMenu menu, int iType, String sText,
								ImageIcon image, int acceleratorKey,
								String sToolTip )
	{
		// Create the item
		JMenuItem menuItem;

		switch( iType )
		{
			case ITEM_RADIO:
				menuItem = new JRadioButtonMenuItem();
				break;

			case ITEM_CHECK:
				menuItem = new JCheckBoxMenuItem();
				break;

			default:
				menuItem = new JMenuItem();
				break;
		}

		// Add the item test
		menuItem.setText( sText );

		// Add the optional icon
		if( image != null )
			menuItem.setIcon( image );

		// Add the accelerator key
		if( acceleratorKey > 0 )
			menuItem.setMnemonic( acceleratorKey );

		// Add the optional tool tip text
		if( sToolTip != null )
			menuItem.setToolTipText( sToolTip );

		// Add an action handler to this menu item
		menuItem.addActionListener( this );

		menu.add( menuItem );

		return menuItem;
	}

	public void actionPerformed( ActionEvent event )
	{
		System.out.println( event );
	}

	
	private class ButtonClickListener implements ActionListener{
		
	      public void actionPerformed(ActionEvent e) {
	    	  
	         String command = e.getActionCommand();  
	         outerLoop:
	         if( command.equals( "Load" ))  {
	        	 System.out.println("I swear this will load something eventually");
	        	 int returnVal = fc.showOpenDialog(topPanel);
	        	 if (returnVal == JFileChooser.APPROVE_OPTION) {
	                 File file = fc.getSelectedFile();
	                 StaticElec.tryIt(file);
	                 break outerLoop;
	             } else {
	                 System.out.println("Ok, fine. Don't load anything");
	             }
	        	 
	         }
	         else if(command.equals("Builder File"))  {
	        	 int returnVal = fc.showOpenDialog(topPanel);
	        	 if (returnVal == JFileChooser.APPROVE_OPTION) {
	                 File file = fc.getSelectedFile();
	                 ArrayList<int[][]> toOpen = l.load(file);
	                 //This is where a real application would open the file.
	                 System.out.println("Opening: " + file.getName() + ".");
	                 dispose();
	                 Simulator s = new Simulator(toOpen.get(0));
	                 File d = new File("C:\\Users\\Alex\\Desktop\\Life Studied\\LifeStudied\\SecondSaved.txt");
	                 l.save(d, s.getMapProgress());
	                 System.out.println("we got to here!");
	        	 }
	         }  	
	      }		
	   }
}