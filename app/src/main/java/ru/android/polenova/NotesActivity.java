package ru.android.polenova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NotesActivity extends AppCompatActivity {

    private EditText mInputNote;
    private SharedPreferences myNoteSharedPref;
    private static String NOTE_TEXT = "note_text";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        initViews();
        getDateFromSharedPref();
    }

    private void getDateFromSharedPref(){
        String noteTxt = myNoteSharedPref.getString(NOTE_TEXT, "");
        mInputNote.setText(noteTxt);
    }

    public void initViews(){
        mInputNote = findViewById(R.id.inputNote);
        Button mBtnSaveNote = findViewById(R.id.btnSaveNote);
        myNoteSharedPref = getSharedPreferences("myNote", MODE_PRIVATE);
        mBtnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteTxt = mInputNote.getText().toString();
                SharedPreferences.Editor myEditor = myNoteSharedPref.edit();
                myEditor.putString(NOTE_TEXT, noteTxt);
                myEditor.apply();
                Toast.makeText(NotesActivity.this, getString(R.string.toast_save), Toast.LENGTH_LONG).show();
            }
        });
    }
}
