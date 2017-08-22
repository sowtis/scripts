package scripts.BicHamAlch.Utils;

import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;

public class Utils {

	// *********
	// INVENTORY
	// *********
	
	public static boolean inventoryFull() {
		return Inventory.isFull();
	}

	public static int emptySpaces() {
		return 28 - Inventory.getAll().length;
	}

	public static boolean hasFood() {
		if (Inventory.find(Variables.FOOD_ID).length > 0)
			return true;
		
		return false;
	}
	
	public static boolean hasNatures() {
		if (Inventory.find(Constants.NATURE).length > 0)
			return true;
		
		return false;
	}
	
	public static boolean hasJewelry() {
		for (int i = 0; i < Constants.JEWELRY.length; i++){
			if (Inventory.find(Constants.JEWELRY[i]).length > 0)
				return true;
		}
		
		return false;
	}
	
	public static boolean hasJunk() {
		for (int i = 0; i < Constants.JUNK.length; i++){
			if (Inventory.find(Constants.JUNK[i]).length > 0)
				return true;
		}
		
		return false;
	}
	
	public static boolean hasKeys() {
		for (int i = 0; i < Constants.KEYS.length; i++){
			if (Inventory.find(Constants.KEYS[i]).length > 0)
				return true;
		}
		
		return false;
	}

	// ********
	// LOCATION
	// ********
	
	public static boolean isInRoom(){
		for (int i = 0; i < Constants.ROOMS.length; i++){
			if (Constants.ROOMS[i].contains(Player.getPosition()))
				return true;
		}
		
		return false;
	}
	
	public static boolean isInPickpocketArea(){
		if (Constants.PICKPOCKET_AREA.contains(Player.getPosition()))
			return true;
		
		return false;
	}
	
	

	// **********
	// GET OBJECT
	// **********
	
	

	// *****
	// OTHER 
	// *****
	
	
	
}
