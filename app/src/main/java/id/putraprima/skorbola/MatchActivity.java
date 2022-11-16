package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {
    private TextView txt_home;
    private ImageView home_logo;
    private TextView score_home;
    private Button btn_add_home;
    private Button btn_add_home2;
    private Button btn_add_home3;
    private TextView txt_away;
    private ImageView away_logo;
    private TextView score_away;
    private Button btn_add_away;
    private Button btn_add_away2;
    private Button btn_add_away3;
    private Button btn_result;
    private Button btn_reset;
    private int home_score;
    private int away_score;
    private String home_team;
    private String away_team;
    private String the_winner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
        txt_home = findViewById(R.id.txt_home);
        home_logo = findViewById(R.id.home_logo);
        score_home = findViewById(R.id.score_home);
        btn_add_home = findViewById(R.id.btn_add_home);
        btn_add_home2 = findViewById(R.id.btn_add_home2);
        btn_add_home3 = findViewById(R.id.btn_add_home3);
        txt_away = findViewById(R.id.txt_away);
        away_logo = findViewById(R.id.away_logo);
        score_away = findViewById(R.id.score_away);
        btn_add_away = findViewById(R.id.btn_add_away);
        btn_add_away2 = findViewById(R.id.btn_add_away2);
        btn_add_away3 = findViewById(R.id.btn_add_away3);
        btn_result = findViewById(R.id.btn_result);
        btn_reset = findViewById(R.id.btn_reset);

        home_score = 0;
        away_score = 0;
        score_home.setText(String.valueOf(home_score));
        score_away.setText(String.valueOf(away_score));

        Bundle bundle = getIntent().getExtras();
        home_team = bundle.getString("namahome");
        txt_home.setText(home_team);
        away_team = bundle.getString("namaaway");
        txt_away.setText(away_team);

        home_logo.setImageURI(Uri.parse(bundle.getString("homeImg")));
        away_logo.setImageURI(Uri.parse(bundle.getString("awayImg")));

        btn_add_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_score += 1;
                score_home.setText((String.valueOf(home_score)));
            }
        });

        btn_add_home2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_score += 2;
                score_home.setText((String.valueOf(home_score)));
            }
        });

        btn_add_home3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_score += 3;
                score_home.setText((String.valueOf(home_score)));
            }
        });

        btn_add_away.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                away_score += 1;
                score_away.setText((String.valueOf(away_score)));
            }
        });

        btn_add_away2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                away_score += 2;
                score_away.setText((String.valueOf(away_score)));
            }
        });

        btn_add_away3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                away_score += 3;
                score_away.setText((String.valueOf(away_score)));
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_score = 0;
                away_score = 0;
                score_home.setText((String.valueOf(home_score)));
                score_away.setText((String.valueOf(away_score)));
            }
        });

        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                the_winner = "empty";
                if (home_score < away_score) {
                    the_winner = away_team;
                } else if (home_score > away_score) {
                    the_winner = home_team;
                } else {
                    the_winner = "No One, Because of a Draw";
                }

                Intent intent = new Intent(MatchActivity.this, ResultActivity.class);
                intent.putExtra("thewinner", the_winner);
                startActivity(intent);
            }
        });

    }
}
