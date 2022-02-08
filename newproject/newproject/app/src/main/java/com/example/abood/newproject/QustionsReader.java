package com.example.abood.newproject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QustionsReader {

    Second_Activity activity;


  public QustionsReader(Second_Activity activity){


      this.activity=activity;
}
   public List<Qustions> getQustions(String fileName) throws IOException {
     InputStream is = activity.getAssets().open(fileName);
        Scanner s = new Scanner(is);
        List<Qustions> qustions = new ArrayList<>();
        while (s.hasNextLine()){
            String qtext = s.nextLine();
            List<String> choises = new ArrayList<>();
            for (int i = 0 ;i<3 ;i ++ ){

                choises.add(s.nextLine());
            }
            String correctanswer=  s.nextLine();
            String photo =s.nextLine();

            Qustions  q = new Qustions();
            q.setQuastionText(qtext);
            q.setChoices(choises);
            q.setCorrectAnswer(correctanswer);
            q.setPhoto(photo);
            qustions.add(q);




        }

        return  qustions;



    }

}
