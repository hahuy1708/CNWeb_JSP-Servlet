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
	private PhongBanBO phongBanBO = new PhongBanBO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		if (action == null) action = "list";

		switch (action) {
			case "list":
				ArrayList<PhongBan> danhSachPB = phongBanBO.getAllPB();
				request.setAttribute("dsPB", danhSachPB);
				request.getRequestDispatcher("/PhongBanView/PhongBanList.jsp").forward(request, response);
				break;

			case "showFormAdd":
				request.getRequestDispatcher("/PhongBanView/InsertPhongBan.jsp").forward(request, response);
				break;

//			case "delete":
//				String idXoa = request.getParameter("id");
//				phongBanBO.deletePhongBan(idXoa);
//				response.sendRedirect("PhongBanServlet?action=list");
//				break;
//			case "showFormEdit":
//				ArrayList<PhongBan> updateList = phongBanBO.getAllPB();
//                request.setAttribute("updateList", updateList);
//				request.getRequestDispatcher("/PhongBanView/FormUpdatePB.jsp").forward(request, response);
//				break;

			default:
				response.sendRedirect("PhongBanServlet?action=list");
				break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		if (action == null) action = "";

		if (action.equals("insert")) {
			String idPB = request.getParameter("idPB");
			String tenPB = request.getParameter("tenPB");
			String moTa = request.getParameter("moTa");

			PhongBan pb = new PhongBan(idPB, tenPB, moTa);
			phongBanBO.insertPB(pb);

			response.sendRedirect("PhongBanServlet?action=list");
		}
		else if(action.equals("update")) {
			String idPB = request.getParameter("idPB");
			String tenPB = request.getParameter("tenPB");
			String moTa = request.getParameter("moTa");

			PhongBan pb = new PhongBan(idPB, tenPB, moTa);
			phongBanBO.updatePB(pb);
			response.sendRedirect("PhongBanServlet?action=list");

		}
	}
}
