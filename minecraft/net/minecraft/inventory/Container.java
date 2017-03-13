package net.minecraft.inventory;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;

public abstract class Container
{
    public NonNullList<ItemStack> inventoryItemStacks = NonNullList.<ItemStack>create();
    public List<Slot> inventorySlots = Lists.<Slot>newArrayList();
    public int windowId;
    private short transactionID;

    /**
     * The current drag mode (0 : evenly split, 1 : one item by slot, 2 : not used ?)
     */
    private int dragMode = -1;

    /** The current drag event (0 : start, 1 : add slot : 2 : end) */
    private int dragEvent;
    private final Set<Slot> dragSlots = Sets.<Slot>newHashSet();
    protected List<IContainerListener> listeners = Lists.<IContainerListener>newArrayList();
    private final Set<EntityPlayer> playerList = Sets.<EntityPlayer>newHashSet();

    /**
     * Adds an item slot to this container
     */
    protected Slot addSlotToContainer(Slot slotIn)
    {
        slotIn.slotNumber = this.inventorySlots.size();
        this.inventorySlots.add(slotIn);
        this.inventoryItemStacks.add(ItemStack.EMPTY);
        return slotIn;
    }

    public void addListener(IContainerListener listener)
    {
        if (this.listeners.contains(listener))
        {
            throw new IllegalArgumentException("Listener already listening");
        }
        else
        {
            this.listeners.add(listener);
            listener.updateCraftingInventory(this, this.getInventory());
            this.detectAndSendChanges();
        }
    }

    /**
     * Remove the given Listener. Method name is for legacy.
     */
    public void removeListener(IContainerListener listener)
    {
        this.listeners.remove(listener);
    }

    public NonNullList<ItemStack> getInventory()
    {
        NonNullList<ItemStack> nonnulllist = NonNullList.<ItemStack>create();

        for (int i = 0; i < this.inventorySlots.size(); ++i)
        {
            nonnulllist.add(((Slot)this.inventorySlots.get(i)).getStack());
        }

        return nonnulllist;
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        for (int i = 0; i < this.inventorySlots.size(); ++i)
        {
            ItemStack itemstack = ((Slot)this.inventorySlots.get(i)).getStack();
            ItemStack itemstack1 = (ItemStack)this.inventoryItemStacks.get(i);

            if (!ItemStack.areItemStacksEqual(itemstack1, itemstack))
            {
                itemstack1 = itemstack.isEmpty() ? ItemStack.EMPTY : itemstack.copy();
                this.inventoryItemStacks.set(i, itemstack1);

                for (int j = 0; j < this.listeners.size(); ++j)
                {
                    ((IContainerListener)this.listeners.get(j)).sendSlotContents(this, i, itemstack1);
                }
            }
        }
    }

    /**
     * Handles the given Button-click on the server, currently only used by enchanting. Name is for legacy.
     */
    public boolean enchantItem(EntityPlayer playerIn, int id)
    {
        return false;
    }

    @Nullable
    public Slot getSlotFromInventory(IInventory inv, int slotIn)
    {
        for (int i = 0; i < this.inventorySlots.size(); ++i)
        {
            Slot slot = (Slot)this.inventorySlots.get(i);

            if (slot.isHere(inv, slotIn))
            {
                return slot;
            }
        }

        return null;
    }

    public Slot getSlot(int slotId)
    {
        return (Slot)this.inventorySlots.get(slotId);
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        Slot slot = (Slot)this.inventorySlots.get(index);
        return slot != null ? slot.getStack() : ItemStack.EMPTY;
    }

    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        InventoryPlayer inventoryplayer = player.inventory;

