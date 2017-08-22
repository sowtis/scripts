package scripts.BicMiner.Actions;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSObject;

import scripts.BicMiner.Node;
import scripts.BicMiner.Utils.Constants;
import scripts.BicMiner.Utils.Utils;
import scripts.BicMiner.Utils.Variables;

public class Navigate extends Node {
	
	@Override
	public void execute() {
		
		Variables.status = "Walking";
		
		if (Utils.isInMineArea()){
			if (walkRock()){
				if (breakRock()){
					if (walkMineArea()){
						
					}
				}
			}
		}
			
		if (Utils.isNearChest()){
			if (walkRock()){
				if (breakRock()){
					if (walkHopperArea()){
						
					}
				}
			}
		}
					
	}

	@Override
	public boolean validate() {
		return (Utils.isInMineArea() && Utils.inventoryFull() && Utils.hasDirt()) || 
			   (Utils.isNearChest() && Utils.emptySpaces() == 28);
	}
	
	private boolean walkRock(){
		if (WebWalking.walkTo(Constants.ROCK_AREA.getRandomTile())){
			
			Timing.waitCondition(new Condition(){

				@Override
				public boolean active() {
					General.sleep(100, 150);
					return Constants.ROCK_AREA.getRandomTile().isOnScreen();
				}
				
			}, General.random(5000,  7500));
			
		}
		return true;
	}
	
	private boolean breakRock(){
		if (Utils.rockInWay()){
			RSObject rockfall = Utils.getRock();
			
			if (DynamicClicking.clickRSObject(rockfall, "Mine")){
				
				
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						General.sleep(100,150);
						return !Utils.rockInWay();
					}
					
				}, General.random(3500, 5000));
			}
		}
		
		return true;
	}

	private boolean walkMineArea(){
		if (WebWalking.walkTo(Constants.MINE_AREA[0].getRandomTile())){
			Timing.waitCondition(new Condition(){

				@Override
				public boolean active() {
					General.sleep(100,150);
					return Utils.isInMineArea();
				}
				
			}, General.randomLong(5500, 7000));
		}
		
		return true;
	}
	
	private boolean walkHopperArea(){
		if (WebWalking.walkTo(Constants.HOPPER_AREA.getRandomTile())){
			Timing.waitCondition(new Condition(){

				@Override
				public boolean active() {
					General.sleep(100,150);
					return Constants.HOPPER_AREA.getRandomTile().isOnScreen();
				}
				
			}, General.random(5500, 7000));
		}
		
		return true;
	}
}