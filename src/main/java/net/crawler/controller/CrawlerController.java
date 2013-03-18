package net.crawler.controller;


import net.crawler.model.bao.CrawlingProperties;
import net.crawler.model.bao.ScrapingProperties;
import net.crawler.model.dao.XpathTemplate;
import net.crawler.service.StatusService;
import net.crawler.service.repository.RepositoryService;
import net.crawler.service.scrap.ScrappingService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* This is the Controller in the MVC part :). All the requests are being funneled by Springs DispatcherServlet to here
* according to the annotation {@link http://static.springsource.org/spring/docs/3.0.x/reference/mvc.html}
*
* Note: this class is using OpenSessionInViewFilter which keeps the session open and allow us to work easier with
* Hibernate Lazy Evaluation. There are couple of draw backs for using this pattern but for this code it does not matter.
*/

@Controller
public class CrawlerController {
    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    private ScrappingService scrappingService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private StatusService statusService;


    // Sends the UI to the client
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String initUi() {
        return "webCrawler";
    }

    // Sends the state of the app in the response
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    @ResponseBody
    public String getStatus() {
        return statusService.getStatus();
    }


    // Login service.
    // NOTE: This is just a place holder and meant to demonstrate usage of the status codes.
    // DO NOT USE THIS IN A REAL APP!. You need to encrypt the user name (optional) and the password (must)
    // before sending it to the server (Unless you know you are using ssl) and store it with salt on the DB.
    // You can find how Spring can help you with that here {@link http://static.springsource.org/spring-security/site/}
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void index(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletResponse response) {
        if (userName.equals(password))
            response.setStatus(HttpStatus.OK.value());
        else
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    // initiates the scrapping process
    @RequestMapping(value = "/start/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void scrap(@PathVariable String id, HttpServletResponse response) {
        try {
            log.trace("doing scrap with " + id);
            CrawlingProperties cp = repositoryService.getCrawlingProperties(id);

            scrappingService.execute(cp);
            log.trace("Finished scrapping successfully ");
        }
        catch (RuntimeException e) {
            statusService.setStatus(e.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }


    // adds my crawler properties
    @RequestMapping(value = "/addScraper", method = RequestMethod.GET)
    public void addScrapingParameters(HttpServletResponse response) {
        try {
            CrawlingProperties cp = new CrawlingProperties();


            // Since it is a very simple template I didn't use org.springframework.web.util.UriTemplate but you can change it.
            cp.setName("marketwatch");
            cp.setUrlTemplate("http://www.marketwatch.com/investing/stock/#/analystestimates");

            List<String> parameters = new ArrayList<String>();
            parameters.add("goog");
            parameters.add("lvs");
            cp.setParameters(parameters);

            ScrapingProperties sp = new ScrapingProperties();
            Map<String, XpathTemplate> xpathToScrap = new HashMap<String, XpathTemplate>();

            // xpaths
            XpathTemplate tempXpathTemplate = new XpathTemplate();
            tempXpathTemplate.setXpathTemplate("/html/body/div/div[2]/div[2]/div[2]/div/table/tbody/tr/td[2]");
            xpathToScrap.put("Average Recommendation", tempXpathTemplate);

            tempXpathTemplate = new XpathTemplate();
            tempXpathTemplate.setXpathTemplate("/html/body/div/div[2]/div[2]/div[2]/div/table/tbody/tr[2]/td[2]");
            xpathToScrap.put("Number of Ratings", tempXpathTemplate);

            sp.setXpathTemplates(xpathToScrap);

            // exporter
            sp.setExporterId("CsvMailExporter");

            // adding the scrapping properties
            cp.addScrapingProperties(sp);

            repositoryService.putCrawlingProperties(cp);

        } catch (MappingException e) {
            log.error("Error while interacting with the DB "+ e.getMessage());
            try {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            catch (IOException ex){
                log.error("Error while setting status code on response: "+ ex.getMessage());
            }
        }
    }
}