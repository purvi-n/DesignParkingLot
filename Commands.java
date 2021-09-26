/*
	Commands class has a:
		Commands constructor:
			initializes a map of commands and their corresponding methods in ParkingLot (commandsTable)
		Method to insert key-value pairs into commandsTable (populateCommandsHashMap)
*/

import java.lang.reflect.Method;
import java.util.Map;
import java.util.HashMap;

public class Commands {
	public Map<String, Method> commandsTable;

	public Commands() {
		commandsTable = new HashMap<String, Method>();
		try {
			populateCommandsHashMap();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	private void populateCommandsHashMap() throws NoSuchMethodException {
		commandsTable.put("Create_parking_lot", ParkingLot.class.getMethod("createParkingLot", String.class));
		commandsTable.put("Park", ParkingLot.class.getMethod("park", String.class, String.class));
		commandsTable.put("Leave", ParkingLot.class.getMethod("leave", String.class));
		commandsTable.put("Slot_numbers_for_driver_of_age", ParkingLot.class.getMethod("getSlotNumbersFromAge", String.class));
		commandsTable.put("Vehicle_registration_number_for_driver_of_age", ParkingLot.class.getMethod("getRegistrationNumbersFromAge", String.class));
		commandsTable.put("Slot_number_for_car_with_number", ParkingLot.class.getMethod("getSlotNumberFromRegNo", String.class));
	}
}