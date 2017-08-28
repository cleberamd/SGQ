package SGQ.construct.controller;

import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import SGQ.construct.dao.ItsDao;
import SGQ.construct.dao.ObraDao;
import SGQ.construct.dao.RisDao;
import SGQ.construct.dao.utils.DAOFactory;
import SGQ.construct.model.Its;
import SGQ.construct.model.Obra;
import SGQ.construct.model.RIS;
import util.GenerateValidation;

/**
 * Servlet implementation class ItsController
 */
@WebServlet("/ItsController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class ItsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processarRequisicao(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processarRequisicao(request, response);
	}

	private void processarRequisicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession s = request.getSession();
		String action = request.getParameter("action");
		if (action == null || s.getAttribute("usuario") == null) {
			System.out.println("Nenhuma ação especificada ou você não está logado");

			RequestDispatcher rd = request.getRequestDispatcher("/publica/index.jsp");
			try {
				request.setAttribute("msm", "expirou");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (action.equals("salvar")) {
			salvarIts(request, response);
		} else if (action.equals("upload")) {
			upload(request, response);
		} else if (action.equals("listaIts")) {
			listarIts(request, response);
		} else if (action.equals("verIts")) {
			verIts(request, response);
		} else if (action.equals("deleta_its")) {
			deletaIts(request, response);
		} else if (action.equals("its")) {
			getItsObra(request, response);
		}
	}

	private void getItsObra(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt((String) request.getParameter("obra"));
		ObraDao obraDao = DAOFactory.criarObra();
		Obra obra = obraDao.getObraunq(id);
		List<Its> its = new ArrayList<Its>();
		for (int i = 0; i < obra.getRis().size(); i++) {
			if (obra.getRis().get(i).getRis().isAtiva()) {
				its.add(obra.getRis().get(i).getRis().getIts());
			}
			
		}
		
		request.setAttribute("its", its);
		RequestDispatcher rd = request.getRequestDispatcher("/private/mobile/thisIts.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deletaIts(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("itsId");
		ItsDao itsDao = DAOFactory.criarIts();
		Its its = itsDao.getItsunq(Integer.parseInt(id));

		System.out.println(its.getNome());
		itsDao.deletar(its);
		getIts(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/private/getIts.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void verIts(HttpServletRequest request, HttpServletResponse response) {

		if (request.getParameter("itsViewM") == null) {
			String id = request.getParameter("ItsId");
			ItsDao itsDao = DAOFactory.criarIts();
			Its its = itsDao.getItsunq(Integer.parseInt(id));
			request.setAttribute("its", its);
			System.out.println("true " + its.getLink());

			RequestDispatcher rd = request.getRequestDispatcher("/private/viewIts.jsp");
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String idM = request.getParameter("itsViewM");
			ItsDao itsDao = DAOFactory.criarIts();
			Its its = itsDao.getItsunq(Integer.parseInt(idM));
			request.setAttribute("its", its);
			System.out.println("Else" + its.getLink());

			RequestDispatcher rd = request.getRequestDispatcher("/private/mobile/viewIts.jsp");
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void listarIts(HttpServletRequest request, HttpServletResponse response) {
		getIts(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/private/getIts.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void getIts(HttpServletRequest request, HttpServletResponse response) {

		ItsDao ItsDao = DAOFactory.criarIts();
		HttpSession session = request.getSession(true);
		ArrayList<Its> listaIts = ItsDao.getIts();
		session.setAttribute("listIts", listaIts);

	}

	private void upload(HttpServletRequest request, HttpServletResponse response) {
		RisDao RisDao = DAOFactory.criarRis();
		HttpSession session = request.getSession(true);
		ArrayList<RIS> listaRIS = RisDao.getRIS();
		session.setAttribute("minhalist", listaRIS);
		RequestDispatcher rd = request.getRequestDispatcher("/private/cadastraITs.jsp");

		try {
			rd.forward(request, response);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void salvarIts(HttpServletRequest request, HttpServletResponse response) {
		String descricao = request.getParameter("descricao");
		String id = request.getParameter("ris");

		RisDao RisDao = DAOFactory.criarRis();
		RIS ris = RisDao.getRISunq(Integer.parseInt(id));
			System.out.println(request.getServletContext().getRealPath("/"));
			
				String location = request.getServletContext().getRealPath("/").substring(0,request.getServletContext().getRealPath("/").indexOf("SGQ")) + "\\data";
		System.out.println(location);
		int split = request.getRequestURL().indexOf("SGQ");
		String url = request.getRequestURL().substring(0, split) + "data/";

		Its its = new Its();
		File fileSaveDir = new File(location);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		try {
			ItsDao ItsDao = DAOFactory.criarIts();
			for (Part part : request.getParts()) {

				if (part.getName().contains("file")) {
					String fileName1 = extractFileName(part);

					String fileName = GenerateValidation.keyValidation() + SetData(fileName1);

					part.write(location + File.separator + SetData(fileName));

					its.setDescric(descricao);
					its.setNome(ris.getNome());
					its.setLink(url + SetData(fileName));
					its.setNum(ris.getNumero());
					
					ItsDao.salvar(its);
					System.out.println(its);
					ris.setIts(its);
					RisDao.salvar(ris);
				}

			}

			request.setAttribute("its", its);
			getServletContext().getRequestDispatcher("/private/viewIts.jsp").forward(request, response);

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");

		String[] items = contentDisp.split(";");

		for (String s : items) {
			if (s.trim().startsWith("filename")) {

				return s.substring(s.indexOf("."), s.length() - 1);
			}
		}
		return "";
	}

	private String SetData(String arquivo) {
		// String result = "";
		CharSequence cs = new StringBuilder(arquivo);
		return Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("[^\\p{ASCII}]", "").replace(" ", "")
				.toLowerCase().trim();
	}

}