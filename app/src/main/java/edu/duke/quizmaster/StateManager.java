package edu.duke.quizmaster;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Webster on 3/6/18.
 */

public class StateManager {
    public static JSONObject loadStateFile(Context context) throws IOException, JSONException {
        String filename = "state.json";
        File file = new File(context.getFilesDir(), filename);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        String str = new String(data, "UTF-8");
        return new JSONObject(str);
    }

    public static void saveStateFile(Context context, JSONObject updated) throws IOException {
        String filename = "state.json";
        File file = new File(context.getFilesDir(), filename);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(updated.toString().getBytes("UTF-8"));
        fos.close();
    }

    public static void saveState(Context context, String quizId, JSONObject state) {
        try {
            System.out.println("Saving state file");
            try {
                JSONObject current = loadStateFile(context);
                current.put(quizId, state);
                saveStateFile(context, current);
            } catch (FileNotFoundException e) {
                JSONObject current = new JSONObject();
                current.put(quizId, state);
                saveStateFile(context, current);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getState(Context context, String quizId) {
        try {
            JSONObject current = loadStateFile(context);
            if (!current.has(quizId)) {
                return null;
            } else {
                return current.getJSONObject(quizId);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isComplete(Context context, String quizId) {
        try {
            JSONObject current = loadStateFile(context);
            if (!current.has(quizId)) {
                System.out.println("Quiz state not found");
                return false;
            } else {
                System.out.println("Quiz state found");
                JSONObject state = current.getJSONObject(quizId);
                return state.getBoolean("completed");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (JSONException e) {
            return false;
        }
    }
}
