package com.darkcart.xdolf.clickgui.elements;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.clickgui.XdolfGuiClick;
import com.darkcart.xdolf.fonts.Fonts;
import com.darkcart.xdolf.util.Friend;
import com.darkcart.xdolf.util.RenderUtils;
import com.darkcart.xdolf.util.Value;

import net.minecraft.entity.player.EntityPlayer;

public class XdolfWindow
{
	protected String title;
	protected int xPos;
	protected int yPos;
	
	protected boolean isOpen;
	protected boolean isExtended;
	protected boolean isPinned;
	
	public int dragX;
	public int dragY;
	public int lastDragX;
	public int lastDragY;
	protected boolean dragging;
	
	public ArrayList<XdolfButton> buttons = new ArrayList<XdolfButton>();
	public ArrayList<XdolfSlider> sliders = new ArrayList<XdolfSlider>();
	
	public XdolfWindow(String title, int x, int y)
	{
		this.title = title;
		this.xPos = x;
		this.yPos = y;
		XdolfGuiClick.windows.add(this);
		XdolfGuiClick.unFocusedWindows.add(this);
	}
	
	public void windowDragged(int x, int y) {
		dragX = x - lastDragX;
		dragY = y - lastDragY;
	}
	
	private int buttonCount = 0, sliderCount = 0;
	
	public void addButton(Module mod) {
		buttons.add(new XdolfButton(this, mod, xPos + 2, yPos + (11 * buttons.size()) + 16));
	}
	
	public XdolfSlider addSlider(Value value)
	{
		return addSlider(value, 10.0F);
	}
	
	public XdolfSlider addSlider(Value value, float maxValue)
	{
		return addSlider(value, 0.0F, maxValue, false);
	}
	
	public XdolfSlider addSlider(Value value, float minValue, float maxValue, boolean shouldRound)
	{
		XdolfSlider slider = new XdolfSlider(this, value, xPos + 2, yPos + (19 * sliderCount) + 16, minValue, maxValue, shouldRound);
		sliders.add(slider);
		sliderCount++;
		
		return slider;
	}
	
	public void draw(int x, int y)
	{
		if(dragging)
		{
			windowDragged(x, y);
		}

		if(isExtended)
		{
			RenderUtils.drawBetterBorderedRect(xPos + dragX, yPos + dragY, xPos + 90 + dragX, yPos + (11 * buttons.size() + 17) + (19 * sliders.size()) + dragY, 0.5F, 0xFF000000, 0x60000000);
				
			for(XdolfButton button: buttons)
			{
				button.draw();
				if(x >= button.getX() + dragX && y >= button.getY() + 1 + dragY && x <= button.getX() + 85.5 + dragX && y <= button.getY() + 10 + dragY)
				{
					button.isOverButton = true;
				}else{
					button.isOverButton = false;
				}
			}
				
			for(XdolfSlider slider: sliders)
			{
				slider.draw(x);
			}
			Fonts.roboto18.drawStringWithShadow(title, xPos + 3 + dragX, yPos + dragY + 1, 0xFFFFFFFF);
				
			RenderUtils.drawBetterBorderedRect(xPos + 70 + dragX, yPos + 3 + dragY, xPos + 78 + dragX, yPos + 11 + dragY, 0.5F, 0xFF000000, isPinned ? 0xFFFF0000 : 0xFF383b42);
			RenderUtils.drawBetterBorderedRect(xPos + 80 + dragX, yPos + 3 + dragY, xPos + 88 + dragX, yPos + 11 + dragY, 0.5F, 0xFF000000, isExtended ? 0xFFFF0000 : 0xFF383b42);
				
		}else{
			RenderUtils.drawBetterBorderedRect(xPos + dragX, yPos + dragY, xPos + 90 + dragX, yPos + 14 + dragY, 0.5F, 0xff000000, 0x60000000);
			Fonts.roboto18.drawStringWithShadow(title, xPos + 3 + dragX, yPos + dragY + 1, 0xFFFFFFFF);
				
			RenderUtils.drawBetterBorderedRect(xPos + 70 + dragX, yPos + 3 + dragY, xPos + 78 + dragX, yPos + 11 + dragY, 0.5F, 0xFF000000, isPinned ? 0xFFFF0000 : 0xFF383b42);
			RenderUtils.drawBetterBorderedRect(xPos + 80 + dragX, yPos + 3 + dragY, xPos + 88 + dragX, yPos + 11 + dragY, 0.5F, 0xFF000000, isExtended ? 0xFFFF0000 : 0xFF383b42);
				
		}
	}
	
