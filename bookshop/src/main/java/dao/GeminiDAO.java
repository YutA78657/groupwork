package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.ServletContext;

import org.json.JSONArray;
import org.json.JSONObject;

public class GeminiDAO {

    private final String MODEL = "gemini-2.0-flash-lite";
    private String API_KEY;
    private String API_URL;

    public GeminiDAO(ServletContext context) {
        this.API_KEY = context.getInitParameter("GEMINI_API_KEY");
        this.API_URL =
                "https://generativelanguage.googleapis.com/v1beta/models/"
                        + MODEL + ":generateContent?key=" + API_KEY;
    }

    public String getChatResponse(String userMessage, String itemList) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);

            // -----------------------------
            // ★ プロンプト最適化（1冊だけ選ばせる）
            // -----------------------------
            String prompt =
                    "あなたは優秀な書店員です。以下の在庫リストから、ユーザーの希望に最も合う本を1冊だけ選んでください。\n"
                  + "回答は必ず次のJSON形式で返してください：\n"
                  + "{\n"
                  + "  \"title\": \"選んだ本のタイトル\",\n"
                  + "  \"reason\": \"短い理由（50文字以内）\"\n"
                  + "}\n\n"
                  + "【在庫リスト】\n" + itemList + "\n\n"
                  + "【ユーザーの質問】\n" + userMessage;

            // -----------------------------
            // リクエスト JSON 作成
            // -----------------------------
            JSONObject jsonRequest = new JSONObject();
            JSONArray contents = new JSONArray();

            JSONObject content = new JSONObject();
            content.put("role", "user");

            JSONArray parts = new JSONArray();
            JSONObject part = new JSONObject();
            part.put("text", prompt);
            parts.put(part);

            content.put("parts", parts);
            contents.put(content);

            jsonRequest.put("contents", contents);

            // 送信
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonRequest.toString().getBytes(StandardCharsets.UTF_8));
            }

            int code = conn.getResponseCode();

            if (code == 200) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)
                );

                StringBuilder response = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) response.append(line);

                JSONObject jsonResponse = new JSONObject(response.toString());

                JSONArray candidates = jsonResponse.optJSONArray("candidates");
                if (candidates == null || candidates.length() == 0) {
                    return "{\"title\":\"不明\",\"reason\":\"AIの返答が取得できませんでした\"}";
                }

                JSONObject contentObj = candidates.getJSONObject(0).optJSONObject("content");
                if (contentObj == null) {
                    return "{\"title\":\"不明\",\"reason\":\"返答形式が不正です\"}";
                }

                JSONArray partsArr = contentObj.optJSONArray("parts");
                if (partsArr == null || partsArr.length() == 0) {
                    return "{\"title\":\"不明\",\"reason\":\"返答が空でした\"}";
                }

                String answer = partsArr.getJSONObject(0).optString("text", "{}");

                // -----------------------------
                // ★ Gemini の返答をログ出力
                // -----------------------------
                System.out.println("GeminiDAO 返答(JSON想定): " + answer);

                return answer;
            }

            return "{\"title\":\"不明\",\"reason\":\"エラー Code:" + code + "\"}";

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"title\":\"不明\",\"reason\":\"接続エラーが発生しました\"}";
        }
    }
}
