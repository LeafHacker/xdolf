package net.minecraft.src;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectorRaw
{
    public static Field getField(Class p_getField_0_, Class p_getField_1_)
    {
        try
        {
            Field[] afield = p_getField_0_.getDeclaredFields();

            for (int i = 0; i < afield.length; ++i)
            {
                Field field = afield[i];

                if (field.getType() == p_getField_1_)
                {
                    field.setAccessible(true);
                    return field;
                }
            }

            return null;
        }
        catch (Exception var5)
        {
            return null;
        }
    }

    public static Field[] getFields(Class p_getFields_0_, Class p_getFields_1_)
    {
        List list = new ArrayList();

        try
        {
            Field[] afield = p_getFields_0_.getDeclaredFields();

            for (int i = 0; i < afield.length; ++i)
            {
                Field field = afield[i];

                if (field.getType() == p_getFields_1_)
                {
                    field.setAccessible(true);
                    list.add(field);
                }
            }

            Field[] afield1 = (Field[])((Field[])list.toArray(new Field[list.size()]));
            return afield1;
        }
        catch (Exception var6)
        {
            return null;
        }
    }

    public static Field getField(Class p_getField_0_, Class p_getField_1_, int p_getField_2_)
    {
        Field[] afield = getFields(p_getField_0_, p_getField_1_);
        return p_getField_2_ >= 0 && p_getField_2_ < afield.length ? afield[p_getField_2_] : null;
    }

    public static Object getFieldValue(Object p_getFieldValue_0_, Class p_getFieldValue_1_, Class p_getFieldValue_2_)
    {
        ReflectorField reflectorfield = getReflectorField(p_getFieldValue_1_, p_getFieldValue_2_);
        return reflectorfield == null ? null : (!reflectorfield.exists() ? null : Reflector.getFieldValue(p_getFieldValue_0_, reflectorfield));
    }

    public static Object getFieldValue(Object p_getFieldValue_0_, Class p_getFieldValue_1_, Class p_getFieldValue_2_, int p_getFieldValue_3_)
    {
        ReflectorField reflectorfield = getReflectorField(p_getFieldValue_1_, p_getFieldValue_2_, p_getFieldValue_3_);
        return reflectorfield == null ? null : (!reflectorfield.exists() ? null : Reflector.getFieldValue(p_getFieldValue_0_, reflectorfield));
    }

    public static boolean setFieldValue(Object p_setFieldValue_0_, Class p_setFieldValue_1_, Class p_setFieldValue_2_, Object p_setFieldValue_3_)
    {
        ReflectorField reflectorfield = getReflectorField(p_setFieldValue_1_, p_setFieldValue_2_);
        return reflectorfield == null ? false : (!reflectorfield.exists() ? false : Reflector.setFieldValue(p_setFieldValue_0_, reflectorfield, p_setFieldValue_3_));
    }

    public static boolean setFieldValue(Object p_setFieldValue_0_, Class p_setFieldValue_1_, Class p_setFieldValue_2_, int p_setFieldValue_3_, Object p_setFieldValue_4_)
    {
        ReflectorField reflectorfield = getReflectorField(p_setFieldValue_1_, p_setFieldValue_2_, p_setFieldValue_3_);
        return reflectorfield == null ? false : (!reflectorfield.exists() ? false : Reflector.setFieldValue(p_setFieldValue_0_, reflectorfield, p_setFieldValue_4_));
    }

    public static ReflectorField getReflectorField(Class p_getReflectorField_0_, Class p_getReflectorField_1_)
    {
        Field field = getField(p_getReflectorField_0_, p_getReflectorField_1_);

        if (field == null)
        {
            return null;
        }
        else
        {
            ReflectorClass reflectorclass = new ReflectorClass(p_getReflectorField_0_);
            return new ReflectorField(reflectorclass, field.getName());
        }
    }

    public static ReflectorField getReflectorField(Class p_getReflectorField_0_, Class p_getReflectorField_1_, int p_getReflectorField_2_)
    {
        Field field = getField(p_getReflectorField_0_, p_getReflectorField_1_, p_getReflectorField_2_);

        if (field == null)
        {
            return null;
        }
        else
        {
            ReflectorClass reflectorclass = new ReflectorClass(p_getReflectorField_0_);
            return new ReflectorField(reflectorclass, field.getName());
        }
    }
}
