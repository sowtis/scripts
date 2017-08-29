package scripts.BicCowKiller.Methods;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;

import scripts.BicCowKiller.BicCowKiller;
import scripts.BicCowKiller.Node;
import scripts.BicCowKiller.Utils.Checks;
import scripts.BicCowKiller.Utils.Vars;

public class Eat extends Node {

	@Override
	public void execute() {
		Vars.status = "Eating";
		
		double currentHp = Checks.getHpPercent();
		RSItem[] food = Inventory.find(Vars.foodId);
		
		if (Clicking.click("Eat", food[0])) {
			Timing.waitCondition(new Condition() {
				public boolean active() {
					General.sleep(100, 300);
					return Checks.getHpPercent() > currentHp;
				}
			}, General.random(1750, 4500));
				
			Vars.eatHp = BicCowKiller.abc.generateEatAtHP();
		}
		
		
	}

	@Override
	public boolean validate() {
		// We have food, and current HP less than ABC eat hp
		return Checks.hasFood() && Checks.getHpPercent() < Vars.eatHp;
	}

}
