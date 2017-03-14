package com.darkcart.xdolf.mods.aura;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.util.Category;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;

public class AutoArmor extends Module {
	
	public AutoArmor() {
		super("AutoArmor", "Automatically equips armour detected in inventory.", Keyboard.KEYBOARD_SIZE, Category.AURA);
	}
	
	private int timer;
	private final Item NULL_ITEM = Item.getItemFromBlock(Blocks.AIR);

	@Override
	public void onEnable() {
		timer = 0; 
	}

	@Override
	public void onUpdate(EntityPlayerSP player) {
		if(isEnabled()) {
			equip();
		}
	}
	
	private void equip() {
		// wait for timer
				if(timer > 0)
				{
					timer--;
					return;
				}
				
				// check screen
				if(Client.mc.currentScreen instanceof GuiContainer
					&& !(Client.mc.currentScreen instanceof InventoryEffectRenderer))
					return;
				
				// store slots and values of best armor pieces
				int[] bestArmorSlots = new int[4];
				int[] bestArmorValues = new int[4];
				
				// initialize with currently equipped armor
				for(int armorType = 0; armorType < 4; armorType++)
				{
					ItemStack oldArmor = Client.mc.player.inventory.armorItemInSlot(armorType);
					if(oldArmor != null && oldArmor.getItem() instanceof ItemArmor)
						bestArmorValues[armorType] =
							((ItemArmor)oldArmor.getItem()).damageReduceAmount;
					
					bestArmorSlots[armorType] = -1;
				}
				
				// search inventory for better armor
				for(int slot = 0; slot < 36; slot++)
				{
					ItemStack stack = Client.mc.player.inventory.getStackInSlot(slot);
					if(stack == null || !(stack.getItem() instanceof ItemArmor))
						continue;
					
					ItemArmor armor = (ItemArmor)stack.getItem();
					int armorType = this.getArmorType(armor);
					int armorValue = armor.damageReduceAmount;
					
					if(armorValue > bestArmorValues[armorType])
					{
						bestArmorSlots[armorType] = slot;
						bestArmorValues[armorType] = armorValue;
					}
				}
				
				// equip better armor
				for(int armorType = 0; armorType < 4; armorType++)
				{
					// check if better armor was found
					int slot = bestArmorSlots[armorType];
					if(slot == -1)
						continue;
						
					// check if armor can be swapped
					// needs 1 free slot where it can put the old armor
					ItemStack oldArmor = Client.mc.player.inventory.armorItemInSlot(armorType);
					if(oldArmor == null || !this.isEmptySlot(oldArmor)
						|| Client.mc.player.inventory.getFirstEmptyStack() != -1)
					{
						// hotbar fix
						if(slot < 9)
							slot += 36;
						
						// swap armor
						Client.mc.playerController.windowClick(0, 8 - armorType, 0,
							ClickType.QUICK_MOVE, Client.mc.player);
						Client.mc.playerController.windowClick(0, slot, 0,
							ClickType.QUICK_MOVE, Client.mc.player);
						
						break;
					}
				}
				
				// set timer
				timer = 2;
	}

	public int getArmorType(ItemArmor armor)
	{
		return armor.armorType.ordinal() - 2;
	}
	
	public boolean isEmptySlot(ItemStack slot)
	{
		return slot.getItem() == NULL_ITEM;
	}

	@Override
	public String getName() {
		return "AutoArmor";
	}

	@Override
	public String getDescription() {
		return "Automatically equips armour detected in inventory.";
	}


}
