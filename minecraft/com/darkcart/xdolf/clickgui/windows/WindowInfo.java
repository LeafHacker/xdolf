package com.darkcart.xdolf.clickgui.windows;

import com.darkcart.xdolf.Wrapper;
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
			windowDragged(x, y);
		}
			
		if(isExtended())
		{
			RenderUtils.drawBetterBorderedRect(getX() + dragX, getY() + dragY, getX() + 90 + dragX, getY() + 66 + dragY, 0.5F, 0xFF000000, 0x80000000);
				
			Fonts.roboto18.drawStringWithShadow(Wrapper.getMinecraft().getDebugFPS() + " FPS", getX() + 3 + dragX, getY() + 13 + dragY, 0xFFFFFF);
			Fonts.roboto18.drawStringWithShadow("X: " + (int)Wrapper.getPlayer().posX, getX() + 3 + dragX, getY() + 23 + dragY, 0xFFFFFF);
			Fonts.roboto18.drawStringWithShadow("Y: " + (int)Wrapper.getPlayer().posY, getX() + 3 + dragX, getY() + 33 + dragY, 0xFFFFFF);
			Fonts.roboto18.drawStringWithShadow("Z: " + (int)Wrapper.getPlayer().posZ, getX() + 3 + dragX, getY() + 43 + dragY, 0xFFFFFF);
		
			Fonts.roboto18.drawStringWithShadow(Wrapper.getMinecraft().session.username, getX() + 3 + dragX, getY() + 53 + dragY, 0xFFFFFF);
				
			for(XdolfButton button: buttons)
			{
				button.draw();
			}	
			
			Fonts.roboto18.drawStringWithShadow(getTitle(), getX() + 3 + dragX, getY() + dragY + 1, 0xFFFFFFFF);
			RenderUtils.drawBetterBorderedRect(getX() + 70 + dragX, getY() + 3 + dragY, getX() + 78 + dragX, getY() + 11 + dragY, 0.5F, 0xFF000000, isPinned() ? 0xFFFF0000 : 0xFF383b42);
			RenderUtils.drawBetterBorderedRect(getX() + 80 + dragX, getY() + 3 + dragY, getX() + 88 + dragX, getY() + 11 + dragY, 0.5F, 0xFF000000, isExtended() ? 0xFFFF0000 : 0xFF383b42);
		}else{
			RenderUtils.drawBetterBorderedRect(getX() + dragX, getY() + dragY, getX() + 90 + dragX, getY() + 14 + dragY, 0.5F, 0xFF000000, 0x80000000);
			Fonts.roboto18.drawStringWithShadow(getTitle(), getX() + 3 + dragX, getY() + dragY + 1, 0xFFFFFFFF);
			
			RenderUtils.drawBetterBorderedRect(getX() + 70 + dragX, getY() + 3 + dragY, getX() + 78 + dragX, getY() + 11 + dragY, 0.5F, 0xFF000000, isPinned() ? 0xFFFF0000 : 0xFF383b42);
			RenderUtils.drawBetterBorderedRect(getX() + 80 + dragX, getY() + 3 + dragY, getX() + 88 + dragX, getY() + 11 + dragY, 0.5F, 0xFF000000, isExtended() ? 0xFFFF0000 : 0xFF383b42);
		}
	}
}
