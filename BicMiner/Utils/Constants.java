package scripts.BicMiner.Utils;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class Constants {
	
	// Items
	
	public static final int DIRT_ID = 12011;
	
	// Objects
	
	public static final String ORE = "Ore vein";
	public static final String ROCK = "Rockfall";
	public static final String HOPPER = "Hopper";
	public static final int HOPPER_ID = 26674;
	public static final String CRATE = "Crate"; // Hammer crate
	public static final int CRATE_ID = 357;		// Hammer crate
	public static final String SACK = "Sack";
	public static final int SACK_ID = 26688;	
	public static final String STRUT = "Broken strut";
	public static final int STRUT_ID = 26670;	
	public static final String BANK = "Bank chest";
	public static final int BANK_ID = 26707;
	
	// Tiles

	public static final RSArea HOPPER_AREA =
			new RSArea(new RSTile(3747, 5674, 0), new RSTile(3752, 5670, 0));
	
	public static final RSArea SACK_AREA =
			new RSArea(new RSTile(3749, 5661, 0), new RSTile(3751, 5658, 0));
	
	public static final RSTile ROCK_A_TILE = new RSTile(3727, 5652, 0);	
	public static final RSTile ROCK_B_TILE = new RSTile(3728, 5651, 0);
	public static final RSArea ROCK_AREA =
			new RSArea(new RSTile(3726, 5652, 0), new RSTile(3729, 5651, 0));
	
	public static final RSArea MINE_AREA[] =
		{
			new RSArea(new RSTile(3712, 5632, 0), new RSTile(3723, 5649, 0))
		};

	public static final int MINING_ANIMATION = 6752;
	
}
