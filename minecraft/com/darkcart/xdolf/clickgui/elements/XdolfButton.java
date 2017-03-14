package com.darkcart.xdolf.clickgui.elements;

import org.lwjgl.input.Mouse;

import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.clickgui.XdolfGuiClick;
import com.darkcart.xdolf.fonts.Fonts;
import com.darkcart.xdolf.util.RenderUtils;
import com.ibm.icu.impl.duration.impl.Utils;

public class XdolfButton {
	
	private XdolfWindow window;
	private Module mod;
	private int xPos;
	private int yPos;
	
	public boolean isOverButton;
	
	public XdolfButton(XdolfWindow window, Module mod, int xPos, int yPos) {
		this.window = window;
		this.mod = mod;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void draw() {
		RenderUtils.drawBorderedRect(getX() + window.dragX, getY() + window.dragY, getX() + 86 + window.dragX, getY() + 10 + window.dragY, 0.5F, 0xFF000000, mod.isEnabled() ? isOverButton ? 0xFF44AAFF : 0xFFFF0000 : isOverButton ? 0xFF888888 : 0xFF33363d);
		RenderUtils.drawBorderedRect(getX() + window.dragX, getY() + window.dragY, getX() + 86 + window.dragX, getY() + 10 + window.dragY, 0.5F, 0xFF000000, 0x00000000);
		
		if(isOverButton) {
			drawTooltip(mod.getDescription(), getX() + window.dragX + 89, getY() + window.dragY);
		}
		drawCenteredTTFString(mod.getName(), getX() + 3 + 40 + window.dragX, (getY() - 1) + window.dragY, mod.isEnabled() ? 0xFFFFFF : 0xBBBBBB);
		
	}
	
	public void drawCenteredTTFString(String s, int x, int y, int color)
    {
		Fonts.roboto18.drawCenteredString(s, x, y, color, true);
    }
	
	public void mouseClicked(int x, int y, int button) {
		if(x >= getX() + window.dragX && y >= getY() + window.dragY && x <= getX() + 85.5 + window.dragX && y <= getY() + 9 + window.dragY && button == 0 && window.isOpen() && window.isExtended()) {
			XdolfGuiClick.sendPanelToFront(window);
			Wrapper.addChatMessage("test");
			mod.toggle();
		}
	}
	
	
	public void drawTooltip(String description, int x, int y)
	{
		int strWidth = Wrapper.getMinecraft().fontRendererObj.getStringWidth(description) + 3;
		RenderUtils.drawBorderedRect(x, y, x + strWidth, y + 10, 0.5F, 0xff000000, 0x90000000);
		Wrapper.getMinecraft().fontRendererObj.drawString(description, x + 2, y + 1, 0xffffff);
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}
}