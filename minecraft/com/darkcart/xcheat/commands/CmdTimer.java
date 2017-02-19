package com.darkcart.xcheat.commands;

import com.darkcart.xcheat.mods.world.Timer;

public class CmdTimer extends Command {

	public CmdTimer() {
		super("timer");
	}

	@Override
	public void runCommand(String s, String[] args) {
		Timer.speed = Integer.parseInt(args[0]);
	}

	@Override
	public String getDescription() {
		return "Makes your game run EVEN faster";
	}

	@Override
	public String getSyntax() {
		return "timer <speed>";
	}

}
