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
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author vIiZz29
 */
public class ImageScreen extends Screen{
    private Image image;
    ImageScreen(String title, String imageName, GameArea gameArea, GameWindow gameWindow)
    {
        super(title,gameArea,gameWindow);
        
        try {
            image = ImageIO.read(gameWindow.getClass().getResourceAsStream("/"+imageName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    @Override
    public void draw(Graphics g) {
        Point center = gameArea.getCenter();
        g.drawImage(image,center.x - image.getWidth(null)/2, center.y - image.getHeight(null)/2, null);
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void startOrResume() {
       
    }

    
}
