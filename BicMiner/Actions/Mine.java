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
						return Player.getAnimation() == 6752;
					}
					
				}, General.random(1200, 2300));
			}
		}
		
		AntiBan.generateTrackers(6500, false);
		
		if (Player.getAnimation() == 6752){
			
			long mineTimer = System.currentTimeMillis() + 1450;
			
			while (mineTimer > System.currentTimeMillis()){
				if (Player.getAnimation() == 6752)
					mineTimer = System.currentTimeMillis() + 1450;
				
				General.sleep(100,150);
				AntiBan.timedActions();
			}
			
			Variables.isMining = false;
			General.println("ABC sleep: " + AntiBan.getReactionTime());;
			AntiBan.sleepReactionTime();
		}
		

		
		
	}

	@Override
	public boolean validate() {
		return Utils.isInMineArea() && !Utils.inventoryFull();
	}
}