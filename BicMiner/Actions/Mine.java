package scripts.BicMiner.Actions;


import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Player;

import scripts.BicMiner.BicMiner;
import scripts.BicMiner.Node;
import scripts.BicMiner.Utils.AntiBan;
import scripts.BicMiner.Utils.Constants;
import scripts.BicMiner.Utils.Utils;

public class Mine extends Node {
	
	@Override
	public void execute() {
		BicMiner.status = "Mining";
		
		if (Player.getAnimation() == -1){
			if (DynamicClicking.clickRSObject(Utils.getOreVein(), "Mine")){
				
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						General.sleep(100,150);
						return Player.getAnimation() == Constants.MINING_ANIMATION;
					}
					
				}, General.random(1200, 2300));
				

				AntiBan.generateTrackers(10000, false);
				
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						General.sleep(100,150);
						return Player.getAnimation() == -1;
					}
					
				}, General.randomLong(10000, 15000));
				
				AntiBan.getReactionTime();
				AntiBan.sleepReactionTime();
			}
		}
	}

	@Override
	public boolean validate() {
		return Utils.isInMineArea() && !Utils.inventoryFull();
	}
}