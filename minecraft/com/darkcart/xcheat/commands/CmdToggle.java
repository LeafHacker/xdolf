package com.darkcart.xcheat.commands;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;
import com.darkcart.xcheat.Wrapper;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class CmdToggle extends Command
{
	public CmdToggle()
	{
		super("toggle");
	}

	@Override
	public void runCommand(String s, String[] args)
	{
		try
		{
			boolean valid = false;
			for(Module mod: Client.modules)
			{
				if(mod.getName().trim().toLowerCase().equalsIgnoreCase(s.substring(7)))
				{
					mod.toggle();
					Wrapper.addChatMessage("Toggled " + mod.getName() + ".");
					valid = true;
					break;
				}
			}
			
			if(!valid)
			{
				Wrapper.addChatMessage("Invalid mod.");
			}
		}catch(Exception e)
		{
			Wrapper.addChatMessage("Usage: " + getSyntax());
		}
	}

	@Override
	public String getDescription()
	{
		return "Toggles the specified hack";
	}

	@Override
	public String getSyntax()
	{
		return "toggle <name of hack>";
	}
}
