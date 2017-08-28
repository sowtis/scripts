package scripts.BicHamAlch.Actions;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSObject;

import scripts.BicHamAlch.Node;
import scripts.BicHamAlch.Utils.AntiBan;
import scripts.BicHamAlch.Utils.Constants;
import scripts.BicHamAlch.Utils.Utils;
import scripts.BicHamAlch.Utils.Variables;

public class LootChest extends Node {

	@Override
	public void execute() {
		Variables.status = "Loot Chest";
		
		int key = -1;
		int counter = 0;
				
		while (key == -1)
		{
			if (Utils.hasKey(counter))
				key = counter;
				
			counter++;
		}
		
		if (enterRoom(key)){
			if (openChest(key)){
				General.sleep(200,500);
						
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
		
		if (!Constants.ROOM_AREA[i].contains(Player.getPosition())){
			
			if (Utils.isInCrackRoom()){
				RSObject[] crack = Objects.findNearest(5, Constants.DOORS[0]);
				
				if (DynamicClicking.clickRSObject(crack[0], "Squeeze-through")){
					Timing.waitCondition(new Condition(){

						@Override
						public boolean active() {
							return !Utils.isInRoom();
						}
						
					}, General.random(3500, 5500));
					
					return true;
				}
			}
			
			
			General.println("Not in desired room");
			
			RSObject door = Utils.getDoor(i);
			
			if (Constants.DOOR_TILE[i].distanceTo(Player.getPosition()) > 5){
				if (WebWalking.walkTo(Constants.DOOR_TILE[i])){
					Timing.waitCondition(new Condition() {

						@Override
						public boolean active() {
							return door.isOnScreen();
						}
						
					}, General.random(7500, 15000));
				}
			}

			if (i == 0){
				if (DynamicClicking.clickRSObject(door, "Squeeze-through")){
					Timing.waitCondition(new Condition(){

						@Override
						public boolean active() {
							return Utils.isInRoom();
						}
						
					}, General.random(3500,7500));
				}
			} else {
				if (DynamicClicking.clickRSObject(door, "Pick-lock")){
					
					AntiBan.generateTrackers(7500, false);
					Timing.waitCondition(new Condition(){

						@Override
						public boolean active() {
							return Utils.isInRoom();
						}
						
					}, General.random(30000, 45999));
					
					General.println("ABC sleep: " + AntiBan.getReactionTime());
					AntiBan.sleepReactionTime();
				}
			}

		}
		
		return true;
	}

	private boolean openChest(int i) {
		
		RSObject chest = Utils.getChest(i);
		
		while (chest == null){
			chest = Utils.getChest(i);
		}
		
		if (DynamicClicking.clickRSObject(chest, "Open")){
			Timing.waitCondition(new Condition(){

				@Override
				public boolean active() {
					return Utils.getChest(i) == null;
				}
				
			}, General.random(2000, 4000));
		}
		
		
		return false;
	}
}
