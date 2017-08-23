package scripts.BicHamAlch.Utils;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class Constants {

	// ITEMS

	public static final int MONEY = 995;
	
	public static final int NATURE = 561;
	
	public static final String[] JEWELRY = 
		{ 	"Gold ring", "Gold necklace", "Gold amulet",
			"Sapphire", "Sapphire ring", "Sapphire necklace", "Sapphire amulet",
			"Emerald", "Emerald ring", "Emerald necklace", "Emerald amulet",
			"Ruby", "Ruby ring", "Ruby necklace", "Ruby amulet",
			"Diamond", "Diamond ring", "Diamond necklace", "Diamond amulet" 	};
	
	public static final int[] KEYS = 
		{	8867, 8868, 8866, 8869	};
	
	public static final int[] JUNK =
		{	1205, 1739, 1349, 199, 1734, 4306, 886, 1203, 697, 1353, 590, 882,
			1349, 1269, 1129, 2138, 4302, 4304, 4298, 4300, 4310, 4308, 1207,
			1625, 321, 1734, 1351, 453, 314, 688, 1265, 1511, 946, 1627, 1267,
			203};
	
	// TILES + AREAS
	
	public static final RSArea PICKPOCKET_AREA =
			new RSArea(new RSTile(2567, 5188, 0), new RSTile(2566, 5190, 0));
	
	public static final RSArea[] ROOM_AREA = 
		{	new RSArea(new RSTile(2567, 5194, 0), new RSTile(2570, 5190, 0)),
			new RSArea(new RSTile(2567, 5200, 0), new RSTile(2571, 5197, 0)),
			new RSArea(new RSTile(2576, 5194, 0), new RSTile(2572, 5190, 0)),
			new RSArea(new RSTile(2576, 5200, 0), new RSTile(2572, 5196, 0))	};
	
	public static final RSTile[] DOOR_TILE = 
		{	new RSTile(2569, 5189, 0),
			new RSTile(2566, 5198, 0),
			new RSTile(2577, 5192, 0),
			new RSTile(2577, 5198, 0)	};
	
	public static final RSArea GAME_AREA = 
			new RSArea(new RSTile(2566, 5203, 0), new RSTile(2577, 2573, 0));
	
	public static final RSArea BANK_AREA =
			new RSArea(new RSTile(0,0,0), new RSTile(0,0,0));
	
	public static final RSArea CASTLE_AREA = 
			new RSArea(new RSTile(0,0,0), new RSTile(0,0,0));
	
	
	// OBJECTS + NPC
	
	public static final int[] DOORS = 
		{ 	15731, 15759, 15759, 15759, 	};
	
	public static final int[] CHESTS = 
		{	15723, 15724, 15722, 15726	};
	
	public static final int GUARD = 4522;


	
	// ANIMATIONS
	
}
