package SGQ.construct.controller;

import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
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

import SGQ.construct.dao.ItensDao;
import SGQ.construct.dao.ObraRisDao;
import SGQ.construct.dao.PItemDao;
import SGQ.construct.dao.RisDao;

import SGQ.construct.dao.utils.DAOFactory;
import SGQ.construct.model.Itens;
import SGQ.construct.model.Obra;
import SGQ.construct.model.ObraRis;
import SGQ.construct.model.PItem;
import SGQ.construct.model.RIS;
import SGQ.construct.model.Usuario;
import util.GenerateValidation;

/**
 * Servlet implementation class RISController
 */
@WebServlet("/RISController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class RISController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RISController() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void processarRequisicao(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		HttpSession s = request.getSession();
		String action = request.getParameter("action");
		if (action == null || s.getAttribute("usuario") == null) {
			System.out.println("Nenhuma a��o especificada ou voc� n�o est� logado");

			RequestDispatcher rd = request.getRequestDispatcher("/publica/index.jsp");
			try {
				request.setAttribute("msm", "expirou");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (action.equals("cadastra")) {
			cadastra(request, response);
		} else if (action.equals("cad")) {
			salvacad(request, response);
		} else if (action.equals("getRIS")) {
			getRIS(request, response);
		} else if (action.equals("ris")) {
			preencheRis(request, response);
		} else if (action.equals("iten")) {
			preencheItens(request, response);
		} else if (action.equals("regItem")) {
			registraItens(request, response);
		} else if (action.equals("verRIS")) {
			getRISUnic(request, response);
		} else if (action.equals("edit")) {
			editaRis(request, response);
		} else if (action.equals("deleta_RIS")) {
			deletaRIS(request, response);
		} else if (action.equals("revisao_RIS")) {
			revisaoRIS(request, response);
		} else if (action.equals("revisaoSalvar")) {
			revisaoSalvar(request, response);
		}
	}

	// direciona para tela de revis�o
	private void revisaoRIS(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("RisId");
		RisDao risDao = DAOFactory.criarRis();
		RIS ris = risDao.getRISunq(Integer.parseInt(id));

		request.setAttribute("RisId", ris);
		RequestDispatcher rd = request.getRequestDispatcher("/private/revisaRis.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Salvar revis�o
	private void revisaoSalvar(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("RisId");
		RisDao risDao = DAOFactory.criarRis();
		RIS risoff = risDao.getRISunq(Integer.parseInt(id));
		risoff.setAtiva(false);
		
		
		RIS ris = new RIS();
		Date data = new Date();
		ItensDao ItensDao = DAOFactory.criarItens();
		ris.setRev(risoff.getRev() + 1);
		ris.setNumero(risoff.getNumero());
		ris.setIts(risoff.getIts());		
		ris.setNome(risoff.getNome());
		ris.setAtiva(true);
		ris.setData(data);
		
		ObraRisDao obraRisDao = DAOFactory.criarObraRis();
		List<ObraRis> ListobraRis= risoff.getObra();
		
		for (int i = 0; i < ListobraRis.size(); i++) {
			ObraRis obraRis = new ObraRis();
			Obra obra = ListobraRis.get(i).getObra();  
			obraRis.setObra(obra);
			obraRis.setRis(ris);
			obraRisDao.salvar(obraRis);
			
		}	
		
		
		
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		ris.setUsuarioCriacao(usuario);
		risDao.salvar(ris);
		
				

		for (int i = 0; i < request.getParameterValues("insp").length; i++) {
			Integer numIten = Integer.parseInt(request.getParameterValues("num")[i]);
			String inspecao = request.getParameterValues("insp")[i];
			String verificacao = request.getParameterValues("veref")[i];
			String tolerancia = request.getParameterValues("tole")[i];
			
			System.out.println(request.getParameterValues("insp").length);

			if (inspecao != "" & verificacao != "" & tolerancia != "") {
				
				Itens itens = new Itens();
				itens.setNum(numIten);
				itens.setInspecao(inspecao);
				itens.setVerificacao(verificacao);
				itens.setTolerancia(tolerancia);
				itens.setRis(ris);
				System.out.println("at� aqui ok");
				ItensDao.salvar(itens);
				System.out.println(" " + numIten + " " + inspecao + " " + verificacao + " " + tolerancia);
			}

		}
		getRIS(request, response);
	}

	// Deleta RIS
	private void deletaRIS(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("RisId");
		RisDao risDao = DAOFactory.criarRis();
		RIS ris = risDao.getRISunq(Integer.parseInt(id));
		ItensDao itenDao = DAOFactory.criarItens();
		System.out.println(ris.getNome());
		for (Itens i : ris.getItens()) {
			itenDao.delete(i);
		}
		ObraRisDao obraRisDao = DAOFactory.criarObraRis();
		List<ObraRis> obraRis = obraRisDao.getObraRis(ris);
		obraRisDao.deletar(obraRis);

		risDao.deletar(ris);

		getRIS(request, response);

	}

	// Direciona para pagina de ed��o da RIS
	private void editaRis(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("RisId");
		RisDao risDao = DAOFactory.criarRis();
		RIS ris = risDao.getRISunq(Integer.parseInt(id));

		request.setAttribute("RisId", ris);
		RequestDispatcher rd = request.getRequestDispatcher("/private/editaRis.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Retorna uma unica Ris e direciona para visualizar a mesma
	private void getRISUnic(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("RisId");
		RisDao risDao = DAOFactory.criarRis();
		RIS ris = risDao.getRISunq(Integer.parseInt(id));

		request.setAttribute("RisId", ris);
		RequestDispatcher rd = request.getRequestDispatcher("/private/verRis.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Realiza o preenchimento dos itens na tabela Pitens
	private void registraItens(HttpServletRequest request, HttpServletResponse response) {
		String obraItem = request.getParameter("obraItem");
		String local = request.getParameter("local");
		String descr = request.getParameter("descr");
		String[] conforme = request.getParameterValues("gender");

		String iten = request.getParameter("iten");
		ItensDao ItensDao = DAOFactory.criarItens();
		int it = Integer.parseInt(iten);
		Itens itens = ItensDao.getItensunq(it);
		String conform;
		PItemDao PItemDao = DAOFactory.criarPItem();
		PItem pitem = new PItem();
		Date data = new Date();
		HttpSession s = request.getSession(true);

		if (conforme[0].contains("1")) {
			conform = conforme[0];

			pitem.setData(data);
			pitem.setLocal(local);
			pitem.setConformidade(Integer.parseInt(conform));
			pitem.setDescricao(null);
			pitem.setItem(itens);
			pitem.setObraId(Integer.parseInt(obraItem));
			PItemDao.salvar(pitem);
			ItensDao.salvar(itens);

			s.setAttribute("risID", itens.getRis());
			preencheRis(request, response);

		} else if (conforme[0].contains("2")) {
			conform = conforme[0];

			pitem.setData(data);
			pitem.setLocal(local);
			pitem.setConformidade(Integer.parseInt(conform));
			pitem.setDescricao(null);
			pitem.setItem(itens);
			pitem.setObraId(Integer.parseInt(obraItem));
			PItemDao.salvar(pitem);
			ItensDao.salvar(itens);

			s.setAttribute("risID", itens.getRis());
			preencheRis(request, response);}
			else  {

			// ----------------------------------------------------------------------------------------
			String location = request.getServletContext().getRealPath("/").substring(0,
					request.getServletContext().getRealPath("/").indexOf("SGQ")) + "\\data";
			File fileSaveDir = new File(location);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}

			try {

				for (Part part : request.getParts()) {

					if (part.getName().contains("foto")) {
						String fileName1 = extractFileName(part);

						String fileName = GenerateValidation.keyValidation() + SetData(fileName1);

						part.write(location + File.separator + SetData(fileName));
						System.out.println(fileName);
						pitem.setFoto("http://localhost:8080/data/" + SetData(fileName));

					} else {
						System.out.println("sem imagem");
					}

				}
				if (conforme[0].contains("0")) {
					conform = "0";

					pitem.setData(data);
					pitem.setLocal(local);
					pitem.setConformidade(Integer.parseInt(conform));
					pitem.setDescricao(descr);
					pitem.setItem(itens);
					pitem.setObraId(Integer.parseInt(obraItem));
					PItemDao.salvar(pitem);
					ItensDao.salvar(itens);
					s.setAttribute("risID", itens.getRis());

					preencheRis(request, response);

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// ----------------------------------------------------------------------------------------

		}

	}

	// Preenche array de itens e direciona para pagina mobile
	private void preencheItens(HttpServletRequest request, HttpServletResponse response) {
		String obraItem = request.getParameter("obraItem");
		String iten = request.getParameter("iten");
		ItensDao ItensDao = DAOFactory.criarItens();
		int it = Integer.parseInt(iten);
		Itens itens = ItensDao.getItensunq(it);
		request.setAttribute("iten", itens);
		request.setAttribute("obraItem", obraItem);
		RequestDispatcher rd = request.getRequestDispatcher("/private/mobile/thisIten.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// carrega ris na sess�o Mobile e direciona para itens
	private void preencheRis(HttpServletRequest request, HttpServletResponse response) {
		String obraItem = request.getParameter("obraId");
		String ris = request.getParameter("ris");
		RisDao RisDao = DAOFactory.criarRis();
		RIS ris1;
		if (ris == null) {
			HttpSession s = request.getSession(true);
			ris1 = (RIS) s.getAttribute("risID");
			request.setAttribute("ris", ris1);
			request.setAttribute("obraItem", obraItem);
			RequestDispatcher rd = request.getRequestDispatcher("/private/mobile/thisRis.jsp");
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			int r = Integer.parseInt(ris);
			ris1 = RisDao.getRISunq(r);
			request.setAttribute("ris", ris1);
			request.setAttribute("obraItem", obraItem);
			RequestDispatcher rd = request.getRequestDispatcher("/private/mobile/thisRis.jsp");
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// carrega liata de ris na sess�o e direciona para lista de ris
	private void getRIS(HttpServletRequest request, HttpServletResponse response) {

		RisDao RisDao = DAOFactory.criarRis();
		HttpSession session = request.getSession(true);
		ArrayList<RIS> listaRIS = RisDao.getRIS();
		session.setAttribute("minhalist", listaRIS);

		RequestDispatcher rd = request.getRequestDispatcher("/private/getRIS.jsp");

		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void cadastra(HttpServletRequest request, HttpServletResponse response) {

		RequestDispatcher rd = request.getRequestDispatcher("/private/cadastraRis.jsp");

		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// salva o cadastro da ris + itens ou salva edi��o
	private void salvacad(HttpServletRequest request, HttpServletResponse response) {

		String Titulo = request.getParameter("Titulo");
		String RisNum = request.getParameter("RisNum");
		RisDao RisDao = DAOFactory.criarRis();
		Date data = new Date();
		ItensDao ItensDao = DAOFactory.criarItens();
		if (request.getParameter("RisId") != null) {

			RIS ris = RisDao.getRISunq(Integer.parseInt(request.getParameter("RisId")));
			ris.setNome(Titulo);
			ris.setRev(0);
			ris.setAtiva(true);
			ris.setNumero(RisNum);
			ris.setData(data);
			ris.setIts(null);
			HttpSession session = request.getSession();
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			ris.setUsuarioCriacao(usuario);
			RisDao.salvar(ris);

			for (Itens i : ris.getItens()) {

				ItensDao.delete(i);
			}

			for (int i = 0; i < request.getParameterValues("insp").length; i++) {
				Integer numIten = Integer.parseInt(request.getParameterValues("num")[i]);
				String inspecao = request.getParameterValues("insp")[i];
				String verificacao = request.getParameterValues("veref")[i];
				String tolerancia = request.getParameterValues("tole")[i];

				if (inspecao != "" & verificacao != "" & tolerancia != "") {

					Itens itens = new Itens();
					itens.setNum(numIten);
					itens.setInspecao(inspecao);
					itens.setVerificacao(verificacao);
					itens.setTolerancia(tolerancia);
					
					itens.setRis(ris);
					ItensDao.salvar(itens);
					System.out.println(" " + numIten + " " + inspecao + " " + verificacao + " " + tolerancia);
				}

			}

		} else {

			RIS ris = new RIS();
			ris.setNome(Titulo);
			ris.setAtiva(true);
			ris.setRev(0);
			ris.setNumero(RisNum);
			ris.setData(data);
			System.out.println(" " + Titulo + " " + RisNum + " " + data);
			HttpSession session = request.getSession();
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			ris.setUsuarioCriacao(usuario);
			RisDao.salvar(ris);

			for (int i = 0; i < request.getParameterValues("insp").length; i++) {
				Integer numIten = Integer.parseInt(request.getParameterValues("num")[i]);
				String inspecao = request.getParameterValues("insp")[i];
				String verificacao = request.getParameterValues("veref")[i];
				String tolerancia = request.getParameterValues("tole")[i];

				if (inspecao != "" & verificacao != "" & tolerancia != "") {

					Itens itens = new Itens();
					itens.setNum(numIten);
					itens.setInspecao(inspecao);
					itens.setVerificacao(verificacao);
					itens.setTolerancia(tolerancia);
					itens.setRis(ris);
					ItensDao.salvar(itens);
					System.out.println(" " + numIten + " " + inspecao + " " + verificacao + " " + tolerancia);
				}

			}
		}
		request.setAttribute("msm", "salvo");
		getRIS(request, response);

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

}
