package Controller;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import Model.bo.NhanVienBO;
import Model.bean.NhanVien;
import Model.bean.PhongBan;
import Model.bo.PhongBanBO;

@WebServlet("/NhanVienServlet")
public class NhanVienServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NhanVienBO nhanVienBO = new NhanVienBO();
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
				ArrayList<NhanVien> dsNV = nhanVienBO.getAllNhanVien();
				request.setAttribute("dsNV", dsNV);
				request.getRequestDispatcher("/NhanVienView/NhanVienList.jsp").forward(request, response);
				break;

			case "addNV":
				ArrayList<PhongBan> dsPB = phongBanBO.getAllPB();
				request.setAttribute("dsPB", dsPB);
				request.getRequestDispatcher("/NhanVienView/InsertNhanVien.jsp").forward(request, response);
				break;

			case "editNV":
				String idNV = request.getParameter("id");
				if (idNV == null) {
					request.setAttribute("dsNV", nhanVienBO.getAllNhanVien());
					request.getRequestDispatcher("/NhanVienView/UpdateNVList.jsp")
					.forward(request, response);
				} else {
					NhanVien nv = nhanVienBO.getNhanVienByID(idNV);
					request.setAttribute("nhanVien", nv);
					request.getRequestDispatcher("/NhanVienView/FormUpdateNV.jsp").forward(request, response);
				}
				break;

			case "deleteNV":
				String idDelete = request.getParameter("id");
				if (idDelete != null) {
					nhanVienBO.deleteNhanVien(idDelete);
					request.setAttribute("msg", "Đã xoá nhân viên [" + idDelete + "] thành công");
				}
				request.setAttribute("dsNV", nhanVienBO.getAllNhanVien());
				request.getRequestDispatcher("/NhanVienView/DeleteNVList.jsp").forward(request, response);
				break;

			case "deleteAll":
				request.setAttribute("dsNV", nhanVienBO.getAllNhanVien());
				request.getRequestDispatcher("/NhanVienView/DeleteAllNV.jsp").forward(request, response);
				break;

			default:
				response.sendRedirect("NhanVienServlet?action=list");
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

		switch (action) {
			case "insert":
				String idNV = request.getParameter("idNV");
				String hoTen = request.getParameter("hoTen");
				String idPB = request.getParameter("idPB");
				String diaChi = request.getParameter("diaChi");

				NhanVien nvInsert = new NhanVien(idNV, hoTen, idPB, diaChi);
				nhanVienBO.insertNhanVien(nvInsert);
				response.sendRedirect("NhanVienServlet?action=list");
				break;

			case "update":
				String idUpdate = request.getParameter("idNV");
				String tenUpdate = request.getParameter("hoTen");
				String pbUpdate = request.getParameter("idPB");
				String dcUpdate = request.getParameter("diaChi");

				NhanVien nvUpdate = new NhanVien(idUpdate, tenUpdate, pbUpdate, dcUpdate);
				nhanVienBO.updateNhanVien(nvUpdate);
				response.sendRedirect("NhanVienServlet?action=list");
				break;

			case "deleteAll":
				String[] ids = request.getParameterValues("idNV");
				ArrayList<String> listIdNV = (ids != null) ? new ArrayList<>(Arrays.asList(ids)) : new ArrayList<>();

				int count = nhanVienBO.deleteAllNhanVien(listIdNV);
				String msg = (count > 0)
						? "✅ Đã xóa thành công " + count + " nhân viên."
						: "❌ Không có nhân viên nào được chọn.";

				request.setAttribute("msg", msg);
				request.setAttribute("dsNV", nhanVienBO.getAllNhanVien());
				request.getRequestDispatcher("/NhanVienView/DeleteAllNV.jsp").forward(request, response);
				break;
		}
	}
}
