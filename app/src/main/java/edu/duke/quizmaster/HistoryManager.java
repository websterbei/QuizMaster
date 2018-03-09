package edu.duke.quizmaster;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Webster on 3/8/18.
 */

public class HistoryManager {
    public static JSONObject loadResultHistoryFile(Context context) throws IOException, JSONException {
        try {
            String filename = "history.json";
            File file = new File(context.getFilesDir(), filename);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            String str = new String(data, "UTF-8");
            return new JSONObject(str);
        } catch (FileNotFoundException e) {
            return new JSONObject();
        }
    }

    public static void saveResultHistoryFile(Context context, JSONObject historyObject) throws IOException {
        String filename = "history.json";
        File file = new File(context.getFilesDir(), filename);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(historyObject.toString().getBytes("UTF-8"));
        fos.close();
    }

    public static void addToResultHistory(Context context, String quizId, String resultString) {
        try {
            JSONObject historyObject = loadResultHistoryFile(context);
            JSONArray resultArray = historyObject.has(quizId) ? historyObject.getJSONArray(quizId) : new JSONArray();
            resultArray.put(resultString);
            historyObject.put(quizId, resultArray);
            saveResultHistoryFile(context, historyObject);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getResultHistory(Context context, String quizId) {
        try {
            JSONObject historyObject = loadResultHistoryFile(context);
            JSONArray resultArray = historyObject.getJSONArray(quizId);
            ArrayList<String> result = new ArrayList<>();
            for(int i=0; i<resultArray.length(); i++) {
                result.add(resultArray.getString(i));
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
