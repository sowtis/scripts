package scripts.BicCowKiller.Methods;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.types.RSNPC;

import scripts.BicCowKiller.Node;
import scripts.BicCowKiller.Utils.Checks;
import scripts.BicCowKiller.Utils.Constants;
import scripts.BicCowKiller.Utils.Vars;
import scripts.webwalker_logic.WebWalker;

public class Tan extends Node {

	@Override
	public void execute() {
		Vars.status = "Tanning Hides";
		
		if (!Checks.isAtTanner()){
			WebWalker.walkTo(Constants.tanArea.getRandomTile());
		}
		
		if (Checks.isAtTanner()){
			RSNPC[] tanner = NPCs.findNearest(Constants.tannerId);
			
			if (tanner.length > 0){
				if (DynamicClicking.clickRSNPC(tanner[0], "Buy")){
					Timing.waitCondition(new Condition(){

						@Override
						public boolean active() {
							General.sleep(100,300);
							return Checks.tanShopOpen();
						}
						
					}, General.random(3200, 7700));
				}
			}
			
			if (Checks.tanShopOpen()){
				// tan hides
			}
		}
	}

	@Override
	public boolean validate() {
		return Inventory.isFull() && Vars.tanHide && Checks.hasRawHide();
	}

}