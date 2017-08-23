package scripts.BicHamAlch.Actions;

import org.tribot.api.Clicking;
import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.WebWalking;

import scripts.BicHamAlch.Node;
import scripts.BicHamAlch.Utils.AntiBan;
import scripts.BicHamAlch.Utils.Constants;
import scripts.BicHamAlch.Utils.Utils;
import scripts.BicHamAlch.Utils.Variables;

public class Pickpocket extends Node {

	@Override
	public void execute() {
		Variables.status = "Picking Keys";
		
		if (!Utils.isInPickpocketArea()){
			if (goToPickpocketArea()){
				
			}
		}
		
		if (!Utils.isStunned()){
			pickpocket();
			General.sleep(75, 200);
		} else {
			AntiBan.timedActions();
		}
		
	}

	@Override
	public boolean validate() {
		return Utils.hasFood() && Utils.emptySpaces() > 3 && !Utils.hasJewelry() &&
				(!Utils.hasKeys() || Utils.isInPickpocketArea());
	}

	private void pickpocket() {
		DynamicClicking.clickRSNPC(Utils.getGuard(), "Pickpocket");
			
	}
	
	private boolean goToPickpocketArea(){
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
		
		if (!Constants.PICKPOCKET_AREA.getRandomTile().isOnScreen()){
			if (WebWalking.walkTo(Constants.PICKPOCKET_AREA.getRandomTile())){
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						return Constants.PICKPOCKET_AREA.getRandomTile().isOnScreen();
					}
					
				}, General.random(5500, 12500));
			}
		}
		
		if (Clicking.click(Constants.PICKPOCKET_AREA.getRandomTile())){
			Timing.waitCondition(new Condition(){

				@Override
				public boolean active() {
					// TODO Auto-generated method stub
					return Utils.isInPickpocketArea();
				}
				
			}, General.random(450, 1250));
		}
		
		return true;
	}

}
