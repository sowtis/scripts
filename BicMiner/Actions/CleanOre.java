package scripts.BicMiner.Actions;

import org.tribot.api2007.Player;

import scripts.BicMiner.Node;
import scripts.BicMiner.Utils.Constants;
import scripts.BicMiner.Utils.Utils;
import scripts.BicMiner.Utils.Variables;

public class CleanOre extends Node {
	
	@Override
	public void execute() {
		// Hey there
	}

	@Override
	public boolean validate() {
		return Constants.HOPPER_AREA.contains(Player.getPosition());
	}
}