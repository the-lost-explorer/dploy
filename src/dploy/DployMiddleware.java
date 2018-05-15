package dploy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class DployMiddleware {

	private String OS;

	// Check the operating system that the user is deploying dploy on.
	CheckOS Objos = new CheckOS();

	// Scanner
	Scanner scan = new Scanner(System.in);

	// GUI
	DployGUI gui = new DployGUI();

	public DployMiddleware(boolean flag) {

		// Check if dploy is being run for the first time.
		if (!flag) {
			// If flag.txt didn't exist then create it i.e. dploy is being run for the first
			// time.
			try {
				System.out.println("Entered try");
				PrintWriter write = new PrintWriter("flag.txt", "UTF-8");
				write.printf("You seem to be very curious to have found this file.\n "
						+ "Please don't delete this file for your own good.");
				write.close();

			} catch (UnsupportedEncodingException e1) {

				e1.printStackTrace();
			} catch (FileNotFoundException e1) {

				e1.printStackTrace();
			}
		}

		// Deployment Environment
		setOS(Objos.getOS());
		if (OS.trim() == "windows") {
			gui.getDeployButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					DeployOnWindows(gui.getProjectHome(), gui.getProjectName());

				}

			});

		}

	}

	// Get OS name
	public String getOS() {
		return OS;
	}

	// Set OS name
	public void setOS(String os) {
		OS = os;
	}

	// Run the batch file
	private void DeployOnWindows(String PROJECT_HOME, String PROJECT_NAME) {
		System.out.println(OS + " system detected.");

		// Make a temporary file for project details
		File tmp2 = new File("src/tmp2.bat");

		try {
			if (!tmp2.exists()) {
				System.out.println("We had to make a new file.");
				tmp2.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(tmp2, true);

			BufferedWriter btmp2 = new BufferedWriter(fileWriter);
			btmp2.write("set PROJECT_HOME=" + PROJECT_HOME + "\n");
			btmp2.write("set PROJECT_NAME=" + PROJECT_NAME);
			btmp2.close();

			System.out.println("Done");
		} catch (IOException e) {
			System.out.println("COULD NOT MAKE CHANGES!!");
		}

		// Merge the files

		File[] files = new File[2];
		files[0] = new File("src/tmp2.bat");
		files[1] = new File("src/tmp1.bat");

		File mergedFile = new File("src/dploy.bat");

		mergeFiles(files, mergedFile);

		// Run the batch file
		System.out.println("Running batch file...");
		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dploy.bat");
		File dir = new File("src");
		pb.directory(dir);

		// Try to run the batch file
		try {
			gui.loadingAnimation();
			pb.start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Couldn't find the batch file!");
		}
	}

	// Function for merging files
	public static void mergeFiles(File[] files, File mergedFile) {

		FileWriter fstream = null;
		BufferedWriter out = null;
		try {
			fstream = new FileWriter(mergedFile, true);
			out = new BufferedWriter(fstream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		for (File f : files) {
			System.out.println("merging: " + f.getName());
			FileInputStream fis;
			try {
				fis = new FileInputStream(f);
				BufferedReader in = new BufferedReader(new InputStreamReader(fis));

				String aLine;
				while ((aLine = in.readLine()) != null) {
					out.write(aLine);
					out.newLine();
				}

				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
