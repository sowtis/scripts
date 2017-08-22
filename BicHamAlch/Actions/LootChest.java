package scripts.BicHamAlch.Actions;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSObject;

import scripts.BicHamAlch.Node;
import scripts.BicHamAlch.Utils.Constants;
import scripts.BicHamAlch.Utils.Utils;
import scripts.BicHamAlch.Utils.Variables;

public class LootChest extends Node {

	@Override
	public void execute() {
		Variables.status = "Loot Chest";
		
		for (int i = 0; i < 4; i++){
			if (Utils.hasKey(i)){
				if (enterRoom(i)){
					if (openChest(i)){
						
					}
				}
			}
		}
		
	}

	@Override
	public boolean validate() {
		return Utils.hasKeys() && !Utils.hasJunk() && !Utils.hasJewelry() && !Utils.isStunned() &&
				(Utils.isInRoom()) ||
				(Utils.isInPickpocketArea() && Utils.emptySpaces() < 4) ||
				(!Utils.hasFood());
	}

	private boolean enterRoom(int i) {
		
		if (Utils.isInRoom()){
			if (DynamicClicking.clickRSObject(Utils.getDoor(), "Open")){
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						return !Utils.isInRoom();
					}
					
				}, General.random(5500,  10000));
			}
		}
		
		RSObject door = Utils.getDoor(i);
		
		if (!door.isClickable()){
			if (WebWalking.walkTo(Constants.DOOR_AREA[i].getRandomTile())){
				Timing.waitCondition(new Condition() {

					@Override
					public boolean active() {
						return door.isClickable();
					}
					
				}, General.random(7500, 15000));
			}
		}
		
		if (DynamicClicking.clickRSObject(door, "Pick-lock")){
			Timing.waitCondition(new Condition(){

				@Override
				public boolean active() {
					return Utils.isInRoom();
				}
				
			}, General.random(3500,7500));
		}
		
		
		return false;
	}

	private boolean openChest(int i) {
		RSObject chest = Utils.getChest(i);
		
		if (DynamicClicking.clickRSObject(chest, "Open")){
			
		}
		
		return false;
	}
}
