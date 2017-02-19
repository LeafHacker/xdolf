package com.darkcart.xcheat.altmanager;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

import com.darkcart.xcheat.Wrapper;
import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import net.minecraft.util.Session;


public class Manager
{
	public static ArrayList<Alt> altList = new ArrayList<Alt>();
	public static GuiAltList altScreen = new GuiAltList();
	public static final int slotHeight = 25;
	public static File adolfDir = new File(Wrapper.getMinecraftDir() + File.separator + "Adolf");

	public static void addAlt(Alt paramAlt)
	{
		altList.add(paramAlt);
	}
 
	public static void addAlt(String username)
	{
		altList.add(new Alt(username));
	}

	public static void addAlt(String username, String password)
	{
		altList.add(new Alt(username, password));
	}

	public static String makePassChar(String regex)
	{
		return regex.replaceAll("(?s).", "*");
	}

	public static void saveAlts()
	{
		try
		{
			File file = new File(adolfDir.getAbsolutePath(), "alts.txt");
			//File file = new File(AdolfWrapper.getMinecraftDir().toString().concat(File.separator).concat("Adolf").concat(File.separator).concat("alts.txt"));
			PrintWriter writer = new PrintWriter(new FileWriter(file));
			for(Alt alt: altList)
			{
				writer.println(alt.getFileLine());
			}
			writer.close();
		}catch(Exception error)
		{
			error.printStackTrace();
		}
	}

	public static ArrayList<Alt> getList()
	{
		return altList;
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

				while ((s2 = bufferedreader.readLine()) != null) {
					stringbuffer.append(s2);
					stringbuffer.append('\r');
				}

				bufferedreader.close();
				String s3 = stringbuffer.toString();
				String s4 = s3;
				return s3;
			}catch (Exception exception){
				exception.printStackTrace();
			}

			return null;
		}finally {
			if (httpsurlconnection != null) {
				httpsurlconnection.disconnect();
			}
		}
	}

	public static void loadAlts()
	{
		try
		{
			File file = new File(adolfDir.getAbsolutePath(), "alts.txt");
			//File file = new File(Adolf.getAppDir("minecraft").toString().concat(File.separator).concat("Adolf").concat(File.separator).concat("alts.txt"));
			if(file.exists() && file.canRead())
			{
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
				String rline = "";
				while((rline = bufferedReader.readLine()) != null)
				{
					String curLine = rline;
					try
					{
						if(curLine.contains(":") && !curLine.trim().endsWith(":"))
						{
							String[] altInfo = curLine.split(":");
							Alt theAlt = new Alt(altInfo[0], altInfo[1]);
							if(!altList.contains(theAlt))
							{
								altList.add(theAlt);
							}
						}else
							if(!curLine.isEmpty() && curLine != null && !curLine.trim().isEmpty())
							{
								Alt theAlt = new Alt(curLine.replace(":", "").trim());
								if(!altList.contains(theAlt))
								{
									altList.add(theAlt);
								}
							}
					}catch(Exception error)
					{
						error.printStackTrace();
					}
				}
				bufferedReader.close();
			}
		}catch(Exception error)
		{
			error.printStackTrace();
		}
	}
	
	public static String getResponse(String userpass) {        
        UUID token = UUID.randomUUID();

        String json ="{\n \"agent\":{\n \"name\": \"Minecraft\",\n \"version\": \"1\"\n },\n \"username\": \""+userpass.split(":")[0]+"\",\n \"password\": \""+userpass.split(":")[1]+"\",\n \"clientToken\": \""+token.toString()+"\"\n}";
        
        return excutePost("https://authserver.mojang.com/authenticate", json);
    }
    
     public static String excutePost2(String targetURL, String urlParameters)
      {
        URL url;
        HttpURLConnection connection = null;  
        try {
          url = new URL(targetURL);
          connection = (HttpURLConnection)url.openConnection();
          connection.setRequestMethod("POST");
          connection.setRequestProperty("Content-Type", "application/json");
                
          connection.setRequestProperty("Content-Length", "" + 
                   Integer.toString(urlParameters.getBytes().length));
          connection.setRequestProperty("Content-Language", "en-US");  
                
          connection.setUseCaches (false);
          connection.setDoInput(true);
          connection.setDoOutput(true);

          DataOutputStream wr = new DataOutputStream (
                      connection.getOutputStream ());
          wr.writeBytes (urlParameters);
          wr.flush ();
          wr.close ();

          InputStream is = connection.getInputStream();
          BufferedReader rd = new BufferedReader(new InputStreamReader(is));
          String line;
          StringBuffer response = new StringBuffer(); 
          while((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
          }
          rd.close();
          return response.toString();

        } catch (Exception e) {
          return null;

        } finally {

          if(connection != null) {
            connection.disconnect(); 
          }
        }
      }
	public static Session loginPassword(String username, String password)
    {
        if(username == null || username.length() <= 0 || password == null || password.length() <= 0)
            return null;

        YggdrasilAuthenticationService a = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        YggdrasilUserAuthentication b = (YggdrasilUserAuthentication)a.createUserAuthentication(Agent.MINECRAFT);
        b.setUsername(username);
        b.setPassword(password);
        try
        {
            b.logIn();
            return new Session(b.getSelectedProfile().getName(), b.getSelectedProfile().getId().toString(), b.getAuthenticatedToken(), "LEGACY");
        } catch (AuthenticationException e)
        {
        	altScreen.dispErrorString = "".concat("\247cBad Login \2477(").concat(username).concat(")");
            e.printStackTrace();
        }
        return null;
    } 
}
