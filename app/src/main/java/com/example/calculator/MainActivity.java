package com.example.calculator;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.resultDisplayArea);
        //to not show the manual keyboard
        display.setShowSoftInputOnFocus(false);

    }

    private void updateText(String addition) {
        String oldStr = display.getText().toString();
        //grabbing the cursor position i.e. where the cursor, the string gets added
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        display.setText(String.format("%s%s%s", leftStr, addition, rightStr));
        // above function will update our display where cursor is.
        display.setSelection(cursorPos + 1);
    }

    public void zeroBtn(View view) {
        updateText("0");
    }

    public void oneBtn(View view) {
        updateText("1");
    }

    public void twoBtn(View view) {
        updateText("2");
    }

    public void threeBtn(View view) {
        updateText("3");
    }

    public void fourBtn(View view) {
        updateText("4");
    }

    public void fiveBtn(View view) {
        updateText("5");
    }

    public void sixBtn(View view) {
        updateText("6");
    }

    public void sevenBtn(View view) {
        updateText("7");
    }

    public void eightBtn(View view) {
        updateText("8");
    }

    public void nineBtn(View view) {
        updateText("9");
    }

    public void bracketsBtn(View view) {
        int cursorPos = display.getSelectionStart();
        int openBracket = 0;
        int closeBracket = 0;
        int textLength = display.getText().length();
        for (int i = 0; i < cursorPos; i++) {
            if(display.getText().toString().charAt(i) == '(') {
                openBracket += 1;
            }
            if(display.getText().toString().charAt(i) == ')') {
                closeBracket += 1;
            }
        }
        if(openBracket == closeBracket || display.getText().toString().charAt(textLength - 1) == '(') {
            updateText("(");
        }
        if(openBracket > closeBracket && display.getText().toString().charAt(textLength - 1) != '(') {
            updateText(")");
        }
        display.setSelection(cursorPos + 1);
    }

    public void piBtn(View view) {
        updateText("π");
    }

    public void factorialBtn(View view) {
        updateText("!");
    }

    public void clearBtn(View view) {
        display.setText("");
    }

    public void percentageBtn(View view) {
        updateText("%");
    }

    public void divisionBtn(View view) {
        updateText("÷");
    }

    public void backspaceBtn(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if(cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }

    public void multiplicationBtn(View view) {
        updateText("×");
    }

    public void subtractionBtn(View view) {
        updateText("-");
    }

    public void additionBtn(View view) {
        updateText("+");
    }

    public void decimalBtn(View view) {
        updateText(".");
    }

    public void equalToBtn(View view) {
        String userExpression = display.getText().toString();
        userExpression = userExpression.replaceAll("÷", "/");
        userExpression = userExpression.replaceAll("×", "*");
        userExpression = userExpression.replaceAll("π", "3.142");

        Expression exp = new Expression(userExpression);
        String result = String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());
    }

}