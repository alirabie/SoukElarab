package souk.arab.com.soukelarab;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.MassagesAdapter;
import Model.ChatModel;

public class Chat extends AppCompatActivity {
    LinearLayout layout;
    RelativeLayout layout_2;
    ImageView sendButton;
    EditText messageArea;
    ScrollView scrollView;
    Firebase reference1, reference2;
    String userId, matgerId;
    private DatabaseReference posts;
    FirebaseDatabase database;
    private String key;

    RecyclerView recy_chat;
    private ArrayList<ChatModel> messageList;
    private String child;
    ChatModel ch1;
    private String photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        userId = getIntent().getExtras().getString("userId");
        matgerId = getIntent().getExtras().getString("matgerId");
        photo = getIntent().getExtras().getString("photo");

        sendButton = (ImageView) findViewById(R.id.sendButton);
        messageArea = (EditText) findViewById(R.id.messageArea);
        recy_chat = (RecyclerView) findViewById(R.id.recy_chat);

//chat
        messageList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        posts = database.getReference("AllChat");


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendChatData();

            }
        });

        retrieveAllSnapShots();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                retrieveValues();
            }
        }, 4000);
    }

    private void sendChatData() {
        long tsLong = System.currentTimeMillis() / 1000;
        ChatModel model = new ChatModel();

        if (messageArea.getText().toString().equals("") || messageArea.getText().toString().isEmpty()) {
        } else {
            model.setText(messageArea.getText().toString());
        }
        model.setTimeStamp(tsLong);
        model.setFrom(userId);
        model.setTo(matgerId);
        if (key != null) {
            posts.child(key).push().setValue(model);
        } else {
            posts.child(userId + "-" + matgerId).push().setValue(model);
        }

        messageArea.setText("");

    }

    public void addMessageBox(String message, int type) {
        TextView textView = new TextView(Chat.this);
        textView.setText(message);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.weight = 1.0f;

        if (type == 1) {
            lp2.gravity = Gravity.LEFT;
            textView.setBackgroundResource(R.drawable.bubble_in);
        } else {
            lp2.gravity = Gravity.RIGHT;
            textView.setBackgroundResource(R.drawable.bubble_out);
        }
        textView.setLayoutParams(lp2);
        layout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }


    private void retrieveValues() {
//        retrieveAllSnapShots();
        if (key != null) {
            child = key;
        } else {
            child = userId + "-" + matgerId;
        }
        posts.child(child).addChildEventListener(new com.google.firebase.database.ChildEventListener() {
            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                Log.e("allSnap", "" + dataSnapshot.getValue());

                List<ChatModel> posts = new ArrayList<>();
                ch1 = dataSnapshot.getValue(ChatModel.class);
                posts.add(ch1);
                for (int i = 0; i < posts.size(); i++) {
                    ChatModel model = new ChatModel();
                    model.setTimeStamp(posts.get(i).getTimeStamp());
                    model.setText(posts.get(i).getText());
                    model.setFrom(posts.get(i).getFrom());
                    model.setTo(posts.get(i).getTo());
                    model.setPhoto(photo);

                    messageList.add(model);
                }

                if (messageList.size() > 0) {
                    recy_chat.setAdapter(new MassagesAdapter(Chat.this, messageList));
                    recy_chat.setLayoutManager(new LinearLayoutManager(Chat.this, LinearLayoutManager.VERTICAL, false));
                }
            }

            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.google.firebase.database.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void retrieveAllSnapShots() {
        posts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                Log.e("allSnap", "" + dataSnapshot.getValue());
                if (dataSnapshot.exists()) {
                    Object value = dataSnapshot.getValue();
                    String s = value.toString();
                    String[] split = s.split("=");
                    String s1 = split[0];
                    String replace = s1.replace("{", "");
                    Log.e("key", "" + replace);
                    if (replace.equals(userId + "-" + matgerId) || replace.equals(matgerId + "-" + userId)) {
                        String[] split1 = replace.split("-");

                        if (split1[1].equals(userId)) {
                            key = matgerId + "-" + userId;
                        } else {
                            key = userId + "-" + matgerId;
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}




