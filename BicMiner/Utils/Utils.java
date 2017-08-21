package scripts.BicMiner.Utils;

import scripts.BicMiner.Utils.Constants;

import org.tribot.api.General;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSObject;

public class Utils {
	
	public static boolean hasDirt(){
		if (Inventory.find(Constants.DIRT_ID).length > 0)
			return true;
			
		return false;
	}
	
	public static boolean inventoryFull(){
		return Inventory.isFull();
	}

	public int emptySpaces() {
		return 28 - Inventory.getAll().length;
	}
	
	public static boolean isInMineArea(){
		return Constants.MINE_AREA[0].contains(Player.getPosition());
	}
	
	public static boolean rockInWay(){
		RSObject[] rockfall = Objects.find(7, Constants.ROCK);
		
		for (int i =0; i < rockfall.length; i++){
			if (Constants.ROCK_AREA.contains(rockfall[i].getPosition()))
				return true;
		}
		
		return false;
	}

	public static RSObject getRock() {
		RSObject[] rockfall = Objects.find(7, Constants.ROCK);
		
		for (int i =0; i < rockfall.length; i++){
			if (Constants.ROCK_AREA.contains(rockfall[i].getPosition()))
				return rockfall[i];
		}
		
		return null;
	}
	
	public static RSObject getOreVein(){
		RSObject[] oreVein = Objects.findNearest(10, Constants.ORE);
		
		return AntiBan.selectNextTarget(oreVein);
	}
	
	public static boolean isMining(){
		
		if (Player.getAnimation() != 6752){
			long mineTimer = System.currentTimeMillis();
			while(System.currentTimeMillis() - mineTimer < 1300){
				General.sleep(50,75);
			}
			return false;
		}
		
		return true;
	}
}
