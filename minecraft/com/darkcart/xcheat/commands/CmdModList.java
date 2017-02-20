package com.darkcart.xcheat.commands;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;
import com.darkcart.xcheat.Wrapper;

import net.minecraft.util.text.TextComponentString;

public class CmdModList extends Command
{
	public CmdModList()
	{
		super("modlist");
	}

	@Override
	public void runCommand(String s, String[] args)
	{
		for(Module mod: Client.modules)
		{
			if(mod.getDescription() != "") {
				Wrapper.addChatMessage(mod.getName().replace("<", "<\247a").replace(">", "\247f>") + " - " + mod.getDescription());
			}
		}
	}

	@Override
	public String getDescription()
	{
		return "Lists all modules";
	}

	@Override
	public String getSyntax()
	{
		return "modlist";
	}
}