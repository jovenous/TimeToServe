package com.example.veoliaexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InformationDetailActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewChoose;
    private TextView textViewWeapon;
    private TextView textViewWho;

    private static final String EXTRA_USER_NAME = "userName";
    private static final String EXTRA_POSITIONS = "positions";
    private static final String EXTRA_TYPE_OF_WEAPON = "typeOfWeapon";
    private static final String EXTRA_WHAT_DO_YOU_WANT_TO_BE = "whatDoYouWantToBe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_detail);
        initViews();

        Intent intent = getIntent();
        textViewName.setText(intent.getStringExtra(EXTRA_USER_NAME));
        textViewChoose.setText(intent.getStringExtra(EXTRA_POSITIONS));
        textViewWeapon.setText(intent.getStringExtra(EXTRA_TYPE_OF_WEAPON));
        textViewWho.setText(intent.getStringExtra(EXTRA_WHAT_DO_YOU_WANT_TO_BE));
    }


    private void initViews() {
        textViewName = findViewById(R.id.textViewName);
        textViewChoose = findViewById(R.id.textViewChoose);
        textViewWeapon = findViewById(R.id.textViewWeapon);
        textViewWho = findViewById(R.id.textViewWho);
    }

    public static Intent newIntent(
            Context context,
            String userName,
            String positions,
            String typeOfWeapon,
            String whatDoYouWantToBe
    ) {
        Intent intent = new Intent(context, InformationDetailActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        intent.putExtra(EXTRA_POSITIONS, positions);
        intent.putExtra(EXTRA_TYPE_OF_WEAPON, typeOfWeapon);
        intent.putExtra(EXTRA_WHAT_DO_YOU_WANT_TO_BE, whatDoYouWantToBe);
        return intent;
    }
}