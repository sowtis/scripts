package scripts.BicMiner.Actions;


import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSObject;

import scripts.BicMiner.Node;
import scripts.BicMiner.Utils.AntiBan;
import scripts.BicMiner.Utils.Utils;
import scripts.BicMiner.Utils.Variables;

public class Mine extends Node {
	
	@Override
	public void execute() {
		
		Variables.status = "Mining";
		long mineTimer;
		
		if (Utils.isMining()){
			
			AntiBan.generateTrackers(6500, false);
			
			mineTimer = System.currentTimeMillis() + 1650;
			
			while (mineTimer > System.currentTimeMillis()){
				if (Player.getAnimation() == 6752)
					mineTimer = System.currentTimeMillis() + 1650;
				
				General.sleep(100,150);
				AntiBan.timedActions();
			}
			
			Variables.isMining = false;
			
			General.println("ABC sleep: " + AntiBan.getReactionTime());;
			AntiBan.sleepReactionTime();
		}
		
		if (!Utils.inventoryFull()){
			mineOre();
		}
		
	}

	@Override
	public boolean validate() {
		return Utils.isInMineArea() && !Utils.inventoryFull();
	}
	
	
	private boolean mineOre(){
		
		if (Player.getAnimation() == -1){
			
			RSObject oreVein = Utils.getOreVein();
			
			General.println("Ore: " + Utils.getOreDirection(oreVein));
			General.println("Me: " + Utils.getCamDirection());
			
			handleCamera(oreVein);
			
			if (DynamicClicking.clickRSObject(oreVein, "Mine")){
				
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						General.sleep(100,150);
						return Player.getAnimation() == 6752;
					}
					
				}, General.random(1200, 2300));
			}
		}
		
		return true;
	}
	
	private void handleCamera(RSObject oreVein) {
		General.println("Cam Handler");
		if (Utils.getOreDirection(oreVein) == "north" && Utils.getCamDirection() != "south"){
			Utils.turnCam("s");
			General.sleep(550, 1250);
		}

		if (Utils.getOreDirection(oreVein) == "east" && Utils.getCamDirection() != "west"){
			Utils.turnCam("w");
			General.sleep(550, 1250);
		}
		
		if (Utils.getOreDirection(oreVein) == "south" && Utils.getCamDirection() != "north"){
			Utils.turnCam("n");
			General.sleep(550, 1250);
		}
		
		if (Utils.getOreDirection(oreVein) == "west" && Utils.getCamDirection() != "east"){
			Utils.turnCam("e");
			General.sleep(550, 1250);
		}
		
	}
}