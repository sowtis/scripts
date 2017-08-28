package scripts.BicMiner.Utils;

import scripts.BicMiner.Utils.Variables;
import scripts.BicMiner.Utils.Constants;

import org.tribot.api.General;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

public class Utils {
	
	// *********
	// INVENTORY
	// *********
	
	public static boolean inventoryFull(){
		return Inventory.isFull();
	}

	public static int emptySpaces() {
		return 28 - Inventory.getAll().length;
	}
	
	public static boolean hasHammer(){
		return Inventory.find("Hammer").length > 0;
	}
	
	public static boolean hasDirt(){
		if (Inventory.find(Constants.DIRT_ID).length > 0)
			return true;
			
		return false;
	}
	
	// ********
	// LOCATION
	// ********
	
	public static boolean isInMineArea(){
		return Constants.MINE_AREA[0].contains(Player.getPosition());
	}
	
	public static boolean isNearChest(){
		RSObject chest = getChest();
		if (chest == null)
			return false;
		
		return chest.isOnScreen();
	}
	
	public static boolean isNearHopper() {
		RSObject hopper = getHopper();
		if (hopper == null)
			return false;
		
		return hopper.isOnScreen();
	}
	
	// **********
	// GET OBJECT
	// **********

	public static RSObject getChest() {
		RSObject[] chest = Objects.find(15, Constants.BANK_ID);
		
		if (chest.length > 0){
			return chest[0];
		}
		
		return null;
	}
	
	public static RSObject getRock() {
		RSObject[] rockfall = Objects.find(6, Constants.ROCK);
		
		if (rockfall.length > 0){
			for (RSTile tile : Variables.path){
				for (int i = 0; i < rockfall.length; i++){
					if (tile.equals(rockfall[i].getPosition())){
						
						return rockfall[i];
						
					}
				}
			}
		}
		
		return null;
	}
	
	public static RSObject getHopper(){
		RSObject[] hopper = Objects.findNearest(12, Constants.HOPPER_ID);
		
		if (hopper.length > 0){
			return hopper[0];
		}
		
		return null;
	}
	
	public static RSObject getOreVein(){
		RSObject[] oreVein = Objects.findNearest(10, Constants.ORE);
		
		for (int i = 0; i < oreVein.length; i++){
			if (Constants.MINE_AREA[0].contains(oreVein[i].getPosition()))
				return oreVein[i];
		}
		
		return null;
	}

	public static RSObject getStrut(){
		RSObject[] struts = Objects.findNearest(20, Constants.STRUT_ID);
		
		if (struts.length > 0){
			return struts[0];
		}
		
		return null;
	}
	
	public static RSObject getSack(){
		RSObject[] sack = Objects.findNearest(15, Constants.SACK_ID);
		
		if (sack.length > 0)
			return sack[0];
		
		return null;
	}
	
	// *****
	// OTHER 
	// *****
	
	public static void turnCam(String direction){
		switch (direction){
		case "n":
			if (General.random(1,2) == 1){
				Camera.setCameraRotation(General.random(0, 43));
			} else {
				Camera.setCameraRotation(General.random(316, 360));
			}
			break;
		case "e":
			Camera.setCameraRotation(General.random(225, 315));
			break;
		case "s":
			Camera.setCameraRotation(General.random(134, 224));
			break;
		case "w":
			Camera.setCameraRotation(General.random(43, 133));
			break;
		default:
			break;
		}
		
	}
	
	public static String getOreDirection(RSObject ore){

		if (ore.getOrientation() == 1)
			return "west";
		if (ore.getOrientation() == 2)
			return "north";
		if (ore.getOrientation() == 4)
			return "east";
		
		return "south";
	}
	
	public static String getCamDirection(){
		if (Camera.getCameraRotation() < 45 || Camera.getCameraRotation() > 315)
			return "north";
		
		if (Camera.getCameraRotation() > 225)
			return "east";
		
		if (Camera.getCameraRotation() > 135)
			return "south";
		
		return "west";
	}

	public static boolean rockInWay(){
		RSObject[] rockfall = Objects.find(6, Constants.ROCK);
		
		if (rockfall.length > 0){
			for (RSTile tile : Variables.path){
				for (int i = 0; i < rockfall.length; i++){
					if (tile.equals(rockfall[i].getPosition())){
						
						RSTile destination = Variables.path[Variables.path.length-1];
						
						if (rockfall[i].getPosition().distanceToDouble(destination) < Player.getPosition().distanceToDouble(destination)){
							return true;
						}
						
					}
				}
			}
		}
				
		return false;
	}

	public static int strutsBroken(){
		RSObject[] struts = Objects.findNearest(20, Constants.STRUT_ID);
		
		return struts.length;
	}
	
	public static boolean isMining(){
		
		if (Player.getAnimation() == Constants.MINING_ANIMATION){
			Variables.mineTimer = System.currentTimeMillis() + 1650;
			return true;
		}
		
		if (Variables.mineTimer > System.currentTimeMillis())
			return true;
		
		return false;
	}

	

}
