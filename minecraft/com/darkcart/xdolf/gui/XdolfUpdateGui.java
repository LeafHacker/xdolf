package com.darkcart.xdolf.gui;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.util.RenderUtils;
import com.darkcart.xdolf.util.XdolfUpdater;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class XdolfUpdateGui extends GuiScreen
{
	private GuiScreen parentScreen;
	public boolean downloadedUpdate;
	private static boolean notWindows = true;
	public XdolfUpdater updater;
	public boolean isUpdating;
	
	public GuiButton backButton;

	public XdolfUpdateGui(GuiScreen screen)
	{
		parentScreen = screen;
	}

	public void updateScreen()
	{
		((GuiButton)this.buttonList.get(1)).enabled = !(isUpdating || downloadedUpdate);
	}

	public void initGui()
	{
		this.buttonList.add(backButton = new GuiButton(1, width / 2 - 100, height / 6 + 168, "Back"));
		this.buttonList.add(new GuiButton(2, width / 2 - 100, height / 6 + 145, "Update"));
	}

	protected void actionPerformed(GuiButton button) throws IOException {
		if(button.id == 1)
		{
			Wrapper.getMinecraft().displayGuiScreen(parentScreen);
		}
		if(button.id == 2)
		{
			updater = new XdolfUpdater();
			updater.start();
		}
	}

	public void drawGradientRoundedRect(int x, int y, int x1, int y1, int borderC, int insideC, int insideC1)
	{
    	x *= 2; x1 *= 2; y *= 2; y1 *= 2;
    	GL11.glScalef(0.5F, 0.5F, 0.5F);
        drawVerticalLine(x, y + 1, y1 -2, borderC);
        drawVerticalLine(x1 - 1, y + 1, y1 - 2, borderC);
        drawHorizontalLine(x + 2, x1 - 3, y, borderC);
        drawHorizontalLine(x + 2, x1 - 3, y1 -1, borderC);
        drawHorizontalLine(x + 1, x + 1, y + 1, borderC);
        drawHorizontalLine(x1 - 2, x1 - 2, y + 1, borderC);
        drawHorizontalLine(x1 - 2, x1 - 2, y1 - 2, borderC);
        drawHorizontalLine(x + 1, x + 1, y1 - 2, borderC);
        drawGradientRect(x + 1, y + 1, x1 - 1, y1 - 1, insideC, insideC1);
        GL11.glScalef(2.0F, 2.0F, 2.0F);
	}

	public void drawScreen(int i, int j, float k)
	{
		drawDefaultBackground();
		drawCenteredString(fontRendererObj, "Update Xdolf", width / 2, 15, 0xFFFFFF);
		drawCenteredString(fontRendererObj, "There is a new update avaliable for Xdolf!", width / 2, 46, 0xFFFFFF);
		drawCenteredString(fontRendererObj, "Click \"Update\" to download the new update.", width / 2, 56, 0xFFFFFF);

		if(downloadedUpdate)
		{
			drawCenteredString(fontRendererObj, "\247aUpdate complete! \247fPlease open the READ_ME in\2472Xdolf.rar", width / 2 + 10, 96, 0xFFFFFF);
		}

		if(isUpdating)
		{
			int dispNumber = (int) updater.percentDone;
			if(((int) updater.percentDone) < 60) dispNumber = (int) updater.percentDone - 55;
			if(((int) updater.percentDone) > 60) dispNumber = (int) updater.percentDone;

			int xIndent = 50;
			int yIndent = 18;
			RenderUtils.drawRoundedRect(((width / 2) - 100) + xIndent, (106 - 10) + yIndent, (width /2) + xIndent, 106 + yIndent, 0xFF000000, 0xFF000000);
			RenderUtils.drawRoundedRect(((width / 2) - 100) + xIndent, (106 - 10) + yIndent, ((width /2) - (100 - dispNumber)) + xIndent, 106 + yIndent, 0xFF00FF00, 0xFF009900);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			drawCenteredString(fontRendererObj, ((int) updater.percentDone) + "%", (width / 2) * 2, ((106 + yIndent) - 7) * 2, 0xFFFFFF);
			GL11.glScalef(2F, 2F, 2F);

			System.out.println((updater.percentDone));
		}
		
		
		backButton.enabled  = downloadedUpdate;

		super.drawScreen(i, j, k);
	}

	public static void downloadUpdate()
	{
		try
		{
			BufferedInputStream in = new BufferedInputStream(new URL
					("http://darkcart.co/2F4U_latest_of_29_march.rar").openStream()); //TODO: insert dl link
			OutputStream out = new FileOutputStream(Wrapper.getMinecraftDir() + "/version/Xdolf.rar");

			int count;
			byte data[] = new byte[1024];

			while ((count = in.read(data)) != -1)
				out.write(data, 0, count);

			out.flush();
			out.close();
			in.close();
		}catch (Exception e) {}

		String s = System.getProperty("os.name").toLowerCase();
		String os = "unknown";
		if (s.contains("win"))
		{
			os = "win";
		}
		if (s.contains("mac"))
		{
			os = "mac";
		}
		if (s.contains("linux"))
		{
			os = "linux";
		}
		if (s.contains("unix"))
		{
			os = "linux";
		}
		if(os == "win")
		{
			Sys.openURL((new StringBuilder()).append("file://").append(Wrapper.getMinecraftDir().getAbsolutePath()).append("/bin/").toString());
		}
		if(os == "mac" || os == "linux")
		{
			notWindows = true;
		}
	}
}
