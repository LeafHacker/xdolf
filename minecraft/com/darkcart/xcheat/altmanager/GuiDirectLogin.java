package com.darkcart.xcheat.altmanager;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.Session;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.Client;

public class GuiDirectLogin extends GuiScreen
{
	public GuiScreen parent;
	public GuiTextField usernameBox;
	public GuiPasswordField passwordBox;
	
	public GuiDirectLogin(GuiScreen paramScreen)
	{
		this.parent = paramScreen;
	}
	
	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
		buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 96 + 12, "Login"));
		buttonList.add(new GuiButton(2, width / 2 - 100, height / 4 + 96 + 36, "Back"));
		usernameBox = new GuiTextField(0, this.fontRendererObj, width / 2 - 100, 76 - 25, 200, 20);
		passwordBox = new GuiPasswordField(2, this.fontRendererObj, width / 2 - 100, 116 - 25, 200, 20);
		usernameBox.setMaxStringLength(200);
		passwordBox.setMaxStringLength(200);
	}
	
	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
	}
	
	public void updateScreen()
	{
		usernameBox.updateCursorCounter();
		passwordBox.updateCursorCounter();
	}
	
	public void mouseClicked(int x, int y, int b) throws IOException
	{
		usernameBox.mouseClicked(x, y, b);
		passwordBox.mouseClicked(x, y, b);
		super.mouseClicked(x, y, b);
	}
	
	public void actionPerformed(GuiButton button)
	{
        if (button.id == 1) {
        	YggdrasilAuthenticator auth = new YggdrasilAuthenticator(usernameBox.getText(), passwordBox.getText());
            if (auth.login()) {
                Client.mc.session = auth.getSession();
            }
        	/*if (usernameBox.getText().length() > 0) {
        		if (passwordBox.getText().length() > 0) {
        			new Thread() {
        				@Override
        				public void run() {
        					String[] login = LoginUtils.loginToMinecraft(usernameBox.getText(), passwordBox.getText());
        					// If the return code was OK, we'll swap accounts.
        					if (login.length >= 5) {
        						AdolfWrapper.getMinecraft().session = new Session(login[2].trim(), login[4].trim(), login[3].trim());
        						Manager.altScreen.dispErrorString = "Sucessfully logged into: " + login[2].trim();
        						//prevScreen.setLastLoginStatus(EnumChatFormatting.GRAY + "Success" + EnumChatFormatting.RESET);
        					} else {
        						Manager.altScreen.dispErrorString = login[0];
        						//prevScreen.setLastLoginStatus(EnumChatFormatting.GRAY + login[0] + EnumChatFormatting.RESET);
        					}
        				}
        			}.start();
        		} else {
        			AdolfWrapper.getMinecraft().session = new Session(usernameBox.getText(), "hue", "hue");
        			Manager.altScreen.dispErrorString = "Changed name to: " + usernameBox.getText();
        			//prevScreen.setLastLoginStatus(EnumChatFormatting.GRAY + "Success" + EnumChatFormatting.RESET);
        		}
        	}*/
        } else if (button.id == 2) {
            Client.mc.displayGuiScreen(parent);
        }
        
		/*if(button.id == 1)
		{
			if(!usernameBox.getText().trim().isEmpty() && !passwordBox.getText().trim().isEmpty())
			{
				try
				{
					String post = (new StringBuilder("user=")).append(URLEncoder.encode(usernameBox.getText().trim(), "UTF-8")).append("&password=").append(URLEncoder.encode(passwordBox.getText().trim(), "UTF-8")).append("&version=").append(13).toString();
					String returnInf = Manager.excutePost("https://login.minecraft.net/", post);
					String[] parsedInfo = returnInf.split(":");
					AdolfWrapper.getMinecraft().session = new Session(parsedInfo[2].trim(), parsedInfo[4].trim(), parsedInfo[3].trim());
	                System.out.println(parsedInfo[2].trim() + " " + parsedInfo[4].trim() + " " + parsedInfo[3].trim());
					Manager.altScreen.dispErrorString = "";
				}catch(Exception error)
				{
					Manager.altScreen.dispErrorString = "".concat("\247cBad Login \2477(").concat(usernameBox.getText()).concat(")");
				}
			}else if(!usernameBox.getText().trim().isEmpty() && passwordBox.getText().trim().isEmpty())
			{
				AdolfWrapper.getMinecraft().session.username = usernameBox.getText().trim();
			}
            
			AdolfWrapper.getMinecraft().displayGuiScreen(parent);
		}else
		if(button.id == 2)
		{
			AdolfWrapper.getMinecraft().displayGuiScreen(parent);
		}*/
	}
	
	public static String excutePost(String s, String s1)
	{
		HttpsURLConnection httpsurlconnection = null;

		try
		{
			try
			{
				URL url = new URL(s);
				httpsurlconnection = (HttpsURLConnection)url.openConnection();
				httpsurlconnection.setRequestMethod("POST");
				httpsurlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				httpsurlconnection.setRequestProperty("Content-Type", Integer.toString(s1.getBytes().length));
				httpsurlconnection.setRequestProperty("Content-Language", "en-US");
				httpsurlconnection.setUseCaches(false);
				httpsurlconnection.setDoInput(true);
				httpsurlconnection.setDoOutput(true);
				httpsurlconnection.connect();
				DataOutputStream dataoutputstream = new DataOutputStream(httpsurlconnection.getOutputStream());
				dataoutputstream.writeBytes(s1);
				dataoutputstream.flush();
				dataoutputstream.close();
				InputStream inputstream = httpsurlconnection.getInputStream();
				BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
				StringBuffer stringbuffer = new StringBuffer();
				String s2;

				while ((s2 = bufferedreader.readLine()) != null)
				{
					stringbuffer.append(s2);
					stringbuffer.append('\r');
				}

				bufferedreader.close();
				String s3 = stringbuffer.toString();
				String s4 = s3;
				return s3;
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}

			return null;
		}finally 
		{
			if (httpsurlconnection != null)
			{
				httpsurlconnection.disconnect();
			}
		}
	}
	
	protected void keyTyped(char c, int i)
	{
		usernameBox.textboxKeyTyped(c, i);
		passwordBox.textboxKeyTyped(c, i);
		if(c == '\t')
		{
			if(usernameBox.isFocused())
			{
				usernameBox.setFocused(false);
				passwordBox.setFocused(true);
			}else
			if(passwordBox.isFocused())
			{
				usernameBox.setFocused(false);
				passwordBox.setFocused(false);
			}
		}
		if(c == '\r')
		{
			actionPerformed((GuiButton) buttonList.get(0));
		}
	}
	
	public void drawScreen(int x, int y, float f)
	{
		drawDefaultBackground();
		drawString(this.fontRendererObj, "Username", width / 2 - 100, 63 - 25, 0xA0A0A0);
		drawString(this.fontRendererObj, "\2474*", width / 2 - 106, 63 - 25, 0xA0A0A0);
		drawString(this.fontRendererObj, "Password", width / 2 - 100, 104 - 25, 0xA0A0A0);
		try{
		usernameBox.drawTextBox();
		passwordBox.drawTextBox();
		}catch(Exception err)
		{
			err.printStackTrace();
		}
		super.drawScreen(x, y, f);
	}
}
