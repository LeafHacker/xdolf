package com.darkcart.xdolf;

import com.darkcart.xdolf.util.Category;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;

public class Module {

	private String name;
	private String description;
	public Object[] original;
	private int keyBind;

	private boolean isEnabled;
	private boolean isVisible;

	private Category category;
	
	public Module(String name, String description, Category category) {
		this.name = name;
		this.setDescription(description);
		this.isVisible = false;
		this.category = category;
		this.original = new Object[] { name };
		this.keyBind = 0;
		System.out.println("[Xdolf] " + name + " instantiated.");
	}

	public Module(String name, String description, int keyBind, Category category) {
		this.name = name;
		this.setDescription(description);
		this.keyBind = keyBind;
		this.isVisible = false;
		this.category = category;
		this.original = new Object[] { name };
		System.out.println("[Xdolf] " + name + " instantiated.");
	}

	public Module(String name, String description, int keyBind, int arrayColor, Category category) {
		this.name = name;
		this.setDescription(description);
		this.keyBind = keyBind;
		this.isVisible = true;
		this.category = category;
		this.original = new Object[] { name, arrayColor };
		System.out.println("[Xdolf] " + name + " instantiated.");
	}

	public void onEnable() {}
	public void onDisable() {}
	public void onToggled() {}
	public void beforeUpdate(EntityPlayerSP player) {}
	public void onUpdate(EntityPlayerSP player) {}
	public void afterUpdate(EntityPlayerSP player) {}
	public void runTick() {}
	public void onRender() {}
	
	public GuiScreen onDisplayGuiScreen(GuiScreen guiScreen) { 
		return guiScreen; 
	}

	public void onKeyPressed(int key) {
		if(key == keyBind) {
			toggle();
		}
	}

	public String getName() {
		return name;
	}

	public int getKey() {
		return keyBind;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public boolean getVisible() {
		return isVisible;
	}
	
	public Category getCategory() {
		return category;
	}

	public final void setState(boolean flag) {
		isEnabled = flag;
		if(isEnabled()) {
			onEnable();
			if(isVisible) {
				Wrapper.getHacks().display.add(this);
			}
		}else{
			onDisable();
			Wrapper.getHacks().display.remove(this);
		}
	}

	public void setName(String s) {
		name = s;
	}

	public void setKey(int i) {
		keyBind = i;
	}

	public final void toggle() {
		setState(!isEnabled);
		onToggled();
		Wrapper.getFileManager().saveHacks();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
