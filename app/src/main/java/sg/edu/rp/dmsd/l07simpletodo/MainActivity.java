package sg.edu.rp.dmsd.l07simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner s1;
    EditText e1;
    Button b1;
    Button b2;
    Button b3;
    ArrayList<String> sia = new ArrayList<>();
    ListView l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s1 = findViewById(R.id.spinner);
        e1 = findViewById(R.id.editText);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);

        l1 = findViewById(R.id.listView);
        final ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,sia);
        l1.setAdapter(adapter);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sia.add(e1.getText().toString());
                adapter.notifyDataSetChanged();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sia.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_LONG).show();
                    return;
                }
                String text = e1.getText().toString();
                if (text.matches("\\d+(?:\\.\\d+)?")) {
                    int index = Integer.parseInt(text);
                    if (index < sia.size()) {
                        sia.remove(index);
                        adapter.notifyDataSetChanged();
                        return;
                    }
                }
                Toast.makeText(MainActivity.this, "Wrong Index Number", Toast.LENGTH_LONG).show();
                return;

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sia.clear();
                adapter.notifyDataSetChanged();
            }
        });

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        b1.setEnabled(true);
                        b2.setEnabled(false);
                        e1.setHint("Type in a new Task Here");
                        break;
                    case 1:
                        b2.setEnabled(true);
                        b1.setEnabled(false);
                        e1.setHint("Type in the index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


}
