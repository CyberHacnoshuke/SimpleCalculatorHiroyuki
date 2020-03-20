package jp.ac.seiko.li.simplecalculatorhiroyuki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mTextViewAnswer;
    TextView mTextViewFormula;

    Button mButton_1;
    Button mButton_2;
    Button mButton_3;
    Button mButton_4;
    Button mButton_5;
    Button mButton_6;
    Button mButton_7;
    Button mButton_8;
    Button mButton_9;
    Button mButton_0;

    Button mButtonMinus;
    Button mButtonPlus;
    Button mButtonMultiplication;
    Button mButtonDivision;

    Button mButtonEqual;
    Button mButtonClear;

    int mProcessValue = 1;
    // 1足し算 2引き算 3掛け算 4割り算
    int mOperation = 0;
    int mNextOperation = 0;
    String mFirstValue;
    String mSecondValue;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init ();
    }

    private void init() {
        mProcessValue = 1;
        mOperation = 0;
        mFirstValue = "";
        mSecondValue = "";

        mTextViewAnswer = findViewById(R.id.textView_answer);
        mTextViewFormula = findViewById(R.id.textView_formula);
        mButton_0 = findViewById(R.id.button_0);
        mButton_1 = findViewById(R.id.button_1);
        mButton_2 = findViewById(R.id.button_2);
        mButton_3 = findViewById(R.id.button_3);
        mButton_4 = findViewById(R.id.button_4);
        mButton_5 = findViewById(R.id.button_5);
        mButton_6 = findViewById(R.id.button_6);
        mButton_7 = findViewById(R.id.button_7);
        mButton_8 = findViewById(R.id.button_8);
        mButton_9 = findViewById(R.id.button_9);
        mButtonPlus = findViewById(R.id.button_plus);
        mButtonMinus = findViewById(R.id.button_minus);
        mButtonMultiplication = findViewById(R.id.button_multiplication);
        mButtonDivision = findViewById(R.id.button_division);
        mButtonEqual = findViewById(R.id.button_eq);
        mButtonClear = findViewById(R.id.button_clear);

        mTextViewAnswer.setText("");
        mTextViewFormula.setText("");

        Button[] allButton = {
                mButton_0,
                mButton_1,
                mButton_2,
                mButton_3,
                mButton_4,
                mButton_5,
                mButton_6,
                mButton_7,
                mButton_8,
                mButton_9,
                mButtonPlus,
                mButtonMinus,
                mButtonMultiplication,
                mButtonDivision,
                mButtonEqual,
                mButtonClear
        };

        for (Button button : allButton) {
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.button_0:
                if (mFirstValue.length() != 0 && mSecondValue.length() != 0) addFigure("0");
                break;
            case R.id.button_1:
                addFigure("1");
                break;
            case R.id.button_2:
                addFigure("2");
                break;
            case R.id.button_3:
                addFigure("3");
                break;
            case R.id.button_4:
                addFigure("4");
                break;
            case R.id.button_5:
                addFigure("5");
                break;
            case R.id.button_6:
                addFigure("6");
                break;
            case R.id.button_7:
                addFigure("7");
                break;
            case R.id.button_8:
                addFigure("8");
                break;
            case R.id.button_9:
                addFigure("9");
                break;
            case R.id.button_clear:
                init();
                break;
            case R.id.button_eq:
                enter();
                break;
            case R.id.button_plus:
                addOperation(1);
                break;
            case R.id.button_minus:
                addOperation(2);
                break;
            case R.id.button_multiplication:
                addOperation(3);
                break;
            case R.id.button_division:
                addOperation(4);
                break;
        }
    }

    private void addFigure (String figure) {
        switch (mProcessValue) {
            case 1:
                mFirstValue += figure;
                mTextViewFormula.setText(mFirstValue);
                break;
            case 2:
                // do nothing
                break;
            case 3:
                mSecondValue += figure;
                mTextViewFormula.setText(mTextViewFormula.getText() + figure);
                break;
            case 4:
                // do nothing
                break;
        }
    }

    private void addOperation (int ope) {
        String operation = "";
        switch (ope) {
            case 1:
                operation = " + ";
                break;
            case 2:
                operation = " - ";
                break;
            case 3:
                operation = " × ";
                break;
            case 4:
                operation = " ÷ ";
                break;
        }

        if (mProcessValue == 1 && mFirstValue.length() != 0) {
            mOperation = ope;
            mTextViewFormula.setText(mFirstValue + operation);
        } else if (mProcessValue == 3 && mSecondValue.length() == 0) {
            mOperation = ope;
            mTextViewFormula.setText(mFirstValue + operation);
        } else if (mProcessValue == 3 || mProcessValue == 4) {
            enter();
            mFirstValue = mTextViewAnswer.getText().toString();
            mSecondValue = "";
            mOperation = ope;
            mTextViewFormula.setText(mFirstValue + operation);

        }
        mProcessValue = 3;
    }

    private void enter () {
        if (mProcessValue != 3 || mFirstValue.length() == 0) { return; }
        mTextViewFormula.setText(mTextViewFormula.getText() + " =");
        int answer = 0;
        switch (mOperation) {
            case 1:
                answer = Integer.parseInt(mFirstValue) + Integer.parseInt(mSecondValue);
                break;
            case 2:
                answer = Integer.parseInt(mFirstValue) - Integer.parseInt(mSecondValue);
                break;
            case 3:
                answer = Integer.parseInt(mFirstValue) * Integer.parseInt(mSecondValue);
                break;
            case 4:
                answer = Integer.parseInt(mFirstValue) / Integer.parseInt(mSecondValue);
                break;
        }
        mProcessValue = 4;
        mTextViewAnswer.setText(answer + "");
    }
}
