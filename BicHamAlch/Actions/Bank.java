package scripts.BicHamAlch.Actions;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.GameTab.TABS;
import org.tribot.api2007.Magic;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;

import scripts.BicHamAlch.Node;
import scripts.BicHamAlch.Utils.Utils;
import scripts.BicHamAlch.Utils.Variables;

public class Bank extends Node {

	@Override
	public void execute() {
		Variables.status = "Banking";
		
		if (goToBank()){
			if (openBank()){
				if (bank()){
					
				}
			}
		}
		

	}

	@Override
	public boolean validate() {
		return (!Utils.hasFood() || !Utils.hasNatures()) && !Utils.hasJewelry() && !Utils.hasJunk() ;
	}

	private boolean goToBank() {
		
		if (Utils.isInGameRoom()){
			if (!GameTab.getOpen().equals(TABS.MAGIC)){
				if (GameTab.open(TABS.MAGIC)){
					Timing.waitCondition(new Condition(){

						@Override
						public boolean active() {
							return GameTab.getOpen().equals(TABS.MAGIC);
						}
						
					}, General.random(350, 750));
				}
			}

			if (Magic.selectSpell("Home teleport")){
				
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						return Player.getAnimation() == 1234;
					}
					
				}, General.random(3500, 7500));
				
				if (!GameTab.getOpen().equals(TABS.INVENTORY)){
					if (GameTab.open(TABS.INVENTORY)){
						
					}
				}
				
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						return Utils.isInCastle();
					}
					
				}, General.random(7500, 15000));
			}
			
		}
		
		if (Utils.isInCastle()){
			if (WebWalking.walkToBank()){
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						return Utils.isInBank();
					}
					
				}, General.random(15000, 25000));
			}
		}
		
		return true;
	}
	
	private boolean openBank() {
		if (Banking.openBank()){
			Timing.waitCondition(new Condition(){

				@Override
				public boolean active() {
					return Banking.isBankScreenOpen();
				}
				
			}, General.random(2500, 5500));
		}
		return false;
	}
	
	private boolean bank() {
		// TODO Auto-generated method stub
		return false;
	}
}
