package scripts.BicMiner.Actions;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSObject;

import scripts.BicMiner.Node;
import scripts.BicMiner.Utils.Constants;
import scripts.BicMiner.Utils.Utils;
import scripts.BicMiner.Utils.Variables;

public class CleanOre extends Node {
	
	@Override
	public void execute() {
		
		Variables.status = "Cleaning Dirt";
		
		RSObject hopper = Utils.getHopper();
		
			
		if (fillHopper(hopper)){	
			
			while (Variables.lastMessage == "dirty"){
				
				if (Utils.strutsBroken() > 1){
					fixStrut();
				}
			}
			
			emptySack();
		}
		
	}

	@Override
	public boolean validate() {
		return Utils.isNearHopper() && Utils.hasDirt();
	}
	
	private boolean fillHopper(RSObject hopper){
		
		if (Utils.hasDirt()){
				
				if (DynamicClicking.clickRSObject(Utils.getHopper(), "Deposit")){
					
					Timing.waitCondition(new Condition(){

						@Override
						public boolean active() {
							General.sleep(100,150);
							return !Utils.hasDirt();
						}
						
					}, General.random(3500, 6000));
					
					Variables.lastMessage = "dirty";
					General.println("Hopper Filled");
					
				}
			
		}
		
		return true;
	}
	
	private void getHammer() {
		RSObject[] crate = Objects.find(15, Constants.CRATE_ID);
		
		if (!crate[0].isOnScreen()){
			if (WebWalking.walkTo(crate[0].getPosition())){
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						General.sleep(100,150);
						return crate[0].isOnScreen();
					}
					
				}, General.random(3200, 4500));
			}
		}
		
		if (DynamicClicking.clickRSObject(crate[0], "Open")){
			Timing.waitCondition(new Condition(){

				@Override
				public boolean active() {
					General.sleep(100,150);
					return Utils.hasHammer();
				}
				
			}, General.random(2300, 4000));
		}
	}
	
	private void fixStrut() {
		
		RSObject strut = Utils.getStrut();
		
		if (!Utils.hasHammer()){
			getHammer();
		}
		
		if (!strut.isOnScreen()){
			if (WebWalking.walkTo(Constants.STRUT_AREA.getRandomTile())){
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						General.sleep(100,150);
						return strut.isOnScreen();
					}
					
				}, General.random(3500, 6500));
			}
		}
		
		if (DynamicClicking.clickRSObject(strut, "Repair")){
			Timing.waitCondition(new Condition(){

				@Override
				public boolean active() {
					General.sleep(100,150);
					return Player.getAnimation() == Constants.HAMMER_ANIM;
				}
				
			}, General.random(3500, 7500));
			
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					General.sleep(100,150);
					return Player.getAnimation() == -1 && !Player.isMoving();
				}
				
			}, General.random(3500, 7500));
		}
		
		if (Utils.getStrut() != null)
			fixStrut();
		
	}
	
	private boolean emptySack(){
		
		RSObject sack = Utils.getSack();
			
		if (!sack.isOnScreen()){
				
			if (WebWalking.walkTo(sack.getPosition())){
					
				if (Utils.hasHammer())
					Inventory.drop(2347);
					
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						General.sleep(100,150);
						return sack.isOnScreen();
					}
						
				}, General.random(3500, 6500));
			}
		}
			
		if (DynamicClicking.clickRSObject(sack, "Empty")){
				
			Timing.waitCondition(new Condition(){

				@Override
					public boolean active() {
					General.sleep(100,150);
					return Utils.emptySpaces() < 9;
				}
					
			}, General.random(3500, 6500));
		}
		
		Variables.lastMessage = "";
		
		return true;
	}
}