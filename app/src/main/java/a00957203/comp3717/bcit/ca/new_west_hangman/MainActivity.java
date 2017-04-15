package a00957203.comp3717.bcit.ca.new_west_hangman;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> sports_fields       = new ArrayList<>();
    static ArrayList<String> skytrain_stations   = new ArrayList<>();
    static ArrayList<String> public_art          = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundColor(Color.GRAY);
        get_sports_fields();
        get_skytrain_stations();
        get_public_art();
    }

    public void get_sports_fields()
    {
        Ion.with(this).
                load("http://opendata.newwestcity.ca/downloads/sports-fields/SPORTS_FIELDS.json").
                asJsonArray().
                setCallback(
                        new FutureCallback<JsonArray>()
                        {
                            @Override
                            public void onCompleted(final Exception ex,
                                                    final JsonArray array)
                            {
                                if(ex != null)
                                {
                                    Toast.makeText(MainActivity.this,
                                            "Error: " + ex.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    if (array.size() == 0) {
                                        Toast.makeText (MainActivity.this, "Error: JsonEmpty", Toast.LENGTH_LONG).show ();
                                        return;
                                    }
                                    if (sports_fields.size() == 0)
                                    for(final JsonElement element : array)
                                    {
                                        final JsonObject  json;
                                        final JsonElement parkElement;

                                        json              = element.getAsJsonObject();
                                        parkElement       = json.get("PARK");
                                        sports_fields.add (parkElement.getAsString ().toLowerCase ());
                                    }
                                }
                            }
                        });
    }

    public void get_skytrain_stations()
    {
        Ion.with(this).
                load("http://opendata.newwestcity.ca/downloads/skytrain-stations/SKYTRAIN_STATIONS.json").
                asJsonArray().
                setCallback(
                        new FutureCallback<JsonArray>()
                        {
                            @Override
                            public void onCompleted(final Exception ex,
                                                    final JsonArray array)
                            {
                                if(ex != null)
                                {
                                    Toast.makeText(MainActivity.this,
                                            "Error: " + ex.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    if (array.size() == 0) {
                                        Toast.makeText (MainActivity.this, "Error: JsonEmpty", Toast.LENGTH_LONG).show ();
                                        return;
                                    }
                                    if (skytrain_stations.size() == 0)
                                    for(final JsonElement element : array)
                                    {
                                        final JsonObject  json;
                                        final JsonElement nameElement;


                                        json              = element.getAsJsonObject();
                                        nameElement       = json.get("NAME");
                                        String station    = nameElement.getAsString ().toLowerCase ();
                                        station = station.replace(" station", "");
                                        skytrain_stations.add (station);
                                    }
                                }
                            }
                        });
    }

    public void get_public_art()
    {
        Ion.with(this).
                load("http://opendata.newwestcity.ca/downloads/public-art/PUBLIC_ART.json").
                asJsonArray().
                setCallback(
                        new FutureCallback<JsonArray>()
                        {
                            @Override
                            public void onCompleted(final Exception ex,
                                                    final JsonArray array)
                            {
                                if(ex != null)
                                {
                                    Toast.makeText(MainActivity.this,
                                            "Error: " + ex.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    if (array.size() == 0) {
                                        Toast.makeText (MainActivity.this, "Error: JsonEmpty", Toast.LENGTH_LONG).show ();
                                        return;
                                    }
                                    if (public_art.size() == 0)
                                        for(final JsonElement element : array)
                                        {
                                            final JsonObject  json;
                                            final JsonElement parkElement;

                                            json              = element.getAsJsonObject();
                                            parkElement       = json.get("Name");
                                            public_art.add (parkElement.getAsString ().toLowerCase ());
                                        }
                                }
                            }
                        });
    }

    public void startSinglePlayerGame (View v) {
        startActivity(new Intent(this, GameActivity.class));
    }
    public void startMultiPlayerGame(View v){
        startActivity(new Intent(this, MultiplayerActivity.class));
    }
    public void openScores(View v){
        startActivity(new Intent(this, ScoresActivity.class));
    }
}
