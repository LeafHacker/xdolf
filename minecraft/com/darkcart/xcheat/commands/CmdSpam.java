package com.darkcart.xcheat.commands;

import java.io.File;

import com.darkcart.xcheat.Wrapper;
import com.darkcart.xcheat.mods.player.Spammer;

public class CmdSpam extends Command {

	public CmdSpam() {
		super("spam");
	}

	@Override
	public void runCommand(String s, String[] args) {
		if (args[0].equalsIgnoreCase("mode")) {
			Spammer.mode = Integer.parseInt(args[1]);
		}
		if (args[0].equalsIgnoreCase("msg")) {
			Spammer.message = args[1];
		}
		if (args[0].equalsIgnoreCase("delay")) {
			Spammer.delay = Integer.parseInt(args[1]);
		}
		if (args[0].equalsIgnoreCase("file")) {
			Spammer.file = Wrapper.getMinecraftDir() + File.pathSeparator + "spam" + File.pathSeparator + args[1];
		}
	}

	@Override
	public String getDescription() {
		return "Changes message and optionally frequency of spam and mode";
	}

	@Override
	public String getSyntax() {
		return "spam <mode/msg/delay/file> <args>";
	}

}
