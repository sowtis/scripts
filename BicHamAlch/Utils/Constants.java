package scripts.BicHamAlch.Utils;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class Constants {

	// ITEMS
	
	public static final int NATURE = 441;
	
	public static final String[] JEWELRY = 
		{ 	"Gold ring", "Gold necklace", "Gold amulet",
			"Uncut sapphire", "Sapphire", "Sapphire ring", "Sapphire necklace", "Sapphire amulet",
			"Uncut emerald", "Emerald", "Emerald ring", "Emerald necklace", "Emerald amulet",
			"Uncut ruby", "Ruby", "Ruby ring", "Ruby necklace", "Ruby amulet",
			"Uncut diamond", "Diamond", "Diamond ring", "Diamond necklace", "Diamond amulet" 	};
	
	public static final String[] JUNK =
		{ "" };
	
	public static final int[] KEYS = 
		{	1, 2, 3, 4	};
	
	// TILES + AREAS
	
	public static final RSArea PICKPOCKET_AREA =
			new RSArea(new RSTile(0,0,0), new RSTile(0,0,0));
	
	public static final RSArea[] ROOM_AREA = 
		{	new RSArea(new RSTile(0,0,0), new RSTile(0,0,0)),
			new RSArea(new RSTile(0,0,0), new RSTile(0,0,0)),
			new RSArea(new RSTile(0,0,0), new RSTile(0,0,0)),
			new RSArea(new RSTile(0,0,0), new RSTile(0,0,0))	};
	
	public static final RSArea[] DOOR_AREA = 
		{	new RSArea(new RSTile(0,0,0), new RSTile(0,0,0)),
			new RSArea(new RSTile(0,0,0), new RSTile(0,0,0)),
			new RSArea(new RSTile(0,0,0), new RSTile(0,0,0)),
			new RSArea(new RSTile(0,0,0), new RSTile(0,0,0)),	};
	
	public static final RSArea GAME_AREA = 
			new RSArea(new RSTile(0,0,0), new RSTile(0,0,0));
	
	public static final RSArea BANK_AREA =
			new RSArea(new RSTile(0,0,0), new RSTile(0,0,0));
	
	public static final RSArea CASTLE_AREA = 
			new RSArea(new RSTile(0,0,0), new RSTile(0,0,0));
	
	
	// OBJECTS + NPC
	
	public static final int[] DOORS = 
		{ 	1, 2, 3, 4	};
	
	public static final int[] CHESTS = 
		{	1, 2, 3, 4	};
	
	public static final int GUARD = 0;

	
	// ANIMATIONS
	
}
