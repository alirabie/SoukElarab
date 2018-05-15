package souk.arab.com.soukelarab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;
import com.whinc.widget.ratingbar.RatingBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ConstantClasss.Custom_EditText;
import ConstantClasss.Custom_Textview;
import Model.CommentModel;
import java.util.ArrayList;

import Adapter.CardAdapter;
import Adapter.ClientFavAdapter;
import Adapter.CommentAdapter;
import Adapter.DepartmentAdapter;
import Adapter.ImagesAdapter;
import ConstantClasss.Custom_Button;
import Model.Model;

import URLS.URLS;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class ProuductDetails extends AppCompatActivity {

    private String proID;
    private SharedPreferences preferencesid;
    private String user_id;
    private ImageView favIma,shareImage,imageHomee;
    private ACProgressFlower dialog;
    TextView txt_name,txt_des,txt_prise;
    RatingBar ratingBar;
    RecyclerView images;
ArrayList<Model>imageList;
Custom_Button addComment;
    private RecyclerView coments;
    ArrayList<CommentModel> faqModels;
LinearLayout  commentLin ,detalisLin,visbleComment,backe,pasket;
RatingBar ratingCount;

Custom_EditText name ,comment;
Custom_Textview comment_found;
int countrating=0;
    private String owner_id;
    private CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prouduct_details);
        AndroidNetworking.initialize(ProuductDetails.this);
        preferencesid = ProuductDetails.this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_id = preferencesid.getString("user_id","0000");
        proID="";
        owner_id="";
         proID = getIntent().getExtras().getString("proID");
        setVar();
        getDeils();
     //   setComent();
        setVisibility();
        setRating();
        setComent();
    }

    public void setVar(){
        imageList=new ArrayList<>();
        faqModels=new ArrayList<>();
        favIma= findViewById(R.id.favIma);
        ratingCount= findViewById(R.id.ratingCount);
        shareImage= findViewById(R.id.shareImage);
        shareImage= findViewById(R.id.shareImage);
        imageHomee= findViewById(R.id.imageHomee);
        txt_name= findViewById(R.id.txt_name);
        txt_des= findViewById(R.id.txt_des);
        txt_prise= findViewById(R.id.txt_prise);
        ratingBar= findViewById(R.id.ratingBar);
        images= findViewById(R.id.images);
        addComment= findViewById(R.id.addComment);
        commentLin= findViewById(R.id.commentLin);
        detalisLin= findViewById(R.id.detalisLin);
        visbleComment= findViewById(R.id.visbleComment);
        comment_found= findViewById(R.id.comment_found);
        coments =(RecyclerView)findViewById(R.id.coments);
           name= findViewById(R.id.name);
        comment= findViewById(R.id.comment);

        backe= findViewById(R.id.backe);
        pasket= findViewById(R.id.pasket);
        backe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        commentLin.setBackgroundColor(getResources().getColor(R.color.gray_btn_bg_pressed_color));
        detalisLin.setBackgroundColor(getResources().getColor(R.color.wite));
        txt_des.setVisibility(View.GONE);
        visbleComment.setVisibility(View.VISIBLE);
        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/html");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>This is the text that will be shared.</p>"));
                startActivity(Intent.createChooser(sharingIntent,"Share using"));
            }
        });
    }

    public void getDeils(){
        dialog = new ACProgressFlower.Builder(ProuductDetails.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(getResources().getColor(R.color.colorAccent))
                .fadeColor(getResources().getColor(R.color.colorAccent)).build();
        dialog.show();
        AndroidNetworking.post(URLS.viewPro)
                .addBodyParameter("user_id",user_id)
                .addBodyParameter("product_id",proID)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        dialog.dismiss();
                        try {
                            String success = response.getString("success");
                            if (success.equals("1")){
                                String fav = response.getString("fav");
                                JSONObject product = response.getJSONObject("Product");
                                String product_id = product.getString("product_id");
                                String def_img = product.getString("def_img");
                                String rate = product.getString("rate");
                                String price = product.getString("price");
                                String description = product.getString("description");
                                 owner_id = product.getString("owner_id");
                                String title = product.getString("title");
                                txt_name.setText(title);
                                txt_prise.setText(price + " SAR ");
                                txt_des.setText(description);
                                ratingBar.setCount(Integer.valueOf(rate));
                                if (fav.equals("0")){
                                    favIma.setImageResource(R.drawable.shares);
                                }else if (fav.equals("1")){
                                    favIma.setImageResource(R.drawable.hearts);
                                }
                                Picasso.with(ProuductDetails.this).load(URLS.imageBase+def_img).into(imageHomee);


                                JSONArray ProductImages = response.getJSONArray("ProductImages");
                                if (ProductImages.length()==0){
                                    images.setVisibility(View.GONE);
                                }else {
                                    for (int i=0;i<ProductImages.length();i++){
                                        JSONObject jsonObject = ProductImages.getJSONObject(i);
                                        String image = jsonObject.getString("image");
                                        Model model=new Model();
                                        model.setimage(image);
                                        imageList.add(model);

                                        ImagesAdapter adapter = new ImagesAdapter(ProuductDetails.this,imageList);
                                        images.setLayoutManager(new LinearLayoutManager(ProuductDetails.this, LinearLayoutManager.HORIZONTAL, false));
                                        images.setAdapter(adapter);
                                    }
                                }

                                JSONArray comments = response.getJSONArray("comments");
                                if (comments.length()==0){
                                    comment_found.setVisibility(View.VISIBLE);
                                }else {
                                    for (int i=0;i<comments.length();i++){
                                        CommentModel model=new CommentModel();
                                        JSONObject jsonObject = comments.getJSONObject(i);
                                        String username = jsonObject.getString("username");
                                        String rateing = jsonObject.getString("rate");
                                        String comment = jsonObject.getString("comment");
                                        model.setName(username);
                                        model.setComment(comment);
                                        model.setRating(rateing);
                                        faqModels.add(model);

                                    }
                                     adapter = new CommentAdapter(ProuductDetails.this,faqModels);
                                    coments.setLayoutManager(new LinearLayoutManager(ProuductDetails.this, LinearLayoutManager.VERTICAL, false));
                                    coments.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                }

                            }
                        } catch (JSONException e) {
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    public void setComent(){
        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equals("")&&comment.getText().toString().equals("")){
                    Toast.makeText(ProuductDetails.this, "من فضلك اضف تعليقك", Toast.LENGTH_SHORT).show();
                }else {
                    addComment();
                }



            }
        });
    }

    public void setRating(){
        ratingCount.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onChange(RatingBar view, int preCount, int curCount) {
                countrating=curCount;
            }
        });

        favIma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDeleteFav(proID);
            }
        });
        pasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTopasket(proID,owner_id);
            }
        });

    }
    //add comment

    public void addComment(){

        AndroidNetworking.post(URLS.addcomment)
                .addBodyParameter("user_id",user_id)
                .addBodyParameter("product_id",proID)
                .addBodyParameter("rate_number",String.valueOf(countrating))
                .addBodyParameter("comment",comment.getText().toString())
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Toast.makeText(ProuductDetails.this, "شكرا لك تم إضافة تقيمك بنجاح", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
    public void setVisibility(){
        detalisLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detalisLin.setBackgroundColor(getResources().getColor(R.color.gray_btn_bg_pressed_color));
                commentLin.setBackgroundColor(getResources().getColor(R.color.wite));
                txt_des.setVisibility(View.VISIBLE);
                visbleComment.setVisibility(View.GONE);
            }
        });
        commentLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentLin.setBackgroundColor(getResources().getColor(R.color.gray_btn_bg_pressed_color));
                detalisLin.setBackgroundColor(getResources().getColor(R.color.wite));
                txt_des.setVisibility(View.GONE);
                visbleComment.setVisibility(View.VISIBLE);
            }
        });
    }



    // المفضله
    public void addDeleteFav(String proID){

        AndroidNetworking.post(URLS.fav)
                .addBodyParameter("user_id",user_id)
                .addBodyParameter("product_id",proID)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String success = response.getString("success");
                            if (success.equals("1")){
                                favIma.setImageResource(R.drawable.hearts);

                                Toast.makeText(ProuductDetails.this, "تم إضافة المنتج الى المفضلة بنجاح", Toast.LENGTH_SHORT).show();
                            }
                            else if (success.equals("0")){
                                favIma.setImageResource(R.drawable.shares);
                                Toast.makeText(ProuductDetails.this, "تم حذف المنتج من المفضلة بنجاح", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(ProuductDetails.this, "تم إضافة المنتج الى السلة بنجاح", Toast.LENGTH_SHORT).show();
                            }
                            else if (success.equals("0")){
                                Toast.makeText(ProuductDetails.this, "تم حذف المنتج من السلة بنجاح", Toast.LENGTH_SHORT).show();
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
