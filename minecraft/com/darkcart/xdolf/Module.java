package com.darkcart.xdolf;

public abstract class Module {

	boolean toggled = false;

	public void enable() {
	}

	public void disable() {
	}

	public void tick() {
	}
	
	public void beforeUpdate() {
	}
	
	public void afterUpdate() {
	}

	public void toggle() {
		toggled = !toggled;
		if (toggled) {
			enable();
		} else {
			disable();
		}
	}

	public boolean isToggled() {
		return toggled;
	}

	public int getKeyCode() {
		return 0;
	}
	
	public abstract String getName();
	
	public abstract String getDescription();

	public void render() {
		
	}
}
