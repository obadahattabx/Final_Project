package edu.birzeit.projectpart1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProprtiesJasonParser {
    public static List<Properties> getObjectFromJason(String jason) {
        List<Properties> students;
        try {
            JSONArray jsonArray = new JSONArray(jason);
            students = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject = (JSONObject) jsonArray.get(i);
                Properties student = new Properties();
                student.setID(jsonObject.getInt("id"));
              //  student.setName(jsonObject.getString("name"));
                //student.setAge(jsonObject.getDouble("age"));
                students.add(student);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return students;
    }
}
