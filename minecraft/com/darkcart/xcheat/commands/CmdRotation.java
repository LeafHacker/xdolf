package com.darkcart.xcheat.commands;

import com.darkcart.xcheat.Wrapper;

public class CmdRotation extends Command {

	public CmdRotation() {
		super("rotate");
	}

	@Override
	public void runCommand(String s, String[] args) {
		Wrapper.getPlayer().rotationYaw = Integer.parseInt(args[0]);
		Wrapper.getPlayer().rotationPitch = Integer.parseInt(args[1]);
	}

	@Override
	public String getDescription() {
		return "Rotates the player. Useful for autowalking.";
	}

	@Override
	public String getSyntax() {
		return "rotate <yaw> <pitch>";
	}

}
