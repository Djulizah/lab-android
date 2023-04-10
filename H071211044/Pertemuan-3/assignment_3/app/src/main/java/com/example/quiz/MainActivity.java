package com.example.quiz;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etusername;
    private Button btnapply;
    private User user;
    private ImageView profil;
    private ActivityResultLauncher<Intent> imageCaptureLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set view
        profil = findViewById(R.id.profile);
        btnapply = findViewById(R.id.apply);
        etusername = findViewById(R.id.username);
        // image handler
        imageCaptureLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    // utk mnghandle data yg dikembalikan galeri
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            user.setImageUri(uri.toString());
                            profil.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        //mmbuat user baru
        user = new User();
        user.setBestscore(0);

        //utk buka galeri
        profil.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            imageCaptureLauncher.launch(Intent.createChooser(intent, "Pilih Image"));

        });
        // untuk pindah ke main activity 2 dan mengirim nama yang telah di input
        btnapply.setOnClickListener(v -> {
            user.setUsername(String.valueOf(etusername.getText().toString()));
            if (user.getUsername() == null || user.getUsername().isEmpty()) {
                etusername.setError("Tidak boleh kosong!");
                Toast.makeText(getApplicationContext(), "isi nama tsayy !", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(MainActivity2.USER_KEY, user);
                startActivity(intent);
            }
        });
    }
}