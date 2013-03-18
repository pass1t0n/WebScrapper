package net.crawler.service.export;

import au.com.bytecode.opencsv.CSVWriter;
import net.crawler.service.StatusService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//import java.io.FileWriter;

/**
 * @author Chaim Arbiv
 * @version $id$
 */

public class CsvMailExporter implements Exporter {
    private final Log log = LogFactory.getLog(getClass());
    DateFormat df = new SimpleDateFormat("MM_dd_yyyy");

    ArrayList<String> rowOfValues = new ArrayList<String>();
    private Writer strWriter = new StringWriter();
    private CSVWriter csvWriter = new CSVWriter(strWriter);
    private String sendTo;

    private JavaMailSender sender;

    @Autowired
    private StatusService statusService;

    @Autowired
    ServletContext context;

    @Override
    public void init() {
        rowOfValues = new ArrayList<String>();
        strWriter = new StringWriter();
        csvWriter = new CSVWriter(strWriter);
    }

    @Override
    public void writeValue(String value) {
        rowOfValues.add(value);
    }

    @Override
    public void afterPageProcessing() {
        csvWriter.writeNext(rowOfValues.toArray(new String[0]));
        rowOfValues = new ArrayList<String>();
    }

    @Override
    public void close() {
        try {
            csvWriter.close();

            MimeMessage message = getSender().createMimeMessage();

            // use the true flag to indicate you need a multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(getSendTo());
            helper.setSubject("scrapping page from " + df.format(new Date()));
            helper.setText(strWriter.toString());
            getSender().send(message);

            statusService.setStatus("Done. Email was sent");
        } catch (MessagingException e) {
            log.error("MessagingException: " + e);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            log.error("IOException: " + e);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getSendTo() {
        return sendTo;
    }

    public JavaMailSender getSender() {
        return sender;
    }

    public void setSender(JavaMailSender sender) {
        this.sender = sender;
    }
}
