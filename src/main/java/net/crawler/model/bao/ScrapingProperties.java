package net.crawler.model.bao;

import net.crawler.model.dao.ScrapingPropertiesDao;
import net.crawler.model.dao.XpathTemplate;

import java.util.Map;

/**
 * @author Chaim Arbiv
 * @version $id$
 * This a bao class to wrap ScrapingPropertiesDoa and give more logic like iterator and exporter handling
 */
public class ScrapingProperties {
    private ScrapingPropertiesDao  scrapingPropertiesDao;

    public ScrapingProperties(ScrapingPropertiesDao scrapingPropertiesDao) {
        this.scrapingPropertiesDao = scrapingPropertiesDao;
    }

    public ScrapingProperties() {
        this.scrapingPropertiesDao = new ScrapingPropertiesDao();
    }

    public String getName() {
        return getScrapingPropertiesDao().getName();
    }

    public void setName(String name) {
        this.getScrapingPropertiesDao().setName(name);
    }

    public String getExporterId() {
        return getScrapingPropertiesDao().getExporterId();
    }

    public void setExporterId(String exporterId) {
        this.getScrapingPropertiesDao().setExporterId(exporterId);
    }

//    public Exporter getExporter() {
//        return getUtils().getExporterMap().get(getExporterId());
//    }

    public Map<String, XpathTemplate> getXpathTemplates() {
        return getScrapingPropertiesDao().getXpathTemplates();
    }

    public ScrapingPropertiesDao getScrapingPropertiesDao() {
        return scrapingPropertiesDao;
    }

    public void setXpathTemplates(Map<String, XpathTemplate> xpathTemplates) {
        this.getScrapingPropertiesDao().setXpathTemplates(xpathTemplates);
    }

//    public Utils getUtils() {
//        return utils;
//    }
//
//    public void setUtils(Utils utils) {
////        this.utils = utils;
//    }

    //    public CrawlingPropertiesDao getCrawlingProperties() {
//        return getScrapingPropertiesDao().crawlingProperties;
//    }
//
//    public void setCrawlingProperties(CrawlingPropertiesDao crawlingProperties) {
//        this.getScrapingPropertiesDao().crawlingProperties = crawlingProperties;
//    }
}
