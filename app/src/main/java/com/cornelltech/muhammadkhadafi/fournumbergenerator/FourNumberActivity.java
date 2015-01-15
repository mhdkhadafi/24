package com.cornelltech.muhammadkhadafi.fournumbergenerator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;


public class FourNumberActivity extends Activity {

    Button btnGenerate;
    Button btnCan;
    Button btnCannot;
    Button btnFirstNumber;
    Button btnSecondNumber;
    Button btnThirdNumber;
    Button btnFourthNumber;
    Button btnPlus;
    Button btnMinus;
    Button btnMultiply;
    Button btnDivide;
    Button btnPower;
    Button btnOpenBracket;
    Button btnCloseBracket;
    Button btnCount;

    TextView txtNumber;
    TextView txtResult;
    TextView txtCorrect;
    TextView txtWrong;

    LinearLayout layoutFormula;
    ImageView imgResult;

    public String randomNumberString = "";
    public String resultString = "";
    public SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGenerate = (Button) findViewById(R.id.btn_generate);
        btnCan = (Button) findViewById(R.id.btn_can);
        btnCannot = (Button) findViewById(R.id.btn_cannot);
        btnFirstNumber = (Button) findViewById(R.id.btn_firstNumber);
        btnSecondNumber = (Button) findViewById(R.id.btn_secondNumber);
        btnThirdNumber = (Button) findViewById(R.id.btn_thirdNumber);
        btnFourthNumber = (Button) findViewById(R.id.btn_fourthNumber);
        btnPlus = (Button) findViewById(R.id.btn_plus);
        btnMinus = (Button) findViewById(R.id.btn_minus);
        btnMultiply = (Button) findViewById(R.id.btn_multiply);
        btnDivide = (Button) findViewById(R.id.btn_divide);
        btnPower = (Button) findViewById(R.id.btn_power);
        btnOpenBracket = (Button) findViewById(R.id.btn_openBracket);
        btnCloseBracket = (Button) findViewById(R.id.btn_closeBracket);
        btnCount = (Button) findViewById(R.id.btn_count);

        txtNumber = (TextView) findViewById(R.id.txt_fourNumber);
        txtResult = (TextView) findViewById(R.id.txt_result);
        txtCorrect = (TextView) findViewById(R.id.txt_correct);
        txtWrong = (TextView) findViewById(R.id.txt_wrong);

        layoutFormula = (LinearLayout) findViewById(R.id.layout_formula);
        imgResult = (ImageView) findViewById(R.id.img_result);

        randomNumberString = getRandomNumber() + "";
//                String randomNumberString = "3979";

        txtNumber.setText(randomNumberString);
        resultString = get24Result(randomNumberString);
//        txtResult.setText(get24Result(randomNumberString));
        imgResult.setVisibility(View.INVISIBLE);
        layoutFormula.setVisibility(View.INVISIBLE);

        prefs = getPreferences(0);
        txtCorrect.setText(prefs.getString("correct", "0"));
        txtWrong.setText(prefs.getString("wrong", "0"));

        btnCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgResult.setVisibility(View.VISIBLE);
                if (resultString.equals("No Possible 24")) {
                    imgResult.setImageResource(R.drawable.x_mark);
                    txtResult.setText(resultString);
                    txtWrong.setText((Integer.parseInt(txtWrong.getText().toString()) + 1 + ""));
                    SharedPreferences.Editor editor = getPreferences(0).edit();
                    editor.putString("wrong", (Integer.parseInt(txtCorrect.getText().toString()) + 1 + ""));
                    editor.apply();
                }
                else {
                    imgResult.setImageResource(R.drawable.check_mark);
                    txtResult.setText(resultString);
                    txtCorrect.setText((Integer.parseInt(txtCorrect.getText().toString()) + 1 + ""));
                    SharedPreferences.Editor editor = getPreferences(0).edit();
                    editor.putString("correct", (Integer.parseInt(txtCorrect.getText().toString()) + 1 + ""));
                    editor.apply();
                }
            }
        });

        btnCannot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgResult.setVisibility(View.VISIBLE);
                if (resultString.equals("No Possible 24")) {
                    imgResult.setImageResource(R.drawable.check_mark);
                    txtResult.setText(resultString);
                    txtCorrect.setText((Integer.parseInt(txtCorrect.getText().toString()) + 1 + ""));
                    SharedPreferences.Editor editor = getPreferences(0).edit();
                    editor.putString("correct", (Integer.parseInt(txtCorrect.getText().toString()) + 1 + ""));
                    editor.apply();
                }
                else {
                    imgResult.setImageResource(R.drawable.x_mark);
                    txtResult.setText(resultString);
                    txtWrong.setText((Integer.parseInt(txtWrong.getText().toString()) + 1 + ""));
                    SharedPreferences.Editor editor = getPreferences(0).edit();
                    editor.putString("wrong", (Integer.parseInt(txtCorrect.getText().toString()) + 1 + ""));
                    editor.apply();
                }
            }
        });

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgResult.setVisibility(View.INVISIBLE);
                randomNumberString = getRandomNumber() + "";
