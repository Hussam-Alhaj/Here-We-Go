package com.mydevz.softwarequizapp;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.res.ColorStateList;
import android.os.CountDownTimer;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.softwarequizapp.R;

import java.util.Locale;

public class Play_Activity extends AppCompatActivity implements View.OnClickListener{
    private static final long COUNTDOWN_IN_MILLIS = 150000;

    private TextView textViewCountDown;
    private ColorStateList textColorDefaultCd;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    MainActivity object = new MainActivity();
    TextView totalQuestionsTextView;
    TextView artotalQuestionsTextView;

    TextView questionTextView;
    ImageView imageView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;
    int score=0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    int question = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play2);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        textColorDefaultCd = textViewCountDown.getTextColors();

        totalQuestionsTextView = findViewById(R.id.total_question);
        artotalQuestionsTextView = findViewById(R.id.total_question);

        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);
        findViewById(R.id.imageView).setOnClickListener(
                h-> finish()
        );


        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);


        if(object.getGetLanguage()== 1){
            artotalQuestionsTextView.setText("إجمالي عدد الاسئلة: " + "١٠");
        }
        else {
            totalQuestionsTextView.setText("Total questions : "+totalQuestion);

        }






        loadNewQuestion();




    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(object.getGetLanguage()== 1){
            if(clickedButton.getId()==R.id.submit_btn){
                if(selectedAnswer.equals(QuestionAnswer.arCorrectAnswers[currentQuestionIndex])){
                    score++;
                }
                currentQuestionIndex++;
                loadNewQuestion();
            }else {
                selectedAnswer  = clickedButton.getText().toString();
                clickedButton.setBackgroundColor(Color.DKGRAY);
            }
        }
        else {
            if(clickedButton.getId()==R.id.submit_btn){
                if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                    score++;
                }
                currentQuestionIndex++;
                loadNewQuestion();

            }else {

                selectedAnswer  = clickedButton.getText().toString();
                clickedButton.setBackgroundColor(Color.DKGRAY);

            }
        }
    }

    void loadNewQuestion(){
        if(object.getGetLanguage() == 1){
            if(currentQuestionIndex == totalQuestion ){
                finishQuiz();
                return;
            }
            questionTextView.setText(QuestionAnswer.arQuestion[currentQuestionIndex]);
            ansA.setText(QuestionAnswer.arChoices[currentQuestionIndex][0]);
            ansB.setText(QuestionAnswer.arChoices[currentQuestionIndex][1]);
            ansC.setText(QuestionAnswer.arChoices[currentQuestionIndex][2]);
            ansD.setText(QuestionAnswer.arChoices[currentQuestionIndex][3]);
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            if(question == 1){
                startCountDown();
                question++;
            }
        }
        else {
            if(currentQuestionIndex == totalQuestion ){
                finishQuiz();

                return;
            }
            questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
            ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
            ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
            ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
            ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;

            if(question == 1){
                startCountDown();
                question++;
            }
        }
    }
    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                finishQuiz();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }

    }

    void finishQuiz(){
        countDownTimer.cancel();

        String passStatus = "";
        if(object.getGetLanguage() == 1 ){
            if(score >= totalQuestion*0.60){
                passStatus = "تهانينا، لقد نجحت!!";
            }else{
                passStatus = "لقد رسبت، تعال الفصل القادم";
            }

            new AlertDialog.Builder(this)
                    .setTitle(passStatus)
                    .setMessage("درجتك هي   "+ score+"   من أصل  "+ totalQuestion)
                    .setPositiveButton("إعادة الاختبار",(dialogInterface, i) -> restartQuiz() )
                    .setCancelable(false)
                    .show();
        }
        else {
            if(score >= totalQuestion*0.60){
                passStatus = "Congratulations, you passed!!";
            }else{
                passStatus = "Failed, next semester";
            }

            new AlertDialog.Builder(this)
                    .setTitle(passStatus)
                    .setMessage("Score is "+ score+" out of "+ totalQuestion)
                    .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                    .setCancelable(false)
                    .show();
        }

    }

    void restartQuiz(){
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
        startCountDown();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

}