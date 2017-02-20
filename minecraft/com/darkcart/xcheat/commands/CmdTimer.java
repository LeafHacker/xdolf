package com.darkcart.xcheat.commands;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Wrapper;
import com.darkcart.xcheat.mods.world.Timer;

import net.minecraft.util.text.TextComponentString;

public class CmdTimer extends Command {

	public CmdTimer() {
		super("timer");
	}

	@Override
	public void runCommand(String s, String[] args) {
		Timer.speed = Integer.parseInt(args[0]);
		Wrapper.addChatMessage("Timer updated.");
	}

	@Override
	public String getDescription() {
		return "Change timer speed.";
	}

	@Override
	public String getSyntax() {
		return "timer <speed>";
	}

}
