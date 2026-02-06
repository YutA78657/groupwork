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

    /**
     * 選書API
     */
    public String getChatResponse(
            String userMessage,
            String itemList,
            String excludeList,
            String prevTitle,
            String prevGenre
    ) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);

         // -----------------------------
         // ★ 修正版プロンプト（「似たやつ」対応・完全合体Ver.）
         // -----------------------------
         String prompt =
               "あなたは熟練の書店員です。在庫リストから最適な1冊を提案してください。\n\n"
             + "【厳守ルール】\n"
             + "1. 回答は必ず指定のJSON形式のみとし、前置きや解説は一切含めないこと。\n"
             + "2. 理由(reason)には、「AIが判断した」「前回が〜だったので」「追加の要望なので」といったシステム的な説明やメタ発言を絶対に含めないこと。\n"
             + "3. 理由(reason)は、その本の内容、舞台、魅力、読後感のみを100文字以内で具体的に書くこと。\n"
             + "4. 在庫リストにない本は提案しない。除外リストにある本も選ばない。\n\n"
             + "【ジャンル選択の優先順位】\n"
             + "・ユーザーが特定のテーマ（例：動物、ミステリー、感動、怖い話など）を指定した場合：\n"
             + "  前回ジャンルに関わらず、在庫リストの「cname」や内容がそのテーマに最も合致する本を優先して選ぶこと。\n"
             + "・ユーザーが追加の提案を求めている場合（例：「ほかには」「別のは」「もっと」「次」「似たやつ」「関連して」など）：\n"
             + "  前回ジャンル「" + prevGenre + "」を【絶対に維持】し、同じジャンルのリスト内から別の本を選ぶこと。\n\n"
             + "【データ参照】\n"
             + "前回提案：" + prevTitle + "（ジャンル：" + prevGenre + "）\n"
             + "在庫リスト：\n" + itemList + "\n"
             + "除外リスト：\n" + excludeList + "\n\n"
             + "【ユーザーの入力】\n" + userMessage + "\n\n"
             + "回答形式（JSON）：\n"
             + "{\n"
             + "  \"title\": \"本のタイトル\",\n"
             + "  \"reason\": \"（システム的な説明を除き、その本の魅力だけを伝える紹介文）\",\n"
             + "  \"more\": 同じジャンルの本がまだ在庫にあればtrue、なければfalse\n"
             + "}";
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
                    return "{\"title\":\"不明\",\"reason\":\"AIの返答が取得できませんでした\",\"more\":false}";
                }

                JSONObject contentObj = candidates.getJSONObject(0).optJSONObject("content");
                if (contentObj == null) {
                    return "{\"title\":\"不明\",\"reason\":\"返答形式が不正です\",\"more\":false}";
                }

                JSONArray partsArr = contentObj.optJSONArray("parts");
                if (partsArr == null || partsArr.length() == 0) {
                    return "{\"title\":\"不明\",\"reason\":\"返答が空でした\",\"more\":false}";
                }

                String answer = partsArr.getJSONObject(0).optString("text", "{}");

                System.out.println("GeminiDAO 返答(JSON想定): " + answer);

                return answer;
            }

            return "{\"title\":\"不明\",\"reason\":\"エラー Code:" + code + "\",\"more\":false}";

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"title\":\"不明\",\"reason\":\"接続エラーが発生しました\",\"more\":false}";
        }
    }
}
