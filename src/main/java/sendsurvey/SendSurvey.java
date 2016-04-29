package sendsurvey;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import email.Send;
import model.form;


@WebServlet(urlPatterns={"/SendSurvey"})
public class SendSurvey extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6432540378268456035L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Properties properties = new Properties();
		//properties.load(getServletContext().getResourceAsStream("/WEB-INF/servlet.properties"));
		
		String nif = req.getParameter("NIF");
		String nombre = req.getParameter("NOMBRE");
		String pap = req.getParameter("PAP");
		String sap = req.getParameter("SAP");
		String comaut = req.getParameter("COMAUT");
		String provincia = req.getParameter("PROVINCIA");
		String municipio = req.getParameter("MUNICIPIO");
		String localidad = req.getParameter("LOCALIDAD");
		String direccion = req.getParameter("DIRECCION");
		String motivisol = req.getParameter("MOTIVISOL");
		String correo = req.getParameter("CORREO");
		String tel = req.getParameter("TEL");
		String texto = req.getParameter("TEXTO");
		
		form frm = new form();
		frm.set_NIF(nif);
		frm.set_NOMBRE(nombre);
		frm.set_PAP(pap);
		frm.set_SAP(sap);
		frm.set_COMAUT(comaut);
		frm.set_PROVINCIA(provincia);
		frm.set_MUNICIPIO(municipio);
		frm.set_LOCALIDAD(localidad);
		frm.set_DIRECCION(direccion);
		frm.set_MOTIVISOL(motivisol);
		frm.set_CORREO(correo);
		frm.set_TEL(tel);
		frm.set_TEXTO(texto);
		
		//String l_nif = frm.get_NIF();
		
		if (frm.get_NIF() != null 
				&& frm.get_NOMBRE() != null 
				&& frm.get_PAP() != null 
				&& frm.get_SAP() != null
				&& frm.get_COMAUT() != null
				&& frm.get_PROVINCIA() != null
				&& frm.get_MUNICIPIO() != null
				&& frm.get_LOCALIDAD() != null
				&& frm.get_DIRECCION() != null
				&& frm.get_MOTIVISOL() != null
				&& frm.get_CORREO() != null
				&& frm.get_TEL() != null
				&& frm.get_TEXTO() != null) {
		
			
			Send sendMail = new Send();
			sendMail.Caller(frm, null);
			
			PrintWriter pw = resp.getWriter();

			pw.write("<html><body><p> Email enviado com sucesso. </p></body></html>");
		}
		

	}

}
