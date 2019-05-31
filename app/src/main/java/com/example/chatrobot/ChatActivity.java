package com.example.chatrobot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRvMsg;
    private Button mBtnSend;
    private EditText mEtInput;
    private List<Msg> msgList = new ArrayList<>();
    private MsgAdapter msgAdapter = new MsgAdapter(msgList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mBtnSend = findViewById(R.id.btn_send);
        mEtInput = findViewById(R.id.et_input);
        mRvMsg = findViewById(R.id.rv_msg);
        init();
        mRvMsg.setLayoutManager(new LinearLayoutManager(this));
        mRvMsg.setAdapter(msgAdapter);
        mBtnSend.setOnClickListener(this);
    }

    //初始数据
    private void init() {
        Msg msg = new Msg("123", Msg.TYPE_RECEIVED);
        msgList.add(msg);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_send) {
            String content = mEtInput.getText().toString();
            Msg sendMsg = new Msg(content, Msg.TYPE_SENT);
            msgList.add(sendMsg);
            mEtInput.setText("");
            msgAdapter.notifyItemInserted(msgList.size() - 1);
            mRvMsg.scrollToPosition(msgList.size() - 1);
            URL url = null;
            try {
                url = new URL("http://www.tuling123.com/openapi/api?key=e2e9e09addce4603a94497fdc733847e&info=" + content);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Get.get(url, new Get.Callback() {
                @Override
                public void onResponse(String response) {
                    String receiveMsg = receiveMsg(response);
                    Msg msg = new Msg(receiveMsg, Msg.TYPE_RECEIVED);
                    msgList.add(msg);
                    msgAdapter.notifyItemInserted(msgList.size() - 1);
                    mRvMsg.scrollToPosition(msgList.size() - 1);
                }
            });

        }
    }

    private String receiveMsg(String json) {
        String receiveMsg = "";
        try {
            JSONObject jsonObject = new JSONObject(json);
            receiveMsg = jsonObject.getString("text");
            if (jsonObject.getString("url") != null) {
                receiveMsg = receiveMsg + '\n' + jsonObject.getString("url");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return receiveMsg;
    }
}
