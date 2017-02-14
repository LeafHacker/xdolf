package com.darkcart.xcheat;

public abstract class Module {

	boolean toggled = false;

	public abstract void enable();

	public abstract void disable();

	public abstract void tick();

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

	public abstract int getKeyCode();
	
	public abstract String getDescription();

	public void render() {
		
	}
}
