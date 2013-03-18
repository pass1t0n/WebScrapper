package net.crawler.service.export;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Chaim Arbiv
 * @version $id$
 * Sends an email with 2 attachments
 */
public class SendCustomMailExporter implements Exporter {
    private final Log log = LogFactory.getLog(getClass());

    private List<String> emailAddresses = new LinkedList<String>();
    private File pic1, pic2;
    private String sendTo;

    private JavaMailSender sender;

    @Autowired
    ServletContext context;
    private String mailAddress;

    @Override
    public void init() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void writeValue(String value) {
        mailAddress = value;
        emailAddresses.add(value);
    }

    @Override
    public void afterPageProcessing() {
        try{
            MimeMessage message = getSender().createMimeMessage();

            // use the true flag to indicate you need a multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo("kal.lizkor@gmail.com"); //helper.setTo(mailAddress);
            helper.setSubject("Hey you");
            helper.setText(" Bla Bla");

            pic1 = new File("D:\\C_Drive\\temp\\1_small.jpg");
            pic1 = new File("D:\\C_Drive\\temp\\2_small.jpg");


            FileSystemResource file1 = new FileSystemResource(pic1);
            FileSystemResource file2 = new FileSystemResource(pic2);

            helper.addAttachment(pic1.getName(), file1);
            helper.addAttachment(pic2.getName(), file2);

            getSender().send(message);
        }
        catch (MessagingException e) {
            log.error("MessagingException: " + e);
        }
    }

    @Override
    public void close(){

    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public JavaMailSender getSender() {
        return sender;
    }

    public void setSender(JavaMailSender sender) {
        this.sender = sender;
    }

    public ServletContext getContext() {
        return context;
    }

    public void setContext(ServletContext context) {
        this.context = context;
    }
}
