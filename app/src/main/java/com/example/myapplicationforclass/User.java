package com.example.myapplicationforclass;

public class User {
    private int _id;
    private String _UserName;
    private String _password;

    public User() {
    }

    public User(int _id, String _UserName, String _password) {
        this._id = _id;
        this._UserName = _UserName;
        this._password = _password;
    }

    public User(String _UserName, String _password) {
        this._UserName = _UserName;
        this._password = _password;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_UserName() {
        return _UserName;
    }

    public void set_UserName(String _UserName) {
        this._UserName = _UserName;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }
}
