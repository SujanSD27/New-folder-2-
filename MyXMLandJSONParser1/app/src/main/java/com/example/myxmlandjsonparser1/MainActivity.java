package com.example.myxmlandjsonparser1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    TextView result_txt;
    Button xml_btn,json_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_txt = (TextView)findViewById(R.id.txt_result);
        xml_btn = (Button) findViewById(R.id.btn_xml);
        json_btn = (Button) findViewById(R.id.btn_json);

    }
    public void parseXML(View view){
        try{
            InputStream inputStream=getAssets().open("places.xml");
           DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("XML Data");
            stringBuilder.append("\n---------------------");

           NodeList nodeList = document.getElementsByTagName("place");
            for (int i=0;i<nodeList.getLength();i++){
             Node node = nodeList.item(i);
                Element element = (Element)node;

                stringBuilder.append("\nName:"+ getValue("name",element));
                stringBuilder.append("\nLatitude:"+ getValue("latitude",element));
                stringBuilder.append("\nLongitude:"+ getValue("longitude",element));
                stringBuilder.append("\nTemperature:"+ getValue("temperature",element));
                stringBuilder.append("\nHumidity:"+ getValue("humidity",element));
                stringBuilder.append("\n-------------------");
            }
            result_txt.setText(stringBuilder.toString());
            inputStream.close();
        }catch (Exception e) {
            Log.e("ERROR", e.getMessage());
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }
    }
    private String getValue(String tag, Element element){
        return
                element.getElementsByTagName(tag).item(0).getChildNodes().item(0).getNodeValue();
    }
    public void parseJSON(View view){
        String json;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream inputStream=getAssets().open("places.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);
            stringBuilder.append("JSON Data");
            stringBuilder.append("\n------------------");
            for (int i=0;i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                stringBuilder.append("\nName:"+ jsonObject.getString("name"));
                stringBuilder.append("\nLatitude: "+ jsonObject.getString("latitude"));
                stringBuilder.append("\nLongitude: "+ jsonObject.getString("longitude"));
                stringBuilder.append("\nTemperature: "+ jsonObject.getString("temperature"));
                stringBuilder.append("\nHumidity: "+ jsonObject.getString("humidity"));
                stringBuilder.append("\n-------------------");
            }
            result_txt.setText(stringBuilder.toString());
            inputStream.close();
        }catch (Exception e){
            Log.e("ERROR",e.getMessage());
            Toast.makeText(this,"Error while Parsing JSON",Toast.LENGTH_LONG).show();
        }
    }
}

