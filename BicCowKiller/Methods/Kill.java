package scripts.BicCowKiller.Methods;

import org.tribot.api2007.Inventory;

import scripts.BicCowKiller.Node;
import scripts.BicCowKiller.Utils.Checks;
import scripts.BicCowKiller.Utils.Vars;

public class Kill extends Node {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validate() {
		// In cow pen, and inventory is not full, and current HP greater than ABC eat HP
		return Checks.isInCowPen() && !Inventory.isFull() && Checks.getHpPercent() > Vars.eatHp;
	}

}
