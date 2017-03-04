package com.darkcart.xcheat;

public class Module {

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
	
	public String getName() {
		return null;
	}
	
	public String getDescription() {
		return null;
	}

	public void render() {
		
	}
}
