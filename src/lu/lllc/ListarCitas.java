package lu.lllc;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListBooks
 */
@WebServlet("/ListarCitas")
public class ListarCitas  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarCitas() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBTools dbTools = new DBTools();
		ArrayList<Cita> citaList = dbTools.getAllCitaList();

		request.setAttribute("citaList", citaList);

		RequestDispatcher disp = request
				.getRequestDispatcher("/WEB-INF/showCitaList.jsp");
		disp.forward(request, response);

	}

}
