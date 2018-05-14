import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

import java.awt.Color;
import java.awt.Graphics;

/**
 * Counter that displays a number with a text prefix.
 * 
 * @author Michael Kolling, bearbeitet von M.M.
 * @version 1.0.3
 */
public class Counter extends Actor
{
    private static final Color textColor = new Color(0,0,0);
    
    private int value = 0;
    private String text;
    private int stringLength;

    public Counter()
    {
        this("");
    }

    public Counter(String prefix)
    {
        text = prefix;
        stringLength = (text.length() + 2) * 10;

        setImage(new GreenfootImage(stringLength, 16));
        GreenfootImage image = getImage();
        image.setColor(textColor);
        updateImage();
    }
    
    public void add(int score)
    {
        value = value + score;
        updateImage();
    }

    public void subtract(int score)
    {
        value = value - score;
        updateImage();
    }

    public int getValue()
    {
        return value;
    }

    /**
     * Make the image
     */
    private void updateImage()
    {
        GreenfootImage image = getImage();
        image.clear();
        image.drawString(text + value, 1, 12);
    }
}