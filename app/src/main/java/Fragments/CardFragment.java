package Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import Adapter.AllTrader;
import Adapter.CardAdapter;
import Adapter.ClientFavAdapter;
import Model.RequestsModel;
import URLS.URLS;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import souk.arab.com.soukelarab.MapOrder;
import souk.arab.com.soukelarab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment {
LinearLayout ripple_confirm,totle;

    private View view;
    private ArrayList<RequestsModel> faqModels;
    private RecyclerView cart;
    private SharedPreferences preferencesid;
    private String user_id;
    private Paint p = new Paint();
    TextView price;
    private CardAdapter adapter;
    private String total;
    int textviewIntPrice;
    private ACProgressFlower dialog;

    public CardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_card, container, false);
        AndroidNetworking.initialize(getActivity());
        preferencesid = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_id = preferencesid.getString("user_id","0000");
        setUpAll();
        allPro();
        setUpClick();

        return view;
    }


public void setUpClick()
    {
        ripple_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MapOrder.class));
            }
        });
    }
    public void setUpAll(){
        faqModels=new ArrayList<>();
        cart =(RecyclerView)view.findViewById(R.id.cart);
        price =(TextView) view.findViewById(R.id.price);
        ripple_confirm =(LinearLayout) view.findViewById(R.id.ripple_confirm);
        totle =(LinearLayout) view.findViewById(R.id.totle);
    }
    // كارت
    public void allPro(){
        dialog = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(getResources().getColor(R.color.colorAccent))
                .fadeColor(getResources().getColor(R.color.colorAccent)).build();
        dialog.show();
        faqModels.clear();
        AndroidNetworking.post(URLS.cart)
                .addBodyParameter("user_id",user_id)
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog.dismiss();
                        // do anything with response
                        try {
                            String success = response.getString("success");
                            if (success.equals("1")){
                                 total = response.getString("total");
                                price.setText(total);
                                JSONArray categores = response.getJSONArray("products");
                                if (categores.length()==0){

//                                    ripple_confirm.setVisibility(View.GONE);
//                                    totle.setVisibility(View.GONE);
//                                    Toast.makeText(getActivity(), "لأ يوجد منتجات فى السلة", Toast.LENGTH_SHORT).show();
                                }else {

                                    ripple_confirm.setVisibility(View.VISIBLE);
                                    totle.setVisibility(View.VISIBLE);
                                    for (int i = 0; i < categores.length(); i++) {
                                        RequestsModel latest = new RequestsModel();
                                        JSONObject jsonObject = categores.getJSONObject(i);
                                        String product_id = jsonObject.getString("product_id");
                                        String title = jsonObject.getString("title");
                                        String description = jsonObject.getString("description");
                                        String def_img = jsonObject.getString("def_img");
                                        String rate = jsonObject.getString("rate");
                                        String price = jsonObject.getString("price");
                                        String owner_id = jsonObject.getString("owner_id");
                                        String qty = jsonObject.getString("qty");
                                        //  int fav = jsonObject.getInt("fav");

                                        latest.setProductId(product_id);
                                        latest.setTitle(title);
                                        latest.setDescription(description);
                                        latest.setDefImg(def_img);
                                        latest.setRate(rate);
                                        latest.setPrice(price);
                                        latest.setTrader_id(owner_id);
                                        latest.setQuantity(qty);
                                        //   latest.setFav(fav);
                                        faqModels.add(latest);
                                    }
                                }
                                 adapter = new CardAdapter(getActivity(),faqModels);
                                cart.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                                cart.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                adapter.setOnClickListener(new CardAdapter.OnItemClickListener() {
                                    @Override
                                    public void onclick(int position) {

                                            String pricee = faqModels.get(position).getPrice();
                                        String realPrice = price.getText().toString();
                                        Integer newPrice = Integer.parseInt(realPrice) + Integer.parseInt(pricee);
                                            price.setText(""+newPrice);
                                    }
                                });
                                adapter.setOnClickListenerMia(new CardAdapter.OnItemClickListenermius() {
                                    @Override
                                    public void onclickked(int position) {
                                        String pricee = faqModels.get(position).getPrice();
                                        String realPrice = price.getText().toString();
                                        Integer newPrice = Integer.parseInt(realPrice) - Integer.parseInt(pricee);
                                        price.setText(""+newPrice);
                                    }
                                });
                                initSwipe();
                            }
                        } catch (JSONException e) {
                            dialog.dismiss();
                        }

                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void initSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }


            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {


                if (direction == ItemTouchHelper.LEFT){
                    int positionn = viewHolder.getAdapterPosition();
                    RequestsModel requestsModel = faqModels.get(positionn);

                    String productId = requestsModel.getProductId();
                    String trader_id = requestsModel.getTrader_id();
                    addTopasket(productId,trader_id);
                    faqModels.remove(positionn);
                    adapter.notifyItemRemoved(positionn);

                    String pricee = requestsModel.getPrice();
                    String realPrice = price.getText().toString();
                    Integer newPrice = Integer.parseInt(realPrice) - Integer.parseInt(pricee);
                    price.setText(""+newPrice);

                    if (faqModels.size()==0){
                        ripple_confirm.setVisibility(View.GONE);
                        totle.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "لأ يوجد منتجات فى السلة", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //   editquantity(Cart.this);
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

//                    if(dX > 0){
//                        p.setColor(Color.parseColor("#ee3984"));
//                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
//                        c.drawRect(background,p);
//                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.iccdelete);
//                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
//                        c.drawBitmap(icon,null,icon_dest,p);
//                    } else {
                    p.setColor(Color.parseColor("#ef473a"));
                    RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                    c.drawRect(background,p);
                    icon = BitmapFactory.decodeResource(getResources(), R.drawable.iccdelete);
                    RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                    c.drawBitmap(icon,null,icon_dest,p);
                    //   }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(cart);
    }
    public void addTopasket(String proID,String traderId){

        AndroidNetworking.post(URLS.delete_insert_basket)
                .addBodyParameter("client_id",user_id)
                .addBodyParameter("user_id",user_id)
                .addBodyParameter("product_id",proID)
                .addBodyParameter("trader_id",traderId)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String success = response.getString("success");
                            if (success.equals("1")){

                            }
                            else if (success.equals("0")){


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

}
