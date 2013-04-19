package com.example.iceberg;


import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Game {
	 
    //private variables
    int _id;
    String _date;
    String _opponent;
	String _home_away;
	String _result;
	String _opponent_image;
	String _game_type;
	int _month_round;
	SimpleDateFormat FORMATTER = new SimpleDateFormat("EEE, MM-dd hh:mm");
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
 
    // Empty constructor
    public Game(){
 
    }
    
    // constructor if you have all the information
	public Game(int id, String date, String opponent, String homeAway, String result, String opponentImage, String gameType, int monthRound){
        this._id = id;
        this._date = date;
		this._opponent = opponent;
        this._home_away = homeAway;
		this._result = result;
		this._opponent_image = opponentImage;
		this._game_type = gameType;
		this._month_round = monthRound;		
    }
 
    // constructor
    public Game(String date, String opponent, String homeAway, String result, String opponentImage, String gameType){
        this._date = date;
		this._opponent = opponent;
        this._home_away = homeAway;
		this._result = result;
		this._opponent_image = opponentImage;
		this._game_type = gameType;
		
		try {
			this._month_round = Integer.parseInt(dateFormat.format(dateFormat.parse(date)).split("-")[1]);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    // constructor
 	public Game(String date, String opponent, String homeAway, String result, String opponentImage, String gameType, int monthRound){
        this._date = date;
 		this._opponent = opponent;
        this._home_away = homeAway;
 		this._result = result;
 		this._opponent_image = opponentImage;
 		this._game_type = gameType;
 		this._month_round = monthRound;
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
    
    public String getGameType(){
    	return this._game_type;
    }
    
    public void setGameType(String gameType){
    	this._game_type = gameType;
    }
    
    public int getMonthRoundAsInt(){
    	return this._month_round;
    }
    
    public String getMonthRoundAsString(){
    	return String.valueOf(this._month_round);
    }
    
    public void setMonthRound(int monthRound){
    	this._month_round = monthRound;
    }
    
    public void setMonthRound(String monthRound){
    	this._month_round = Integer.parseInt(monthRound);
    }
}