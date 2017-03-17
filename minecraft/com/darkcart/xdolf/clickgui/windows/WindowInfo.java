package com.darkcart.xdolf.clickgui.windows;

import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.clickgui.XdolfGuiClick;
import com.darkcart.xdolf.clickgui.elements.XdolfButton;
import com.darkcart.xdolf.clickgui.elements.XdolfWindow;
import com.darkcart.xdolf.fonts.Fonts;
import com.darkcart.xdolf.mods.Hacks;
import com.darkcart.xdolf.util.RenderUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WindowInfo extends XdolfWindow
{
	public WindowInfo() 
	{
		super("Info", 94, 2);
	}
	
	@Override
	public void draw(int x, int y)
	{
		if(dragging)
		{
			drag(x, y);
		}
			
		if(isOpen())
		{
			RenderUtils.drawBetterBorderedRect(getXAndDrag(), getYAndDrag(), getXAndDrag() + 100, getYAndDrag() + 66, 0.5F, 0xFF000000, 0x80000000);
				
			Fonts.roboto18.drawStringWithShadow(Wrapper.getMinecraft().getDebugFPS() + " FPS", getXAndDrag() + 3, getYAndDrag() + 13, 0xFFFFFF);
			Fonts.roboto18.drawStringWithShadow("X: " + (int)Wrapper.getPlayer().posX, getXAndDrag() + 3, getYAndDrag() + 23, 0xFFFFFF);
			Fonts.roboto18.drawStringWithShadow("Y: " + (int)Wrapper.getPlayer().posY, getXAndDrag() + 3, getYAndDrag() + 33, 0xFFFFFF);
			Fonts.roboto18.drawStringWithShadow("Z: " + (int)Wrapper.getPlayer().posZ, getXAndDrag() + 3, getYAndDrag() + 43, 0xFFFFFF);
		
			Fonts.roboto18.drawStringWithShadow(Wrapper.getMinecraft().session.username, getXAndDrag() + 3, getYAndDrag() + 53, 0xFFFFFF);
				
			Fonts.roboto18.drawStringWithShadow(getTitle(), getXAndDrag() + 3, getYAndDrag() + 1, 0xFFFFFFFF);
			if(Wrapper.getMinecraft().currentScreen instanceof XdolfGuiClick) {
				RenderUtils.drawBetterBorderedRect(getXAndDrag() + 79, getYAndDrag() + 2, getXAndDrag() + 88, getYAndDrag() + 11, 0.5F, 0xFF000000, isPinned() ? 0xFFFF0000 : 0xFF383b42);
				RenderUtils.drawBetterBorderedRect(getXAndDrag() + 89, getYAndDrag() + 2, getXAndDrag() + 98, getYAndDrag() + 11, 0.5F, 0xFF000000, isOpen() ? 0xFFFF0000 : 0xFF383b42);
			}
			}else{
			RenderUtils.drawBetterBorderedRect(getXAndDrag(), getYAndDrag(), getXAndDrag() + 100, getYAndDrag() + 14, 0.5F, 0xFF000000, 0x80000000);
			Fonts.roboto18.drawStringWithShadow(getTitle(), getXAndDrag() + 3, getYAndDrag() + 1, 0xFFFFFFFF);
			
			RenderUtils.drawBetterBorderedRect(getXAndDrag() + 79, getYAndDrag() + 2, getXAndDrag() + 88, getYAndDrag() + 11, 0.5F, 0xFF000000, isPinned() ? 0xFFFF0000 : 0xFF383b42);
			RenderUtils.drawBetterBorderedRect(getXAndDrag() + 89, getYAndDrag() + 2, getXAndDrag() + 98, getYAndDrag() + 11, 0.5F, 0xFF000000, isOpen() ? 0xFFFF0000 : 0xFF383b42);
		}
	}
}
