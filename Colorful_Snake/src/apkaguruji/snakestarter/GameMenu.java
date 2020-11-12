/*
 * Copyright (C) 2020 ApkaGuruji.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package apkaguruji.snakestarter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;

/**
 *
 * @author vIiZz29
 */
public class GameMenu extends Screen{
    private LinkedList<Screen> screens;
    private int choice;
    private Image logo;
    GameMenu(GameArea gameArea, GameWindow gameWindow, LinkedList<Screen> options)
    {
        super("Game Menu", gameArea, gameWindow);
        this.screens = options;
        choice=0;
        try {
            logo = ImageIO.read(getClass().getResourceAsStream("/logo.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics g) {
        Point center = gameArea.getCenter();
        g.drawImage(logo,center.x - logo.getWidth(null)/2, gameArea.y+logo.getHeight(null)/2,null);
        
        g.setFont(new Font("LucidaSans",Font.PLAIN,20));
        int maxTextWidth = 0;
        LinkedList<String> titles = new LinkedList();
        for(Screen screen:screens)
        {
            String title = screen.getTitle();
            titles.add(title);
            int width = g.getFontMetrics().stringWidth(title);
            if(width>maxTextWidth)
                maxTextWidth = width;
        }
        maxTextWidth += 200;
        
        int padding = 10;
        int textHeight = g.getFontMetrics().getHeight();
        int y=center.y - textHeight*(titles.size()/2);
        for(int i =0;i<titles.size();i++)
        {
            if(choice == i)
                g.setColor(Color.orange);
            else
                g.setColor(Color.blue);
            String title = titles.get(i);
            int x = center.x-g.getFontMetrics().stringWidth(title)/2;
            g.fillRoundRect(center.x - maxTextWidth/2,y-textHeight*3/4,maxTextWidth,textHeight,textHeight/4,textHeight/4);
            g.setColor(Color.black);
            g.drawString(title,x,y);
            y+=textHeight+padding;
        }

    }

    @Override
    public boolean keyPressed(int code) {
        switch(code)
        {
           case  KeyEvent.VK_UP:
                choice = (choice>0)?choice-1:screens.size()-1;
           break;
           case KeyEvent.VK_DOWN:
                choice = (choice<screens.size()-1)?choice+1:0;
           break;
        }
        gameWindow.repaint();
        return true;
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void startOrResume() {
        
    }

    @Override
    public void stop() {
        
    }
    
    int getSelection()
    {
        return choice;
    }
}
