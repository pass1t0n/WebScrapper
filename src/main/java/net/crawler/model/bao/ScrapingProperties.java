package net.crawler.model.bao;

import net.crawler.model.dao.ScrapingPropertiesDao;
import net.crawler.model.dao.XpathTemplate;

import java.util.Map;

/**
 * @author Chaim Arbiv
 * @version $id$
 * This a bao class to wrap ScrapingPropertiesDoa and give more logic like iterator and exporter handling
 * @see net.crawler.model.dao.ScrapingPropertiesDao
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

    public Map<String, XpathTemplate> getXpathTemplates() {
        return getScrapingPropertiesDao().getXpathTemplates();
    }

    public ScrapingPropertiesDao getScrapingPropertiesDao() {
        return scrapingPropertiesDao;
    }

    public void setXpathTemplates(Map<String, XpathTemplate> xpathTemplates) {
        this.getScrapingPropertiesDao().setXpathTemplates(xpathTemplates);
    }
}
