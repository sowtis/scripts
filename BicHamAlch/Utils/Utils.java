package scripts.BicHamAlch.Utils;

import org.tribot.api.General;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSObject;

public class Utils {
	
    public static double hpPercent(){
        double currentHP = Skills.SKILLS.HITPOINTS.getCurrentLevel();
        double totalHP = Skills.SKILLS.HITPOINTS.getActualLevel();
        return currentHP / totalHP * 100;   
    }

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
	
	// Check for keys
	public static boolean hasKeys() {
		for (int i = 0; i < Constants.KEYS.length; i++){
			if (Inventory.find(Constants.KEYS[i]).length > 0)
				return true;
		}
		
		return false;
	}
	
	// Check for specific key
	public static boolean hasKey(int x){
		if (Inventory.find(Constants.KEYS[x]).length > 0)
			return true;
		
		return false;
	}

	// ********
	// LOCATION
	// ********
	
	public static boolean isInRoom(){
		for (int i = 0; i < Constants.ROOM_AREA.length; i++){
			if (Constants.ROOM_AREA[i].contains(Player.getPosition()))
				return true;
		}
		
		return false;
	}
	
	public static boolean isInGameRoom(){
		if (Constants.GAME_AREA.contains(Player.getPosition()))
			return true;
		
		return false;
	}
	
	public static boolean isInPickpocketArea(){
		if (Constants.PICKPOCKET_AREA.contains(Player.getPosition()))
			return true;
		
		return false;
	}
	
	public static boolean isInCrackRoom() {
		if (Constants.ROOM_AREA[0].contains(Player.getPosition()))
			return true;			
		
		return false;
	}
	
	public static boolean isInBank(){
		if (Constants.BANK_AREA.contains(Player.getPosition()))
			return true;
		
		return false;
	}
	public static boolean isInCastle() {
		if (Constants.CASTLE_AREA.contains(Player.getPosition()))
			return true;
		
		return false;
	}
	

	// **********
	// GET OBJECT
	// **********
	
	public static RSObject getDoor(){
		RSObject[] door = Objects.findNearest(7, "Door");
		
		if (door.length > 0)
			return door[0];
		
		return null;
	}
	
	public static RSObject getDoor(int x){
		RSObject[] door = Objects.find(30, Constants.DOORS[x]);
		
		for (int i = 0; i < door.length; i++){
			if (Constants.ROOM_AREA[x].contains(door[i].getPosition()))
				return door[i];
		}

		return null;
	}
	
	public static RSObject getChest(int x){
		RSObject[] chest = Objects.find(10, Constants.CHESTS[x]);
		
		if (chest.length > 0)
			return chest[0];
		
		return null;
	}
	
	public static RSNPC getGuard(){
		RSNPC[] guard = NPCs.findNearest("Guard");
		
		for (int i = 0; i < guard.length; i++){
			if (Constants.PICKPOCKET_AREA.contains(guard[i].getPosition())){
				return guard[i];
			}
		}
		
		return null;
	}
	

	// *****
	// OTHER 
	// *****
	
	public static boolean isStunned(){
		
		if (Variables.lastMessage == "stun"){
			Variables.stunTimer = System.currentTimeMillis() + General.random(2850, 3650);
			Variables.lastMessage = "";
			return true;
		}
		
		if (Variables.stunTimer > System.currentTimeMillis())
			return true;
		
		return false;
	}


	
	
	
}
