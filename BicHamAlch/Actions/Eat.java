package scripts.BicHamAlch.Actions;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;

import scripts.BicHamAlch.BicHamAlch;
import scripts.BicHamAlch.Node;
import scripts.BicHamAlch.Utils.Utils;
import scripts.BicHamAlch.Utils.Variables;

public class Eat extends Node{

	@Override
	public void execute() {
		Variables.status = "Eating";
		
		if (eatFood()){
			BicHamAlch.eatAt = BicHamAlch.abc.generateEatAtHP();
		}
		
	}

	@Override
	public boolean validate() {
		return Utils.hpPercent() < BicHamAlch.eatAt && Utils.hasFood();
	}

	private boolean eatFood() {
		RSItem[] food = Inventory.find(Variables.FOOD_ID);
		
		if (food.length > 0) {
			if (Clicking.click("Eat", food[0])) {
				Timing.waitCondition(new Condition() {
					public boolean active() {
						General.sleep(100, 200);
						return Utils.hpPercent() > BicHamAlch.eatAt;
					}
				}, General.random(3500, 4000));
			}
		}
		
		return true;
	}

}
