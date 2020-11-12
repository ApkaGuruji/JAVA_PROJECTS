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
import apkaguruji.snakestarter.game.Game;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
/**
 *
 * @author vIiZz29
 */
public  class GameWindow extends JFrame
{
    LinkedList<Screen> screens;
    GameArea gameArea;
    GameMenu gameMenu;
    Screen activeScreen;
    public GameWindow()
    {
        setTitle("सांप रंगीला");
        setLocationRelativeTo(null);
        setVisible(true);

        setSize(new Dimension(Config.WINDOW_WIDTH,Config.WINDOW_HEIGHT));
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        setLocation(screenRect.width/2 - Config.WINDOW_WIDTH/2,screenRect.height/2 - Config.WINDOW_HEIGHT/2);

        
        gameArea = new GameArea(getWidth(),getHeight());
        screens = new LinkedList();
        screens.add(new Game(gameArea,this));
        screens.add(new ScoreScreen(gameArea,this));
        screens.add(new ImageScreen("इस गेम के बारे में जानना है","about.png",gameArea,this));
        screens.add(new ImageScreen("कैसे खेलूं?","help.png",gameArea,this));
        
        
        gameMenu = new GameMenu(gameArea, this,screens);
        activeScreen = gameMenu;
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                int code = ke.getKeyCode();
                if( code == KeyEvent.VK_ENTER) //ENTER
                {
                    if(activeScreen == gameMenu)
                    {
                        activeScreen.pause();
                        int selection = gameMenu.getSelection();
                        activeScreen = screens.get(selection);
                        activeScreen.startOrResume();
                    }
                }
                else if(code == KeyEvent.VK_ESCAPE)
                {
                    activeScreen.pause();
                    gameMenu.startOrResume();
                    activeScreen = gameMenu;
                }
                else if(activeScreen == gameMenu)
                {
                    gameMenu.keyPressed(code);
                }
                else
                {
                    activeScreen.keyPressed(code);
                }
                repaint();
            } 
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                activeScreen.stop();
                System.exit(0);
            }
        });
        repaint();
    }
    
    public void paint(Graphics g)
    {
        g.clearRect(0,0,getWidth(),getHeight());
        if(gameArea!=null)
            gameArea.draw(g);
        if(activeScreen != null)
            activeScreen.draw(g);
    }    
    
    public void home()
    {
        gameMenu.startOrResume();
        activeScreen = gameMenu;
    }
}