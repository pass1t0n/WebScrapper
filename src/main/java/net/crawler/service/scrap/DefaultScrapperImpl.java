package net.crawler.service.scrap;

import net.crawler.general.PendingPages;
import net.crawler.general.Utils;
import net.crawler.model.bao.CrawlingProperties;
import net.crawler.model.bao.ScrapingProperties;
import net.crawler.model.dao.XpathTemplate;
import net.crawler.service.export.Exporter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Chaim Arbiv
 * @version $id$
 */

public class DefaultScrapperImpl implements Scrapper {
    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    PendingPages pendingPages;

    @Autowired
    Utils utils;

    XPath xpath = XPathFactory.newInstance().newXPath();

    @Override
    public void beforeGetPage(CrawlingProperties sp) {}


    @Override
    public void getPages(CrawlingProperties cp) {
        try {
            for (String urlParam : cp.getParameters()) {

                String urlStr = buildUrl(urlParam, cp);

                // fetches the html and makes sure the html is well formed XML
                HtmlCleaner cleaner = new HtmlCleaner();
                TagNode tagNode = cleaner.clean(new URL(urlStr));

                // once done. adds it to the prepared pages.
                // NOTE: for better performance you can do multithreaded with Producer Consumer pattern
                pendingPages.addDoc(new DomSerializer(new CleanerProperties()).createDOM(tagNode));
            }
        } catch (MalformedURLException e) {
            log.error("MalformedURLException: " + e.getMessage());
        } catch (IOException e) {
            log.error("IOException: " + e.getMessage());
        } catch (ParserConfigurationException e) {
            log.error("ParserConfigurationException: " + e.getMessage());
        }
    }


    public void beforePagesScrap(ScrapingProperties sp) {
        getExporter(sp.getExporterId()).init();

        // writing the headers
        for (String header:getFileHeaders(sp))
            getExporter(sp.getExporterId()).writeValue(header);
        getExporter(sp.getExporterId()).afterPageProcessing();
    }

    // getting the headers from the xpath map
    private String[] getFileHeaders(ScrapingProperties sp) {
        return sp.getXpathTemplates().keySet().toArray(new String[0]);
    }

    public void scrapPages(ScrapingProperties sp) {
        try {

            Iterator<Document> pageIter = pendingPages.getPages().iterator();
            Document page;

            // for the fetched pages till now
            while (pageIter.hasNext())
            {
                page = pageIter.next();
                Collection<XpathTemplate> xpathTemplates = sp.getXpathTemplates().values();

                //TODO: make it work with out the if statement. The itereator should just return the next pathx
                Iterator<XpathTemplate> templatesIter = xpathTemplates.iterator();
                XpathTemplate xpathTemplate;

                // get the xpath value
                while (templatesIter.hasNext())
                {
                    xpathTemplate = templatesIter.next();

                    if (xpathTemplate.isSimpleExpression())
                        getExporter(sp.getExporterId()).writeValue(((String) xpath.evaluate(xpathTemplate.getXpathTemplate(), page, XPathConstants.STRING)).trim());
                    else {
                        Iterator<String> xpathIter = xpathTemplate.iterator();
                        while (xpathIter.hasNext()){
                            getExporter(sp.getExporterId()).writeValue(((String) xpath.evaluate(xpathIter.next(), page, XPathConstants.STRING)).trim());
                        }
                    }
                }

                getExporter(sp.getExporterId()).afterPageProcessing();
            }
        } catch (XPathExpressionException e) {
            log.error("XPathExpressionException", e);
        }

        // finalize (send mail, close resources) - depends on your exporter
        getExporter(sp.getExporterId()).afterPageProcessing();
    }

    private Exporter getExporter(String exporterId) {
        return utils.getExporterMap().get(exporterId);
    }

    public void afterPagesScrap(ScrapingProperties sp) {
        getExporter(sp.getExporterId()).close();
    }

    // Creates URL splitting the url parameter line and placing them in the url pattern
    private String buildUrl(String urlParameter, CrawlingProperties cp) {
        String urlPatternToFeel = cp.getUrlTemplate();
        // in case there is more than one parameter to compose in the url
        String[] parametersArr = urlParameter.split(cp.getParameterDelimiter());

        int i = 0;
        while (urlPatternToFeel.contains(cp.getUrlTemplateDelimiter())) {
            urlPatternToFeel = urlPatternToFeel.replaceFirst(cp.getUrlTemplateDelimiter(), parametersArr[i]);
            i++;
        }
        log.debug("created url: " + urlPatternToFeel);

        return urlPatternToFeel;
    }
}
