package com.darkcart.xdolf.clickgui.elements;

import java.awt.Cursor;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;

import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.clickgui.XdolfGuiClick;
import com.darkcart.xdolf.fonts.Fonts;
import com.darkcart.xdolf.mods.Hacks;
import com.darkcart.xdolf.util.Category;
import com.darkcart.xdolf.util.RenderUtils;
import com.darkcart.xdolf.util.Value;

public class XdolfWindow {
	private String title;
	private int x, y;
	public int dragX;
	public int dragY;
	private int lastDragX;
	private int lastDragY;
	private boolean isOpen, isPinned;
	protected boolean dragging;
	
	private ArrayList<XdolfButton> buttonList = new ArrayList<XdolfButton>();
	private ArrayList<XdolfSlider> sliderList = new ArrayList<XdolfSlider>();
	
	public void drag(int x, int y) {
		dragX = x - lastDragX;
		dragY = y - lastDragY;
	}
	
	public XdolfWindow(String title, int x, int y) {
		this.title = title;
		this.x = x;
		this.y = y;
		XdolfGuiClick.windowList.add(this);
	}
	
	public void draw(int x, int y) {
		int toAdd = 0;
		if(dragging) {
			drag(x, y);
		}
		
		RenderUtils.drawBorderedRect(getXAndDrag(), getYAndDrag(), getXAndDrag() + 100, getYAndDrag() + 13 + (isOpen ? (12 * buttonList.size() + 0.5F) + (20 * sliderList.size() + (sliderList.size() != 0 ? 2.5F : 0)) : 0) + toAdd, 0.5F, 0xFF000000, 0x80000000);
		Fonts.roboto18.drawStringWithShadow(title, getXAndDrag() + 3, getYAndDrag()  + 1, 0xFFFFFF);

		if(Wrapper.getMinecraft().currentScreen instanceof XdolfGuiClick) {
			RenderUtils.drawBorderedRect(getXAndDrag() + 79, getYAndDrag() + 2, getXAndDrag() + 88, getYAndDrag() + 11, 0.5F, 0xFF000000, isPinned ? 0xFFFF0000 : 0xFF383b42);
			RenderUtils.drawBorderedRect(getXAndDrag() + 89, getYAndDrag() + 2, getXAndDrag() + 98, getYAndDrag() + 11, 0.5F, 0xFF000000, isOpen ? 0xFFFF0000 : 0xFF383b42);
		}
		
		if(isOpen) {
			for(XdolfButton b : buttonList) {
				b.draw();
				if(x >= b.getX() + dragX && y >= b.getY() + dragY && x <= b.getX() + 96 + dragX && y <= b.getY() + 11 + dragY) {
					b.overButton = true;
				} else {
					b.overButton = false;
				}
			}
			for(XdolfSlider s : sliderList) {
				s.draw(x);
			} 
		}
	}
	
	public void mouseClicked(int x, int y, int button) throws IOException {
		for(XdolfButton b : buttonList) {
			b.mouseClicked(x, y, button);
		}
		for(XdolfSlider s : sliderList) {
			s.mouseClicked(x, y, button);
		}
		
		if(x >= getXAndDrag() && y >= getYAndDrag() && x <= getXAndDrag() + 80 && y <= getYAndDrag() + 11) {
			dragging = true;
			lastDragX = x - dragX;
			lastDragY = y - dragY;
		}
		if(x >= getXAndDrag() + 89 && y >= getYAndDrag() + 2 && x <= getXAndDrag() + 98 && y <= getYAndDrag() + 11) {
			isOpen = !isOpen;
		}
		if(x >= getXAndDrag() + 79 && y >= getYAndDrag() + 2 && x <= getXAndDrag() + 88 && y <= getYAndDrag() + 11) {
			isPinned = !isPinned;
		}
	}
	
	public void mouseReleased(int x, int y, int state) {
		for(XdolfSlider s : sliderList) {
			s.mouseReleased(x, y, state);
		}
		if(state == 0) {
			dragging = false;
		}
	}
	
	public void addButton(Module module) {
		buttonList.add(new XdolfButton(this, module, x + 2, y + 12 + (12 * buttonList.size())));
	}
	
	public XdolfSlider addSlider(Value value, float minValue, float maxValue, boolean shouldRound) {
		XdolfSlider slider = new XdolfSlider(this, value, getX() + 2, getY() + (19 * sliderList.size()) + 16, minValue, maxValue, shouldRound);
		sliderList.add(slider);
		
		return slider;
	}
	
	protected void loadButtonsFromCategory(Category category) {
		for(Module m : Hacks.hackList) {
			if(m.getCategory() == category) {
				addButton(m);
			}
		}
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getDragX() {
		return dragX;
	}
	
	public int getDragY() {
		return dragY;
	}
	
	public int getXAndDrag() {
		return x + dragX;
	}
	
	public int getYAndDrag() {
		return y + dragY;
	}
	
	public void setTitle(String s) {
		this.title = s;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isOpen() {
		return isOpen;
	}
	
	public boolean isPinned() {
		return isPinned;
	}
	
	public void setOpen(boolean b) {
		this.isOpen = b;
	}
	
	public void setPinned(boolean b) {
		this.isPinned = b;
	}
	
	public void setDragX(int x) {
		this.dragX = x;
	}
	
	public void setDragY(int y) {
		this.dragY = y;
	}
	
	public ArrayList<XdolfButton> getButtonList() {
		return buttonList;
	}
}
