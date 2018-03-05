package DataContainers;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		System.out.println("Entered main function");
		boolean testMode = true; //uses cached images 
		PrintWriter out = response.getWriter();
		String query = request.getParameter("query");
		HttpSession session = request.getSession();
		CollageGenerator generator = new CollageGenerator();
		String collage = "";
		if (session.getAttribute("link") != null && testMode) {
			System.out.println("Reusing previous images");
			ArrayList<Image> images = (ArrayList<Image>) session.getAttribute("images");
			collage = generator.buildCollage(query, mPath, images);
		} else {
			collage = generator.buildCollage(query, mPath, null);
			ArrayList<Image> images = generator.getImages();
			session.setAttribute("images", images);
		}
		
		
		
		
		if (!collage.equals("ERROR")) {
			out.println("{\"src\": \"" + collage + "\"}");
		} else {
			out.println("{\"src\": \"images/NotEnoughImages.png\"}");
		}
		
		
	
	}


}
