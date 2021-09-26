/*
	ParkingLot class has a:
		Car class with members as registration number and the driver's age
		List of avalaible slots (availableSlotList)
		Maps of {slot and car} (slotAndCar), {reg no and slot} (regNoAndSlot) and {age and its list of reg no} (ageAndRegNoList)
		Method to create parking lot with given parking lot size (createParkingLot):
			initializes the list of available slots with slot no = index, with total size of list = parking lot size
		Method to park a car with given reg no and driver's age (park):
			inserts the {slot and car}, {reg no and slot} and {age with list of reg no} key-value pairs into their respective maps
		Method to make a car leave (remove) from the parking lot given its slot no (leave):
			removes the {slot and car}, {reg no and slot} and {age with list of reg no} key-value pairs from their respective maps
		Method to get registration no from given driver's age (getRegistrationNumbersFromAge):
			creates list of reg nos by getting the list of regnos/single regno from the {age and list of reg no} map and display
		Method to get slot no from given driver's age (getSlotNumbersFromAge):
			creates list of reg nos by getting the list of regnos/single regno from the {age and list of reg no} map and
			create list of slot nos by using elements in list of reg nos and getting them from the {reg no and slot} map,
			sort the slot nos and display
		Method to get slot no from given reg no (getSlotNumberFromRegNo):
			gets the reg no from the {reg no and slot} map and display
*/

import java.util.*;

public class ParkingLot {
    int MAX_SIZE = 0;
    private class Car {
        String regNo;
        String age;
        public Car(String regNo, String age) {
            this.regNo = regNo;
            this.age = age;
        }
    }

    ArrayList<Integer> availableSlotList;               // list of available slots

    Map<String, Car> slotAndCar;                        // list of slot and car

    Map<String, String> regNoAndSlot;                   // list of reg no and slot

    Map<String, ArrayList<String>> ageAndRegNoList;     // list of age and its list of reg nos


    public void createParkingLot(String lotCount) {
        try {
            this.MAX_SIZE = Integer.parseInt(lotCount);
        } catch (Exception e) {
            System.out.println("Invalid lot count");
        }
        this.availableSlotList = new ArrayList<Integer>() {};
        for (int i=1; i<= this.MAX_SIZE; i++) {
            availableSlotList.add(i);
        }
        this.slotAndCar = new HashMap<String, Car>();
        this.regNoAndSlot = new HashMap<String, String>();
        this.ageAndRegNoList = new HashMap<String, ArrayList<String>>();
        System.out.println("Created parking lot with " + lotCount + " slots");
    }
    public void park(String regNo, String age) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Parking lot does not exist");
        } else if (this.slotAndCar.size() == this.MAX_SIZE) {
            System.out.println("Parking lot full");
        } else {
            Collections.sort(availableSlotList);
            String slot = availableSlotList.get(0).toString();
            Car car = new Car(regNo, age);
            this.slotAndCar.put(slot, car);
            this.regNoAndSlot.put(regNo, slot);
            if (this.ageAndRegNoList.containsKey(age)) {
                ArrayList<String> regNoList = this.ageAndRegNoList.get(age);
                this.ageAndRegNoList.remove(age);
                regNoList.add(regNo);
                this.ageAndRegNoList.put(age, regNoList);
            } else {
                ArrayList<String> regNoList = new ArrayList<String>();
                regNoList.add(regNo);
                this.ageAndRegNoList.put(age, regNoList);
            }
            System.out.println("Car with vehicle registration number \"" + regNo + "\" has been parked at slot number " + slot);
            availableSlotList.remove(0);
        }
    }
    public void leave(String slotNo) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Parking lot does not exist");
        }
        else if (this.slotAndCar.size() > 0) {
          Car carToLeave = this.slotAndCar.get(slotNo);
          if (carToLeave != null) {
              String carLeavingRegNo=carToLeave.regNo;
              String carLeavingDriverAge=carToLeave.age;
              this.slotAndCar.remove(slotNo);
              this.regNoAndSlot.remove(carToLeave.regNo);
              ArrayList<String> regNoList = this.ageAndRegNoList.get(carToLeave.age);
              if (regNoList.contains(carToLeave.regNo)) {
                  regNoList.remove(carToLeave.regNo);
              }
              this.availableSlotList.add(Integer.parseInt(slotNo));
              System.out.println("Slot number " + slotNo + " vacated, the car with vehicle registration number \"" + carLeavingRegNo + "\" left the space, the driver of the car was of age " + carLeavingDriverAge);
          }
          else {
            System.out.println("Slot number " + slotNo + " is already empty");
          }
        }
        else {
          System.out.println("Parking lot is empty");
        }
    }
    public void getRegistrationNumbersFromAge(String age) {
        if (this.MAX_SIZE == 0) {
          System.out.println("Parking lot does not exist");
        }
        else if (this.ageAndRegNoList.containsKey(age)) {
          ArrayList<String> regNoList = this.ageAndRegNoList.get(age);
          for (int i=0; i < regNoList.size(); i++) {
            if (!(i==regNoList.size() - 1)){
              System.out.print(regNoList.get(i) + ",");
            }
            else {
              System.out.print(regNoList.get(i));
            }
          }
          System.out.println();
        }
        else {
          System.out.println();
      }
    }
    public void getSlotNumbersFromAge(String age) {
        if (this.MAX_SIZE == 0) {
          System.out.println("Parking lot does not exist");
        }
        else if (this.ageAndRegNoList.containsKey(age)) {
          ArrayList<String> regNoList = this.ageAndRegNoList.get(age);
          ArrayList<Integer> slotList = new ArrayList<Integer>();
          for (int i=0; i < regNoList.size(); i++) {
            slotList.add(Integer.valueOf(this.regNoAndSlot.get(regNoList.get(i))));
          }
          Collections.sort(slotList);
            for (int j=0; j < slotList.size(); j++) {
              if (!(j == slotList.size() - 1)) {
                System.out.print(slotList.get(j) + ",");
              }
              else {
                System.out.print(slotList.get(j));
              }
            }
            System.out.println();
        }
        else {
          System.out.println();
        }
    }
    public void getSlotNumberFromRegNo(String regNo) {
      if (this.MAX_SIZE == 0) {
        System.out.println("Parking lot does not exist");
      }
      else if (this.regNoAndSlot.containsKey(regNo)) {
        System.out.println(this.regNoAndSlot.get(regNo));
      }
      else {
        System.out.println();
      }
    }
}