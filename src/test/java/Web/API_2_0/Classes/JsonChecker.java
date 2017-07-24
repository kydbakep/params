package Web.API_2_0.Classes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class JsonChecker {

    private final String targetFields[] = {"success", "errors", "warnings", "info"};
    private final JSONObject outputJson = new JSONObject();

    public JsonChecker(String input) {
        if (input.startsWith("{")) {
            try {
                JSONObject jsonObject = new JSONObject(input);
                checkObject(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (input.startsWith("[")) {
            try {
                JSONArray jsonArray = new JSONArray(input);
                checkArray(jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //INVALID JSON
        }
        String output = outputJson.toString();
        System.out.println(output);
    }

    public void checkObject(JSONObject object) {
        Iterator iter = object.keys();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            Object value = null;
            try {
                value = object.get(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (value instanceof JSONObject) {
                JSONObject json = (JSONObject) value;
                JSONObject jsonOutput = null;
                for (String targetField : targetFields) {
                    if (json.has(targetField)) {
                        if (jsonOutput == null) {
                            jsonOutput = new JSONObject();
                        }
                        try {
                            String targetFieldValue = json.optString(targetField);
                            if (targetFieldValue != null && !targetFieldValue.equals("")) {
                                targetFieldValue = targetFieldValue.replaceAll("\\[", "")
                                        .replaceAll("\\]", "")
                                        .replaceAll("\\{", "")
                                        .replaceAll("\\}", "")
                                        .replaceAll("\"", "");
                                if (!targetFieldValue.equals("")) {
                                    jsonOutput.put(targetField, targetFieldValue);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    outputJson.put(key, jsonOutput);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (value instanceof JSONArray) {
                JSONArray array = (JSONArray) value;
                checkArray(array);
            }
        }
    }

    private void checkArray(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject jsonObject = array.getJSONObject(i);
                checkObject(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
