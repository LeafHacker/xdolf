package com.darkcart.xcheat.commands;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;

import net.minecraft.util.text.TextComponentString;

public class CmdAllOff extends Command
{
	public CmdAllOff() 
	{
		super("alloff");
	}

	@Override
	public void runCommand(String s, String[] args)
	{
		int count = 0;
		for(Module mod: Client.modules)
		{
			if(mod.isToggled())
			{
				mod.toggle();
				count++;
			}
		}
		
		Client.mc.player.addChatMessage(new TextComponentString(count + (count == 1 ? " hack" : " hacks") + " turned off."));
	}

	@Override
	public String getDescription()
	{
		return "Turns all hacks off.";
	}

	@Override
	public String getSyntax()
	{
		return "alloff";
	}
}
