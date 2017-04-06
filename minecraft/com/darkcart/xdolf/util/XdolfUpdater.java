package com.darkcart.xdolf.util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.gui.XdolfUpdateGui;

public class XdolfUpdater {

	public static String getRemoteVersion() {
		Scanner s = null;
		try {
			s = new Scanner(new URL("https://darkcart.co/xdolf/version.txt").openStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return s.nextLine();
	}

	public static void downloadFile() {
		try {
			FileUtils.copyURLToFile(new URL("https://darkcart.co/xdolf/xdolf.zip"),
					new File(Wrapper.getAppDir("minecraft") + File.separator + "versions" + File.separator + "xdolf.zip"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			XdolfUpdateGui.downloadedUpdate = true;
			try {
				Desktop.getDesktop().open(new File(Wrapper.getAppDir("minecraft") + File.separator + "versions"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
