package com.example.myapplicationforclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    // Всички променливи са статични !
    // Версия на Базата от Данни
    private static final int DATABASE_VERSION = 1;
    // Име на базата
    private static final String DATABASE_NAME = "uSERmANAGER";
    // Име на Таблицата
    private static final String TABLE_CONTACTS = "USERS";

    // Имена на колоните на Таблицата
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "UserName";
    private static final String KEY_PH_NO = "Password";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS +
                "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);// Повторно създаване на базата от данни
        onCreate(db);
    }

    public void onDeleteTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        db.delete(TABLE_CONTACTS, null, null);
        // db.close();}
    }

    // Добавяне на нов Потребител
    void addUser(User contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.get_UserName());
        // Име на Потребител
        values.put(KEY_PH_NO, contact.get_password()); // Парола на Потребител
        // Добавяне на Ред
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Затравяне на връзката с базата от данни
    }

    User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
                        KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        // Връщане на Потребител
        return user;
    }

    // Вземане на всички Потребители
    public List<User> getAllUsers() {
        List<User> UserList = new ArrayList<User>();
        // избор на всички
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Обхождане на всички редове и избор
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.set_id(Integer.parseInt(cursor.getString(0)));
                user.set_UserName(cursor.getString(1));
                user.set_password(cursor.getString(2));
                // Добавяне на Потребител в колекцията
                UserList.add(user);
            } while (cursor.moveToNext());
        }
        // Връщане на колекция от Потребители
        return UserList;
    }

    public int updateUser(User contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.get_UserName());
        values.put(KEY_PH_NO, contact.get_password());
        // Обновяване на ред
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",new String[] { String.valueOf(contact.get_id()) });
    }

    // Изтриване на Потребител
    public void deleteUser(User contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.get_id()) });
        db.close();
    }

    // Взимане на Броя на Потребителите
    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();// Връщане на броя
         return cursor.getCount();
    }
}

