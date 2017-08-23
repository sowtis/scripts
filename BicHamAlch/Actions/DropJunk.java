package scripts.BicHamAlch.Actions;

import java.awt.event.KeyEvent;

import org.tribot.api.input.Keyboard;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;

import scripts.BicHamAlch.Node;
import scripts.BicHamAlch.Utils.Constants;
import scripts.BicHamAlch.Utils.Utils;
import scripts.BicHamAlch.Utils.Variables;

public class DropJunk extends Node {

	@Override
	public void execute() {
		Variables.status = "Dropping Junk";
		
		if (dropItem()){
			
		}
	}

	@Override
	public boolean validate() {
		return (Utils.emptySpaces() < 4 || Utils.isStunned() || !Utils.isInPickpocketArea()) && Utils.hasJunk();
	}
	
	private static boolean dropItem(){
		
		Keyboard.sendPress(KeyEvent.CHAR_UNDEFINED, KeyEvent.VK_SHIFT);
		
		for (RSItem item : Inventory.getAll()){
			for (int i = 0; i < Constants.JUNK.length; i++){
				if (item.getID() == Constants.JUNK[i]){
					Inventory.drop(item);
				}
			}
		}
		
		Keyboard.sendRelease(KeyEvent.CHAR_UNDEFINED, KeyEvent.VK_SHIFT);
		
		return true;
	}
}
