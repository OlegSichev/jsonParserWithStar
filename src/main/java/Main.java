import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        String json = readString("new_data.json");
        System.out.println(json);
        List<Employee> list = jsonToList(json);
        Employee employee1 = new Employee(list.get(0));
        System.out.println(list);

    }

    public static String readString(String name){
        try(BufferedReader br = new BufferedReader(new FileReader("new_data.json"))){
            String s;
            StringBuilder sb = new StringBuilder();
            while ((s = br.readLine()) != null){
                sb.append(s);
            }
            name = String.valueOf(sb);
        } catch (IOException e){
            e.printStackTrace();
        }
        return name;
    }

    public static List<Employee> jsonToList(String json) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = new JSONArray();
        jsonArray = (JSONArray) jsonParser.parse(json);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        List <Employee> temp = new ArrayList<>();
        for(int i = 0; i < jsonArray.size(); i++){
            Employee employee = new Employee(gson.fromJson(jsonArray.get(i), Employee.class));
            temp.add(employee);
        }
        return temp;
    }
}
