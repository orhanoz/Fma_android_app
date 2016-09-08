package fm.com.zo.weather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fm.com.zo.R;

/**
 * Created by toornigac on 08.09.16.
 */

public class ForecastFragment extends Fragment {

    ArrayAdapter<String> mForecastAdapter;

    public ForecastFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // some dummy data for the ListView.
        // until the json is readable
        String[] data = {
                "Mon 6/23 - Sunny - 31/17",
                "Tue 6/24 - Foggy - 21/8",
                "Wed 6/25 - Cloudy - 22/17",
                "Thurs 6/26 - Rainy - 18/11",
                "Fri 6/27 - Foggy - 21/10",
                "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
                "Sun 6/29 - Sunny - 20/7"
        };
        List<String> weekForecast = new ArrayList<String>(Arrays.asList(data));



        // The ArrayAdapter will take data from a source (dummy forecast) and
        // use it to populate the ListView it's attached to.
        mForecastAdapter =
                new ArrayAdapter<String>(
                        getActivity(), // The current context (this activity)
                        R.layout.list_item_forecast, // The name of the layout ID.
                        R.id.list_item_forecast_textview, // The ID of the textview to populate.
                        weekForecast);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(mForecastAdapter);



        return rootView;
    }
}

class FetchWeatherTask extends AsyncTask<URL , String , String>{

    // TODO: 09.09.16 https://developer.android.com/reference/android/os/AsyncTask.html read the full doc.

    private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();


    @Override
    protected String doInBackground(URL... params) {


        HttpURLConnection urlConnection=null;// url connection
        BufferedReader reader=null;// buffered reader to read data from response
        String forecastJsonStr=null; // Will contain the raw JSON response as a string.

        try {
            // Construct the URL for the OpenWeatherMap query
            // http://openweathermap.org/API#forecast <- look to have a better understanding of parameter
            // TODO: 09.09.16 : get a commercial api key before publishing.
            // TODO: 09.09.16 : save locations based on houses , and fetch data from cloud
            // TODO: 09.09.16 : better to have city code instead of numbers , find a way do get cities "" maybe from server ?
            String baseUrl = "http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7";
            String apiKey = "&APPID=" + "840316f75b3524493786b508bc7ac270";
            URL url = new URL(baseUrl.concat(apiKey));

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {buffer.append(line + "\n");}

            if (buffer.length() == 0) {return null;} // Stream was empty.  No point in parsing.

            forecastJsonStr = buffer.toString();

        } catch (IOException e) {

            Log.e(LOG_TAG, "Error ", e);
            return null;

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        return forecastJsonStr;
    }

}