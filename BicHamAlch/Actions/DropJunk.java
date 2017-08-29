package scripts.BicHamAlch.Actions;

import java.awt.event.KeyEvent;

<<<<<<< HEAD
import org.tribot.api.Clicking;
import org.tribot.api.General;
=======
>>>>>>> 9f4342b43e52ce520c1bd05fc59bbc1167e2b0aa
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
		
		if (dropItems()){
			General.sleep(200,500);
		}
	}

	@Override
	public boolean validate() {
		return (Utils.emptySpaces() < 4 || Utils.isStunned() || !Utils.isInPickpocketArea()) && Utils.hasJunk();
	}
	
<<<<<<< HEAD
	private static boolean dropItems(){
=======
	private static boolean dropItem(){
		
>>>>>>> 9f4342b43e52ce520c1bd05fc59bbc1167e2b0aa
		Keyboard.sendPress(KeyEvent.CHAR_UNDEFINED, KeyEvent.VK_SHIFT);
		
		for (RSItem item : Inventory.getAll()){
			for (int i = 0; i < Constants.JUNK.length; i++){
				if (item.getID() == Constants.JUNK[i]){
					item.hover();
					item.click();
				}
			}
		}
		
		Keyboard.sendRelease(KeyEvent.CHAR_UNDEFINED, KeyEvent.VK_SHIFT);
		
		return true;
	}
}
