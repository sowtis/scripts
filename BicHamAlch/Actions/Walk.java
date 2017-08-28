package scripts.BicHamAlch.Actions;

import org.tribot.api.Timing;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSTile;

import scripts.BicHamAlch.Node;
import scripts.BicHamAlch.Utils.Utils;
import scripts.BicHamAlch.Utils.Variables;

public class Walk extends Node {

	@Override
	public void execute() {
		Variables.status = "Walking To Hideout"; // to hideout
		
		Variables.path = PathFinding.generatePath(Player.getPosition(), new RSTile(3205, 3210, 2), false);
		
		if (Walking.walkPath(Variables.path)){
		}
		
	}

	@Override
	public boolean validate() {
		return Utils.isInBank() && Utils.hasFood() && Utils.hasNatures();
	}
}
