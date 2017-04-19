package com.darkcart.xdolf.mods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.mods.aura.*;
import com.darkcart.xdolf.mods.player.*;
import com.darkcart.xdolf.mods.render.*;
import com.darkcart.xdolf.mods.world.*;

public class Hacks
{
	public static ArrayList<Module> display = new ArrayList<Module>();
	
	/** All mods stored in this list **/
	public static List<Module> hackList = Arrays.asList(new Module[] {
			new Fullbright(),
			new Tracers(),
			new StorageESP(),
			new EntityESP(),
			new NoHurtCam(),
			new AntiVelocity(),
			new Flight(),
			new Spammer(),
			new Timer(),
			new NoPumpkinBlur(),
			new XRay(),
			new KillAura(),
			new AutoRespawn(),
			new AutoArmor(),
			new AutoWalk(),
			new Chams(),
			new AuraMob(),
			new AuraPlayer(),
			new GUI(),
			new AntiTotemAnimation(),
			new SafeWalk(),
			new AutoLog(),
			new ItemESP(),
			new NoSlowdown(),
			new Breadcrumb(),
			new Waypoints(),
			new FastPlace(),
			new AutoTotem(),
			new HorseJump(),
			new Sprint(),
			new Trajectories(),
			new HitSpheres(),
			new CrystalAura(),
			new Freecam(),
			new PlayerESP(),
			new Nametags(),
			new Criticals(),
			new NoFall(),
			new CrystalLog(),
			new AutoEat(),
			new Jesus(),
	});

	public static Module[] getEnabledHacks()
	{
		ArrayList<Module> enabledMods = new ArrayList<Module>();
		for(Module mod: hackList)
		{
			if(mod.isEnabled()) 
			{
				enabledMods.add(mod);
			}
		}
		
		return enabledMods.toArray(new Module[enabledMods.size()]);
	}
	
	public static Module getModByClassName(String name)
	{
		for(Module mod: hackList) 
		{
			if(mod.getClass().getSimpleName().toLowerCase().trim().equals(name.toLowerCase().trim()))
			{
				return mod;
			}
		}
		
		return null;
	}
	
	public static Module getModByName(String name) 
	{
		for(Module mod: hackList)
		{
			if(mod.getName().trim().equalsIgnoreCase(name.trim()) || mod.toString().trim().equalsIgnoreCase(name.trim())) 
			{
				return mod;
			}
		}
		
		return null;
	}
	
	public static Module findMod(Class<?extends Module> clazz) 
	{
		for(Module mod: hackList)
		{
			if(mod.getClass() == clazz)
			{
				return mod;
			}
		}
		
		return null;
	}
	
	public static Module findMod(String name)
	{
		Module mod = getModByName(name);
		if(mod != null) 
		{
			return mod;
		}
		mod = getModByClassName(name);
		
		return mod;
	}
}