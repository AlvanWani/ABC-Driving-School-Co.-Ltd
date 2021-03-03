package com.example.abc.ui.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.abc.Dashboard;
import com.example.abc.Quizs;
import com.example.abc.R;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class TestFragment extends Fragment {
    private static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";

    private TextView textViewHighScore;

    private int highScore;

    private TestViewModel testViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final Button mBtnStartTest;

        testViewModel =
                new ViewModelProvider(this).get(TestViewModel.class);
        View root = inflater.inflate(R.layout.fragment_theory_test, container, false);
        final TextView textView = root.findViewById(R.id.test);

                textViewHighScore = root.findViewById(R.id.tv_high_score);
                loadHighScore();

                mBtnStartTest = root.findViewById(R.id.btn_start_test);
                mBtnStartTest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       Intent intent = new Intent(getActivity(),Quizs.class);
                       startActivityForResult(intent,REQUEST_CODE_QUIZ);
                    }
                });

        testViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK);
            int score = data.getIntExtra(Quizs.EXTRA_SCORE,0);
            if (score > highScore) {
                updateHighScore(score);
            }
        }
    }

    private void loadHighScore(){
        SharedPreferences prefs = this.getActivity().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        highScore = prefs.getInt(KEY_HIGHSCORE,0);
        textViewHighScore.setText("HighScore: " + highScore );
    }

    private void updateHighScore(int highScoreNew) {
        highScore = highScoreNew;
        textViewHighScore.setText("HighScore: " + highScore );

        SharedPreferences prefs = this.getActivity().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highScore);
        editor.apply();
    }
}