package com.example.calculator;

import android.graphics.Insets;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.calculator.databinding.ActivityMainBinding;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    String operator, procces;
    boolean btnEqualPressed = false;
    boolean zeroPressed = false;
    int lenghtSecondNumber;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvResult.setText("");
                binding.tvFormula.setText("");
                operator = null;
                binding.times.setEnabled(true);
                binding.plus.setEnabled(true);
                binding.minus.setEnabled(true);
                binding.divide.setEnabled(true);
                lenghtSecondNumber = 0;

            }
        });

        binding.zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnEqualPressed) {
                    binding.tvFormula.setText("0");
                    binding.tvResult.setText("");
                    lenghtSecondNumber = 1;
                    operator = null;
                    btnEqualPressed = false;
                } else {
                    procces = binding.tvFormula.getText().toString();
                    binding.tvFormula.setText(procces + "0");
                    if(operator != null) {
                        lenghtSecondNumber++;
                    }
                }
                zeroPressed = true;
            }
        });

        binding.one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnEqualPressed) {
                    binding.tvFormula.setText("1");
                    binding.tvResult.setText("");
                    lenghtSecondNumber = 0;
                    operator = null;
                    btnEqualPressed = false;
                } else {
                    procces = binding.tvFormula.getText().toString();
                    if(zeroPressed && lenghtSecondNumber == 1) {
                        binding.tvFormula.setText(procces.substring(0, procces.length()-1) + "1");
                        lenghtSecondNumber = 0;
                        zeroPressed = false;
                    } else {
                        binding.tvFormula.setText(procces + "1");
                    }
                    if(operator != null) {
                        lenghtSecondNumber++;
                    }
                }
            }
        });

        binding.two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnEqualPressed) {
                    binding.tvFormula.setText("2");
                    binding.tvResult.setText("");
                    lenghtSecondNumber = 0;
                    operator = null;
                    btnEqualPressed = false;
                } else {
                    procces = binding.tvFormula.getText().toString();
                    if(zeroPressed && lenghtSecondNumber == 1) {
                        binding.tvFormula.setText(procces.substring(0, procces.length()-1) + "2");
                        lenghtSecondNumber = 0;
                        zeroPressed = false;
                    } else {
                        binding.tvFormula.setText(procces + "2");
                    }
                    if(operator != null) {
                        lenghtSecondNumber++;
                    }
                }
            }
        });

        binding.three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnEqualPressed) {
                    binding.tvFormula.setText("3");
                    binding.tvResult.setText("");
                    lenghtSecondNumber = 0;
                    operator = null;
                    btnEqualPressed = false;
                } else {
                    procces = binding.tvFormula.getText().toString();
                    if(zeroPressed && lenghtSecondNumber == 1) {
                        binding.tvFormula.setText(procces.substring(0, procces.length()-1) + "3");
                        lenghtSecondNumber = 0;
                        zeroPressed = false;
                    } else {
                        binding.tvFormula.setText(procces + "3");
                    }
                    if(operator != null) {
                        lenghtSecondNumber++;
                    }
                }
            }
        });

        binding.four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnEqualPressed) {
                    binding.tvFormula.setText("4");
                    binding.tvResult.setText("");
                    lenghtSecondNumber = 0;
                    operator = null;
                    btnEqualPressed = false;
                } else {
                    procces = binding.tvFormula.getText().toString();
                    if(zeroPressed && lenghtSecondNumber == 1) {
                        binding.tvFormula.setText(procces.substring(0, procces.length()-1) + "4");
                        lenghtSecondNumber = 0;
                        zeroPressed = false;
                    } else {
                        binding.tvFormula.setText(procces + "4");
                    }
                    if(operator != null) {
                        lenghtSecondNumber++;
                    }
                }
            }
        });

        binding.five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnEqualPressed) {
                    binding.tvFormula.setText("5");
                    binding.tvResult.setText("");
                    lenghtSecondNumber = 0;
                    operator = null;
                    btnEqualPressed = false;
                } else {
                    procces = binding.tvFormula.getText().toString();
                    if(zeroPressed && lenghtSecondNumber == 1) {
                        binding.tvFormula.setText(procces.substring(0, procces.length()-1) + "5");
                        lenghtSecondNumber = 0;
                        zeroPressed = false;
                    } else {
                        binding.tvFormula.setText(procces + "5");
                    }
                    if(operator != null) {
                        lenghtSecondNumber++;
                    }
                }
            }
        });

        binding.six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnEqualPressed) {
                    binding.tvFormula.setText("6");
                    binding.tvResult.setText("");
                    lenghtSecondNumber = 0;
                    operator = null;
                    btnEqualPressed = false;
                } else {
                    procces = binding.tvFormula.getText().toString();
                    if(zeroPressed && lenghtSecondNumber == 1) {
                        binding.tvFormula.setText(procces.substring(0, procces.length()-1) + "6");
                        lenghtSecondNumber = 0;
                        zeroPressed = false;
                    } else {
                        binding.tvFormula.setText(procces + "6");
                    }
                    if(operator != null) {
                        lenghtSecondNumber++;
                    }
                }
            }
        });

        binding.seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnEqualPressed) {
                    binding.tvFormula.setText("7");
                    binding.tvResult.setText("");
                    lenghtSecondNumber = 0;
                    operator = null;
                    btnEqualPressed = false;
                } else {
                    procces = binding.tvFormula.getText().toString();
                    if(zeroPressed && lenghtSecondNumber == 1) {
                        binding.tvFormula.setText(procces.substring(0, procces.length()-1) + "7");
                        lenghtSecondNumber = 0;
                        zeroPressed = false;
                    } else {
                        binding.tvFormula.setText(procces + "7");
                    }
                    if(operator != null) {
                        lenghtSecondNumber++;
                    }
                }
            }
        });

        binding.eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnEqualPressed) {
                    binding.tvFormula.setText("8");
                    binding.tvResult.setText("");
                    lenghtSecondNumber = 0;
                    operator = null;
                    btnEqualPressed = false;
                } else {
                    procces = binding.tvFormula.getText().toString();
                    if(zeroPressed && lenghtSecondNumber == 1) {
                        binding.tvFormula.setText(procces.substring(0, procces.length()-1) + "8");
                        lenghtSecondNumber = 0;
                        zeroPressed = false;
                    } else {
                        binding.tvFormula.setText(procces + "8");
                    }
                    if(operator != null) {
                        lenghtSecondNumber++;
                    }
                }
            }
        });

        binding.nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnEqualPressed) {
                    binding.tvFormula.setText("9");
                    binding.tvResult.setText("");
                    lenghtSecondNumber = 0;
                    operator = null;
                    btnEqualPressed = false;
                } else {
                    procces = binding.tvFormula.getText().toString();
                    if(zeroPressed && lenghtSecondNumber == 1) {
                        binding.tvFormula.setText(procces.substring(0, procces.length()-1) + "9");
                        lenghtSecondNumber = 0;
                        zeroPressed = false;
                    } else {
                        binding.tvFormula.setText(procces + "9");
                    }
                    if(operator != null) {
                        lenghtSecondNumber++;
                    }
                }
            }
        });

        binding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnEqualPressed) {
                    String first = binding.tvResult.getText().toString();
                    binding.tvResult.setText("");
                    binding.tvFormula.setText(first + "+");
                    operator = "+";
                    lenghtSecondNumber = 0;
                    btnEqualPressed = false;
                } else {
                    if(lenghtSecondNumber == 0){
                        procces = binding.tvFormula.getText().toString();
                        if(operator == null){
                            binding.tvFormula.setText(procces + "+");
                        } else {
                            binding.tvFormula.setText(procces.substring(0, procces.length()-1) + "+");
                        }
                        operator = "+";
                    } else {
                        binding.times.setEnabled(false);
                        binding.minus.setEnabled(false);
                        binding.divide.setEnabled(false);

                    }
                }
            }
        });

        binding.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnEqualPressed) {
                    String first = binding.tvResult.getText().toString();
                    binding.tvResult.setText("");
                    binding.tvFormula.setText(first + "-");
                    operator = "-";
                    lenghtSecondNumber = 0;
                    btnEqualPressed = false;
                } else {
                    if(lenghtSecondNumber == 0){
                        procces = binding.tvFormula.getText().toString();
                        if(operator == null){
                            binding.tvFormula.setText(procces + "-");
                        } else {
                            binding.tvFormula.setText(procces.substring(0, procces.length()-1) + "-");
                        }
                        operator = "+";
                    } else {
                        binding.times.setEnabled(false);
                        binding.plus.setEnabled(false);
                        binding.divide.setEnabled(false);

                    }
                }
            }
        });

        binding.divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnEqualPressed) {
                    String first = binding.tvResult.getText().toString();
                    binding.tvResult.setText("");
                    binding.tvFormula.setText(first + "/");
                    operator = "/";
                    lenghtSecondNumber = 0;
                    btnEqualPressed = false;
                } else {
                    if(lenghtSecondNumber == 0){
                        procces = binding.tvFormula.getText().toString();
                        if(operator == null){
                            binding.tvFormula.setText(procces + "/");
                        } else {
                            binding.tvFormula.setText(procces.substring(0, procces.length()-1) + "/");
                        }
                        operator = "+";
                    } else {
                        binding.times.setEnabled(false);
                        binding.minus.setEnabled(false);
                        binding.divide.setEnabled(false);

                    }
                }
            }
        });

        binding.times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnEqualPressed) {
                    String first = binding.tvResult.getText().toString();
                    binding.tvResult.setText("");
                    binding.tvFormula.setText(first + "x");
                    operator = "x";
                    lenghtSecondNumber = 0;
                    btnEqualPressed = false;
                } else {
                    if(lenghtSecondNumber == 0){
                        procces = binding.tvFormula.getText().toString();
                        if(operator == null){
                            binding.tvFormula.setText(procces + "x");
                        } else {
                            binding.tvFormula.setText(procces.substring(0, procces.length()-1) + "x");
                        }
                        operator = "+";
                    } else {
                        binding.plus.setEnabled(false);
                        binding.minus.setEnabled(false);
                        binding.divide.setEnabled(false);

                    }
                }
            }
        });

        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = binding.tvFormula.getText().toString();
                int input = word.length();
                if(btnEqualPressed) {
                    binding.tvFormula.setText("");
                    operator = null;
                    btnEqualPressed = false;
                } else {
                    if(input > 0 ) {
                        binding.tvFormula.setText(word.substring(0, input -1));
                        binding.times.setEnabled(true);
                        binding.plus.setEnabled(true);
                        binding.minus.setEnabled(true);
                        binding.divide.setEnabled(true);
                        if(lenghtSecondNumber == 0) {
                            operator = null;
                            binding.times.setEnabled(true);
                            binding.plus.setEnabled(true);
                            binding.minus.setEnabled(true);
                            binding.divide.setEnabled(true);
                        } else {
                            lenghtSecondNumber--;
                        }
                    }
                }
            }
        });

        binding.equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnEqualPressed = true;

                double finalResult = 0;
                int finalResult2 = 0;
                procces = binding.tvFormula.getText().toString();
                String hasil[] = procces.split("[+x/-]");

                try {
                    double satu = Integer.parseInt(hasil[0]);
                    double dua = Integer.parseInt(hasil[1]);

                    if(operator == "x"){
                        finalResult2 = (int) satu * (int) dua;
                        binding.tvResult.setText(String.valueOf(finalResult2));
                    } else if(operator == "+"){
                        finalResult2 = (int) satu + (int) dua;
                        binding.tvResult.setText(String.valueOf(finalResult2));
                    } else if(operator == "-"){
                        finalResult2 = (int) satu - (int) dua;
                        binding.tvResult.setText(String.valueOf(finalResult2));
                    } else if(operator == "/") {
                        if (dua == 0) {
                            binding.tvResult.setText("");
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        } else if (satu == 0 && dua == 0) {
                            binding.tvResult.setText("");
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        } else {
                            finalResult = satu / dua;
                            DecimalFormat format = new DecimalFormat("0.###");
                            String Tiga = (format.format(finalResult));
                            binding.tvResult.setText(Tiga);
                        }
                    }
                } catch (Exception e) {

                }
            }
        });



    }
}
