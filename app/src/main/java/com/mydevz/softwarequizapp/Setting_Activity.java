package com.mydevz.softwarequizapp;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.softwarequizapp.R;

public class Setting_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }
}








//        //public void main_btn (View view){
//            Button btn = (Button) view;
//            if (R.id.btn_play == btn.getId()) {
//                startActivity(new Intent(MainActivity.this, playActivity.class));
//            } else if (R.id.btn_setting == btn.getId()) {
//                startActivity(new Intent(MainActivity.this, settingActivity.class));
//            } else if (R.id.btn_exit == btn.getId()) {
//                this.finishAffinity();
//            }





// switch (view.getId()){
// case R.id.btn_play:
// startActivity(new Intent(MainActivity.this , playActivity.class));
// break;
//  case R.id.btn_setting:
// startActivity(new Intent(MainActivity.this , settingActivity.class));

//  break;
//case R.id.btn_exit:
//this.finishAffinity();
// break;
// }




/*

    //btn_play = findViewById(R.id.btn_play);
    //two = findViewById(R.id.btn_setting);
    //three = findViewById(R.id.btn_exit);

        btn_play.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openplayActivity();
        }
    });
}

    public void openplayActivity() {
        Intent intent = new Intent(this, playActivity.class);
        startActivity(intent);

}
*/









//btn_play = findViewById(R.id.btn_play);
//two.setOnClickListener(new Clik());
//three.setOnClickListener(new Clik());
//}
//}

//    //public class Clik implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//                Button btn = (Button) v;
//                if (R.id.btn_play == btn.getId()) {
//                    startActivity(new Intent(MainActivity.this, playActivity.class));
//                } else if (R.id.btn_setting == btn.getId()) {
//                    startActivity(new Intent(MainActivity.this, settingActivity.class));
//                } else if (R.id.btn_exit == btn.getId()) {
//                    finishAffinity();
//
//            }

// }
//}
