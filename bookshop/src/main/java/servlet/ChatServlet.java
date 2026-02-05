package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.BookDAO;
import dao.GeminiDAO;
import model.Book;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // JSON受信
        BufferedReader reader = request.getReader();
        String body = reader.lines().collect(Collectors.joining());
        JSONObject json = new JSONObject(body);
        String message = json.getString("message");

        // 在庫リスト取得
        BookDAO dao = new BookDAO();
        List<Book> list = dao.search(null, null);

        String itemList = list.stream()
            .map(b -> "・タイトル:" + b.getTitle() +
                      " / 著者:" + b.getAuthor() +
                      " / 内容:" + b.getDescription())
            .collect(Collectors.joining("\n"));

        // Gemini 呼び出し
        ServletContext context = getServletContext();
        GeminiDAO gemini = new GeminiDAO(context);

        String answer = gemini.getChatResponse(message, itemList);

        System.out.println("Gemini返答(生): " + answer);

        // -----------------------------
        // ★ Markdown の ```json ``` を完全除去
        // -----------------------------
        answer = answer
                .replace("```json", "")
                .replace("```", "")
                .replace("```JSON", "")
                .replace("```Json", "")
                .trim();

        System.out.println("Gemini返答(整形後): " + answer);

        // -----------------------------
        // JSON パース（失敗しても落ちない）
        // -----------------------------
        String tmpTitle = "";
        String tmpReason = "";

        try {
            JSONObject ai = new JSONObject(answer);
            tmpTitle = ai.optString("title", "");
            tmpReason = ai.optString("reason", "");
        } catch (Exception e) {
            tmpTitle = "おすすめの本";
            tmpReason = answer;
        }

        final String title = tmpTitle;
        final String reason = tmpReason;

        // 在庫から一致する本を探す
        Book target = list.stream()
            .filter(b -> b.getTitle().equals(title))
            .findFirst()
            .orElse(null);

        // JSON返却
        JSONObject resJson = new JSONObject();
        resJson.put("title", title);
        resJson.put("reason", reason);

        if (target != null) {
            resJson.put("img", target.getImg());
            resJson.put("pid", target.getPid());
        } else {
            resJson.put("img", "");
            resJson.put("pid", -1);
        }

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(resJson.toString());
    }
}