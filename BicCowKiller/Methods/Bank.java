package scripts.BicCowKiller.Methods;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;

import scripts.BicCowKiller.Node;
import scripts.BicCowKiller.Utils.Checks;
import scripts.BicCowKiller.Utils.Constants;
import scripts.BicCowKiller.Utils.Vars;
import scripts.webwalker_logic.WebWalker;

public class Bank extends Node {

	@Override
	public void execute() {
		
		// If not in bank, then walk to bank
		if (!Banking.isInBank()){
			WebWalker.walkToBank();
		}
		
		// If bank screen not open, then open bank screen
		if (!Banking.isBankScreenOpen()){
			if (Banking.isBankScreenOpen()){
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						General.sleep(75,300);
						return Banking.isBankScreenOpen();
					}
					
				}, General.random(4500, 9500));
			}
		}
		
		// If bank screen open, then bank!
		if (Banking.isBankScreenOpen()){
			
			// If we have leather, deposit it
			if (Checks.hasLeather()){
				if (Banking.deposit(0, Vars.leatherType)){
					Timing.waitCondition(new Condition(){

						@Override
						public boolean active() {
							General.sleep(75,300);
							return !Checks.hasLeather();
						}
						
					}, General.random(1750,4500));
				}
			}
			
			// If we have raw hide, deposit it
			if (Checks.hasRawHide()){
				if (Banking.deposit(0, Constants.rawHideId)){
					Timing.waitCondition(new Condition(){

						@Override
						public boolean active() {
							General.sleep(75,300);
							return !Checks.hasRawHide();
						}
						
					}, General.random(1750,4500));
				}
			}
			
			// If we need food, withdraw food
			if (Checks.foodCount() < Vars.foodAmount){
				if (Banking.find(Vars.foodId).length > 0){
					if (Banking.withdraw(Vars.foodAmount - Checks.foodCount(), Vars.foodId)){
						Timing.waitCondition(new Condition(){

							@Override
							public boolean active() {
								General.sleep(75,300);
								return Checks.foodCount() == Vars.foodAmount;
							}
							
						}, General.random(1750,4500));
					}
				}
			}
			
			// If we need coins, withdraw coins
			if (Checks.coinsCount() < 1000){
				if (Banking.find("Coins").length > 0){
					if (Banking.withdraw(General.random(1000, 3500), "Coins")){
						Timing.waitCondition(new Condition(){

							@Override
							public boolean active() {
								General.sleep(75,300);
								return Checks.coinsCount() > 1000;
							}
							
						}, General.random(1750,4500));
					}
				}
			}
			
		}
	}

	@Override
	public boolean validate() {
		return Inventory.isFull() && (!Vars.tanHide || Checks.hasLeather());
	}

}
