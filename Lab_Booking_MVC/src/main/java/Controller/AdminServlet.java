import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.bo.UserBO;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserBO userBO;

    public void init() {
        userBO = new UserBO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "dashboard";

        switch (action) {
            case "dashboard":
                request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
                break;
            case "manageUsers":
                // load danh sách người dùng cho admin
                break;
            // thêm các action khác của admin
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // xử lý post cho admin
    }
}
