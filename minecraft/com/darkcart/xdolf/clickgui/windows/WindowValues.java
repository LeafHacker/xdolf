package com.darkcart.xdolf.clickgui.windows;

import com.darkcart.xdolf.clickgui.elements.XdolfWindow;
import com.darkcart.xdolf.mods.aura.AutoLog;
import com.darkcart.xdolf.mods.aura.KillAura;
import com.darkcart.xdolf.mods.player.Flight;

public class WindowValues extends XdolfWindow
{
	public WindowValues()
	{
		super("Values", 2, 2);
		addSlider(Flight.flySpeed, 0.1F, 10F, false).setValue(1.0F);
		addSlider(KillAura.auraSpeed, 1.0F, 20.0F, true).setValue(8.0F);
		addSlider(KillAura.auraRange, 3.0F, 10.0F, false).setValue(3.75F);
		addSlider(AutoLog.pvpHealthFactor, 1.00F, 19F, true).setValue(6.00F);
	}
}
