package com.example.iceberg;


import java.text.SimpleDateFormat;

public class Game {
	 
    //private variables
    int _id;
    String _date;
    String _opponent;
	String _home_away;
	String _result;
	String _opponent_image;
	SimpleDateFormat FORMATTER = new SimpleDateFormat("EEE, MM-dd hh:mm");
 
    // Empty constructor
    public Game(){
 
    }
    
    // constructor
    public Game(int id, String date, String opponent, String homeAway, String result, String opponentImage){
        this._id = id;
        this._date = date;
		this._opponent = opponent;
        this._home_away = homeAway;
		this._result = result;
		this._opponent_image = opponentImage;
    }
 
    // constructor
    public Game(String date, String opponent, String homeAway, String result, String opponentImage){
        this._date = date;
		this._opponent = opponent;
        this._home_away = homeAway;
		this._result = result;
		this._opponent_image = opponentImage;
    }
	
    // getting ID
    public int getID(){
        return this._id;
    }
 
    // setting id
    public void setID(int id){
        this._id = id;
    }
 
	// getting date
    public String getDate(){
        return this._date;
    }
	
	// setting date
    public void setDate(String date){
    	//Log.i("Game-date",date);
        this._date = date;
    }
	
	// getting opponent
    public String getOpponent(){
        return this._opponent;
    }
 
    // setting opponent
    public void setOpponent(String opponent){
        this._opponent= opponent;
    }
 
    // getting home away
    public String getHomeAway(){
        return this._home_away;
    }
 
    // setting home away
    public void setHomeAway(String homeAway){
        this._home_away = homeAway;
    }
 
    // getting result
    public String getResult(){
        return this._result;
    }
 
    // setting result
    public void setResult(String result){
        this._result = result;
    }
	
	// getting opponents image
    public String getOpponentImage(){
        return this._opponent_image;
    }
 
    // setting opponents image
    public void setOpponentImage(String opponentImage){
        this._opponent_image = opponentImage;
    }	
}