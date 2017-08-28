package SGQ.construct.controller;


import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import SGQ.construct.dao.ObraDao;
import SGQ.construct.dao.ObraRisDao;
import SGQ.construct.dao.RisDao;
import SGQ.construct.dao.utils.DAOFactory;
import SGQ.construct.model.Obra;
import SGQ.construct.model.ObraRis;
import SGQ.construct.model.RIS;






/**
 * Servlet implementation class RISController
 */
@WebServlet("/ObraController")
public class ObraController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ObraController() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void processarRequisicao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
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

		}  else if (action.equals("cadastra")) {
			ircadastra(request, response);
		} else if (action.equals("salvacad")) {
			cadastra(request, response);
		} else if (action.equals("Obra/RIS")) {
			irobraris(request, response);
		}else if (action.equals("cadObra/RIS")) {
			cadobraris(request, response);
		}else if (action.equals("acessaObra")) {
			acessoObra(request, response);
		}else if (action.equals("ris")) {
			selecionaRis(request, response);
		}else if (action.equals("listaObra")) {
			listaObra(request, response);
		}else if (action.equals("verObra")) {
			editarObra(request, response);
		}else if (action.equals("deleta_obra")) {
			deletaObra(request, response);
		}
	}
	
	
	
	
	
	private void deletaObra(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("obraId") ;
		ObraDao obraDao = DAOFactory.criarObra();
		Obra obra = obraDao.getObraunq(Integer.parseInt(id));
		obraDao.deletar(obra);
		getObra(request, response);
		RequestDispatcher rd = request
				.getRequestDispatcher("/private/getObra.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//seleciona Obra e envia para tela de editar
	private void editarObra(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("obraId") ;
		ObraDao obraDao = DAOFactory.criarObra();
		Obra obra = obraDao.getObraunq(Integer.parseInt(id));
		getRISObra(request, response);
		request.setAttribute("obraId", obra);
		
		RequestDispatcher rd = request
				.getRequestDispatcher("/private/editaObra.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listaObra(HttpServletRequest request, HttpServletResponse response) {
		getObra(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("/private/getObra.jsp");
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	//retorna todas a ris vinculadas a Obra
	private void selecionaRis(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		Obra obra = (Obra) session.getAttribute("obra1");
		ObraRisDao obrarisD = DAOFactory.criarObraRis();
		List<RIS> r =  obrarisD.getListaRis(obra);
		System.out.println(r.get(0).getNome());
		session.setAttribute("ris", r);
		RequestDispatcher rd = request.getRequestDispatcher("/private/mobile/thisobra.jsp");
		
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//verifica obras do usuario e envia para p�gina ObraUser
	private void acessoObra(HttpServletRequest request, HttpServletResponse response) {
		String obra = request.getParameter("obra");
		HttpSession session = request.getSession(true);
		
		if (obra==null) {
			
			int ob1 =  (Integer) session.getAttribute("temp");
			ObraDao ObraDao = DAOFactory.criarObra();
			Obra obra1 = ObraDao.getObraunq(ob1);
			RequestDispatcher rd = request.getRequestDispatcher("/private/mobile/obraUser.jsp");
			request.setAttribute("obraId", obra1);
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			int ob = Integer.parseInt(obra);
			session.setAttribute("temp", ob);
			ObraDao ObraDao = DAOFactory.criarObra();
			Obra obra1 = ObraDao.getObraunq(ob);
			RequestDispatcher rd = request.getRequestDispatcher("/private/mobile/obraUser.jsp");
			request.setAttribute("obraId", obra1);
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		
		
		
		
	}
	
	//vincula Obra a RIS
	private void cadobraris(HttpServletRequest request, HttpServletResponse response) {
		
		String obra_id = request.getParameter("obra");
		int id_obra = Integer.parseInt(obra_id);
		String[] ris = request.getParameterValues("ris");
		
		
		
		ObraDao ObraDao = DAOFactory.criarObra();
		Obra obra = ObraDao.getObraunq(id_obra);
		RisDao risDao = DAOFactory.criarRis();
		List<RIS> listaRIS = (List<RIS>)risDao.getRIS(ris);
		
		ObraRisDao obraRisDao = DAOFactory.criarObraRis();
		
		if (listaRIS!=null) {
			for (RIS r : listaRIS) {
				
				ObraRis obraRis= new ObraRis();
				
				
				obraRis.setObra(obra);
				obraRis.setRis(r);
				obraRisDao.salvar(obraRis);
				
			}
		}else{
			List<ObraRis> listObraRis = obra.getRis();
			obraRisDao.deletar(listObraRis);
		}
		getObra(request, response);
		getRISObra(request, response);
		request.setAttribute("obraId", obra);
		RequestDispatcher rd = request
				.getRequestDispatcher("/private/editaObra.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void getRISObra(HttpServletRequest request, HttpServletResponse response) {

		RisDao RisDao = DAOFactory.criarRis();
		ArrayList<RIS> listaRIS = RisDao.getRIS();
		request.setAttribute("minhalist", listaRIS);
		

	}
	
	private void irobraris(HttpServletRequest request, HttpServletResponse response) {
		getObra(request, response);
		getRISObra(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("/private/obraRis.jsp");
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
		
		
	

	private void getObra(HttpServletRequest request, HttpServletResponse response) {

		ObraDao ObraDao = DAOFactory.criarObra();
		HttpSession session = request.getSession(true);
		ArrayList<Obra> listaObra = ObraDao.getObra();
		session.setAttribute("listObra", listaObra);
/**
		RequestDispatcher rd = request
				.getRequestDispatcher("/private/ObraRis.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
	}
	
	private void ircadastra(HttpServletRequest request,
			HttpServletResponse response) {
		
		RequestDispatcher rd = request.getRequestDispatcher("/private/cadastraObra.jsp");

		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void cadastra(HttpServletRequest request,
			HttpServletResponse response) {
			
		String ccusto = request.getParameter("ccusto");
		String nome = request.getParameter("nomeObra");
		String descr = request.getParameter("descr");
		
		
		ObraDao ObraDao = DAOFactory.criarObra();
		if (request.getParameter("obraId")!=null) {
			
			Obra obra = ObraDao.getObraunq(Integer.parseInt(request.getParameter("obraId")));
			
			if (request.getParameter("status")!=null) {
				String status = request.getParameter("status");
				obra.setStatus(Integer.parseInt(status));
			}else{
				String status = "0";
				obra.setStatus(Integer.parseInt(status));
			}
			obra.setNumero(ccusto);
			obra.setNome(nome);
			obra.setDescricao(descr);
			
			
			ObraDao.salvar(obra);
		}else{
		Obra obra = new Obra();
	
		if (request.getParameter("status")!=null) {
			String status = request.getParameter("status");
			obra.setStatus(Integer.parseInt(status));
		}else{
			String status = "0";
			obra.setStatus(Integer.parseInt(status));
		}
		
		obra.setNumero(ccusto);
		obra.setNome(nome);
		obra.setDescricao(descr);
		
		ObraDao.salvar(obra);
		}
		getObra(request, response);
		RequestDispatcher rd = request
				.getRequestDispatcher("/private/getObra.jsp");
		
			request.setAttribute("msm", "salvo");
		try {
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processarRequisicao(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processarRequisicao(request, response);
	}

}
