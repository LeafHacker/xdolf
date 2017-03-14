package com.darkcart.xdolf.clickgui;

import java.util.ArrayList;

import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.clickgui.elements.XdolfWindow;
import com.darkcart.xdolf.clickgui.windows.WindowInfo;
import com.darkcart.xdolf.clickgui.windows.WindowPlayer;
import com.darkcart.xdolf.clickgui.windows.WindowRadar;
import com.darkcart.xdolf.clickgui.windows.WindowRender;
import com.darkcart.xdolf.clickgui.windows.WindowValues;

import net.minecraft.client.gui.GuiScreen;

public class XdolfGuiClick extends GuiScreen
{
	public static ArrayList<XdolfWindow> windows = new ArrayList<XdolfWindow>();
	public static ArrayList<XdolfWindow> unFocusedWindows = new ArrayList<XdolfWindow>();
	
	public XdolfWindow values = new WindowValues().init();
	public XdolfWindow info = new WindowInfo();
	public XdolfWindow radar = new WindowRadar();
	public XdolfWindow player = new WindowPlayer().init();
	public XdolfWindow render = new WindowRender().init();
	
	public void initGui()
	{
		Wrapper.getFileManager().loadGuiSettings();
	}
	
	public void onGuiClosed()
	{
		Wrapper.getFileManager().saveGuiSettings();
	}
	
	public static void sendPanelToFront(XdolfWindow window)
	{
		if(windows.contains(window))
		{
			int panelIndex = windows.indexOf(window);
			windows.remove(panelIndex);
			windows.add(windows.size(), window);
		}
	}
	
	public static XdolfWindow getFocusedPanel()
	{
		return windows.get(windows.size() - 1);
	}
	
	public void drawScreen(int x, int y, float f)
	{
		for(XdolfWindow window: windows)
		{
			window.draw(x, y);
		}
	}
	
	public void mouseClicked(int x, int y, int button)
	{
		try
		{
			for(XdolfWindow window: windows)
			{
				window.mouseClicked(x, y, button);
			}
		}catch(Exception e) {}
	}
	
	public void mouseReleased(int x, int y, int button)
	{
		for(XdolfWindow window: windows)
		{
			window.mouseReleased(x, y, button);
		}
	}
	
	public boolean doesGuiPauseGame()
	{
		return false;
	}
}
