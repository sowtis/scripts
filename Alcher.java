package scripts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.GameTab.TABS;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.Game;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Magic;
import org.tribot.api2007.Skills;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Ending;
import org.tribot.script.interfaces.Painting;
import scripts.orbMakerReloaded.utilities.antiban.Seeding;
import scripts.org.dezapi.grandexchange.GrandExchange;
import scripts.org.dezapi.grandexchange.restocker.Restocker;
import scripts.org.dezapi.nodes.SaleNode;
import scripts.org.dezapi.nodes.PurchaseNode;

@ScriptManifest(authors = { "" }, category = "mage", name = "Alcher")
public class Alcher extends Script implements Ending, Painting {
	/* v Items to edit, best in a high populated world. v */ private String ITEM = "Yew longbow";
	/* ^ Items to edit, best in a high populated world.^ */ /*
															 * v Dont touch
															 * these v
															 */ private String ALCH = "High Level alchemy";
	private String NATS = "Nature rune";
	private boolean run = true;

	/* ^ Dont touch these ^ */ public void alch() {
		if (Inventory.find(ITEM, NATS).length < 2) {
			resupply();
			return;
		}
		if (Magic.isSpellSelected() && GameTab.getOpen().equals(TABS.MAGIC)) {
			Mouse.click(1);
			return;
		}
		if (Magic.isSpellSelected()) {
			General.sleep((long) General.randomSD(37.923, 22.39948));
			if (Clicking.click(Inventory.find(ITEM))) {
				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						return GameTab.getOpen().equals(TABS.MAGIC);
					}
				}, (long) General.randomSD(6402.2034, 24039.9234, 9559.9123));
			}
			return;
		}
		if (GameTab.getOpen().equals(TABS.MAGIC)) {
			General.sleep((long) General.randomSD(29.923, 12.39948));
			if (Magic.selectSpell(ALCH)) {
				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						return GameTab.getOpen().equals(TABS.INVENTORY);
					}
				}, (long) General.randomSD(6402.2034, 24039.9234, 9559.9123));
			}
			return;
		}
		if (Game.getItemSelectionState() == 1) {
			General.sleep((long) General.randomSD(3.923, 2.39948));
			Mouse.click(1);
			return;
		}
		General.sleep((long) General.randomSD(3.923, 2.39948));
		if (GameTab.open(TABS.MAGIC)) {
			Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					return GameTab.getOpen().equals(TABS.MAGIC);
				}
			}, (long) General.randomSD(6402.2034, 24039.9234, 9559.9123));
		}
	}

	private void resupply() {
		final int q = General.randomSD(4000, 6000, 4500, 1934);
		Restocker.restock(new PurchaseNode[] { new PurchaseNode(NATS, General.randomSD(300, 500, 350, 23), q),
				new PurchaseNode(ITEM, General.randomSD(800, 1000, 845, 193), q) }, new SaleNode[0]);
	}

	final double[] sleep = Seeding.generateCharacterProfile(5.49234, 3.9584);

	@Override
	public void run() {
		final ABC2 abc = new ABC2();
		General.useAntiBanCompliance(true);
		startMonitor();
		while (run) {
			if (!Magic.isSpellSelected())
				abc.timedActions();
			alch();
			General.sleep((long) General.randomSD(sleep[0], sleep[1]));
		}
	}

	private int previousxp;

	private void startMonitor() {
		new Thread() {
			@Override
			public void run() {
				while (run) {
					if (!Timing.waitCondition(new Condition() {
						@Override
						public boolean active() {
							return previousxp != Skills.getXP(SKILLS.MAGIC);
						}
					}, 455000)) {
						run = false;
					}
					previousxp = Skills.getXP(SKILLS.MAGIC);
				}
			}
		}.start();
	}

	@Override
	public void onEnd() {
		run = false;
	}

	private final long starttime = System.currentTimeMillis();
	private final int startxp = Skills.getXP(SKILLS.MAGIC);
	private final int LEVEL_XPDESIRED = 7944614;
	private final int LEVEL_DESIRED = 94;

	@Override
	public void onPaint(Graphics g) {
		final long time = System.currentTimeMillis() - starttime;
		final int seconds = (int) (time / 1000) % 60;
		final int minutes = (int) ((time / (1000 * 60)) % 60);
		final int hours = (int) ((time / (1000 * 60 * 60)) % 24);
		final int xpGained = Skills.getXP(SKILLS.MAGIC) - startxp;
		final int xpPerHour = (int) (xpGained * 3600000D / (System.currentTimeMillis() - starttime));
		g.setColor(Color.black);
		g.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 24));
		g.drawString("Time running: " + hours + ":" + minutes + ":" + seconds, 200, 400);
		g.drawString("XP/PH: " + xpPerHour, 200, 420);
		g.drawString("XP Gained: " + xpGained, 200, 440);
		g.drawString("Hrs til " + LEVEL_DESIRED + " mage: "
				+ (int) ((LEVEL_XPDESIRED - Skills.getXP(SKILLS.MAGIC)) / xpPerHour), 200, 460);
	}
}