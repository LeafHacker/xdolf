package net.minecraft.src;

import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.settings.GameSettings;

public class GuiOptionButtonOF extends GuiOptionButton implements IOptionControl
{
    private GameSettings.Options option = null;

    public GuiOptionButtonOF(int p_i43_1_, int p_i43_2_, int p_i43_3_, GameSettings.Options p_i43_4_, String p_i43_5_)
    {
        super(p_i43_1_, p_i43_2_, p_i43_3_, p_i43_4_, p_i43_5_);
        this.option = p_i43_4_;
    }

    public GameSettings.Options getOption()
    {
        return this.option;
    }
}