	public void drawCenteredTTFString(String s, int x, int y, int color)
    {
		Fonts.roboto18.drawCenteredString(s, x, y, color, true);
    }
	
	public void mouseClicked(int x, int y, int button)
	{
		for(XdolfButton xButton: buttons)
		{
			xButton.mouseClicked(x, y, button);
		}
		
		for(XdolfSlider slider: sliders)
		{
			slider.mouseClicked(x, y, button);
		}
		
		if(x >= xPos + 80 + dragX && y >= yPos + 3 + dragY && x <= xPos + 88 + dragX && y <= yPos + 11 + dragY)
		{
			//AdolfWrapper.getMinecraft().playSoundFX("random.click", 1.0F, 1.0F);
			isExtended = !isExtended;
		}
		if(x >= xPos + 70 + dragX && y >= yPos + 3 + dragY && x <= xPos + 78 + dragX && y <= yPos + 11 + dragY)
		{
			//AdolfWrapper.getMinecraft().sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			isPinned = !isPinned;
		}
		if(x >= xPos + dragX && y >= yPos + dragY && x <= xPos + 69 + dragX && y <= yPos + 12 + dragY)
		{
			XdolfGuiClick.sendPanelToFront(this);
			dragging = true;
			lastDragX = x - dragX;
			lastDragY = y - dragY;
		}
		if(this.title.equalsIgnoreCase("Radar"))
		{
			int count = 0;
			for(Object o: Wrapper.getWorld().playerEntities)
			{
				EntityPlayer e = (EntityPlayer) o;
				if(e != Wrapper.getPlayer() && !e.isDead)
				{
					int x2 = 26;
					if(x >= xPos + dragX && y >= yPos + dragY + (10 * count) + 2 && x <= xPos + dragX + 90 && y <= yPos + dragY + (10 * count) + 13 + 2)
					{
						for(Friend friend: Wrapper.getFriends().friendsList)
						{
							if(Wrapper.getFriends().isFriend(e.getName()))
							{
								Wrapper.getFriends().removeFriend(e.getName());
								Wrapper.addChatMessage("Removed " + e.getName() + " from friends.");
								Wrapper.getFileManager().saveFriends();
							}else{
								Wrapper.getFriends().addFriend(e.getName(), e.getName());
								Wrapper.addChatMessage("Protected " + e.getName() + " as " + e.getName() + ".");
								Wrapper.getFileManager().saveFriends();
							}
						}
						Wrapper.getFileManager().saveFriends();
					}
				}
				count++;
			}
		}
	}
	
	public void mouseReleased(int x, int y, int b)
	{
		for(XdolfSlider slider: sliders)
		{
			slider.mouseReleased(x, y, b);
		}
		if(b == 0) {
			dragging = false;
		}
	}
	
	public final String getTitle()
	{
		return this.title;
	}
	
	public int getX()
	{
		return this.xPos;
	}
	
	public int getY()
	{
		return this.yPos;
	}
	
	public boolean isExtended()
	{
		return isExtended;
	}
	
	public boolean isOpen()
	{
		return isOpen;
	}
	
	public boolean isPinned()
	{
		return isPinned;
	}
	
	public void setOpen(boolean flag)
	{
		this.isOpen = flag;
	}
	
	public void setExtended(boolean flag)
	{
		this.isExtended = flag;
	}
	
	public void setPinned(boolean flag)
	{
		this.isPinned = flag;
	}
}
