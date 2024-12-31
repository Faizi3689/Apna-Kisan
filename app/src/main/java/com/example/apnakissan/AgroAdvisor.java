package com.example.apnakissan;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class AgroAdvisor extends AppCompatActivity {

    private JSONObject responseMap;
    private EditText userQuery;
    private TextView chatResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agro_advisor);

        userQuery = findViewById(R.id.user_query);
        chatResponse = findViewById(R.id.chat_response);
        Button sendButton = findViewById(R.id.send_button);

        try {
            responseMap = loadJSONFromAsset("agriculture_questions.json");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            chatResponse.setText("Error: Response map not initialized");
        }

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (responseMap != null) {
                    String query = userQuery.getText().toString().trim();
                    String response = getResponse(query);
                    chatResponse.setText(response);
                } else {
                    chatResponse.setText("Error: Response map not initialized");
                }
            }
        });
    }

    private JSONObject loadJSONFromAsset(String fileName) throws IOException, JSONException {
        InputStream is = getAssets().open(fileName);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        String json = new String(buffer, StandardCharsets.UTF_8);
        return new JSONObject(json);
    }

    private String getResponse(String query) {
        try {
            if (responseMap.has(query)) {
                return responseMap.getString(query);
            } else {
                return "I am a agro advisor. Please talk about related questions.";
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return "Error processing your request.";
        }
    }
}
