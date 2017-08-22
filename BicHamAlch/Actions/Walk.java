package scripts.BicHamAlch.Actions;

import scripts.BicHamAlch.Node;
import scripts.BicHamAlch.Utils.Utils;
import scripts.BicHamAlch.Utils.Variables;

public class Walk extends Node {

	@Override
	public void execute() {
		Variables.status = "Walking"; // to hideout
		
	}

	@Override
	public boolean validate() {
		return Utils.isInBank() && Utils.hasFood() && Utils.hasNatures();
	}
}
