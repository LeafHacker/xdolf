package com.darkcart.xdolf.clickgui.windows;

import com.darkcart.xdolf.clickgui.elements.XdolfWindow;
import com.darkcart.xdolf.util.Category;

public class WindowWorld extends XdolfWindow
{
	public WindowWorld() {
		super("World", 186, 2);
		this.loadButtonsFromCategory(Category.World);
	}
}
