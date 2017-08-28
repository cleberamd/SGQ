package SGQ.construct.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import SGQ.construct.dao.ObraDao;
import SGQ.construct.dao.UsuarioDao;
import SGQ.construct.dao.utils.DAOFactory;
import SGQ.construct.model.Obra;

import SGQ.construct.model.Usuario;
import util.GenerateMD5;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/UsuarioController")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioController() {
		super();
		
	}

	private UsuarioDao usuarioDao;

	private void processarRequisicao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		
		HttpSession s = request.getSession();
		String action = request.getParameter("action");
		
		if (action == null ) {
			System.out.println("Nenhuma ação especificada ou você não está logado");

			RequestDispatcher rd = request.getRequestDispatcher("/publica/index.jsp");
			try {
				request.setAttribute("msm", "expirou");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}  else if (action.equals("login")) {
			logar(request, response);
		}else if (action.equals("mobile")) {
			mobile(request, response);
		} else if (action.equals("loginM")) {
			logarM(request, response);
		}else if (s.getAttribute("usuario") != null) {
		 
		 if (action.equals("novo_user")) {
			novo_user(request, response);
		} else if (action.equals("criar_conta")) {
			irParaIniciarCriarConta(request, response);
		} else if (action.equals("cadObra/User")) {
			cadObraUser(request, response);
		}else if (action.equals("sair")) {
			sairM(request, response);
		} else if (action.equals("sair*")) {
			sair(request, response);
		}else if (action.equals("usuarios")) {
			listaUsuarios(request, response);
		}else if (action.equals("verUsuario")) {
			getUserUnic(request, response);
		}else if (action.equals("deleta_user")) {
			deletaUser(request, response);
		}}else{
			RequestDispatcher rd = request.getRequestDispatcher("/publica/index.jsp");
			try {
				request.setAttribute("msm", "expirou");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void mobile(HttpServletRequest request, HttpServletResponse response) {

		RequestDispatcher rd = request
				.getRequestDispatcher("/publica/mobile/indexM.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void logarM(HttpServletRequest request, HttpServletResponse response) {
		GenerateMD5 md5 =  new GenerateMD5();
		String nome = request.getParameter("nome");
		String senha =md5.generate(request.getParameter("senha"));
		
		usuarioDao = DAOFactory.createUsuario();
		HttpSession session = request.getSession(true);
		Usuario usuario;
		if (nome == null) {
			usuario = (Usuario) session.getAttribute("usuario");
			if (usuario != null) {

				session.setAttribute("usuario", usuario);

				RequestDispatcher rd = request
						.getRequestDispatcher("/private/mobile/telaInicioM.jsp");

				try {
					rd.forward(request, response);
				} catch (Exception e) {

					e.printStackTrace();
				}

			} else {
				System.out.println("nÃ£o logado");
				RequestDispatcher rd = request
						.getRequestDispatcher("/private/mobile/telaInicioM.jsp");

				try {
					rd.forward(request, response);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		} else {
			usuario = usuarioDao.login(nome, senha);

			if (usuario != null) {

				session.setAttribute("usuario", usuario);

				RequestDispatcher rd = request
						.getRequestDispatcher("/private/mobile/telaInicioM.jsp");

				try {
					rd.forward(request, response);
				} catch (Exception e) {

					e.printStackTrace();
				}

			} else {
				System.out.println("nÃ£o logado");
				RequestDispatcher rd = request
						.getRequestDispatcher("/private/mobile/indexM.jsp");
				
				try {
					rd.forward(request, response);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}

	}

	private void deletaUser(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("usuarioId") ;
		UsuarioDao UsuarioDao = DAOFactory.createUsuario();
		Usuario usuario = UsuarioDao.getUserunq(Integer.parseInt(id));
		UsuarioDao.deletar(usuario);
		getUsers(request, response);
		RequestDispatcher rd = request
				.getRequestDispatcher("/private/getUsuarios.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getUserUnic(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("usuarioId") ;
		UsuarioDao UsuarioDao = DAOFactory.createUsuario();
		Usuario usuario = UsuarioDao.getUserunq(Integer.parseInt(id));
		getObra(request, response);
		request.setAttribute("usuarioId", usuario);
		RequestDispatcher rd = request
				.getRequestDispatcher("/private/editaUsuario.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private void listaUsuarios(HttpServletRequest request, HttpServletResponse response) {
		getObra(request, response);
		getUsers(request, response);
		RequestDispatcher rd = request
				.getRequestDispatcher("/private/getUsuarios.jsp");
		
		try {
			
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

	private void sairM(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();

		RequestDispatcher rd = request
				.getRequestDispatcher("/publica/mobile/indexM.jsp");

		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private void sair(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();

		RequestDispatcher rd = request
				.getRequestDispatcher("/publica/index.jsp");

		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void cadObraUser(HttpServletRequest request,
			HttpServletResponse response) {
		String user = request.getParameter("user");
		int us = Integer.parseInt(user);
		String[] obra = request.getParameterValues("obra_id");

		UsuarioDao UsuarioDao = DAOFactory.createUsuario();

		Usuario user1 = UsuarioDao.getUserunq(us);

		
		if (obra!=null) {
			ArrayList<Obra> listaObra = (ArrayList<Obra>) UsuarioDao
					.getListObra(obra);
			user1.setObra(listaObra);
			UsuarioDao.save(user1);
		} else {
			user1.getObra().clear();
			UsuarioDao.save(user1);

		}
		getObra(request, response);
		getUsers(request, response);
		request.setAttribute("usuarioId", user1);
		RequestDispatcher rd = request
				.getRequestDispatcher("/private/editaUsuario.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	private void getObra(HttpServletRequest request,
			HttpServletResponse response) {

		ObraDao ObraDao = DAOFactory.criarObra();
		
		ArrayList<Obra> listaObra = ObraDao.getObra();
		request.setAttribute("listObra", listaObra);
	
	}

	private void getUsers(HttpServletRequest request,
			HttpServletResponse response) {

		UsuarioDao UsuarioDao = DAOFactory.createUsuario();
		
		ArrayList<Usuario> listaUsuario = UsuarioDao.getUser();
		
		request.setAttribute("listUsuario", listaUsuario);

	}

	private void irParaIniciarCriarConta(HttpServletRequest request,
			HttpServletResponse response) {
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("private/cadastraUsuario.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void logar(HttpServletRequest request, HttpServletResponse response) {
		
		
		GenerateMD5 md5 =  new GenerateMD5();
		String user = request.getParameter("usuario");
		String senha =md5.generate(request.getParameter("senha"));
		UsuarioDao usuarioDao = DAOFactory.createUsuario();
		Usuario usuario = usuarioDao.login(user, senha);

		if (usuario != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("usuario", usuario);
			if(usuario.getPerfil()==1){
			RequestDispatcher rd = request
					.getRequestDispatcher("/private/telaInicio.jsp");
			try {
				request.setAttribute("msm", "Login");
				rd.forward(request, response);
			} catch (Exception e) {

				e.printStackTrace();
			}
			}else{
				RequestDispatcher rd = request
						.getRequestDispatcher("/private/mobile/telaInicioM.jsp");
				try {
					rd.forward(request, response);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
			

		} else {
			System.out.println("não logado");
			
			RequestDispatcher rd = request
					.getRequestDispatcher("/publica/index.jsp");
			try {
				request.setAttribute("msm", "erroLogin");
				rd.forward(request, response);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	

	private void novo_user(HttpServletRequest request,
			HttpServletResponse response) {
		GenerateMD5 md5 =  new GenerateMD5();
		UsuarioDao usuarioDao = DAOFactory.createUsuario();
		String nome = request.getParameter("nome");
		String sobrenome = request.getParameter("Sobrenome");
		String Usuario = request.getParameter("Usuario");
		String senha = md5.generate(request.getParameter("senha"));
		
		
		String funcao = request.getParameter("funcao");
		
		if (request.getParameter("usuariId")!=null) {
			Usuario usuario = usuarioDao.getUserunq(Integer.parseInt(request.getParameter("usuariId")));
			
			if (request.getParameter("perfil")!=null) {
				String perfil = request.getParameter("perfil");
				usuario.setPerfil(Integer.parseInt(perfil));
			}else{
				String perfil ="0";
				usuario.setPerfil(Integer.parseInt(perfil));
			}
			if (request.getParameter("status")!=null) {
				String status = request.getParameter("status");
				usuario.setStatus(Integer.parseInt(status));
			}else{
				String status = "0";
				usuario.setStatus(Integer.parseInt(status));
			}
			usuario.setNome(nome);
			usuario.setSobrenome(sobrenome);
			usuario.setUsuario(Usuario);
			usuario.setSenha(senha);
			usuario.setFuncao(funcao);
			
			usuarioDao.save(usuario);
			
		}else{
		
		Usuario usuario = new Usuario();
		
		if (request.getParameter("perfil")!=null) {
			String perfil = request.getParameter("perfil");
			usuario.setPerfil(Integer.parseInt(perfil));
		}else{
			String perfil ="0";
			usuario.setPerfil(Integer.parseInt(perfil));
		}
		if (request.getParameter("status")!=null) {
			String status = request.getParameter("status");
			usuario.setStatus(Integer.parseInt(status));
		}else{
			String status = "0";
			usuario.setStatus(Integer.parseInt(status));
		}
		
		usuario.setNome(nome);
		usuario.setSobrenome(sobrenome);
		usuario.setUsuario(Usuario);
		usuario.setSenha(senha);
		usuario.setFuncao(funcao);
		
		usuarioDao.save(usuario);
		}
		request.setAttribute("msm", "salvo");
		listaUsuarios(request, response);
		
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
