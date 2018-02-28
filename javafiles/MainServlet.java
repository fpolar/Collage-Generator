package DataContainers;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/CollageServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String mPath;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void init() throws ServletException {
        super.init();
        String path = getServletContext().getRealPath("WEB-INF/../");
        mPath = path;
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Entered main function");
		PrintWriter out = response.getWriter();
		String query = request.getParameter("query");
		CollageGenerator generator = new CollageGenerator();
		String collage = generator.buildCollage(query, mPath);
		if (collage.equals("ERROR")) {
			out.println("{\"src\": \"" + collage + "\", "
					+ "\"error\": \"true\"}");
		} else {
			out.println("{\"src\": \"" + collage + "\", "
					+ "\"error\": \"false\"}");
		}
		
		
	
	}


}
