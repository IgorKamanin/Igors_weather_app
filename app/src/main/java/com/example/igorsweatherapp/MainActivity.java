package com.example.igorsweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {
    final String URL_JSON = "http://api.openweathermap.org/data/2.5/forecast/daily?q=%s&cnt=5&lang=ru&appid=b1b35bba8b434a28a0be2a3e1071ae5b&units=metric";
    private Button  buttonSaratov;
    private Button  buttonAstrakhan;
    private EditText editTextTown;
    private TextView textViewTown;

    private TextView textViewDay1;
    private TextView textViewDay2;
    private TextView textViewDay3;
    private TextView textViewDay4;
    private TextView textViewDay5;

    private TextView textViewDayWeek1;
    private TextView textViewDayWeek2;
    private TextView textViewDayWeek3;
    private TextView textViewDayWeek4;
    private TextView textViewDayWeek5;

    SimpleDateFormat sdf;
    private String today;
    private String tomorrow;
    private String day3;
    private String day4;
    private String day5;
    private Calendar cal;
    private int dayOfWeek;

    private TextView textViewWeatherDescDay1;
    private TextView textViewWeatherDescDay2;
    private TextView textViewWeatherDescDay3;
    private TextView textViewWeatherDescDay4;
    private TextView textViewWeatherDescDay5;

    private TextView textViewMorningDay1;
    private TextView textViewMorningDay2;
    private TextView textViewMorningDay3;
    private TextView textViewMorningDay4;
    private TextView textViewMorningDay5;

    private TextView textViewNoonDay1;
    private TextView textViewNoonDay2;
    private TextView textViewNoonDay3;
    private TextView textViewNoonDay4;
    private TextView textViewNoonDay5;

    private TextView textViewEveDay1;
    private TextView textViewEveDay2;
    private TextView textViewEveDay3;
    private TextView textViewEveDay4;
    private TextView textViewEveDay5;

    private TextView textViewNightDay1;
    private TextView textViewNightDay2;
    private TextView textViewNightDay3;
    private TextView textViewNightDay4;
    private TextView textViewNightDay5;

    private ImageView imageViewWindDay1;
    private ImageView imageViewWindDay2;
    private ImageView imageViewWindDay3;
    private ImageView imageViewWindDay4;
    private ImageView imageViewWindDay5;

    private TextView textViewWindDay1;
    private TextView textViewWindDay2;
    private TextView textViewWindDay3;
    private TextView textViewWindDay4;
    private TextView textViewWindDay5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAstrakhan = findViewById(R.id.buttonAstrakhan);
        buttonSaratov = findViewById(R.id.buttonSaratov);
        editTextTown = findViewById(R.id.editTextTown);
        textViewTown = findViewById(R.id.textViewTown);

        sdf = new SimpleDateFormat("dd MMM", Locale.forLanguageTag("ru"));
        today = sdf.format(new Date());
        tomorrow = sdf.format(new Date().getTime() + 86400000);
        day3 = sdf.format(new Date().getTime() + 86400000*2);
        day4 = sdf.format(new Date().getTime() + 86400000*3);
        day5 = sdf.format(new Date().getTime() + 86400000*4);

        textViewDay1 = findViewById(R.id.textViewToday);
        textViewDay2 = findViewById(R.id.textViewTomorrow);
        textViewDay3 = findViewById(R.id.textViewDay3);
        textViewDay4 = findViewById(R.id.textViewDay4);
        textViewDay5 = findViewById(R.id.textViewDay5);

        textViewDayWeek1 = findViewById(R.id.textViewDayWeek1);
        textViewDayWeek2 = findViewById(R.id.textViewDayWeek2);
        textViewDayWeek3 = findViewById(R.id.textViewDayWeek3);
        textViewDayWeek4 = findViewById(R.id.textViewDayWeek4);
        textViewDayWeek5 = findViewById(R.id.textViewDayWeek5);

        textViewDay1.setText(today);
        textViewDay2.setText(tomorrow);
        textViewDay3.setText(day3);
        textViewDay4.setText(day4);
        textViewDay5.setText(day5);

        textViewWeatherDescDay1 = findViewById(R.id.textViewMorningDescDay1);
        textViewWeatherDescDay2 = findViewById(R.id.textViewMorningDescDay2);
        textViewWeatherDescDay3 = findViewById(R.id.textViewMorningDescDay3);
        textViewWeatherDescDay4 = findViewById(R.id.textViewMorningDescDay4);
        textViewWeatherDescDay5 = findViewById(R.id.textViewMorningDescDay5);

        textViewMorningDay1 = findViewById(R.id.textViewMorningDay1);
        textViewMorningDay2 = findViewById(R.id.textViewMorningDay2);
        textViewMorningDay3 = findViewById(R.id.textViewMorningDay3);
        textViewMorningDay4 = findViewById(R.id.textViewMorningDay4);
        textViewMorningDay5 = findViewById(R.id.textViewMorningDay5);

        textViewNoonDay1 = findViewById(R.id.textViewNoonDay1);
        textViewNoonDay2 = findViewById(R.id.textViewNoonDay2);
        textViewNoonDay3 = findViewById(R.id.textViewNoonDay3);
        textViewNoonDay4 = findViewById(R.id.textViewNoonDay4);
        textViewNoonDay5 = findViewById(R.id.textViewNoonDay5);

        textViewEveDay1 = findViewById(R.id.textViewEveDay1);
        textViewEveDay2 = findViewById(R.id.textViewEveDay2);
        textViewEveDay3 = findViewById(R.id.textViewEveDay3);
        textViewEveDay4 = findViewById(R.id.textViewEveDay4);
        textViewEveDay5 = findViewById(R.id.textViewEveDay5);

        textViewNightDay1 = findViewById(R.id.textViewNightDay1);
        textViewNightDay2 = findViewById(R.id.textViewNightDay2);
        textViewNightDay3 = findViewById(R.id.textViewNightDay3);
        textViewNightDay4 = findViewById(R.id.textViewNightDay4);
        textViewNightDay5 = findViewById(R.id.textViewNightDay5);

        imageViewWindDay1 = findViewById(R.id.imageViewWindDay1);
        imageViewWindDay2 = findViewById(R.id.imageViewWindDay2);
        imageViewWindDay3 = findViewById(R.id.imageViewWindDay3);
        imageViewWindDay4 = findViewById(R.id.imageViewWindDay4);
        imageViewWindDay5 = findViewById(R.id.imageViewWindDay5);

        textViewWindDay1 = findViewById(R.id.textViewWindDay1);
        textViewWindDay2 = findViewById(R.id.textViewWindDay2);
        textViewWindDay3 = findViewById(R.id.textViewWindDay3);
        textViewWindDay4 = findViewById(R.id.textViewWindDay4);
        textViewWindDay5 = findViewById(R.id.textViewWindDay5);


        cal = Calendar.getInstance();
        dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        switch(dayOfWeek){
            case(2): textViewDayWeek1.setText(R.string.monday);
                textViewDayWeek1.setTextColor(getResources().getColor(R.color.night));
                textViewDay1.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek2.setText(R.string.tuesday);
                textViewDayWeek2.setTextColor(getResources().getColor(R.color.night));
                textViewDay2.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek3.setText(R.string.wednesday);
                textViewDayWeek3.setTextColor(getResources().getColor(R.color.night));
                textViewDay3.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek4.setText(R.string.thursday);
                textViewDayWeek4.setTextColor(getResources().getColor(R.color.night));
                textViewDay4.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek5.setText(R.string.friday);
                textViewDayWeek5.setTextColor(getResources().getColor(R.color.night));
                textViewDay5.setTextColor(getResources().getColor(R.color.night));
                break;
            case(3): textViewDayWeek1.setText(R.string.tuesday);
                textViewDayWeek1.setTextColor(getResources().getColor(R.color.night));
                textViewDay1.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek2.setText(R.string.wednesday);
                textViewDayWeek2.setTextColor(getResources().getColor(R.color.night));
                textViewDay2.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek3.setText(R.string.thursday);
                textViewDayWeek3.setTextColor(getResources().getColor(R.color.night));
                textViewDay3.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek4.setText(R.string.friday);
                textViewDayWeek4.setTextColor(getResources().getColor(R.color.night));
                textViewDay4.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek5.setText(R.string.saturday);
                textViewDayWeek5.setTextColor(getResources().getColor(R.color.holo_red_light));
                textViewDay5.setTextColor(getResources().getColor(R.color.holo_red_light));
                break;
            case(4): textViewDayWeek1.setText(R.string.wednesday);
                textViewDayWeek1.setTextColor(getResources().getColor(R.color.night));
                textViewDay1.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek2.setText(R.string.thursday);
                textViewDayWeek2.setTextColor(getResources().getColor(R.color.night));
                textViewDay2.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek3.setText(R.string.friday);
                textViewDayWeek3.setTextColor(getResources().getColor(R.color.night));
                textViewDay3.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek4.setText(R.string.saturday);
                textViewDayWeek4.setTextColor(getResources().getColor(R.color.holo_red_light));
                textViewDay4.setTextColor(getResources().getColor(R.color.holo_red_light));
                textViewDayWeek5.setText(R.string.sunday);
                textViewDayWeek5.setTextColor(getResources().getColor(R.color.holo_red_light));
                textViewDay5.setTextColor(getResources().getColor(R.color.holo_red_light));
                break;
            case(5): textViewDayWeek1.setText(R.string.thursday);
                textViewDayWeek1.setTextColor(getResources().getColor(R.color.night));
                textViewDay1.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek2.setText(R.string.friday);
                textViewDayWeek2.setTextColor(getResources().getColor(R.color.night));
                textViewDay2.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek3.setText(R.string.saturday);
                textViewDayWeek3.setTextColor(getResources().getColor(R.color.holo_red_light));
                textViewDay3.setTextColor(getResources().getColor(R.color.holo_red_light));
                textViewDayWeek4.setText(R.string.sunday);
                textViewDayWeek4.setTextColor(getResources().getColor(R.color.holo_red_light));
                textViewDay4.setTextColor(getResources().getColor(R.color.holo_red_light));
                textViewDayWeek5.setText(R.string.monday);
                textViewDayWeek5.setTextColor(getResources().getColor(R.color.night));
                textViewDay5.setTextColor(getResources().getColor(R.color.night));
                break;
            case(6): textViewDayWeek1.setText(R.string.friday);
                textViewDayWeek1.setTextColor(getResources().getColor(R.color.night));
                textViewDay1.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek2.setText(R.string.saturday);
                textViewDayWeek2.setTextColor(getResources().getColor(R.color.holo_red_light));
                textViewDay2.setTextColor(getResources().getColor(R.color.holo_red_light));
                textViewDayWeek3.setText(R.string.sunday);
                textViewDayWeek3.setTextColor(getResources().getColor(R.color.holo_red_light));
                textViewDay3.setTextColor(getResources().getColor(R.color.holo_red_light));
                textViewDayWeek4.setText(R.string.monday);
                textViewDayWeek4.setTextColor(getResources().getColor(R.color.night));
                textViewDay4.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek5.setText(R.string.tuesday);
                textViewDayWeek5.setTextColor(getResources().getColor(R.color.night));
                textViewDay5.setTextColor(getResources().getColor(R.color.night));
                break;
            case(7): textViewDayWeek1.setText(R.string.saturday);
                textViewDayWeek1.setTextColor(getResources().getColor(R.color.holo_red_light));
                textViewDay1.setTextColor(getResources().getColor(R.color.holo_red_light));
                textViewDayWeek2.setText(R.string.sunday);
                textViewDayWeek2.setTextColor(getResources().getColor(R.color.holo_red_light));
                textViewDay2.setTextColor(getResources().getColor(R.color.holo_red_light));
                textViewDayWeek3.setText(R.string.monday);
                textViewDayWeek3.setTextColor(getResources().getColor(R.color.night));
                textViewDay3.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek4.setText(R.string.tuesday);
                textViewDayWeek4.setTextColor(getResources().getColor(R.color.night));
                textViewDay4.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek5.setText(R.string.wednesday);
                textViewDayWeek5.setTextColor(getResources().getColor(R.color.night));
                textViewDay5.setTextColor(getResources().getColor(R.color.night));
                break;
            case(1): textViewDayWeek1.setText(R.string.sunday);
                textViewDayWeek1.setTextColor(getResources().getColor(R.color.holo_red_light));
                textViewDay1.setTextColor(getResources().getColor(R.color.holo_red_light));
                textViewDayWeek2.setText(R.string.monday);
                textViewDayWeek2.setTextColor(getResources().getColor(R.color.night));
                textViewDay2.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek3.setText(R.string.tuesday);
                textViewDayWeek3.setTextColor(getResources().getColor(R.color.night));
                textViewDay3.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek4.setText(R.string.wednesday);
                textViewDayWeek4.setTextColor(getResources().getColor(R.color.night));
                textViewDay4.setTextColor(getResources().getColor(R.color.night));
                textViewDayWeek5.setText(R.string.thursday);
                textViewDayWeek5.setTextColor(getResources().getColor(R.color.night));
                textViewDay5.setTextColor(getResources().getColor(R.color.night));
                break;
        }

        DownloadJSONTask task = new DownloadJSONTask();
        try {
            task.execute(String.format(URL_JSON, getString(R.string.astrakhan))).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class DownloadJSONTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            URL url = null;
            HttpURLConnection connection = null;
            StringBuilder result = new StringBuilder("");
            try {
                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream stream = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(stream);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();
                while (line != null){
                    result.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null){
                    connection.disconnect();
                }
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject mainJSONObject = new JSONObject(s);
                String city = mainJSONObject.getJSONObject("city").getString("name");
                textViewTown.setText(city);

                String weatherDay1 = mainJSONObject.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("description");
                textViewWeatherDescDay1.setText(weatherDay1);
                String weatherDay2 = mainJSONObject.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("description");
                textViewWeatherDescDay2.setText(weatherDay2);
                String weatherDay3 = mainJSONObject.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("description");
                textViewWeatherDescDay3.setText(weatherDay3);
                String weatherDay4 = mainJSONObject.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("description");
                textViewWeatherDescDay4.setText(weatherDay4);
                String weatherDay5 = mainJSONObject.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getString("description");
                textViewWeatherDescDay5.setText(weatherDay5);


                int tempMorningDay1 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(0).getJSONObject("temp").getString("morn"));
                textViewMorningDay1.setText(tempMorningDay1 + getString(R.string.gradus));
                int tempMorningDay2 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(1).getJSONObject("temp").getString("morn"));
                textViewMorningDay2.setText(tempMorningDay2 + getString(R.string.gradus));
                int tempMorningDay3 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(2).getJSONObject("temp").getString("morn"));
                textViewMorningDay3.setText(tempMorningDay3 + getString(R.string.gradus));
                int tempMorningDay4 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(3).getJSONObject("temp").getString("morn"));
                textViewMorningDay4.setText(tempMorningDay4 + getString(R.string.gradus));
                int tempMorningDay5 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(4).getJSONObject("temp").getString("morn"));
                textViewMorningDay5.setText(tempMorningDay5 + getString(R.string.gradus));

                int tempNoonDay1 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(0).getJSONObject("temp").getString("day"));
                textViewNoonDay1.setText(tempNoonDay1 + getString(R.string.gradus));
                int tempNoonDay2 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(1).getJSONObject("temp").getString("day"));
                textViewNoonDay2.setText(tempNoonDay2 + getString(R.string.gradus));
                int tempNoonDay3 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(2).getJSONObject("temp").getString("day"));
                textViewNoonDay3.setText(tempNoonDay3 + getString(R.string.gradus));
                int tempNoonDay4 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(3).getJSONObject("temp").getString("day"));
                textViewNoonDay4.setText(tempNoonDay4 + getString(R.string.gradus));
                int tempNoonDay5 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(4).getJSONObject("temp").getString("day"));
                textViewNoonDay5.setText(tempNoonDay5 + getString(R.string.gradus));

                int tempEveDay1 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(0).getJSONObject("temp").getString("eve"));
                textViewEveDay1.setText(tempEveDay1 + getString(R.string.gradus));
                int tempEveDay2 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(1).getJSONObject("temp").getString("eve"));
                textViewEveDay2.setText(tempEveDay2 + getString(R.string.gradus));
                int tempEveDay3 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(2).getJSONObject("temp").getString("eve"));
                textViewEveDay3.setText(tempEveDay3 + getString(R.string.gradus));
                int tempEveDay4 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(3).getJSONObject("temp").getString("eve"));
                textViewEveDay4.setText(tempEveDay4 + getString(R.string.gradus));
                int tempEveDay5 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(4).getJSONObject("temp").getString("eve"));
                textViewEveDay5.setText(tempEveDay5 + getString(R.string.gradus));

                int tempNightDay1 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(0).getJSONObject("temp").getString("night"));
                textViewNightDay1.setText(tempNightDay1 + getString(R.string.gradus));
                int tempNightDay2 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(1).getJSONObject("temp").getString("night"));
                textViewNightDay2.setText(tempNightDay2 + getString(R.string.gradus));
                int tempNightDay3 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(2).getJSONObject("temp").getString("night"));
                textViewNightDay3.setText(tempNightDay3 + getString(R.string.gradus));
                int tempNightDay4 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(3).getJSONObject("temp").getString("night"));
                textViewNightDay4.setText(tempNightDay4 + getString(R.string.gradus));
                int tempNightDay5 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(4).getJSONObject("temp").getString("night"));
                textViewNightDay5.setText(tempNightDay5 + getString(R.string.gradus));


                int windSpeedDay1 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(0).getString("speed"));
                textViewWindDay1.setText(""+windSpeedDay1);
                int windSpeedDay2 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(1).getString("speed"));
                textViewWindDay2.setText(""+windSpeedDay2);
                int windSpeedDay3 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(2).getString("speed"));
                textViewWindDay3.setText(""+windSpeedDay3);
                int windSpeedDay4 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(3).getString("speed"));
                textViewWindDay4.setText(""+windSpeedDay4);
                int windSpeedDay5 = (int)Double.parseDouble(mainJSONObject.getJSONArray("list").getJSONObject(4).getString("speed"));
                textViewWindDay5.setText(""+windSpeedDay5);


                String windDestinationDay1 = mainJSONObject.getJSONArray("list").getJSONObject(0).getString("deg");
                if (Integer.parseInt(windDestinationDay1) < 23 || Integer.parseInt(windDestinationDay1) >= 337){
                    imageViewWindDay1.setImageResource(R.drawable.n);
                }
                if (Integer.parseInt(windDestinationDay1) >= 23 && Integer.parseInt(windDestinationDay1) > 68){
                    imageViewWindDay1.setImageResource(R.drawable.ne);
                }
                if (Integer.parseInt(windDestinationDay1) >= 68 && Integer.parseInt(windDestinationDay1) > 113){
                    imageViewWindDay1.setImageResource(R.drawable.e);
                }
                if (Integer.parseInt(windDestinationDay1) >= 113 && Integer.parseInt(windDestinationDay1) > 158){
                    imageViewWindDay1.setImageResource(R.drawable.se);
                }
                if (Integer.parseInt(windDestinationDay1) >= 158 && Integer.parseInt(windDestinationDay1) > 203){
                    imageViewWindDay1.setImageResource(R.drawable.s);
                }
                if (Integer.parseInt(windDestinationDay1) >= 203 && Integer.parseInt(windDestinationDay1) > 248){
                    imageViewWindDay1.setImageResource(R.drawable.sw);
                }
                if (Integer.parseInt(windDestinationDay1) >= 248 && Integer.parseInt(windDestinationDay1) > 293){
                    imageViewWindDay1.setImageResource(R.drawable.w);
                }
                if (Integer.parseInt(windDestinationDay1) >= 293 && Integer.parseInt(windDestinationDay1) > 337){
                    imageViewWindDay1.setImageResource(R.drawable.nw);
                }

                String windDestinationDay2 = mainJSONObject.getJSONArray("list").getJSONObject(1).getString("deg");
                if (Integer.parseInt(windDestinationDay2) < 23 || Integer.parseInt(windDestinationDay2) >= 337){
                    imageViewWindDay2.setImageResource(R.drawable.n);
                }
                if (Integer.parseInt(windDestinationDay2) >= 23 && Integer.parseInt(windDestinationDay2) > 68){
                    imageViewWindDay2.setImageResource(R.drawable.ne);
                }
                if (Integer.parseInt(windDestinationDay2) >= 68 && Integer.parseInt(windDestinationDay2) > 113){
                    imageViewWindDay2.setImageResource(R.drawable.e);
                }
                if (Integer.parseInt(windDestinationDay2) >= 113 && Integer.parseInt(windDestinationDay2) > 158){
                    imageViewWindDay2.setImageResource(R.drawable.se);
                }
                if (Integer.parseInt(windDestinationDay2) >= 158 && Integer.parseInt(windDestinationDay2) > 203){
                    imageViewWindDay2.setImageResource(R.drawable.s);
                }
                if (Integer.parseInt(windDestinationDay2) >= 203 && Integer.parseInt(windDestinationDay2) > 248){
                    imageViewWindDay2.setImageResource(R.drawable.sw);
                }
                if (Integer.parseInt(windDestinationDay2) >= 248 && Integer.parseInt(windDestinationDay2) > 293){
                    imageViewWindDay2.setImageResource(R.drawable.w);
                }
                if (Integer.parseInt(windDestinationDay2) >= 293 && Integer.parseInt(windDestinationDay2) > 337){
                    imageViewWindDay2.setImageResource(R.drawable.nw);
                }

                String windDestinationDay3 = mainJSONObject.getJSONArray("list").getJSONObject(2).getString("deg");
                if (Integer.parseInt(windDestinationDay3) < 23 || Integer.parseInt(windDestinationDay3) >= 337){
                    imageViewWindDay3.setImageResource(R.drawable.n);
                }
                if (Integer.parseInt(windDestinationDay3) >= 23 && Integer.parseInt(windDestinationDay3) > 68){
                    imageViewWindDay3.setImageResource(R.drawable.ne);
                }
                if (Integer.parseInt(windDestinationDay3) >= 68 && Integer.parseInt(windDestinationDay3) > 113){
                    imageViewWindDay3.setImageResource(R.drawable.e);
                }
                if (Integer.parseInt(windDestinationDay3) >= 113 && Integer.parseInt(windDestinationDay3) > 158){
                    imageViewWindDay3.setImageResource(R.drawable.se);
                }
                if (Integer.parseInt(windDestinationDay3) >= 158 && Integer.parseInt(windDestinationDay3) > 203){
                    imageViewWindDay3.setImageResource(R.drawable.s);
                }
                if (Integer.parseInt(windDestinationDay3) >= 203 && Integer.parseInt(windDestinationDay3) > 248){
                    imageViewWindDay3.setImageResource(R.drawable.sw);
                }
                if (Integer.parseInt(windDestinationDay3) >= 248 && Integer.parseInt(windDestinationDay3) > 293){
                    imageViewWindDay3.setImageResource(R.drawable.w);
                }
                if (Integer.parseInt(windDestinationDay3) >= 293 && Integer.parseInt(windDestinationDay3) > 337){
                    imageViewWindDay3.setImageResource(R.drawable.nw);
                }

                String windDestinationDay4 = mainJSONObject.getJSONArray("list").getJSONObject(3).getString("deg");
                if (Integer.parseInt(windDestinationDay4) < 23 || Integer.parseInt(windDestinationDay4) >= 337){
                    imageViewWindDay4.setImageResource(R.drawable.n);
                }
                if (Integer.parseInt(windDestinationDay4) >= 23 && Integer.parseInt(windDestinationDay4) > 68){
                    imageViewWindDay4.setImageResource(R.drawable.ne);
                }
                if (Integer.parseInt(windDestinationDay4) >= 68 && Integer.parseInt(windDestinationDay4) > 113){
                    imageViewWindDay4.setImageResource(R.drawable.e);
                }
                if (Integer.parseInt(windDestinationDay4) >= 113 && Integer.parseInt(windDestinationDay4) > 158){
                    imageViewWindDay4.setImageResource(R.drawable.se);
                }
                if (Integer.parseInt(windDestinationDay4) >= 158 && Integer.parseInt(windDestinationDay4) > 203){
                    imageViewWindDay4.setImageResource(R.drawable.s);
                }
                if (Integer.parseInt(windDestinationDay4) >= 203 && Integer.parseInt(windDestinationDay4) > 248){
                    imageViewWindDay4.setImageResource(R.drawable.sw);
                }
                if (Integer.parseInt(windDestinationDay4) >= 248 && Integer.parseInt(windDestinationDay4) > 293){
                    imageViewWindDay4.setImageResource(R.drawable.w);
                }
                if (Integer.parseInt(windDestinationDay4) >= 293 && Integer.parseInt(windDestinationDay4) > 337){
                    imageViewWindDay4.setImageResource(R.drawable.nw);
                }

                String windDestinationDay5 = mainJSONObject.getJSONArray("list").getJSONObject(4).getString("deg");
                if (Integer.parseInt(windDestinationDay5) < 23 || Integer.parseInt(windDestinationDay5) >= 337){
                    imageViewWindDay5.setImageResource(R.drawable.n);
                }
                if (Integer.parseInt(windDestinationDay5) >= 23 && Integer.parseInt(windDestinationDay5) > 68){
                    imageViewWindDay5.setImageResource(R.drawable.ne);
                }
                if (Integer.parseInt(windDestinationDay5) >= 68 && Integer.parseInt(windDestinationDay5) > 113){
                    imageViewWindDay5.setImageResource(R.drawable.e);
                }
                if (Integer.parseInt(windDestinationDay5) >= 113 && Integer.parseInt(windDestinationDay5) > 158){
                    imageViewWindDay5.setImageResource(R.drawable.se);
                }
                if (Integer.parseInt(windDestinationDay5) >= 158 && Integer.parseInt(windDestinationDay5) > 203){
                    imageViewWindDay5.setImageResource(R.drawable.s);
                }
                if (Integer.parseInt(windDestinationDay5) >= 203 && Integer.parseInt(windDestinationDay5) > 248){
                    imageViewWindDay5.setImageResource(R.drawable.sw);
                }
                if (Integer.parseInt(windDestinationDay5) >= 248 && Integer.parseInt(windDestinationDay5) > 293){
                    imageViewWindDay5.setImageResource(R.drawable.w);
                }
                if (Integer.parseInt(windDestinationDay5) >= 293 && Integer.parseInt(windDestinationDay5) > 337){
                    imageViewWindDay5.setImageResource(R.drawable.nw);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }



    public void onClickSaratovJSON(View view){
        buttonAstrakhan.setEnabled(true);
        buttonSaratov.setEnabled(false);
        DownloadJSONTask task = new DownloadJSONTask();
        try {
            task.execute(String.format(URL_JSON, getString(R.string.saratov))).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void onClickAstrakhanJSON(View view){
        buttonAstrakhan.setEnabled(false);
        buttonSaratov.setEnabled(true);
        DownloadJSONTask task = new DownloadJSONTask();
        try {
            task.execute(String.format(URL_JSON, getString(R.string.astrakhan))).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onCLickGetTown(View view) {
        buttonAstrakhan.setEnabled(true);
        buttonSaratov.setEnabled(true);
        String town = editTextTown.getText().toString().trim();
        DownloadJSONTask task = new DownloadJSONTask();
        try {
            task.execute(String.format(URL_JSON, town)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
