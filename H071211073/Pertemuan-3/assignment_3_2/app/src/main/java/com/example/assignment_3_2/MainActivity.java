package com.example.assignment_3_2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imgpick;
    EditText edittext;
    TextView tvname, tvscore;
    Button btnap, btnpl;
    ActivityResultLauncher<Intent> imageCaptureLauncher;
    ActivityResultLauncher<Intent> resultLauncher;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgpick = findViewById(R.id.imgpick);
        edittext = findViewById(R.id.edittext);
        tvname = findViewById(R.id.tvname);
        tvscore = findViewById(R.id.tvscore);
        btnap = findViewById(R.id.btnap);
        btnpl = findViewById(R.id.btnpl);
        user = new User();

        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        assert result.getData() != null;
                        user = result.getData().getParcelableExtra("user");
                        tvscore.setText("Best Score: " + String.valueOf(user.getbScore()));
                        user.setbScore(user.getbScore());
                        btnpl.setText("play again!");
                    }
                });

        imageCaptureLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        user.setImgUri(uri.toString());
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imgpick.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        imgpick.setOnClickListener(view -> onBtnSelectImageClicked());

        btnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setName(String.valueOf(edittext.getText()));

                //handling if edittext is empty
                if (TextUtils.isEmpty(edittext.getText().toString())){
                    Toast.makeText(MainActivity.this, "Please fill in your name!", Toast.LENGTH_SHORT).show();
                } else {
                    edittext.setVisibility(View.GONE);
                    tvname.setVisibility(View.VISIBLE);
                    tvname.setText(user.getName());
                    tvscore.setVisibility(View.VISIBLE);
                    btnap.setVisibility(View.GONE);
                    btnpl.setVisibility(View.VISIBLE);
                }

            }
        });


    }

    public void onBtnSelectImageClicked () {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        imageCaptureLauncher.launch(Intent.createChooser(intent, "Pilih Gambar"));
    }

    public void play(View view) {
        Intent toQuiz = new Intent(MainActivity.this, QuizActivity.class);
        toQuiz.putExtra("user", user);
        resultLauncher.launch(toQuiz);
    }
}