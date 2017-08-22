package scripts.BicHamAlch.Actions;

import scripts.BicHamAlch.Node;
import scripts.BicHamAlch.Utils.Utils;

public class Bank extends Node {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validate() {
		return !Utils.hasFood();
	}
}
