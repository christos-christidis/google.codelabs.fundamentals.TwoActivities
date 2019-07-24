package com.fundamentals.twoactivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String EXTRA_MESSAGE = "com.fundamentals.twoactivities.extra.MESSAGE";
    static final String EXTRA_REPLY = "com.fundamentals.twoactivities.extra.REPLY";

    private EditText mReplyEditText;

    static Intent newIntent(Context context, String message) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mReplyEditText = findViewById(R.id.editText_second);

        String message = getIntent().getStringExtra(EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.text_message);
        textView.setText(message);
    }

    public void returnReply(View view) {
        String reply = mReplyEditText.getText().toString();

        Intent intent = new Intent();
        intent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK, intent);

        finish();
    }
}
