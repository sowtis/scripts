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
import scripts.BicMiner.Utils.Variables;

public class Mine extends Node {
	
	@Override
	public void execute() {
		
		Variables.status = "Mining";
		
		if (Player.getAnimation() == -1){
			if (DynamicClicking.clickRSObject(Utils.getOreVein(), "Mine")){
				
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						General.sleep(100,150);
						return Player.getAnimation() == Constants.MINING_ANIMATION;
					}
					
				}, General.random(1200, 2300));
				
				Variables.isMining = true;
				AntiBan.generateTrackers(6500, false);
				
				long mineTimer = System.currentTimeMillis() + 2000;
				
				while (mineTimer > System.currentTimeMillis()){
					if (Utils.isMining())
						mineTimer = System.currentTimeMillis() + 1100;
					
					General.sleep(50,75);
				}
				
				Variables.isMining = false;
				
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