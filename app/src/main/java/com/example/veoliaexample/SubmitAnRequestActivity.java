package com.example.veoliaexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class SubmitAnRequestActivity extends AppCompatActivity {

    private static final String EXTRA_USER_NAME = "userName";

    private TextView textViewChoose;
    private TextView textViewWho;

    private RadioGroup radioGroupChoose;
    private RadioButton radioButtonAnywhere;
    private RadioButton radioButtonRearPositions;

    private CheckBox checkBoxMedic;
    private CheckBox checkBoxAirDefense;
    private CheckBox checkBoxRifleman;

    private Spinner spinnerFront;
    private Spinner spinnerRear;

    private Button buttonGoOn;

    private String positions;
    private String userName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_an_request);
        initViews();
        setupUserName();

        radioGroupChoose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == radioButtonAnywhere.getId()) {
                    onUserChooseAnywhere();
                } else if (checkedId == radioButtonRearPositions.getId())
                    onUserChooseRearPositions();
            }
        });
        radioButtonAnywhere.setChecked(true);

        buttonGoOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserGoOn();
            }
        });
    }

    private void onUserGoOn() {
        ArrayList<String> whatDoYouWantToBe = new ArrayList<>();
        if (checkBoxAirDefense.isChecked()) {
            whatDoYouWantToBe.add(checkBoxAirDefense.getText().toString());
        }
        if (checkBoxMedic.isChecked()) {
            whatDoYouWantToBe.add(checkBoxMedic.getText().toString());
        }
        if (radioButtonAnywhere.isChecked() && checkBoxRifleman.isChecked()) {
            whatDoYouWantToBe.add(checkBoxRifleman.getText().toString());
        }

        String typeOfWeapon = "";
        if (radioButtonAnywhere.isChecked()) {
            typeOfWeapon = spinnerFront.getSelectedItem().toString();
        } else if (radioButtonRearPositions.isChecked()) {
            typeOfWeapon = spinnerRear.getSelectedItem().toString();
        }

        Intent intent = InformationDetailActivity.newIntent(this,
                userName,
                positions,
                typeOfWeapon,
                whatDoYouWantToBe.toString()
        );
        startActivity(intent);
    }

    private void onUserChooseAnywhere() {
        userName = getIntent().getStringExtra(EXTRA_USER_NAME);
        String whatDoYouWantToBeLabel = getString(R.string.what_do_you_want_to_be, userName);
        textViewWho.setText(whatDoYouWantToBeLabel);
        positions = getString(R.string.anywhere);
        checkBoxRifleman.setVisibility(View.VISIBLE);
        spinnerFront.setVisibility(View.VISIBLE);
        spinnerRear.setVisibility(View.INVISIBLE);
    }

    private void onUserChooseRearPositions() {
        userName = getIntent().getStringExtra(EXTRA_USER_NAME);
        String whatDoYouWantToBeLabel = getString(R.string.what_do_you_want_to_be, userName);
        textViewWho.setText(whatDoYouWantToBeLabel);
        positions = getString(R.string.want_to_be_efficient_in_rear);
        checkBoxRifleman.setVisibility(View.INVISIBLE);
        spinnerFront.setVisibility(View.INVISIBLE);
        spinnerRear.setVisibility(View.VISIBLE);
    }

    private void setupUserName() {
        userName = getIntent().getStringExtra(EXTRA_USER_NAME);
        String where_you_want = getString(R.string.where_you_want, userName);
        textViewChoose.setText(where_you_want);
    }

    private void initViews() {
        textViewChoose = findViewById(R.id.textViewChoose);
        textViewWho = findViewById(R.id.textViewWho);

        radioGroupChoose = findViewById(R.id.radioGroupChoose);
        radioButtonAnywhere = findViewById(R.id.radioButtonAnywhere);
        radioButtonRearPositions = findViewById(R.id.radioButtonRearPositions);

        checkBoxMedic = findViewById(R.id.checkBoxMedic);
        checkBoxAirDefense = findViewById(R.id.checkBoxAirDefense);
        checkBoxRifleman = findViewById(R.id.checkBoxRifleman);

        spinnerFront = findViewById(R.id.spinnerFront);
        spinnerRear = findViewById(R.id.spinnerRear);

        buttonGoOn = findViewById(R.id.buttonGoOn);
    }

    public static Intent newIntent(Context context, String userName) {
        Intent intent = new Intent(context,SubmitAnRequestActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        return intent;
    }
}