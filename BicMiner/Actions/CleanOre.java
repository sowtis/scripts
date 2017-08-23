package scripts.BicMiner.Actions;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSObject;

import scripts.BicMiner.Node;
import scripts.BicMiner.Utils.Constants;
import scripts.BicMiner.Utils.Utils;
import scripts.BicMiner.Utils.Variables;

public class CleanOre extends Node {
	
	@Override
	public void execute() {
		
		Variables.status = "Cleaning Dirt";
		
		if (fillHopper()){
			
			Variables.path = PathFinding.generatePath(Player.getPosition(), Constants.SACK_AREA.getRandomTile(), false);
			
			while (Variables.lastMessage != "ready"){
				
				// Attempts to walk to sack to wait for ore
				if (!Walking.walkPath(Variables.path, new Condition(){

					@Override
					public boolean active() {
						// Stops if both struts are broken
						return Utils.strutsBroken() > 1;
					}
					
				}, General.random(250,750))){
					
					General.println("Failed to walk, struts broken");
					
					// attempts to fix struts
					if (Utils.hasHammer()){
						fixStrut();
					} else {
						getHammer();
					}
				}
			}
		}
		
	}

	private void fixStrut() {
		RSObject strut = Utils.getStrut();
		
		if (strut.isClickable()){
			if (DynamicClicking.clickRSObject(strut, "Hammer")){
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						return !Player.isMoving() && Player.getAnimation() == -1;
					}
					
				}, General.random(5500, 9950));
			}
		} else {
			Walking.walkTo(strut.getPosition());
		}
	}

	private void getHammer() {
		RSObject crate = Utils.getCrate();
		
		if (crate.isClickable()){
			if (DynamicClicking.clickRSObject(crate, "Search")){
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						return Utils.hasHammer();
					}
					
				}, General.random(3500, 7500));
			}
		} else {
			Walking.walkTo(crate.getPosition());
		}
	}

	private boolean fillHopper() {
		
		if (DynamicClicking.clickRSObject(Utils.getHopper(), "Deposit")){
			Timing.waitCondition(new Condition(){

				@Override
				public boolean active() {
					return !Utils.hasDirt();
				}
				
			}, General.random(3500, 6250));
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean validate() {
		return Utils.isNearHopper() && Utils.hasDirt();
	}
	
}