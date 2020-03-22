package com.kr.todoapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import com.kr.todoapplication.R;
import com.kr.todoapplication.model.TodoItem;
import com.kr.todoapplication.persistance.TodoItemRepository;

import java.util.Calendar;
import java.util.Date;

public class ItemFormActivity extends AppCompatActivity {

    private static final String TAG = "ItemFormActivity";

    Button confirmButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_form);

        confirmButton = findViewById(R.id.if_create_update_button);
        cancelButton = findViewById(R.id.if_cancel_button);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String header = ((EditText) findViewById(R.id.if_header)).getText().toString();
                String content = ((EditText) findViewById(R.id.if_content)).getText().toString();
                boolean isImportant = ((CheckBox) findViewById(R.id.if_important)).isChecked();
                Date dueDate = getDateFromDatePicker((DatePicker) findViewById(R.id.if_date));
                TodoItem todoItem = new TodoItem(header, content, isImportant, dueDate);

                TodoItemRepository.getInstance().save(todoItem);

                startActivity(new Intent(ItemFormActivity.this, ItemViewerActivity.class));
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}
