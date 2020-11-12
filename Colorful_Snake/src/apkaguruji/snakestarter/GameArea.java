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


import apkaguruji.snakestarter.game.Config;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author vIiZz29
 */

public class GameArea {
    public int x,y,right,bottom,width,height;
    private Color color = Config.BACKGROUND_COLOR;
    private static final int padding=50;
    private Image background;
    public GameArea(int windowWidth, int windowHeight)
    {
        this.x = padding;
        this.y = padding;
        windowWidth -= padding*2;
        windowHeight -= padding*2;
        width = (windowWidth/Config.PIECE_WIDTH)*Config.PIECE_WIDTH;
        height = (windowHeight/Config.PIECE_WIDTH)*Config.PIECE_WIDTH;
        right = x + width;
        bottom = y + height;
        
        try {
            background = ImageIO.read(getClass().getResourceAsStream("/back.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    public void draw(Graphics g)
    {
        g.drawImage(background,x,y,null);
    }
    public Point getCenter()
    {
        return new Point(x+width/2,y+height/2);
    }
}
