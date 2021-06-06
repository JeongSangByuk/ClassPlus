package com.example.classplus.DTO;

import android.util.Log;

import com.example.classplus.R;

public class User {

    private String email;
    private String name;
    private String major;
    private int imgNumber; //0~6 사이값 set
    private boolean isStudent;

    public User(String email) {
        this.email = email;
    }

    public User(String email, String name, String major, boolean isStudent,int imgNumber)

    {
        this.email =email;
        this.name = name;
        this.major = major;
        this.isStudent = isStudent;
        this.imgNumber = getImageNum(imgNumber);
    }

    public int getImgNumber() {
        return imgNumber;
    }

    public void setImgNumber(int imgNumber) {
        this.imgNumber = imgNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public boolean getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(boolean student) {
        isStudent = student;
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
