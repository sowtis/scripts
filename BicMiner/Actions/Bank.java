package scripts.BicMiner.Actions;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.WebWalking;

import scripts.BicMiner.Node;
import scripts.BicMiner.Utils.Constants;
import scripts.BicMiner.Utils.Utils;
import scripts.BicMiner.Utils.Variables;

public class Bank extends Node {
	
	@Override
	public void execute() {
		Variables.status = "Banking";
		
		if (walkToBank()){
			if (openBank()){
				General.sleep(350,750);
				deposit();
			}
		}
		
	}

	@Override
	public boolean validate() {
		return Utils.emptySpaces() < 9 && !Utils.hasDirt();
	}
	
	private boolean walkToBank(){
		
		if (!Utils.getChest().isOnScreen()){
			
			if (WebWalking.walkTo(Constants.BANK_AREA.getRandomTile())){
				
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						return Utils.getChest().isOnScreen();
					}
					
				}, General.random(4500, 7500));
			}
		}
		
		return true;
	}
	
	private boolean openBank(){
		
		if (!Banking.isBankScreenOpen()){
			
			if (Banking.openBank()){
				
				Timing.waitCondition(new Condition(){
	
					@Override
					public boolean active() {
						General.sleep(100,150);
						return Banking.isBankScreenOpen();
					}
					
				}, General.random(1500, 3500));
			}
		}
		
		return true;
	}
	
	private boolean deposit(){
		Banking.depositAll();
		
		Timing.waitCondition(new Condition(){

			@Override
			public boolean active() {
				// TODO Auto-generated method stub
				return Utils.emptySpaces() == 28;
			}
				
		}, General.random(1500, 3500));
		
		return true;
	}
	
}