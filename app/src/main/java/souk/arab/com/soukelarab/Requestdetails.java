package souk.arab.com.soukelarab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class Requestdetails extends AppCompatActivity {
RecyclerView recycle_requestsdetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestdetails);
        recycle_requestsdetails =(RecyclerView)findViewById(R.id.recycle_requestsdetails);
    }
}
