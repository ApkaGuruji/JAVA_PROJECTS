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

import java.awt.Graphics;

/**
 *
 * @author vIiZz29
 */
public abstract class Screen {
    protected GameWindow gameWindow;
    protected String title;
    protected GameArea gameArea;
    public Screen(String title, GameArea gameArea, GameWindow gameWindow)
    {
        this.title = title;
        this.gameArea = gameArea;
        this.gameWindow = gameWindow;
    }
    public abstract void draw(Graphics g);
    public abstract void pause();
    public abstract void startOrResume();
    public boolean keyPressed(int code){return false;}
    public void stop(){}
    public String getTitle()
    {
        return title;
    }
    protected void goBack()
    {
        gameWindow.home();
    }
}
