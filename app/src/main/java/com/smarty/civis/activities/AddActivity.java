package com.smarty.civis.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.smarty.civis.R;

import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    private TextInputLayout title_textInputLayout;
    private AutoCompleteTextView title_textview;
    private TextInputLayout desc_textInputLayout;
    private AutoCompleteTextView desc_textview;
    private TextInputLayout price_textInputLayout;
    private AutoCompleteTextView price_textview;
    private EditText dueDate_edittext;
    private AppCompatSpinner types_spinner;

    private Date dueDate = Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_textInputLayout = (TextInputLayout) findViewById(R.id.til_add_form_title);
        title_textview = (AutoCompleteTextView) findViewById(R.id.tv_add_form_title);
        desc_textInputLayout = (TextInputLayout) findViewById(R.id.til_add_form_desc);
        desc_textview = (AutoCompleteTextView) findViewById(R.id.tv_add_form_desc);
        price_textInputLayout = (TextInputLayout) findViewById(R.id.til_add_form_price);
        price_textview = (AutoCompleteTextView) findViewById(R.id.tv_add_form_price);
        dueDate_edittext = (EditText) findViewById(R.id.et_add_form_date);

        initActionBar();
        initTypesSpinner();


    }

    /**
     * Init Action Bar [ Title and buttons ]
     */
    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.add_screen_activity_title));

        }
    }

    /**
     * Init Service Types
     */
    private void initTypesSpinner() {
        types_spinner = (AppCompatSpinner) findViewById(R.id.spinner_add_form_types);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.add_form_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        types_spinner.setAdapter(adapter);
    }

    /**
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return false;
    }

    /*
        Pick Date
     */
    @RequiresApi(24)
    public void pickDate(final View view) {
        final Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                ((EditText) view).setText(selectedyear + "/" + selectedmonth + "/" + selectedday);
                mcurrentDate.set(selectedyear, selectedmonth, selectedday);
                dueDate = mcurrentDate.getTime();
            }
        }, mYear, mMonth, mDay);
        mDatePicker.show();
    }

    /*
            Add Service
         */
    public void addService(View view) {
        boolean success = true;

        String title = title_textview.getText().toString();
        if (title.trim().isEmpty()) {
            title_textInputLayout.setError(getString(R.string.error_empty_title));
            success = false;
        }

        String desc = desc_textview.getText().toString();
        if (desc.trim().isEmpty()) {
            desc_textInputLayout.setError(getString(R.string.error_empty_desc));
            success = false;
        }

        double price;
        try {
            String value = price_textview.getText().toString();
            price = Integer.parseInt(value);
        } catch (Exception err) {
            price_textInputLayout.setError(getString(R.string.error_wrong_price));
            success = false;
        }
        if (dueDate.before(Calendar.getInstance().getTime())) {
            Toast.makeText(this, getString(R.string.error_wrong_date), Toast.LENGTH_LONG).show();
            success = false;
        }

        String name = types_spinner.getSelectedItem().toString();
        if (name == null) {
            Toast.makeText(this, getString(R.string.error_select_service_type), Toast.LENGTH_LONG).show();
            success = false;
        }

        if (success) {
            // TODO save data to db or send it to model for processing
            Toast.makeText(this, getString(R.string.success_add_form), Toast.LENGTH_LONG).show();
            finish();
        }

    }
}
