package com.darkcart.xdolf.clickgui.windows;

import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.clickgui.elements.XdolfButton;
import com.darkcart.xdolf.clickgui.elements.XdolfSlider;
import com.darkcart.xdolf.clickgui.elements.XdolfWindow;
import com.darkcart.xdolf.fonts.Fonts;
import com.darkcart.xdolf.mods.Hacks;
import com.darkcart.xdolf.util.Friend;
import com.darkcart.xdolf.util.RenderUtils;

import net.minecraft.entity.player.EntityPlayer;

public class WindowRadar extends XdolfWindow {
	public WindowRadar() {
		super("Radar", 94, 98);
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
			int rect = 0;
			int rect2 = 0;
			for(Object o: Wrapper.getWorld().playerEntities) {
				EntityPlayer e = (EntityPlayer) o;
				if(e != Wrapper.getPlayer() && !e.isDead) {
					rect += 10;
					rect2 += 7;
				}
			}
			int count = 0;
				
			if(!(rect == 0 && count == 0))
			{
				RenderUtils.drawBetterBorderedRect(getX() + dragX, getY() + dragY, getX() + 90 + dragX, getY() + rect + 16 + dragY, 0.5F, 0xFF000000, 0x80000000);
			}
			count = 0;
			for(Object o: Wrapper.getWorld().playerEntities)
			{
				EntityPlayer e = (EntityPlayer) o;
				if(e != Wrapper.getPlayer() && !e.isDead)
				{
					int distance = (int)Wrapper.getPlayer().getDistanceToEntity(e);
					String text = "";
					if(Wrapper.getFriends().isFriend(e.getName()))
					{
						text = "\247a" + e.getName() + "\247f: " + (int)distance;
					}else{
						text = "\247c" + e.getName() + "\247f: " + (int)distance;
					}
					for(Friend friend: Wrapper.getFriends().friendsList)
					{
						text = text.replace(friend.getName(), friend.getAlias());
					}
					int xPosition = getX() + 3 + dragX;
					int yPosition = getY() + (10 * count) + 13 + dragY;
					Fonts.roboto18.drawStringWithShadow(text, xPosition, yPosition, 0xFFFFFF);
					count++;
				}
			}
				
			if(rect == 0 && count == 0)
			{
				RenderUtils.drawBetterBorderedRect(getX() + dragX, getY() + dragY, getX() + 90 + dragX, getY() + 26 + dragY, 0.5F, 0xff000000, 0x80000000);
				Fonts.roboto18.drawStringWithShadow("No players in range.", getX() + 3 + dragX, getY() + 13 + dragY, 0xFFFFFF);
			}
				
			for(XdolfButton button: buttons)
			{
				button.draw();
			}
				
			for(XdolfSlider slider: sliders)
			{
				slider.draw(x);
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
