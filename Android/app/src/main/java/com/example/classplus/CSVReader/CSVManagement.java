package com.example.classplus.CSVReader;

import android.util.Log;

import com.opencsv.CSVReader;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVManagement {

    ArrayList<String> getEmailList(String path)
    {

        ArrayList<String> emailList = new ArrayList<>();

        try{

            InputStreamReader is = new InputStreamReader(new FileInputStream(path), "UTF-8");
            CSVReader reader = new CSVReader(is);
            List<String[]> list = reader.readAll();

            int emailIndex = 0;
            for(int i=0; i<list.get(0).length; i++) {

                if(list.get(0)[i].equals("이메일") || list.get(0)[i].equals("email") )
                {
                    emailIndex = i;
                    break;
                }

            }

            list.remove(0);

            for(String[] str : list){

                emailList.add(str[emailIndex]);

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return emailList;
    }

}