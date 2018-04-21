package souk.arab.com.soukelarab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import Adapter.AllRecyclePro;
import Adapter.AllTrader;
import Adapter.BestTrading;
import Model.RequestsModel;

public class MatgerDetaise extends AppCompatActivity {
    private RecyclerView besttader;
    private ArrayList<RequestsModel> faqModels;
    private RecyclerView proRc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matger_detaise);
        getBestTrader();
        setUpAll();
    }

    public void getBestTrader(){
        besttader=(RecyclerView)findViewById(R.id.recDept);
        BestTrading adapter = new BestTrading(MatgerDetaise.this,faqModels);
        besttader.setLayoutManager(new LinearLayoutManager(MatgerDetaise.this, LinearLayoutManager.HORIZONTAL, false));
        besttader.setAdapter(adapter);
    }

    public void setUpAll(){
        proRc =(RecyclerView)findViewById(R.id.proRc);
        AllRecyclePro adapter = new AllRecyclePro(MatgerDetaise.this,faqModels);
        proRc.setLayoutManager(new GridLayoutManager(MatgerDetaise.this,2));
        proRc.setAdapter(adapter);
    }
}
