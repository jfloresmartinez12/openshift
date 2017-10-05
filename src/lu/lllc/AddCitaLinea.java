package lu.lllc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddBookAndAuthor
 */
@WebServlet("/AddCitaLinea")
public class AddCitaLinea extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCitaLinea() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String establecimiento;
		String tipoDocumento;
		Long numDocumento;
		String apellidoPaterno;
		String consultaSolicitada;
		
		try {
			establecimiento = request.getParameter("establecimiento");
			tipoDocumento = request.getParameter("tipoDocumento");
			numDocumento = Long.parseLong(request.getParameter("numDocumento"));
			apellidoPaterno = request.getParameter("apellidoPaterno");
			consultaSolicitada = request.getParameter("consultaSolicitada");
			
			
			
		} catch (Exception e) {
			System.out.println( e.getStackTrace() );
			RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/wrongParameterError.html");
			disp.forward(request, response);
			return;

		}
		
		DBTools dbTools = new DBTools();
		
		dbTools.addCita(establecimiento, tipoDocumento, numDocumento, apellidoPaterno, consultaSolicitada);
		
		RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/addingCita.jsp");
		disp.forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
