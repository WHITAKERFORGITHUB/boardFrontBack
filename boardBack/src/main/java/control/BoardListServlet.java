package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/list")
public class BoardListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BoardListServlet() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	// CORS 설정 추가
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    	
    	// 게시물 목록을 생성 (실제 애플리케이션에서는 DB에서 가져옴)
        List<BoardItem> boardItems = new ArrayList<>();
        boardItems.add(new BoardItem(1, "First Post", "This is the first post content"));
        boardItems.add(new BoardItem(2, "Second Post", "This is the second post content"));

        // List를 JSON 문자열로 변환
        String jsonResponse = convertToJson(boardItems);
        
        // JSON 응답 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }

    private String convertToJson(List<BoardItem> boardItems) {
        StringBuilder json = new StringBuilder();
        json.append("[");

        for (int i = 0; i < boardItems.size(); i++) {
            BoardItem item = boardItems.get(i);
            json.append("{");
            json.append("\"id\":").append(item.getId()).append(",");
            json.append("\"title\":\"").append(item.getTitle()).append("\",");
            json.append("\"content\":\"").append(item.getContent()).append("\"");
            json.append("}");

            if (i < boardItems.size() - 1) {
                json.append(",");
            }
        }

        json.append("]");
        return json.toString();
    }

    class BoardItem {
        private int id;
        private String title;
        private String content;

        public BoardItem(int id, String title, String content) {
            this.id = id;
            this.title = title;
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
