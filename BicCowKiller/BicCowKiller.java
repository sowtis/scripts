package scripts.BicCowKiller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.util.abc.ABCUtil;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;

import scripts.BicCowKiller.AntiBan;
import scripts.BicCowKiller.Node;
import scripts.BicCowKiller.Methods.*;
import scripts.BicCowKiller.Utils.Vars;

@ScriptManifest (authors = {"Bic"}, category = "Template", name = "Template")
public class BicCowKiller extends Script implements Painting {
	
	public static ABCUtil abc = new ABCUtil();
	private final List<Node> nodes = new ArrayList<>();
	

    Font font = new Font("Verdana", Font.BOLD, 12);
	 
	@Override
	public void run() {
		Collections.addAll(nodes, new Bank(), new Eat(), new Kill(), new Loot(), new Tan());
		loop(20, 40);
	}

	private void loop(int min, int max) {
		while (true) {
			for (final Node node : nodes) {
				if (node.validate()) {
					node.execute();
					sleep(General.random(min, max));	//time in between executing nodes
				}
			}
		}
	}
	
	@Override
	public void onPaint(Graphics g) {
		
		long timeRan = System.currentTimeMillis() - Vars.startTime;
		
		g.setFont(font);
	    g.setColor(Color.WHITE);
	    g.drawString("Bic's Cow Killer", 279, 360);
	    g.drawString("Run time: " + Timing.msToString(timeRan), 279, 375);
	    g.drawString("Status: " + Vars.status, 279, 390);
	    g.drawString("ABC: " + AntiBan.abcActions, 279, 405);
	}
}