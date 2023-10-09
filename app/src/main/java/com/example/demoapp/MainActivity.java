package com.example.demoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoapp.data.DbHandler;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btnAddNote, btnSort;
    ListView lv;
    String[] msg;
    int[] id;
    DbHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DbHandler(MainActivity.this);

        btnAddNote = findViewById(R.id.btnAddNote);

        btnSort = findViewById(R.id.btnSort);



        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialogButtonClicked(view);
            }
        });
        findid();
        displaydata();
    }


    private void displaydata() {
        SQLiteDatabase sqLiteDatabase = dbHandler.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select *from notes", null);
        if (cursor.getCount() > 0) {
            id = new int[cursor.getCount()];
            msg = new String[cursor.getCount()];
            int i = 0;

            while (cursor.moveToNext()) {
                id[i] = cursor.getInt(0);
                msg[i] = cursor.getString(1);
                i++;
            }
            CustAdapter custAdapter = new CustAdapter();
            lv.setAdapter(custAdapter);
        }
    }

    private void findid() {
        lv = (ListView) findViewById(R.id.lv);
    }

    private class CustAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return msg.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView txtMsg;
            ImageButton btnEdit, btnDelete;
            CardView cardview;
            view = LayoutInflater.from(MainActivity.this).inflate(R.layout.single_data, viewGroup, false);
            txtMsg = view.findViewById(R.id.txtMsg);
            btnEdit = view.findViewById(R.id.btnEdit);
            btnDelete = view.findViewById(R.id.btnDelete);
            cardview = view.findViewById(R.id.cardview);


            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            txtMsg.setText(msg[i]);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateView(v, id[i]);
                    displaydata();
                }
            });
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog(i);
                }
            });
            return view;
        }


    }


    /*------------------------------------------------------------------------*/
    public void showAlertDialogButtonClicked(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Note");

        final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
        builder.setView(customLayout);

        builder.setPositiveButton("OK", (dialog, which) -> {
            EditText editText = customLayout.findViewById(R.id.edtAddNote);
            String msg = editText.getText().toString();
            if (msg.isEmpty()) {
                Toast.makeText(this, "Empty Message", Toast.LENGTH_SHORT).show();
            } else {

                Date currentTime = Calendar.getInstance().getTime();
                dbHandler.addNotes(msg, currentTime.toString());
                Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show();
                displaydata();
            }
            sendDialogDataToActivity(editText.getText().toString());
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void sendDialogDataToActivity(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }


    /*update value */
    public void updateView(View view, int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update Note");

        final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
        builder.setView(customLayout);

        builder.setPositiveButton("OK", (dialog, which) -> {
            EditText editText = customLayout.findViewById(R.id.edtAddNote);
            String msg = editText.getText().toString();
            if (msg.isEmpty()) {
                Toast.makeText(this, "Empty Message", Toast.LENGTH_SHORT).show();
            } else {
                dbHandler.update(id, msg);
                Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();
                displaydata();
            }
            sendDialogDataToActivity(editText.getText().toString());
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /*are you sure you want to logout alert dialog*/

    void alertDialog(int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("Do you want to delete?");

        builder.setTitle("Alert !");

        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            SQLiteDatabase sqLiteDatabase = dbHandler.getReadableDatabase();
            long recremove = sqLiteDatabase.delete("notes", "id=" + id[i], null);
            if (recremove != -1) {
                Toast.makeText(MainActivity.this, "successfully delete", Toast.LENGTH_SHORT).show();
                displaydata();
            }
        });


        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}