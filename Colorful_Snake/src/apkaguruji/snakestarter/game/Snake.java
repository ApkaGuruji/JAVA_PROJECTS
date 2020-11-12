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
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author vIiZz29
 */
public class Snake {
    private GameArea gameArea;
    private LinkedList<SnakePiece> pieces;
    private int dx,dy;
    
    Snake(GameArea gameArea)
    {
        this.gameArea = gameArea;
        reset();
    }
    public void reset()
    {
        pieces = new LinkedList();
        int x=gameArea.x;
        int y=gameArea.y;
        for(int i=0;i<Config.MIN_SNAKE_PIECES;i++)
        {
            pieces.add(new SnakePiece(new Point(x,y)));
            x-=Config.PIECE_WIDTH;
        }
        dx = Config.PIECE_WIDTH;
        dy = 0;   
    }
    boolean isEatingSelf()
    {
        Iterator<SnakePiece> i = pieces.iterator();
        SnakePiece head = i.next();
        int x=1;
        while(i.hasNext())
        {
            SnakePiece piece = i.next();
            if(head.pos.x==piece.pos.x && head.pos.y == piece.pos.y) 
            {
                System.out.println(x);
                return true;
            }
            x++;
        }
        return false;
    }
    public boolean checkOverlap(Point foodPosition)
    {
        Iterator<SnakePiece> i = pieces.iterator();
        while(i.hasNext())
        {
            SnakePiece piece = i.next();
            if(foodPosition.x==piece.pos.x && foodPosition.y == piece.pos.y) 
            {
                return true;
            }
        }
        return false;
    }
    public boolean takeStep(Food food)
    {
        Point headPosition = new Point(pieces.get(0).pos);
        headPosition.x +=dx;
        headPosition.y +=dy;        
        
        if(headPosition.x<gameArea.x)
            headPosition.x=gameArea.right-Config.PIECE_WIDTH;
        if(headPosition.x>=gameArea.right)
            headPosition.x=gameArea.x;
        if(headPosition.y<gameArea.x)
            headPosition.y=gameArea.bottom-Config.PIECE_WIDTH;
        if(headPosition.y>=gameArea.bottom)
            headPosition.y=gameArea.y;
        
        Point foodPosition = food.pos;
        if(headPosition.x == foodPosition.x && headPosition.y == foodPosition.y)
        {
            pieces.push(new SnakePiece(foodPosition,food.color));
            return true;
        }
        else
        {
            int size = pieces.size();
            for(int j=size-1;j>0;j--)
            {
                pieces.get(j).setPos(pieces.get(j-1).pos);
            }
            pieces.get(0).setPos(headPosition);
            return false;
        }                
    }
    public void draw(Graphics g)
    {
        for(SnakePiece piece:pieces)
        {
            piece.draw(g);
        }
    }
    
    public boolean keyPressed(int code)
    {
        switch(code)
        {
            case 37://left
                if(dx==0)
                {
                    dx=-Config.PIECE_WIDTH;
                    dy=0;
                }
            break;
            case 38://up
                if(dy==0)
                {
                    dy=-Config.PIECE_WIDTH;
                    dx=0;
                }
            break;
            case 39://right
                if(dx==0)
                {
                    dx=Config.PIECE_WIDTH;
                    dy=0;
                }
            break;
            case 40://down
                if(dy==0)
                {
                    dx=0;
                    dy=Config.PIECE_WIDTH;
                }
            break;
            default:
                return false;
        }
        return true;
    }
    public int getSize()
    {
        return pieces.size();
    }
}
