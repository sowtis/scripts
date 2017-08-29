package scripts.BicHamAlch.Actions;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Game;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Magic;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.GameTab.TABS;
import org.tribot.api2007.Inventory;

import scripts.BicHamAlch.Node;
import scripts.BicHamAlch.Utils.Constants;
import scripts.BicHamAlch.Utils.Utils;
import scripts.BicHamAlch.Utils.Variables;

public class Alch extends Node {

	@Override
	public void execute() {
		Variables.status = "Alching";
		
		if (true == false){
			if (openMagic()){
				General.sleep(125, 450);
				for (int i = 0; i < Constants.JEWELRY.length; i++) {
					if (Inventory.find(Constants.JEWELRY[i]).length > 0){
						
						if (selectSpell()){
							General.sleep(125, 375);
							if (alchItem(i)){
								
							}
						}
					}
				}
			}
		}
		
		if (Magic.isSpellSelected() && GameTab.getOpen().equals(TABS.MAGIC)) {
			Mouse.click(1);
			return;
		}
		if (Magic.isSpellSelected()) {
			General.sleep((long) General.randomSD(37.923, 22.39948));
			if (Clicking.click(Inventory.find(Constants.JEWELRY))) {
				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						return GameTab.getOpen().equals(TABS.MAGIC);
					}
				}, (long) General.randomSD(6402.2034, 24039.9234, 9559.9123));
			}
			return;
		}
		if (GameTab.getOpen().equals(TABS.MAGIC)) {
			General.sleep((long) General.randomSD(29.923, 12.39948));
			if (Magic.selectSpell("High Level Alchemy")) {
				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						return GameTab.getOpen().equals(TABS.INVENTORY);
					}
				}, (long) General.randomSD(6402.2034, 24039.9234, 9559.9123));
			}
			return;
		}
		if (Game.getItemSelectionState() == 1) {
			General.sleep((long) General.randomSD(3.923, 2.39948));
			Mouse.click(1);
			return;
		}
		
		General.sleep((long) General.randomSD(3.923, 2.39948));
		
		if (GameTab.open(TABS.MAGIC)) {
			Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					return GameTab.getOpen().equals(TABS.MAGIC);
				}
			}, (long) General.randomSD(6402.2034, 24039.9234, 9559.9123));
		}
		
		
		
	}

	@Override
	public boolean validate() {
		return Utils.hasJewelry() && Utils.hasNatures();
	}

	private boolean openMagic() {
		if (!GameTab.getOpen().equals(TABS.MAGIC)){
			if (GameTab.open(TABS.MAGIC)){
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						return GameTab.getOpen().equals(TABS.MAGIC);
					}
					
				}, General.random(350,750));
			}
		}
			
		return true;
	}
	
	private boolean selectSpell() {
		if (Magic.selectSpell("High Level Alchemy")){
			Timing.waitCondition(new Condition(){

				@Override
				public boolean active() {
					return Magic.getSelectedSpellName().equals("High Level Alchemy");
				}
				
			}, General.random(350, 750));
		}
		return true;
	}

	private boolean alchItem(int x) {
		if (Clicking.click(Inventory.find(Constants.JEWELRY[x]))){
			Timing.waitCondition(new Condition(){

				@Override
				public boolean active() {
					return GameTab.getOpen().equals(TABS.MAGIC);
				}
				
			}, General.random(3000,5000));
		}
		
		return true;
	}
}
