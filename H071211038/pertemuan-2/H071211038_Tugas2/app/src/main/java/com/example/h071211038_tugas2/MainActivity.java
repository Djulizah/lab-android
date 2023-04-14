package com.example.h071211038_tugas2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    TextView result;
    Button button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9,
    button_equals, button_allClear, button_delete, button_divide, button_multiply, button_subtract, button_add;
    boolean isActive = false;
    String operator = "";
    double num1 = 0;
    double num2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_0 = findViewById(R.id.button_0);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);

        button_equals = findViewById(R.id.button_equals);
        button_allClear = findViewById(R.id.button_allclear);
        button_delete = findViewById(R.id.button_delete);
        button_divide = findViewById(R.id.button_divide);
        button_multiply = findViewById(R.id.button_multiply);
        button_subtract = findViewById(R.id.button_subtract);
        button_add = findViewById(R.id.button_add);
        result = findViewById(R.id.result);

        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_add.setOnClickListener(this);
        button_subtract.setOnClickListener(this);
        button_multiply.setOnClickListener(this);
        button_divide.setOnClickListener(this);
        button_allClear.setOnClickListener(this);
        button_equals.setOnClickListener(this);
        button_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String currentText = result.getText().toString();
        int lengthSecondNum = 0;

        switch (view.getId()) {
            case R.id.button_0:
            case R.id.button_1:
            case R.id.button_2:
            case R.id.button_3:
            case R.id.button_4:
            case R.id.button_5:
            case R.id.button_6:
            case R.id.button_7:
            case R.id.button_8:
            case R.id.button_9:
                String digit = ((Button) view).getText().toString();
                if (currentText.equals("0") || isActive) {
                    result.setText(digit);
                    isActive = false;
                } else {
                    result.setText(currentText + digit);
                    if (!operator.isEmpty()) {
                        lengthSecondNum++;
                    }
                }
                break;
            case R.id.button_add:
                if (!isActive) {
                    if (operator.isEmpty()) {
                        result.setText(currentText + "＋");
                    } else {
                        result.setText(currentText.replace(operator, "＋"));

                    }
                    operator = "＋";
                }
                break;
            case R.id.button_subtract:
                if (!isActive) {
                    if (operator.isEmpty()) {
                        result.setText(currentText + "-");
                    } else {
                        result.setText(currentText.replace(operator, "-"));

                    }
                    operator = "-";
                }
                break;
            case R.id.button_multiply:
                if (!isActive) {
                    if (operator.isEmpty()) {
                        result.setText(currentText + "x");
                    } else {
                        result.setText(currentText.replace(operator, "x"));

                    }
                    operator = "x";
                }
                break;
            case R.id.button_divide:
                if (!isActive) {
                    if (operator.isEmpty()) {
                        result.setText(currentText + "/");
                    } else {
                        result.setText(currentText.replace(operator, "/"));
                    }
                    operator = "/";
                }
                break;
            case R.id.button_allclear:
                result.setText("0");
                operator = "";
                num1 = 0;
                num2 = 0;
                break;
            case R.id.button_delete:
                if (isActive) {
                    result.setText("0");
                    operator = "";
                    num1 = 0;
                    num2 = 0;
                } else {
                    if (currentText.length() > 0) {
                        currentText = currentText.substring(0, currentText.length() - 1);
                        result.setText(currentText);
                        if (!operator.isEmpty()) {
                            if (lengthSecondNum == 0) {
                                operator = "";
                            } else {
                                lengthSecondNum -= 1;
                            }
                            if (currentText.length() == 0) {
                                result.setText("0");
                            }
                        }
                    }
                    if (currentText.length() == 0) {
                        result.setText("0");
                    }
                }
                break;
            case R.id.button_equals:
                try {
                    String [] nums = currentText.split(operator);
                    num1 = Double.parseDouble(nums[0]);
                    num2 = Double.parseDouble(nums[1]);
                    System.out.println(num1);
                    System.out.println(num2);

                    if (num2 == 0 && operator.equals("/")) {
                        Toast.makeText(this, "cannot divided by zero", Toast.LENGTH_SHORT).show();
                    } else {
                        String calculationResult = calculation(num1, num2, operator);
                        result.setText(calculationResult);
                        operator = "";

                        lengthSecondNum = 0;
                        num1 = 0;
                        num2 = 0;
                        isActive = true;
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "please enter an operator or a number", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    private String calculation(double num1, double num2, String operator) {
        double result = 0;
        switch (operator) {
            case "＋":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "x":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                result = 0;
                break;
        }
        return result == (int) result ? Integer.toString((int) result) : Double.toString(result);
    }
}