/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SGQ.construct.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import util.ConnectionFactory;
import util.ReportUtils;

@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
  
	private static final long serialVersionUID = 1L;

	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws Exception {
    	HttpSession s = request.getSession();
		String action = request.getParameter("action");
		if (action == null || s.getAttribute("usuario") == null) {
			System.out.println("Nenhuma opÁ„o especificada ou vocÍ n„o est· logado");

			RequestDispatcher rd = request.getRequestDispatcher("/publica/index.jsp");
			try {
				request.setAttribute("msm", "expirou");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (action.equals("relatorio")) {
			listarRelat(request, response);
		}  else if (action.equals("naoConform")) {
			sendnaoConform(request, response);
		}else if (action.equals("rel")) {
			gerarRel(request, response);
		}else if (action.equals("grafico")) {
			sendgerarRel(request, response);
		}else if (action.equals("relVerif")) {
			sendrelVerif(request, response);
		}else if (action.equals("graficoG")) {
			sendgrafG(request, response);
		}else if (action.equals("getGrafico")) {
			getGrafico(request, response);
		}else if (action.equals("getRel")) {
			getRel(request, response);
		}
    	
		
    	
    	

    }

 





	private void getRel(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher rd = request.getRequestDispatcher("/private/relatorios/listrelatorio.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}







	private void getGrafico(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher rd = request.getRequestDispatcher("/private/relatorios/listgrafico.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}







	private void sendgrafG(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher rd = request.getRequestDispatcher("/private/relatorios/graficoGeral.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	private void sendgerarRel(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher rd = request.getRequestDispatcher("/private/relatorios/grafico.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	private void sendrelVerif(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher rd = request.getRequestDispatcher("/private/relatorios/relVerifica.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	private void sendnaoConform(HttpServletRequest request, HttpServletResponse response) {
    	RequestDispatcher rd = request.getRequestDispatcher("/private/relatorios/naoConform.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private void listarRelat(HttpServletRequest request, HttpServletResponse response) {
    	RequestDispatcher rd = request.getRequestDispatcher("/private/relatorios/relatorio.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void gerarRel(HttpServletRequest request, HttpServletResponse response) throws  Exception {
		
		
		
		String obraNome =request.getParameter("obraNome");
		String relatorio =request.getParameter("relName");
		System.out.println("nome relatÛrio ->"+relatorio);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dti = request.getParameter("datainicio");
		String dtf = request.getParameter("datafinal");
		
		java.util.Date datain=null;
    	java.util.Date datafin=null;
    	
			
			datain = dateFormat.parse(dti) ;
	    	datafin = dateFormat.parse(dtf) ;
			
		    	

        java.sql.Date datainicio = new java.sql.Date(datain.getTime()); 
        java.sql.Date datafinal = new java.sql.Date(datafin.getTime());
        System.out.println("datainicio:" + datainicio);  
        System.out.println("datafinal:" + datafinal); 
    	
    	
    	
        OutputStream out = null;

        // obt√©m o relat√≥rio compilado
        InputStream inputStream = getClass().getResourceAsStream( "/relatorio/"+relatorio+".jasper" );

        // preenche o mapa de par√¢metros
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put( "dinicio", datainicio );
        parametros.put( "dfinal", datafinal);
        parametros.put( "obra", obraNome);
        try {

            // gera o relat√≥rio e atribui o OutputStream gerado
            out = ReportUtils.createPDFReport( inputStream, parametros,
                    ConnectionFactory.getConexao(), response );

        } catch ( SQLException exc ) {
            exc.printStackTrace();
        } catch ( JRException exc ) {
            exc.printStackTrace();
        } finally {

            // se n√£o aconteceu nenhum problema, fecha o output stream
            if ( out != null ) {
                out.close();
            }

        }
		
	}

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
