package com.darkcart.xdolf.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.lwjgl.Sys;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Wrapper;

public class XdolfUpdater extends Thread implements Runnable
{
	public XdolfUpdater()
	{
		macDir = new File(Wrapper.getAppDir("minecraft") + File.separator + "versions");
	}

	private File macDir;
	private boolean notWindows = false;
	public double percentDone = 0.0F;

	public void run()
	{
		try
		{
			URL dlURL = (new URL
					("http://darkcart.co/2F4U_latest_of_29_march.rar"));
			URLConnection dlConnection = dlURL.openConnection();
			dlConnection.connect();
			double fileSize = dlConnection.getContentLength();
			BufferedInputStream in = new BufferedInputStream(dlURL.openStream());
			OutputStream out = new FileOutputStream(macDir + "Xdolf.rar");

			int count;
			byte data[] = new byte[1024];

			while ((count = in.read(data)) != -1)
			{
				Client.xu.isUpdating = true;
				out.write(data, 0, count);
				double newFileSize = (double)(new File(macDir + "/Xdolf.rar")).length();
				percentDone = ((newFileSize / fileSize) * 100);
			}

			out.flush();
			out.close();
			in.close();
		}catch (Exception e) {}

		Client.xu.downloadedUpdate = true;
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
			Sys.openURL((new StringBuilder()).append("file://").append(Wrapper.getMinecraftDir().getAbsolutePath()).append("/versions/").toString());
		}
		if(os == "mac" || os == "linux")
		{
			notWindows = true;
		}
	}
}