        if (clickTypeIn == ClickType.QUICK_CRAFT)
        {
            int i = this.dragEvent;
            this.dragEvent = getDragEvent(dragType);

            if ((i != 1 || this.dragEvent != 2) && i != this.dragEvent)
            {
                this.resetDrag();
            }
            else if (inventoryplayer.getItemStack().isEmpty())
            {
                this.resetDrag();
            }
            else if (this.dragEvent == 0)
            {
                this.dragMode = extractDragMode(dragType);

                if (isValidDragMode(this.dragMode, player))
                {
                    this.dragEvent = 1;
                    this.dragSlots.clear();
                }
                else
                {
                    this.resetDrag();
                }
            }
            else if (this.dragEvent == 1)
            {
                Slot slot = (Slot)this.inventorySlots.get(slotId);
                ItemStack itemstack1 = inventoryplayer.getItemStack();

                if (slot != null && canAddItemToSlot(slot, itemstack1, true) && slot.isItemValid(itemstack1) && (this.dragMode == 2 || itemstack1.getCount() > this.dragSlots.size()) && this.canDragIntoSlot(slot))
                {
                    this.dragSlots.add(slot);
                }
            }
            else if (this.dragEvent == 2)
            {
                if (!this.dragSlots.isEmpty())
                {
                    ItemStack itemstack5 = inventoryplayer.getItemStack().copy();
                    int l = inventoryplayer.getItemStack().getCount();

                    for (Slot slot1 : this.dragSlots)
                    {
                        ItemStack itemstack2 = inventoryplayer.getItemStack();

                        if (slot1 != null && canAddItemToSlot(slot1, itemstack2, true) && slot1.isItemValid(itemstack2) && (this.dragMode == 2 || itemstack2.getCount() >= this.dragSlots.size()) && this.canDragIntoSlot(slot1))
                        {
                            ItemStack itemstack3 = itemstack5.copy();
                            int j = slot1.getHasStack() ? slot1.getStack().getCount() : 0;
                            computeStackSize(this.dragSlots, this.dragMode, itemstack3, j);
                            int k = Math.min(itemstack3.getMaxStackSize(), slot1.getItemStackLimit(itemstack3));

                            if (itemstack3.getCount() > k)
                            {
                                itemstack3.setCount(k);
                            }

                            l -= itemstack3.getCount() - j;
                            slot1.putStack(itemstack3);
                        }
                    }

                    itemstack5.setCount(l);
                    inventoryplayer.setItemStack(itemstack5);
                }

                this.resetDrag();
            }
            else
            {
                this.resetDrag();
            }
        }
        else if (this.dragEvent != 0)
        {
            this.resetDrag();
        }
        else if ((clickTypeIn == ClickType.PICKUP || clickTypeIn == ClickType.QUICK_MOVE) && (dragType == 0 || dragType == 1))
        {
            if (slotId == -999)
            {
                if (!inventoryplayer.getItemStack().isEmpty())
                {
                    if (dragType == 0)
                    {
                        player.dropItem(inventoryplayer.getItemStack(), true);
                        inventoryplayer.setItemStack(ItemStack.EMPTY);
                    }

                    if (dragType == 1)
                    {
                        player.dropItem(inventoryplayer.getItemStack().splitStack(1), true);
                    }
                }
            }
            else if (clickTypeIn == ClickType.QUICK_MOVE)
            {
                if (slotId < 0)
                {
                    return ItemStack.EMPTY;
                }

                Slot slot6 = (Slot)this.inventorySlots.get(slotId);

                if (slot6 != null && slot6.canTakeStack(player))
                {
                    ItemStack itemstack10 = this.transferStackInSlot(player, slotId);

                    if (!itemstack10.isEmpty())
                    {
                        Item item = itemstack10.getItem();
                        itemstack = itemstack10.copy();

                        if (slot6.getStack().getItem() == item)
                        {
                            this.retrySlotClick(slotId, dragType, true, player);
                        }
                    }
                }
            }
            else
            {
                if (slotId < 0)
                {
                    return ItemStack.EMPTY;
                }

                Slot slot7 = (Slot)this.inventorySlots.get(slotId);

                if (slot7 != null)
                {
                    ItemStack itemstack11 = slot7.getStack();
                    ItemStack itemstack13 = inventoryplayer.getItemStack();

                    if (!itemstack11.isEmpty())
                    {
                        itemstack = itemstack11.copy();
                    }

                    if (itemstack11.isEmpty())
                    {
                        if (!itemstack13.isEmpty() && slot7.isItemValid(itemstack13))
                        {
                            int l2 = dragType == 0 ? itemstack13.getCount() : 1;

                            if (l2 > slot7.getItemStackLimit(itemstack13))
                            {
                                l2 = slot7.getItemStackLimit(itemstack13);
                            }

                            slot7.putStack(itemstack13.splitStack(l2));
                        }
                    }
                    else if (slot7.canTakeStack(player))
                    {
                        if (itemstack13.isEmpty())
                        {
                            if (itemstack11.isEmpty())
                            {
                                slot7.putStack(ItemStack.EMPTY);
                                inventoryplayer.setItemStack(ItemStack.EMPTY);
                            }
                            else
                            {
                                int k2 = dragType == 0 ? itemstack11.getCount() : (itemstack11.getCount() + 1) / 2;
                                inventoryplayer.setItemStack(slot7.decrStackSize(k2));

                                if (itemstack11.isEmpty())
                                {
                                    slot7.putStack(ItemStack.EMPTY);
                                }

                                slot7.onTake(player, inventoryplayer.getItemStack());
                            }
                        }
                        else if (slot7.isItemValid(itemstack13))
                        {
                            if (itemstack11.getItem() == itemstack13.getItem() && itemstack11.getMetadata() == itemstack13.getMetadata() && ItemStack.areItemStackTagsEqual(itemstack11, itemstack13))
                            {
                                int j2 = dragType == 0 ? itemstack13.getCount() : 1;

                                if (j2 > slot7.getItemStackLimit(itemstack13) - itemstack11.getCount())
                                {
                                    j2 = slot7.getItemStackLimit(itemstack13) - itemstack11.getCount();
                                }

                                if (j2 > itemstack13.getMaxStackSize() - itemstack11.getCount())
                                {
                                    j2 = itemstack13.getMaxStackSize() - itemstack11.getCount();
                                }

                                itemstack13.shrink(j2);
                                itemstack11.grow(j2);
                            }
                            else if (itemstack13.getCount() <= slot7.getItemStackLimit(itemstack13))
                            {
                                slot7.putStack(itemstack13);
                                inventoryplayer.setItemStack(itemstack11);
                            }
                        }
                        else if (itemstack11.getItem() == itemstack13.getItem() && itemstack13.getMaxStackSize() > 1 && (!itemstack11.getHasSubtypes() || itemstack11.getMetadata() == itemstack13.getMetadata()) && ItemStack.areItemStackTagsEqual(itemstack11, itemstack13) && !itemstack11.isEmpty())
                        {
                            int i2 = itemstack11.getCount();

                            if (i2 + itemstack13.getCount() <= itemstack13.getMaxStackSize())
                            {
                                itemstack13.grow(i2);
                                itemstack11 = slot7.decrStackSize(i2);

                                if (itemstack11.isEmpty())
                                {
                                    slot7.putStack(ItemStack.EMPTY);
                                }

                                slot7.onTake(player, inventoryplayer.getItemStack());
                            }
                        }
                    }

                    slot7.onSlotChanged();
                }
            }
        }
        else if (clickTypeIn == ClickType.SWAP && dragType >= 0 && dragType < 9)
        {
            Slot slot5 = (Slot)this.inventorySlots.get(slotId);
            ItemStack itemstack9 = inventoryplayer.getStackInSlot(dragType);
            ItemStack itemstack12 = slot5.getStack();

            if (!itemstack9.isEmpty() || !itemstack12.isEmpty())
            {
                if (itemstack9.isEmpty())
                {
                    if (slot5.canTakeStack(player))
                    {
                        inventoryplayer.setInventorySlotContents(dragType, itemstack12);
                        slot5.onSwapCraft(itemstack12.getCount());
                        slot5.putStack(ItemStack.EMPTY);
                        slot5.onTake(player, itemstack12);
                    }
                }
                else if (itemstack12.isEmpty())
                {
                    if (slot5.isItemValid(itemstack9))
                    {
                        int k1 = slot5.getItemStackLimit(itemstack9);

                        if (itemstack9.getCount() > k1)
                        {
                            slot5.putStack(itemstack9.splitStack(k1));
                        }
                        else
                        {
                            slot5.putStack(itemstack9);
                            inventoryplayer.setInventorySlotContents(dragType, ItemStack.EMPTY);
                        }
                    }
                }
                else if (slot5.canTakeStack(player) && slot5.isItemValid(itemstack9))
                {
                    int l1 = slot5.getItemStackLimit(itemstack9);

                    if (itemstack9.getCount() > l1)
                    {
                        slot5.putStack(itemstack9.splitStack(l1));
                        slot5.onTake(player, itemstack12);

                        if (!inventoryplayer.addItemStackToInventory(itemstack12))
                        {
                            player.dropItem(itemstack12, true);
                        }
                    }
                    else
                    {
                        slot5.putStack(itemstack9);
                        inventoryplayer.setInventorySlotContents(dragType, itemstack12);
                        slot5.onTake(player, itemstack12);
                    }
                }
            }
        }
        else if (clickTypeIn == ClickType.CLONE && player.capabilities.isCreativeMode && inventoryplayer.getItemStack().isEmpty() && slotId >= 0)
        {
            Slot slot4 = (Slot)this.inventorySlots.get(slotId);

            if (slot4 != null && slot4.getHasStack())
            {
                ItemStack itemstack8 = slot4.getStack().copy();
                itemstack8.setCount(itemstack8.getMaxStackSize());
                inventoryplayer.setItemStack(itemstack8);
            }
        }
        else if (clickTypeIn == ClickType.THROW && inventoryplayer.getItemStack().isEmpty() && slotId >= 0)
        {
            Slot slot3 = (Slot)this.inventorySlots.get(slotId);

            if (slot3 != null && slot3.getHasStack() && slot3.canTakeStack(player))
            {
                ItemStack itemstack7 = slot3.decrStackSize(dragType == 0 ? 1 : slot3.getStack().getCount());
                slot3.onTake(player, itemstack7);
                player.dropItem(itemstack7, true);
            }
        }
        else if (clickTypeIn == ClickType.PICKUP_ALL && slotId >= 0)
        {
            Slot slot2 = (Slot)this.inventorySlots.get(slotId);
            ItemStack itemstack6 = inventoryplayer.getItemStack();

            if (!itemstack6.isEmpty() && (slot2 == null || !slot2.getHasStack() || !slot2.canTakeStack(player)))
            {
                int i1 = dragType == 0 ? 0 : this.inventorySlots.size() - 1;
                int j1 = dragType == 0 ? 1 : -1;

                for (int i3 = 0; i3 < 2; ++i3)
                {
                    for (int j3 = i1; j3 >= 0 && j3 < this.inventorySlots.size() && itemstack6.getCount() < itemstack6.getMaxStackSize(); j3 += j1)
                    {
                        Slot slot8 = (Slot)this.inventorySlots.get(j3);

                        if (slot8.getHasStack() && canAddItemToSlot(slot8, itemstack6, true) && slot8.canTakeStack(player) && this.canMergeSlot(itemstack6, slot8))
                        {
                            ItemStack itemstack14 = slot8.getStack();

                            if (i3 != 0 || itemstack14.getCount() != itemstack14.getMaxStackSize())
                            {
                                int k3 = Math.min(itemstack6.getMaxStackSize() - itemstack6.getCount(), itemstack14.getCount());
                                ItemStack itemstack4 = slot8.decrStackSize(k3);
                                itemstack6.grow(k3);

                                if (itemstack4.isEmpty())
                                {
                                    slot8.putStack(ItemStack.EMPTY);
                                }

                                slot8.onTake(player, itemstack4);
                            }
                        }
                    }
                }
            }

            this.detectAndSendChanges();
        }

