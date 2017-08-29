package scripts.BicMiner.Actions;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import scripts.BicMiner.Node;
import scripts.BicMiner.Utils.Constants;
import scripts.BicMiner.Utils.Utils;
import scripts.BicMiner.Utils.Variables;

public class Navigate extends Node {

	@Override
	public void execute() {

		Variables.status = "Walking";
		
<<<<<<< HEAD
		
		Variables.path = PathFinding.generatePath(Player.getPosition(), Constants.HOPPER_AREA.getRandomTile(), false);
		
		if (Utils.isInMineArea())
			Variables.path = PathFinding.generatePath(Player.getPosition(), Constants.HOPPER_AREA.getRandomTile(), false);
		
		for (RSTile tile : Variables.path){
			General.println(tile);
		}
		
		if (Variables.path != null)
			Walking.walkPath(Variables.path);
		
//		if (Walking.walkPath(Variables.path, new Condition(){
//
//			@Override
//			public boolean active() {
//				return Utils.rockInWay();
//			}
//			
//		}, General.random(250,750)));
		
		
		
//		
//		if (Utils.isInMineArea()){
//			if (Utils.getCamDirection() != "north"){
//				Utils.turnCam("n");
//			}
//			if (walkRock()){
//				if (breakRock()){
//					if (walkHopperArea()){
//						
=======
		if (Utils.isInMineArea())
			Variables.path = PathFinding.generatePath(Player.getPosition(), Constants.HOPPER_AREA.getRandomTile(), false);
		
		if (Utils.isNearChest())
			Variables.path = PathFinding.generatePath(Player.getPosition(), Constants.MINE_AREA[0].getRandomTile(), false);
			
		
		if (!Walking.walkPath(Variables.path, new Condition(){

			@Override
			public boolean active() {
				return Utils.rockInWay();
			}
			
		}, General.random(250, 750))){
			
			if (breakRock()){
				
			}
			
		}

//		if (Utils.isInMineArea()) {
//			if (Utils.getCamDirection() != "north") {
//				Utils.turnCam("n");
//			}
//			if (walkRock()) {
//				if (breakRock()) {
//					if (walkHopperArea()) {
//
>>>>>>> 9f4342b43e52ce520c1bd05fc59bbc1167e2b0aa
//					}
//				}
//			}
//		}
<<<<<<< HEAD
//			
//		if (Utils.isNearChest()){
//			if (walkRock()){
//				if (breakRock()){
//					if (walkMineArea()){
//						
=======
//
//		if (Utils.isNearChest()) {
//			if (walkRock()) {
//				if (breakRock()) {
//					if (walkMineArea()) {
//
>>>>>>> 9f4342b43e52ce520c1bd05fc59bbc1167e2b0aa
//					}
//				}
//			}
//		}
<<<<<<< HEAD
					
=======

>>>>>>> 9f4342b43e52ce520c1bd05fc59bbc1167e2b0aa
	}

	@Override
	public boolean validate() {
		return (Utils.isInMineArea() && Utils.inventoryFull() && Utils.hasDirt())
				|| (Utils.isNearChest() && Utils.emptySpaces() == 28);
	}

	private boolean breakRock() {
		
		RSObject rockfall = Utils.getRock();
		
		if (rockfall != null){
			if (DynamicClicking.clickRSObject(rockfall, "Mine")){
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						return !Utils.rockInWay();
					}
					
				}, General.random(3000, 5000));
			}
		}
		
		return true;
	}
	
	
	
	
	
	private boolean walkRock() {
		if (WebWalking.walkTo(Constants.ROCK_AREA.getRandomTile())) {

			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					General.sleep(100, 150);
					return Constants.ROCK_AREA.getRandomTile().isOnScreen();
				}

			}, General.random(5000, 7500));

		}
		return true;
	}

	private boolean walkMineArea() {
		if (WebWalking.walkTo(Constants.MINE_AREA[0].getRandomTile())) {
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					General.sleep(100, 150);
					return Utils.isInMineArea();
				}

			}, General.randomLong(5500, 7000));
		}

		return true;
	}

	private boolean walkHopperArea() {
		if (WebWalking.walkTo(Constants.HOPPER_AREA.getRandomTile())) {
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					General.sleep(100, 150);
					return Constants.HOPPER_AREA.getRandomTile().isOnScreen();
				}

			}, General.random(5500, 7000));
		}

		return true;
	}
}