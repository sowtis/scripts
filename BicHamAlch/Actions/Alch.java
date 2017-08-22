package scripts.BicHamAlch.Actions;

import scripts.BicHamAlch.Node;
import scripts.BicHamAlch.Utils.Utils;

public class Alch extends Node {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validate() {
		return Utils.hasJewelry() && Utils.hasNatures();
	}
}
