package br.edu.utfpr.md.architecture.infrastructure.mail;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Repository;
import org.springframework.ui.velocity.VelocityEngineUtils;

import br.edu.utfpr.md.architecture.model.entity.User;

/**
 * 
 */
@Repository
public class SampleMailRepository 
{
	/**
	 * 
	 */
	private static final Logger LOG = Logger.getLogger( SampleMailRepository.class.getName() );
	
	
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private JavaMailSender mailSender;
	/**
	 * 
	 */
	@Autowired
	private VelocityEngine engine;
	
	/*-------------------------------------------------------------------
	 * 		 					BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
    public void sendConfirmRegisterMail( final User user )
    {
        MimeMessagePreparator preparator = new MimeMessagePreparator() 
        {
            public void prepare( MimeMessage mimeMessage ) throws Exception 
            {
            	MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
               
            	message.setTo(user.getEmail());
            	message.setFrom("no-reply@descontoesperto.com.br");
            	message.setSubject("Confirmação de cadastro");

            	final Map<String, Object> model = new HashMap<String, Object>();
            	
            	model.put("user", user);
           		model.put("link", "http://localhost:8080/utfpr/#/usuario/confirmar/" + user.getId() );
               
           		String body = VelocityEngineUtils.mergeTemplateIntoString(engine, "templates/confirm-register.html", "UTF-8", model);
           		message.setText(body, true);
            }
         };
         
         mailSender.send( preparator );
         
         LOG.info("Mail sent.");
    }
}