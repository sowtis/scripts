package scripts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.tribot.api.General;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;

import scripts.Node;

@ScriptManifest (authors = {"Bic"}, category = "Template", name = "Template")
public class Template extends Script {
	private final List<Node> nodes = new ArrayList<>();
	 
	@Override
	public void run() {
		//Collections.addAll(nodes, new Class(), new Class());
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
}