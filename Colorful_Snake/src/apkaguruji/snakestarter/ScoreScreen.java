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

import apkaguruji.snakestarter.Screen;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author vIiZz29
 */
public class ScoreScreen extends Screen{
    HashMap<Integer,String> scores;
    ScoreScreen(GameArea gameArea, GameWindow gameWindow)
    {
        super("अब तक का अंकपत्र",gameArea,gameWindow);
    }
    @Override
    public void draw(Graphics g) {
        Integer keys[] = new Integer[scores.keySet().size()];
        scores.keySet().toArray(keys);
        g.setFont(new Font("LucidaSans",Font.PLAIN,20));
        int maxTextWidth = 300;
        
        int padding = 5;
        int textHeight = g.getFontMetrics().getHeight();
        Point center = gameArea.getCenter();
        int y=center.y - textHeight*keys.length/2;
        for(int i =keys.length-1;i>=0;i--)
        {
            String title = String.format("%4d   |   %20s",keys[i],scores.get(keys[i]));
            //title = title.format("%30s",title);
            int x = center.x-g.getFontMetrics().stringWidth(title)/2;
            g.setColor(Color.WHITE);
            g.fillRoundRect(center.x - maxTextWidth/2,y-textHeight*3/4,maxTextWidth,textHeight,textHeight/4,textHeight/4);
            g.setColor(Color.BLACK);
            g.drawString(title,x,y);
            y+=textHeight+padding;
        }
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void startOrResume() {
       ScoreDatabase scoreDatabase = new ScoreDatabase();
       scores = scoreDatabase.getScores();
    }

    
}
