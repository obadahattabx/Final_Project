package edu.birzeit.projectpart1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.IDN;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProprtiesJasonParser {
    public static ArrayList<Properties> parse(String json){
        try{
//            DataBaseHelper dataBaseHelper =new
//                    DataBaseHelper(,MainActivity.nameDatabase,null,1);
            ArrayList<Properties> list = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Properties properties=new Properties();
                properties.setID(jsonObject.getInt("ID"));
                properties.setID_agancy(jsonObject.getInt("ID_agancy"));
               // properties.setID_tenant(jsonObject.getInt("ID_tenant"));
                properties.setGarden(jsonObject.getString("garden"));
                properties.setBalcony(jsonObject.getString("balcony"));
                properties.setCityName(jsonObject.getString("cityName"));
                properties.setAddress(jsonObject.getString("address"));
                properties.setSurfaceArea(jsonObject.getString("surfaceArea"));
                properties.setConstructionYear(jsonObject.getString("constructionYear"));
                properties.setNumOfBedroom(jsonObject.getString("numOfBedroom"));
                properties.setPrice(jsonObject.getString("price"));
                properties.setAvailabilDate(jsonObject.getString("availabilDate"));
                properties.setStatus(jsonObject.getString("status"));
                properties.setCreatDate(jsonObject.getString("creatDate"));
                properties.setDescription(jsonObject.getString("description"));
                properties.setValid(jsonObject.getString("valid"));
                properties.setUrlImage(jsonObject.getString("image"));
                list.add(properties);



//                Movies Movie = new Movies();
//                Movie.setId(jsonObject.getInt("id"));
//                Movie.setTitle(jsonObject.getString("title"));
//                Movie.setYear(jsonObject.getInt("year"));
//                Movie.setGenres(jsonObject.getString("genres"));
//                Movie.setDuration(jsonObject.getString("duration"));
//                Movie.setReleaseDate(jsonObject.getString("releaseDate"));
//                Movie.setStoryline(jsonObject.getString("storyline"));
//                Movie.setActors(jsonObject.getString("actors"));
//                Movie.setImdbRating(jsonObject.getString("imdbRating"));
//                Movie.setPosterurl(jsonObject.getString("posterurl"));
//                movielist.add(Movie);
//                Log.d("Result",jsonObject.getString("posterurl"));
//                MainActivity.dataBaseHelper.addMovie(Movie);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
