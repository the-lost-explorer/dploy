package dploy;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class DployGUI {
	
	
	DployGUI(){
		prepareGUI();
		showFlowLayout();
	}
	
	///////// GUI Below///////////

	private Frame mainFrame;
	private Label headerLabel;
	private Label statusLabel;
	private Panel controlPanel;
	private Label msglabel;

	// Project related variables
	//private String CATALINA_HOME;
	
	//Components
	private TextField PROJECT_HOME = new TextField(25);
	private TextField PROJECT_NAME = new TextField(25);
	private Button deploy = new Button("DEPLOY");
	
	
	//Getter for the UI components
	public String getProjectHome(){
		return PROJECT_HOME.getText();
	};
	
	public String getProjectName(){
		return PROJECT_NAME.getText();
	};
	
	public Button getDeployButton(){
		return deploy;
	};
	
	
	//////////////////////// Prepare the Frame//////////////////////
	private void prepareGUI() {
		mainFrame = new Frame("DPLOY");
		mainFrame.setSize(400, 400);
		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				
				//Delete temperory files
				File tmp2 = new File("src/tmp2.bat");
				tmp2.delete();
				
				File dploy = new File("src/dploy.bat");
				dploy.delete();
				
				System.exit(0);
			}
		});

		headerLabel = new Label();
		headerLabel.setAlignment(Label.CENTER);
		statusLabel = new Label();
		statusLabel.setAlignment(Label.CENTER);
		statusLabel.setSize(350, 100);

		msglabel = new Label();
		msglabel.setAlignment(Label.CENTER);
		msglabel.setText("Dploy");

		controlPanel = new Panel();
		controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
	}

	///////////////////////// Display GUI/////////////////////////
	private void showFlowLayout() {
		headerLabel.setText("Welcome to dploy!");
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT);

		// Project Directory
		Panel panel1 = new Panel();
		panel1.setLayout(layout);
		panel1.add(new Label("Enter Project Directory:"));
		panel1.add(PROJECT_HOME);

		// Project Name
		Panel panel2 = new Panel();
		panel2.setLayout(layout);
		panel2.add(new Label("Enter Project Name:     "));
		panel2.add(PROJECT_NAME);

		// Submit button
		Panel panel3 = new Panel();
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel3.add(deploy);

		controlPanel.add(panel1);
		controlPanel.add(panel2);
		controlPanel.add(panel3);
		mainFrame.setVisible(true);
	}
	
	//Loading animation
	public void loadingAnimation() {
		headerLabel.setText("Deploying. It may take around 30 seconds.");
	}
	
	//Done deploying
	public void deployedAnimation() {
		headerLabel.setText("Application has been deployed!");
	}
	
}
