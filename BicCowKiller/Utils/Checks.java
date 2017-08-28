package scripts.BicCowKiller.Utils;

import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.types.RSItem;

public class Checks {
	
	// other
    public static double getHpPercent(){
        double currentHp = Skills.SKILLS.HITPOINTS.getCurrentLevel();
        double maxHp = Skills.SKILLS.HITPOINTS.getActualLevel();
        return currentHp / maxHp * 100;   
    }

	// areas
	public static boolean isInCowPen(){
		return Constants.cowArea.contains(Player.getPosition());
	}
	public static boolean isAtTanner(){
		return Constants.tanArea.contains(Player.getPosition());
	}
	
	// items
	public static boolean hasFood(){
		return Inventory.find(Vars.foodId).length > 0;
	}
	public static int foodCount(){
		return Inventory.find(Vars.foodId).length;
	}
	public static boolean hasRawHide(){
		return Inventory.find(Constants.rawHideId).length > 0;
	}
	public static boolean hasLeather(){
		return Inventory.find(Vars.leatherType).length > 0;
	}
	public static boolean hasCoins(){
		return Inventory.find("Coins").length > 0;
	}
	public static int coinsCount(){
		RSItem[] coins = Inventory.find("Coins");
		
		if (coins.length > 0)
			return coins[0].getStack();
		
		return 0;
	}

	// interface
	public static boolean tanShopOpen(){
		return false;
	}
}
