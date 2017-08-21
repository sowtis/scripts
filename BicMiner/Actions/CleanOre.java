package scripts.BicMiner.Actions;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSObject;

import scripts.BicMiner.BicMiner;
import scripts.BicMiner.Node;
import scripts.BicMiner.Utils.Constants;
import scripts.BicMiner.Utils.Utils;
import scripts.BicMiner.Utils.Variables;

public class CleanOre extends Node {
	
	@Override
	public void execute() {
		Variables.status = "Cleaning Dirt";
		
		if (Utils.hasDirt()){
			if (Utils.getHopper().isOnScreen()){
				if (DynamicClicking.clickRSObject(Utils.getHopper(), "Deposit")){
					Timing.waitCondition(new Condition(){

						@Override
						public boolean active() {
							General.sleep(100,150);
							return !Utils.hasDirt();
						}
						
					}, General.random(3500, 6000));
					
					Variables.lastMessage = "dirty";
					
				}
			}
		}
		
		if (Variables.lastMessage == "dirty"){
			if (Utils.strutsBroken() > 1){
				if (!Utils.hasHammer()){
					GetHammer();
				}
				
				if (WebWalking.walkTo(Constants.STRUT_AREA.getRandomTile())){
					
				}
			}
		}
		
		if (Variables.lastMessage == "ready"){
			
		}
		

	}

	private void GetHammer() {
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

	@Override
	public boolean validate() {
		return Constants.HOPPER_AREA.getRandomTile().isOnScreen() && Utils.hasDirt();
	}
}