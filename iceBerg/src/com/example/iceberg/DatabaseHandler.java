package com.example.iceberg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "iceBerg";
 
    // Schedule table name
    private static final String TABLE_SCHEDULE = "schedule";
 
    // Schedule Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_OPPONENT = "opponent";
	private static final String KEY_HOME_AWAY = "home_away";
    private static final String KEY_RESULT = "result";
    private static final String KEY_OPPONENT_IMAGE = "opponent_image";
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MM/dd hh:mm");
    Context context;
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
    	//Log.i("af", "Creating db");
        String CREATE_SCHEDULE_TABLE = "CREATE TABLE " + TABLE_SCHEDULE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_DATE + " TEXT,"
                + KEY_OPPONENT + " TEXT," + KEY_HOME_AWAY + " TEXT," + KEY_RESULT + " TEXT," + KEY_OPPONENT_IMAGE + " TEXT" + ")";
        db.execSQL(CREATE_SCHEDULE_TABLE);
        
		new LoadSchedule(context).execute("http://www.lincolnstars.com/leagues/print_schedule.cfm?leagueID=16793&clientID=4806&teamID=343151&mixed=1");
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Add functionality here if an upgrade is needed.
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new game
    void addGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, game.getDate()); // date
        values.put(KEY_OPPONENT, game.getOpponent()); // opponent
		values.put(KEY_HOME_AWAY, game.getHomeAway()); // home away
        values.put(KEY_RESULT, game.getResult()); // result
		values.put(KEY_OPPONENT_IMAGE, game.getOpponentImage()); // opponent image
 
        // Inserting Row
        db.insert(TABLE_SCHEDULE, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single game
    Game getGame(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_SCHEDULE, new String[] { KEY_ID,
                				KEY_DATE, KEY_OPPONENT, KEY_HOME_AWAY, KEY_RESULT, KEY_OPPONENT_IMAGE }, KEY_ID + "=?",
                				new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        
        
        String date = null;
		//date = dateFormat.format(cursor.getString(1));
        Game game = new Game(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
		
		db.close();
        // return game
        return game;
    }
 
    // Getting All Games
    public List<Game> getAllGames() {
        List<Game> gameList = new ArrayList<Game>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SCHEDULE;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Game game = new Game();
                game.setID(Integer.parseInt(cursor.getString(0)));
                game.setDate(cursor.getString(1));
                game.setOpponent(cursor.getString(2));
				game.setHomeAway(cursor.getString(3));
				game.setResult(cursor.getString(4));
				game.setOpponentImage(cursor.getString(5));
                // Adding game to list
				gameList.add(game);
            } while (cursor.moveToNext());
        }
		db.close();
        // return game list
        return gameList;
    }
 
    // Updating single game
    public int updateGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, game.getDate()); // date
        values.put(KEY_OPPONENT, game.getOpponent()); // opponent
		values.put(KEY_HOME_AWAY, game.getHomeAway()); // home away
        values.put(KEY_RESULT, game.getResult()); // result
		values.put(KEY_OPPONENT_IMAGE, game.getOpponentImage()); // opponent image
		db.close();
        // updating row
        return db.update(TABLE_SCHEDULE, values, KEY_ID + " = ?", new String[] { String.valueOf(game.getID()) });
		
    }
 
    // Deleting single game
    public void deleteGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SCHEDULE, KEY_ID + " = ?", new String[] { String.valueOf(game.getID()) });
        db.close();
    }
}