        return itemstack;
    }

    /**
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in
     * is null for the initial slot that was double-clicked.
     */
    public boolean canMergeSlot(ItemStack stack, Slot slotIn)
    {
        return true;
    }

    /**
     * Retries slotClick() in case of failure
     */
    protected void retrySlotClick(int slotId, int clickedButton, boolean mode, EntityPlayer playerIn)
    {
        this.slotClick(slotId, clickedButton, ClickType.QUICK_MOVE, playerIn);
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer playerIn)
    {
        InventoryPlayer inventoryplayer = playerIn.inventory;

        if (!inventoryplayer.getItemStack().isEmpty())
        {
            playerIn.dropItem(inventoryplayer.getItemStack(), false);
            inventoryplayer.setItemStack(ItemStack.EMPTY);
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(IInventory inventoryIn)
    {
        this.detectAndSendChanges();
    }

    /**
     * Puts an ItemStack in a slot.
     */
    public void putStackInSlot(int slotID, ItemStack stack)
    {
        this.getSlot(slotID).putStack(stack);
    }

    public void setAll(List<ItemStack> p_190896_1_)
    {
        for (int i = 0; i < p_190896_1_.size(); ++i)
        {
            this.getSlot(i).putStack((ItemStack)p_190896_1_.get(i));
        }
    }

    public void updateProgressBar(int id, int data)
    {
    }

    /**
     * Gets a unique transaction ID. Parameter is unused.
     */
    public short getNextTransactionID(InventoryPlayer invPlayer)
    {
        ++this.transactionID;
        return this.transactionID;
    }

    /**
     * gets whether or not the player can craft in this inventory or not
     */
    public boolean getCanCraft(EntityPlayer player)
    {
        return !this.playerList.contains(player);
    }

    /**
     * sets whether the player can craft in this inventory or not
     */
    public void setCanCraft(EntityPlayer player, boolean canCraft)
    {
        if (canCraft)
        {
            this.playerList.remove(player);
        }
        else
        {
            this.playerList.add(player);
        }
    }

    /**
     * Determines whether supplied player can use this container
     */
    public abstract boolean canInteractWith(EntityPlayer playerIn);

    /**
     * Merges provided ItemStack with the first avaliable one in the container/player inventor between minIndex
     * (included) and maxIndex (excluded). Args : stack, minIndex, maxIndex, negativDirection. /!\ the Container
     * implementation do not check if the item is valid for the slot
     */
    protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection)
    {
        boolean flag = false;
        int i = startIndex;

        if (reverseDirection)
        {
            i = endIndex - 1;
        }

        if (stack.isStackable())
        {
            while (!stack.isEmpty())
            {
                if (reverseDirection)
                {
                    if (i < startIndex)
                    {
                        break;
                    }
                }
                else if (i >= endIndex)
                {
                    break;
                }

                Slot slot = (Slot)this.inventorySlots.get(i);
                ItemStack itemstack = slot.getStack();

                if (!itemstack.isEmpty() && itemstack.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getMetadata() == itemstack.getMetadata()) && ItemStack.areItemStackTagsEqual(stack, itemstack))
                {
                    int j = itemstack.getCount() + stack.getCount();

                    if (j <= stack.getMaxStackSize())
                    {
                        stack.setCount(0);
                        itemstack.setCount(j);
                        slot.onSlotChanged();
                        flag = true;
                    }
                    else if (itemstack.getCount() < stack.getMaxStackSize())
                    {
                        stack.shrink(stack.getMaxStackSize() - itemstack.getCount());
                        itemstack.setCount(stack.getMaxStackSize());
                        slot.onSlotChanged();
                        flag = true;
                    }
                }

                if (reverseDirection)
                {
                    --i;
                }
                else
                {
                    ++i;
                }
            }
        }

        if (!stack.isEmpty())
        {
            if (reverseDirection)
            {
                i = endIndex - 1;
            }
            else
            {
                i = startIndex;
            }

            while (true)
            {
                if (reverseDirection)
                {
                    if (i < startIndex)
                    {
                        break;
                    }
                }
                else if (i >= endIndex)
                {
                    break;
                }

                Slot slot1 = (Slot)this.inventorySlots.get(i);
                ItemStack itemstack1 = slot1.getStack();

                if (itemstack1.isEmpty() && slot1.isItemValid(stack))
                {
                    if (stack.getCount() > slot1.getSlotStackLimit())
                    {
                        slot1.putStack(stack.splitStack(slot1.getSlotStackLimit()));
                    }
                    else
                    {
                        slot1.putStack(stack.splitStack(stack.getCount()));
                    }

                    slot1.onSlotChanged();
                    flag = true;
                    break;
                }

                if (reverseDirection)
                {
                    --i;
                }
                else
                {
                    ++i;
                }
            }
        }

        return flag;
    }

    /**
     * Extracts the drag mode. Args : eventButton. Return (0 : evenly split, 1 : one item by slot, 2 : not used ?)
     */
    public static int extractDragMode(int eventButton)
    {
        return eventButton >> 2 & 3;
    }

    /**
     * Args : clickedButton, Returns (0 : start drag, 1 : add slot, 2 : end drag)
     */
    public static int getDragEvent(int clickedButton)
    {
        return clickedButton & 3;
    }

    public static int getQuickcraftMask(int p_94534_0_, int p_94534_1_)
    {
        return p_94534_0_ & 3 | (p_94534_1_ & 3) << 2;
    }

    public static boolean isValidDragMode(int dragModeIn, EntityPlayer player)
    {
        return dragModeIn == 0 ? true : (dragModeIn == 1 ? true : dragModeIn == 2 && player.capabilities.isCreativeMode);
    }

    /**
     * Reset the drag fields
     */
    protected void resetDrag()
    {
        this.dragEvent = 0;
        this.dragSlots.clear();
    }

    /**
     * Checks if it's possible to add the given itemstack to the given slot.
     */
    public static boolean canAddItemToSlot(@Nullable Slot slotIn, ItemStack stack, boolean stackSizeMatters)
    {
        boolean flag = slotIn == null || !slotIn.getHasStack();
        return !flag && stack.isItemEqual(slotIn.getStack()) && ItemStack.areItemStackTagsEqual(slotIn.getStack(), stack) ? slotIn.getStack().getCount() + (stackSizeMatters ? 0 : stack.getCount()) <= stack.getMaxStackSize() : flag;
    }

    /**
     * Compute the new stack size, Returns the stack with the new size. Args : dragSlots, dragMode, dragStack,
     * slotStackSize
     */
    public static void computeStackSize(Set<Slot> dragSlotsIn, int dragModeIn, ItemStack stack, int slotStackSize)
    {
        switch (dragModeIn)
        {
            case 0:
                stack.setCount(MathHelper.floor((float)stack.getCount() / (float)dragSlotsIn.size()));
                break;

            case 1:
                stack.setCount(1);
                break;

            case 2:
                stack.setCount(stack.getItem().getItemStackLimit());
        }

        stack.grow(slotStackSize);
    }

    /**
     * Returns true if the player can "drag-spilt" items into this slot,. returns true by default. Called to check if
     * the slot can be added to a list of Slots to split the held ItemStack across.
     */
    public boolean canDragIntoSlot(Slot slotIn)
    {
        return true;
    }

    /**
     * Like the version that takes an inventory. If the given TileEntity is not an Inventory, 0 is returned instead.
     */
    public static int calcRedstone(@Nullable TileEntity te)
    {
        return te instanceof IInventory ? calcRedstoneFromInventory((IInventory)te) : 0;
    }

    public static int calcRedstoneFromInventory(@Nullable IInventory inv)
    {
        if (inv == null)
        {
            return 0;
        }
        else
        {
            int i = 0;
            float f = 0.0F;

            for (int j = 0; j < inv.getSizeInventory(); ++j)
            {
                ItemStack itemstack = inv.getStackInSlot(j);

                if (!itemstack.isEmpty())
                {
                    f += (float)itemstack.getCount() / (float)Math.min(inv.getInventoryStackLimit(), itemstack.getMaxStackSize());
                    ++i;
                }
            }

            f = f / (float)inv.getSizeInventory();
            return MathHelper.floor(f * 14.0F) + (i > 0 ? 1 : 0);
        }
    }
}
