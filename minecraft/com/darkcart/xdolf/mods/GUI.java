package com.darkcart.xdolf.mods;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.util.Category;

public class GUI extends Module {
	public GUI() {
		super("GUI", "", Keyboard.KEY_GRAVE, Category.None);
	}
	
	@Override
	public void onToggled() {
		Wrapper.getMinecraft().displayGuiScreen(Wrapper.getClickGui());
	}
}