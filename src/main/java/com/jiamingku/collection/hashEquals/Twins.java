package com.jiamingku.collection.hashEquals;

import java.awt.Color;

/**
 * 双胞兄弟
 */
public class Twins
{
    /**
     * 衣服颜色
     */
    private Color color;

    public Twins(Color color)
    {
        this.color = color;
    }

//    @Override
//    public boolean equals(Object o)
//    {
//        if (o == this)
//            return true;
//        if (!(o instanceof Twins))
//        {
//            return false;
//        }
//        Twins user = (Twins)o;
//        return color.equals(user.color);
//    }
//
//    @Override
//    public int hashCode()
//    {
//        int result = 17;
//        result = 31 * result + color.hashCode();
//        return result;
//    }

    public void setColor(Color color)
    {
        this.color = color;
    }
}