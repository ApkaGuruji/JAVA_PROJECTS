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
import apkaguruji.snakestarter.GameWindow;
import apkaguruji.snakestarter.ScoreDatabase;
import apkaguruji.snakestarter.Screen;
import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vIiZz29
 */
public class Game extends Screen implements Runnable{
    Snake snake;
    Food food;    
    Thread gameThread;

    enum GameStatus {IDLE, RUNNING, PAUSED};
    GameStatus gameStatus;
    
    int score;
    int level=1;
    
    public Game(GameArea gameArea,GameWindow gameWindow)
    {
        super("",gameArea,gameWindow);
        initGame();
    }

    @Override
    public String getTitle() {
        if(gameStatus == GameStatus.PAUSED)
            return "खेल पे वापस चलो";
        else
            return "चलो शुरू करते हैं";
    }
    
    @Override
    public void pause() {
        gameStatus = GameStatus.PAUSED;
        
    }

    @Override
    public void startOrResume() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void stop() {
        gameStatus = GameStatus.IDLE;
        if(gameThread!=null && gameThread.isAlive())
            try {
                gameThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    public final void initGame()
    {
        snake = new Snake(gameArea);
        gameStatus = GameStatus.IDLE;
        food = new Food(gameArea);
        score = 0;
        level=1;
        food.changePosition(snake);
    }
    
    public void run()
    {
        gameStatus = GameStatus.RUNNING;
        try
        {
            while(gameStatus == GameStatus.RUNNING)
            {
                
                if(snake.isEatingSelf()) //Game Over
                {
                    ScoreDatabase scoreDatabase = new ScoreDatabase();
                    scoreDatabase.addRecord(score);
                    
                    initGame();
                    gameStatus = GameStatus.IDLE;
                    goBack();
                    break;
                }
                if(snake.takeStep(food))
                {
                    score++;
                    if(snake.getSize()==Config.MAX_SNAKE_PIECES + level*2)
                    {
                        level++;
                        snake.reset();
                    }
                    food.changePosition(snake);
                }
                
                
                
                gameWindow.repaint();
                Thread.sleep(512/(level*2));
            }
        }catch(Exception e)
        {
            
        }
        gameWindow.repaint();
        System.out.println("Game Ended");
    }
    
    @Override
    public void draw(Graphics g)
    {
        snake.draw(g);
        food.draw(g);
        g.setColor(Color.WHITE);
        String status = String.format("पायदान: %3d    अंक: %3d ",level,score);
        g.drawString(status,gameArea.right-g.getFontMetrics().stringWidth(status),gameArea.y+g.getFontMetrics().getHeight());
    }
    
    @Override
    public boolean keyPressed(int code) {
        return snake.keyPressed(code);
    }
   
   
}
