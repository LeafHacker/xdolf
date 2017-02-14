package com.darkcart.xcheat.commands;

import java.util.ArrayList;

import com.darkcart.xcheat.Client;

import net.minecraft.util.text.TextComponentString;

public class CommandManager //TODO: STILL NEEDS TO BE HOOKED WHERE CHAT MESSAGES ARE DEALT WITH
{
	public static ArrayList<Command> commands = new ArrayList<Command>();

	public static char cmdPrefix = '.';

	public CommandManager()
	{
		addCommands();
	}

	public void addCommands()
	{
		commands.clear();
	}
	
	public void runCommands(String s)
	{
		if(!s.contains(Character.toString(cmdPrefix)) || !s.startsWith(Character.toString(cmdPrefix))) return;

		boolean commandResolved = false;
		String readString = s.trim().substring(Character.toString(cmdPrefix).length()).trim();
		boolean hasArgs = readString.trim().contains(" ");
		String commandName = hasArgs ? readString.split(" ")[0] : readString.trim();
		String[] args = hasArgs ? readString.substring(commandName.length()).trim().split(" ") : new String[0];

		for(Command command: commands)
		{
			if(command.getCommand().trim().equalsIgnoreCase(commandName.trim())) 
			{
				command.runCommand(readString, args);
				commandResolved = true;
				break;
			}
		}

		if(!commandResolved)
		{
			Client.mc.player.addChatMessage(new TextComponentString("Invalid command. Type .help for a list of commands."));
		}
	}
}