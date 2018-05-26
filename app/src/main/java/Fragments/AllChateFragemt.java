package Fragments;


import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.AllChatAdapter;
import Adapter.MoreBuyerAdapter;
import Model.ChateModelList;
import Model.MoreBuyerModel;
import URLS.URLS;
import souk.arab.com.soukelarab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllChateFragemt extends Fragment {
View view;
    private RecyclerView chat_recy;
    ArrayList<ChateModelList> chateModelLists;
    private SharedPreferences preferencesid;
    private String user_id;

    public AllChateFragemt() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=  inflater.inflate(R.layout.fragment_all_chate_fragemt, container, false);
        AndroidNetworking.initialize(getActivity());
        preferencesid = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_id = preferencesid.getString("user_id","0000");
        setVar();
        getAllChateList();
        return view;

    }

    public void setVar(){
        chat_recy=(RecyclerView)view.findViewById(R.id.chat_recy);
      chateModelLists=new ArrayList<>();
    }
    public void getAllChateList(){

        AndroidNetworking.post(URLS.chateAll)
                .addBodyParameter("user_id",user_id)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            String success = response.getString("success");
                            if (success.equals("1")){
                                JSONArray chats = response.getJSONArray("chats");
                                if (chats.length()==0){
                                    Toast.makeText(getActivity(), "لأ يوجد رسائل حاليا", Toast.LENGTH_SHORT).show();
                                }

                                for (int i=0;i<chats.length();i++){
                                    JSONObject jsonObject = chats.getJSONObject(i);
                                    ChateModelList chat=new ChateModelList();
                                    String chat_id = jsonObject.getString("chat_id");
                                    String user_id = jsonObject.getString("user_id");
                                    String matjar_id = jsonObject.getString("matjar_id");
                                    String matjar_image = jsonObject.getString("matjar_image");
                                    String matjar_name = jsonObject.getString("matjar_name");

                                    chat.setChatId(chat_id);
                                    chat.setUserId(user_id);
                                    chat.setMatjarId(matjar_id);
                                    chat.setMatjarImage(matjar_image);
                                    chat.setMatjarName(matjar_name);
                                    chateModelLists.add(chat);


                                }
                                 AllChatAdapter adapter = new AllChatAdapter(getActivity(),chateModelLists);
                                chat_recy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                                chat_recy.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
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
}
