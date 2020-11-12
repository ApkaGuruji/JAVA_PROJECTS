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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vIiZz29
 */
public class ScoreDatabase {
    public static final class ScoreRecord{
        String datetime;
        int score;
    }
    private String fileName="scores.txt";
    public ScoreDatabase()
    {
    }
    public HashMap<Integer, String> getScores()
    {
        HashMap<Integer,String> scores = new HashMap();
        try {
            Scanner sc = new Scanner(new FileInputStream(fileName));
            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                int ios = line.indexOf(' ');
                int score = Integer.parseInt(line.substring(0,ios));
                String datetime = line.substring(ios+1);
                scores.put(score, datetime);                
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            
        }
        return scores;
    }
    public void saveScores(HashMap<Integer,String> scores)
    {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(fileName,false));
            Set<Integer> keys = scores.keySet();
            for(Integer key: keys)
            {
                pw.write(key + " " + scores.get(key) + "\n");
            }
            pw.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public void addRecord(Integer score)
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("dd MMM h:m a");
        String dateTime = format1.format(cal.getTime());
        
        HashMap<Integer,String> scores = getScores();
        scores.put(score,dateTime);
        saveScores(scores);
    }
    
}
