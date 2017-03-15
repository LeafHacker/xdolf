package com.darkcart.xdolf.clickgui.windows;

import com.darkcart.xdolf.clickgui.elements.XdolfWindow;
import com.darkcart.xdolf.util.Category;

public class WindowAura extends XdolfWindow
{
	public WindowAura() {
		super("Aura", 94, 109);
		this.loadButtonsFromCategory(Category.AURA);
	}
}
