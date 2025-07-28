package Controller;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.util.ArrayList;
import java.util.Arrays;
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
				
			case "addPB":
				request.getRequestDispatcher("/PhongBanView/InsertPhongBan.jsp").forward(request, response);
				break;

			case "editPB":
				String idPB = request.getParameter("id");
			    if (idPB == null) {
			        request.setAttribute("dsPB", phongBanBO.getAllPB());
			        request.getRequestDispatcher("/PhongBanView/UpdatePBList.jsp")
			               .forward(request, response);
			    } else {
			        PhongBan pb = phongBanBO.getPBbyID(idPB);
			        request.setAttribute("phongBan", pb);
			        request.getRequestDispatcher("/PhongBanView/FormUpdatePB.jsp")
			               .forward(request, response);
			    }
			    break;
			case "deletePB":
				String idDelete = request.getParameter("id");
				if(idDelete != null) {
					phongBanBO.deletePB(idDelete);
					request.setAttribute("msg", "Đã xoá phòng ban [" + idDelete + "] thành công");
				}
				request.setAttribute("dsPB", phongBanBO.getAllPB());
				request.getRequestDispatcher("/PhongBanView/DeletePBList.jsp").forward(request, response);
			case "deleteAll":
				request.setAttribute("dsPB", phongBanBO.getAllPB());
				request.getRequestDispatcher("/PhongBanView/DeleteAllPB.jsp").forward(request, response);
				break;
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
		else if ("deleteAll".equals(action)) {
	        String[] ids = request.getParameterValues("idPB");
	        ArrayList<String> listIdPB = ids != null ? new ArrayList<>(Arrays.asList(ids)) 
	        		: new ArrayList<>();
	        int count = phongBanBO.deleteAll(listIdPB);

	        String msg = (count > 0)
	            ? "✅ Đã xóa thành công " + count + " phòng ban."
	            : "❌ Không có phòng ban nào được chọn.";
	        request.setAttribute("msg", msg);
	        request.setAttribute("dsPB", phongBanBO.getAllPB());
	        request.getRequestDispatcher("/PhongBanView/DeleteAllPB.jsp")
	               .forward(request, response);
	        return;
		}
		
	}
}
