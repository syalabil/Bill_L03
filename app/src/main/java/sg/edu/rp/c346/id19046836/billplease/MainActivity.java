package sg.edu.rp.c346.id19046836.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    TextView totalBill;
    TextView eachPays;
    EditText amount;
    EditText pax;
    EditText discount;
    Button split;
    Button reset;
    ToggleButton gst;
    ToggleButton svs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.editAmount);
        pax  = findViewById(R.id.editNumOfPax);
        discount = findViewById(R.id.editDiscount);
        gst = findViewById(R.id.gst);
        svs = findViewById(R.id.svs);
        split = findViewById(R.id.Split);
        reset = findViewById(R.id.Reset);
        totalBill = findViewById(R.id.textBill);
        eachPays = findViewById(R.id.textEachPays);





        // Split Button
        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount.getText().toString().trim().length()!=0 && pax.getText().toString().trim().length()!=0){
                    double newAmt = 0.0;
                    if(!svs.isChecked() && !gst.isChecked()){
                        newAmt = Double.parseDouble(amount.getText().toString());
                    }
                    else if(svs.isChecked() && !gst.isChecked()){
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.1 ;
                    }
                    if(!svs.isChecked() && gst.isChecked()){
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.07;
                    }
                    if(svs.isChecked() && gst.isChecked()){
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.17 ;
                    }

                    if(discount.getText().toString().trim().length() != 0){
                        newAmt *= 1 -Double.parseDouble(discount.getText().toString()) /100 ;
                    }

                    totalBill.setText("Total Bill: $" + String.format("%.2f",newAmt));
                    int numberPerson = Integer.parseInt(pax.getText().toString());
                    if(numberPerson != 1){
                        eachPays.setText("Each Pays: $" + String.format("%.2f", newAmt / numberPerson));
                    }
                    else {
                        eachPays.setText("Each Pays: $" + newAmt);
                    }



                }






            }
        });


            }
}
