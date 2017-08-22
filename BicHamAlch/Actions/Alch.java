package scripts.BicHamAlch.Actions;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
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
		
		if (openMagic()){
			if (selectSpell()){
				if (alchItem()){
					
				}
			}
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
		if (Magic.selectSpell("High alchemy")){
			Timing.waitCondition(new Condition(){

				@Override
				public boolean active() {
					return Magic.getSelectedSpellName().equals("High alchemy");
				}
				
			}, General.random(350, 750));
		}
		return true;
	}

	private boolean alchItem() {
		for (RSItem item : Inventory.getAll()){
			for (int i = 0; i < Constants.JEWELRY.length; i++){
				if (item.name == Constants.JEWELRY[i]){
					if (Clicking.click(item)){
						Timing.waitCondition(new Condition(){

							@Override
							public boolean active() {
								return Player.getAnimation() == 1234;
							}
							
						}, General.random(350, 750));
					}
				}
			}
				
		}
		
		return true;
	}
}
