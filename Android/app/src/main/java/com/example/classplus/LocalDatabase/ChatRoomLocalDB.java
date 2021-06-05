package com.example.classplus.LocalDatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.classplus.DTO.ChatData;
import com.example.classplus.DTO.ChatRoomInfo;

public class ChatRoomLocalDB extends SQLiteOpenHelper {

    private static ChatRoomLocalDB instance = null;
    public static String roomChatTable = "roomChat";


    public ChatRoomLocalDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static ChatRoomLocalDB getInstance(Context context){
        if (instance == null) {
            instance = new ChatRoomLocalDB(context, roomChatTable, null, 1);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createTable(SQLiteDatabase database) {

        String sqlString = "CREATE TABLE " + roomChatTable + " (uuid INTEGER, name TEXT, lastTime TEXT,lastChat TEXT, " +
                "img INTEGER, lastChatID TEXT, isRead INTEGER)";

        try {
            database.execSQL(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void runDb(SQLiteDatabase database, String sqlString){
        database.beginTransaction();
        try {
            database.execSQL(sqlString);
            database.setTransactionSuccessful();
            database.endTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRoomInfo(SQLiteDatabase database,int uuid, String name, String lastTime, String lastChat, int img, String lastChatID, boolean isRead ){
        int readingFlag = (isRead)? 1 : 0;
        String sqlString = "insert into " + roomChatTable + "(uuid,name,lastTime,lastChat,img,lastChatID,isRead)"
                + " values('" + uuid + "', '" + name + "', '" + lastTime + "', '" + lastChat + "', '" + img + "', '" + lastChatID + "', '" + readingFlag + "')";

        runDb(database, sqlString);
    }

    public void updateRoomInfo(SQLiteDatabase database, int uuid,String lastTime,String lastChat,String lastChatID,boolean isRead){
        int readingFlag = (isRead)? 1 : 0;
        String sqlString = "UPDATE " + roomChatTable + " SET lastTime = '" + lastTime + "', lastChat = '" + lastChat + "', lastChatID = '"
                + lastChatID +  "', isRead = '" + readingFlag + "' WHERE uuid = '" + uuid  + "'";
        runDb(database, sqlString);

    }

    public void updateReadingFlagTrue(SQLiteDatabase database,int uuid ){

        String sqlString = "UPDATE " + roomChatTable +" SET isRead = '" + 1 + "' WHERE uuid = '" + uuid  + "'";
        runDb(database, sqlString);
    }

    public ChatRoomInfo getChatRoomInfo(SQLiteDatabase database, int index){
        String sqlSelect = "SELECT * FROM " + roomChatTable;
        Cursor cursor = null;
        int count = 0;

        cursor = database.rawQuery(sqlSelect, null);

        while (cursor.moveToNext()) {

            if (count == index) {
                boolean readingFlag = (cursor.getInt(6) == 1)? true : false;
                return new ChatRoomInfo(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getInt(4), cursor.getString(5), readingFlag);
            }
            count++;
        }
        return null;
    }

    public Integer getRoomCount(SQLiteDatabase database) {
        String sqlSelect = "SELECT * FROM " + roomChatTable;
        Cursor cursor = null;
        cursor = database.rawQuery(sqlSelect, null);

        return cursor.getCount();
    }

}
