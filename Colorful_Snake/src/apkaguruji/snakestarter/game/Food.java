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


import apkaguruji.snakestarter.GameArea;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author vIiZz29
 */
public class Food extends Piece{
    private GameArea gameArea;
    Food(GameArea gameArea)
    {
        super(new Point(0,0));
        this.gameArea = gameArea;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(pos.x+2,pos.y+2,width,width);
    }
    
    public void changePosition(Snake snake)
    {
        boolean collision=false;
        do
        {
            pos.x = gameArea.x + (int)(Math.random()*(gameArea.width/Config.PIECE_WIDTH))*Config.PIECE_WIDTH;
            pos.y = gameArea.y + (int)(Math.random()*(gameArea.height/Config.PIECE_WIDTH))*Config.PIECE_WIDTH;
            collision = snake.checkOverlap(pos);
        }while(collision);
        chooseRandomColor();
    }
    
}
