package com.example.abc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.abc.QuizContract.QuestionsTable;

import java.util.ArrayList;
import java.util.List;

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

        final String SQL_CREATE_QUESTIONS_TABLE = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT %s TEXT %s TEXT %s TEXT %s TEXT %s INTEGER)", QuestionsTable.TABLE_NAME, QuestionsTable._ID, QuestionsTable.COLUMN_QUESTION, QuestionsTable.COLUMN_OPTION1, QuestionsTable.COLUMN_OPTION2, QuestionsTable.COLUMN_OPTION3, QuestionsTable.COLUMN_ANSWER_NR);

        database.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(database);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("A is correct","A","B","C",1);
        addQuestion(q1);
        Question q2 = new Question("B is correct","A","B","C",2 );
        addQuestion(q2);
        Question q3 = new Question("A is correct","A","B","C",1);
        addQuestion(q3);
        Question q4 = new Question("C is correct","A","B","C",1);
        addQuestion(q4);
        Question q5 = new Question("B is correct","A","B","C",1);
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

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
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
