package scripts.BicHamAlch.Actions;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.GameTab.TABS;
import org.tribot.api2007.Objects;
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
		
		if (GameTab.getOpen().equals(TABS.MAGIC)){
			if (GameTab.open(TABS.INVENTORY)){
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						return GameTab.getOpen().equals(TABS.INVENTORY);
					}
					
				}, General.random(1500,3500));
				
				General.sleep(70,300);
			}
		}
		
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
		
		// If not in desired room
		if (!Constants.ROOM_AREA[i].contains(Player.getPosition())){
			
			
			if (Utils.isInRoom()){
				exitRoom();
			}

			RSObject door = Utils.getDoor(i);
			
			if (!door.isClickable()){
				if (WebWalking.walkTo(Constants.DOOR_TILE[i])){
					Timing.waitCondition(new Condition(){

						@Override
						public boolean active() {
							return door.isClickable();
						}
						
					}, General.random(8800,15000));
				}
			}

			if (i == 0){
				// If bronze key, squeeze-through crack
				if (DynamicClicking.clickRSObject(door, "Squeeze-through")){
					Timing.waitCondition(new Condition(){

						@Override
						public boolean active() {
							return Utils.isInRoom();
						}
						
					}, General.random(3500,7500));
					General.sleep(100,750);
				}
			} else {
				// Else pick-lock door
				General.println("Picking lock");
				
				while (!DynamicClicking.clickRSObject(door, "Pick-lock")){
					General.sleep(100,150);
				}
				
				AntiBan.generateTrackers(6500, false);
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						return Utils.isInRoom();
					}
					
				}, General.random(30000, 45999));
				
				General.println("ABC sleep: " + AntiBan.getReactionTime());
				AntiBan.sleepReactionTime();
				
//				if (DynamicClicking.clickRSObject(door, "Pick-lock")){
//					

//				}
			}

		}
		
		return true;
	}
	
	private boolean exitRoom(){
		if (Utils.isInCrackRoom()){
			RSObject[] crack = Objects.findNearest(5, Constants.DOORS[0]);
			
			if (crack.length > 1){
				if (DynamicClicking.clickRSObject(crack[1], "Squeeze-through")){
					Timing.waitCondition(new Condition(){

						@Override
						public boolean active() {
							return !Utils.isInCrackRoom();
						}
						
					}, General.random(3500,7500));
					

					General.sleep(350,1000);
				}
			}
		} else {
			if (DynamicClicking.clickRSObject(Utils.getDoor(), "Open")){
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						return !Utils.isInRoom();
					}
					
				}, General.random(3500,7500));
			}
		}
		
		return true;
	}

	private boolean openChest(int i) {
		
		RSObject chest = Utils.getChest(i);
		
		while (chest == null){
			chest = Utils.getChest(i);
			General.sleep(100, 150);
		}
		
		if (DynamicClicking.clickRSObject(chest, "Open")){
			Timing.waitCondition(new Condition(){

				@Override
				public boolean active() {
					return Utils.getChest(i) == null;
				}
				
			}, General.random(2000, 4000));
		}
		
		General.sleep(100,450);
		
		return false;
	}
}
