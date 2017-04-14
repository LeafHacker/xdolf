package com.darkcart.xdolf.commands;

import com.darkcart.xdolf.Wrapper;

import net.minecraft.entity.player.EntityPlayer;

public class CmdPlayerInfo extends Command {

	public CmdPlayerInfo() {
		super("playerinfo");
	}

	@Override
	public void runCommand(String s, String[] args) {
		EntityPlayer target = null;
		for (EntityPlayer p: Wrapper.getWorld().playerEntities) {
			if (p.getName().equals(args[1])) {
				target = p;
			} else {
				Wrapper.addChatMessage("Player not found.");
				return;
			}
		}
		Wrapper.addChatMessage("Username: " + target.getName());
		Wrapper.addChatMessage("Health: " + target.getHealth() + "/20");
		Wrapper.addChatMessage("Distance: " + Math.abs(target.getDistanceToEntity(Wrapper.getPlayer())));
		
	}

	@Override
	public String getDescription() {
		return "Gives you information about a player. PLAYER MUST BE IN RENDER RANGE.";
	}

	@Override
	public String getSyntax() {
		return "playerinfo <player>";
	}

}
