package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText homeTeamInput;
    private EditText awayTemaInput;
    private ImageView homeLogo;
    private ImageView awayLogo;
    private Button buttonTeam;
    private Uri awayImg;
    private Uri homeImg;
    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int HOME_REQUEST_CODE = 1;
    private static final int AWAY_REQUEST_CODE = 2;
    private String hometeam;
    private String awayteam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
        homeTeamInput = findViewById(R.id.home_team);
        awayTemaInput = findViewById(R.id.away_team);
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);
        buttonTeam = findViewById(R.id.btn_team);

        buttonTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hometeam = homeTeamInput.getText().toString();
                awayteam = awayTemaInput.getText().toString();
                Intent intent = new Intent(MainActivity.this, MatchActivity.class);
                intent.putExtra("namahome", hometeam);
                intent.putExtra("namaaway", awayteam);
                intent.putExtra("homeImg", homeImg.toString());
                intent.putExtra("awayImg", awayImg.toString());
                startActivity(intent);
            }
        });

        homeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), HOME_REQUEST_CODE);
            }
        });
        awayLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), AWAY_REQUEST_CODE);
            }
        });
    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(resultCode == RESULT_CANCELED){
                Log.d(TAG, "Piliih gambar dicancel");
                return;
            }else if(requestCode == HOME_REQUEST_CODE ){
                if (data != null){
                    try {
                        Uri imageUri = data.getData();
                        homeImg = imageUri;
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                        homeLogo.setImageBitmap(bitmap);
                    }
                    catch (IOException error){
                        Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, error.getMessage());
                    }
                }

            }else if (requestCode == AWAY_REQUEST_CODE){
                if (data != null){
                    try {
                        Uri imageUri = data.getData();
                        awayImg = imageUri;
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                        awayLogo.setImageBitmap(bitmap);
                    }
                    catch (IOException error){
                        Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, error.getMessage());
                    }
                }
            }
    }
}
