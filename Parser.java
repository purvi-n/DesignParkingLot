/*
	Parser class has a:
		Parser constructor:
			initializes objects of Commands and ParkingLot class
		Method to take file name as input (parseFileInput)
		Method to invoke appropriate method from ParkingLot depending upon length of input and command (parseTextInput)
*/

import java.io.*;
import java.lang.reflect.*;

public class Parser {
	Commands commands;
	static ParkingLot parkingLot;
	public Parser() {
		commands = new Commands();
		parkingLot = new ParkingLot();
	}
	public void parseTextInput(String inputString) {
		String[] inputs = inputString.split(" ");
		switch (inputs.length) {
			case 2:
				try {
					Method method = commands.commandsTable.get(inputs[0]);
					if (method != null) {
						method.invoke(parkingLot, inputs[1]);
					}
					else {
						System.out.println("Invalid input");
					}
				}
				catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				break;

			case 4:
				try {
					Method method = commands.commandsTable.get(inputs[0]);
					if (method != null) {
						method.invoke(parkingLot, inputs[1], inputs[3]);
					}
					else {
						System.out.println("Invalid input");
					}
				}
				catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Invalid input");
		}
	}
	public void parseFileInput(String filePath) {
		File inputFile = new File(filePath);
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String line;
			try {
				while ((line = br.readLine()) != null) {
					parseTextInput(line);
				}
			}
			catch (IOException ex) {
				System.out.println("Error in reading input file");
				ex.printStackTrace();
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
	}
}