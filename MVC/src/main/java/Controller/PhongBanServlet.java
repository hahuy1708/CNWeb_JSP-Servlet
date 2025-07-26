package Controller;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.util.ArrayList;

import java.io.IOException;

import Model.bo.PhongBanBO;
import Model.bean.PhongBan;

@WebServlet("/PhongBanServlet")
public class PhongBanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // Gọi lớp BO để lấy danh sách phòng ban
        PhongBanBO phongBanBO = new PhongBanBO();
        ArrayList<PhongBan> danhSachPB = phongBanBO.getAllPB();

        // Gán danh sách vào request
        request.setAttribute("dsPB", danhSachPB);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/PhongBanView/PhongBanList.jsp");
        dispatcher.forward(request, response);
    }

}
