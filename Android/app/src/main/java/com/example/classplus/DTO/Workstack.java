package com.example.classplus.DTO;

import com.example.classplus.R;

public class Workstack {
    private String userEmai;
    private String userName;
    private String title;
    private String time;
    private String description;
    private int userImg;

    public Workstack(){};

    public Workstack(String userEmai,String userName, String message,String description, String time, int userImg) {
        this.userEmai = userEmai;
        this.userName = userName;
        this.title = message;
        this.time = time;
        this.description = description;
        this.userImg = getImageNum(userImg);
    }

    public String getUserEmai() {
        return userEmai;
    }

    public void setUserEmai(String userEmai) {
        this.userEmai = userEmai;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String message) {
        this.title = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUserImg() {
        return userImg;
    }

    public void setUserImg(int userImg) {
        this.userImg = userImg;
    }

    private int getImageNum(int img){

        img = (int) (img % 6);

        switch (img){
            case 0 :
                return R.drawable.study1;
            case 1 :
                return R.drawable.study2;
            case 2 :
                return R.drawable.study3;
            case 3 :
                return R.drawable.study4;
            case 4 :
                return R.drawable.study5;
            case 5 :
                return R.drawable.study6;
        }
        return 0;
    }
}