//                String randomNumberString = "3979";

                txtNumber.setText(randomNumberString);
                resultString = get24Result(randomNumberString);

                txtResult.setText("");
            }
        });
    }

    public void onResume(Bundle savedInstanceState) {
        super.onResume();

        prefs = getPreferences(0);
        txtCorrect.setText(prefs.getString("correct", "0"));
        txtWrong.setText(prefs.getString("wrong", "0"));
    }

    private String get24Result(String fourNumbers) {
        int fourNumberInt = Integer.parseInt(fourNumbers);
        int[] numbers = new int[]{fourNumberInt / 1000, (fourNumberInt % 1000) / 100, (fourNumberInt % 100) / 10, fourNumberInt % 10};
        String[] operators = new String[]{"+", "-", "*", "/", "^"};

        Boolean found = false;
        String canBe24 = "No Possible 24";

        for (int i = 0; i < numbers.length; i++) {
            if (!canBe24.equals("No Possible 24")) break;
            for (int j = 0; j < numbers.length; j++) {
                if (j == i) continue;
                if (!canBe24.equals("No Possible 24")) break;

                for (int k = 0; k < numbers.length; k++) {
                    if (k == j || k == i) continue;
                    if (!canBe24.equals("No Possible 24")) break;

                    for (int l = 0; l < numbers.length; l++) {
                        if (l == k || l == j || l == i) continue;
                        if (!canBe24.equals("No Possible 24")) break;

                        for (int m = 0; m < operators.length; m++) {
                            if (!canBe24.equals("No Possible 24")) break;
                            for (int n = 0; n < operators.length; n++) {
                                if (!canBe24.equals("No Possible 24")) break;
                                for (int o = 0; o < operators.length; o++) {
                                    if (!canBe24.equals("No Possible 24")) break;

                                    float firstNumber = (float) numbers[i];
                                    float secondNumber = (float) numbers[j];
                                    float thirdNumber = (float) numbers[k];
                                    float fourthNumber = (float) numbers[l];

                                    canBe24 = count24(firstNumber, secondNumber, thirdNumber,
                                            fourthNumber, operators[m], operators[n], operators[o]);
                                }
                            }
                        }

                    }
                }
            }
        }

        return canBe24;
    }

    private String count24(float a, float b, float c, float d, String op1, String op2, String op3) {

        Log.d("counting", ("" + a + " " + op1 + " " + b + " " + op2 + " " + c + " " + op3 + " " + d));
        float ab = doOperation(op1, a, b);
        float bc = doOperation(op2, b, c);
        float cd = doOperation(op3, c, d);
        float abc = doOperation(op2, ab, c);
        float a_bc = doOperation(op1, a, bc);
        float b_cd = doOperation(op2, b, cd);
        float bc_d = doOperation(op3, bc, d);

        float abcd = doOperation(op3, abc, d);
        float ab_cd = doOperation(op2, ab, cd);
        float a_bc_d = doOperation(op3, a_bc, d);
        float a__b_cd = doOperation(op1, a, b_cd);
        float a__bc_d = doOperation(op1, a, bc_d);

        Log.d("counting", ("" + abcd));

        if (abcd == 24) return "(((" + (int) a + " " + op1 + " " + (int) b + ") " + op2 + " " + (int) c + ") " + op3 + " " + (int) d + ")";
        else if (ab_cd == 24) return "(" + (int) a + " " + op1 + " " + (int) b + ") " + op2 + " (" + (int) c + " " + op3 + " " + (int) d + ")";
        else if (a_bc_d == 24) return "(" + (int) a + " " + op1 + " (" + (int) b + " " + op2 + " " + (int) c + ")) " + op3 + " " + (int) d;
        else if (a__b_cd == 24) return "" + (int) a + " " + op1 + " (" + (int) b + " " + op2 + " (" + (int) c + " " + op3 + " " + (int) d + "))";
        else if (a__bc_d == 24) return "" + (int) a + " " + op1 + " ((" + (int) b + " " + op2 + " " + (int) c + ") " + op3 + " " + (int) d + ")";
        else return "No Possible 24";
    }

    private float doOperation(String operation, float num1, float num2) {
        if (operation.equals("+")) return num1 + num2;
        else if (operation.equals("-")) return num1 - num2;
        else if (operation.equals("*")) return num1 * num2;
        else if (operation.equals("/")) {
            if (num2 != 0) return num1 / num2;
            else return 10000000;
        }
        else if (operation.equals("^")) return (float) Math.pow(num1, num2);
        else return 1;
    }

    private int getRandomNumber() {
        Random rand = new Random();
        int value = rand.nextInt(8999);

        return value + 1000;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.four_number, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
