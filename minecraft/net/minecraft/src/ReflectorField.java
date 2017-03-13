package net.minecraft.src;

import java.lang.reflect.Field;

public class ReflectorField
{
    private ReflectorClass reflectorClass;
    private IFieldLocator fieldLocator;
    private boolean checked;
    private Field targetField;

    public ReflectorField(ReflectorClass p_i79_1_, String p_i79_2_)
    {
        this(p_i79_1_, (IFieldLocator)(new FieldLocatorName(p_i79_1_, p_i79_2_)));
    }

    public ReflectorField(ReflectorClass p_i80_1_, Class p_i80_2_)
    {
        this(p_i80_1_, p_i80_2_, 0);
    }

    public ReflectorField(ReflectorClass p_i81_1_, Class p_i81_2_, int p_i81_3_)
    {
        this(p_i81_1_, (IFieldLocator)(new FieldLocatorType(p_i81_1_, p_i81_2_, p_i81_3_)));
    }

    public ReflectorField(Field p_i82_1_)
    {
        this(new ReflectorClass(p_i82_1_.getDeclaringClass()), (IFieldLocator)(new FieldLocatorFixed(p_i82_1_)));
    }

    private ReflectorField(ReflectorClass p_i83_1_, IFieldLocator p_i83_2_)
    {
        this.reflectorClass = null;
        this.fieldLocator = null;
        this.checked = false;
        this.targetField = null;
        this.reflectorClass = p_i83_1_;
        this.fieldLocator = p_i83_2_;
        this.getTargetField();
    }

    public Field getTargetField()
    {
        if (this.checked)
        {
            return this.targetField;
        }
        else
        {
            this.checked = true;
            this.targetField = this.fieldLocator.getField();
            return this.targetField;
        }
    }

    public ReflectorClass getReflectorClass()
    {
        return this.reflectorClass;
    }

    public Object getValue()
    {
        return Reflector.getFieldValue((Object)null, this);
    }

    public void setValue(Object p_setValue_1_)
    {
        Reflector.setFieldValue((Object)null, this, p_setValue_1_);
    }

    public void setValue(Object p_setValue_1_, Object p_setValue_2_)
    {
        Reflector.setFieldValue(p_setValue_1_, this, p_setValue_2_);
    }

    public boolean exists()
    {
        return this.getTargetField() != null;
    }
}
