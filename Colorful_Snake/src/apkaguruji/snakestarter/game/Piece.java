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
package apkaguruji.snakestarter.game;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author vIiZz29
 */
public abstract class Piece {
    public static Color colors[]={Color.BLACK, Color.RED, Color.BLUE, Color.YELLOW, Color.WHITE};
    protected Point pos;
    protected Color color;
    protected final int width = Config.PIECE_WIDTH;
    
    Piece(Point pos)    
    {
        this.pos = pos;
        chooseRandomColor();
    }
    Piece(Point pos,Color color)
    {
        this.pos = new Point(pos.x, pos.y);
        this.color = color;
    }
    protected void chooseRandomColor()
    {
        color = colors[(int)(Math.random()*colors.length)];
    }
    public Point getPos()
    {
        return pos;
    }
    public void setPos(Point pos)
    {
        this.pos.x = pos.x;
        this.pos.y = pos.y;
    }
    public abstract void draw(Graphics g);
}
