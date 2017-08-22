package scripts.BicHamAlch.Actions;

import scripts.BicHamAlch.Node;
import scripts.BicHamAlch.Utils.Utils;
import scripts.BicMiner.Utils.Variables;

public class DropJunk extends Node {

	@Override
	public void execute() {
		Variables.status = "";
		
		
		
	}

	@Override
	public boolean validate() {
		return Utils.emptySpaces() < 4 && Utils.hasJunk();
	}
}
