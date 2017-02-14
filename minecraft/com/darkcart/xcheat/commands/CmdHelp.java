package com.darkcart.xcheat.commands;

import com.darkcart.xcheat.Client;

import net.minecraft.util.text.TextComponentString;

public class CmdHelp extends Command
{
	public CmdHelp()
	{
		super("help");
	}

	@Override
	public void runCommand(String s, String[] args)
	{
		for(Command cmd: CommandManager.commands)
		{
			if(cmd != this) {
				Client.mc.player.addChatMessage(new TextComponentString(cmd.getSyntax().replace("<", "<\247a").replace(">", "\247f>") + " - " + cmd.getDescription()));
			}
		}
	}

	@Override
	public String getDescription()
	{
		return "Lists all commands";
	}

	@Override
	public String getSyntax()
	{
		return "help";
	}
}