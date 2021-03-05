package com.example.abc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.abc.QuizContract.QuestionsTable;

import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "abc.db";
    public static final int DATABASE_VERSION = 1;

    private SQLiteDatabase database;

    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.database = db;

        database.execSQL("CREATE TABLE IF NOT EXISTS '"+ QuestionsTable.TABLE_NAME+"' ('"+QuestionsTable.COLUMN_QUESTION+"' INTEGER, '"+QuestionsTable.COLUMN_OPTION1+"' TEXT, '"+QuestionsTable.COLUMN_OPTION2+"' TEXT, '"+QuestionsTable.COLUMN_OPTION3+"' TEXT, '"+QuestionsTable.COLUMN_ANSWER_NR+"' TEXT) ");
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(database);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("If you plan to pass another vehicle, you should:"," 1. Not assume the other driver will make space for you to return to your lane."," 2. Assume the other driver will let you pass if you use your turn signal.","3. Assume the other driver will maintain a constant speed.",1);
        addQuestion(q1);
        Question q2 = new Question(" An intersection has a stop sign, crosswalk, but no stop line. You must stop"," 1. 50 feet before the intersection."," 2. Where you think the stop line would be."," 3. Before the crosswalk.",3 );
        addQuestion(q2);
        Question q3 = new Question("Your brake lights tell other drivers that you:","1. Have your emergency brake on","2. Are slowing down or stopping","3. Have your emergency brake on",2);
        addQuestion(q3);
        Question q4 = new Question("You drive along a street and hear a siren. You cannot immediately see the emergency vehicle. You should","1. Keep driving until you see the vehicle.","2. Pull to the curb and look to see if it is on your street.","3. Speed up and turn at the next intersection.",2);
        addQuestion(q4);
        Question q5 = new Question("You want to turn left at an intersection. The light is green but oncoming traffic is heavy. You should","1. Take the right-of-way since you have the light.","2. Wait at the crosswalk for traffic to clear."," 3. Wait in the center of the intersection for traffic to clear.",3);
        addQuestion(q5);
    }

    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION2,question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR,question.getAnswer());
        database.insert(QuestionsTable.TABLE_NAME,null,cv);
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME,null);

        if (c.moveToFirst())  {
           do {
                   Question question = new Question();
                   question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                   question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                   question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                   question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                   question.setAnswer(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));

                   questionList.add(question);

           } while (c.moveToNext());
        }
        c.close();
         return questionList;
    }
}
