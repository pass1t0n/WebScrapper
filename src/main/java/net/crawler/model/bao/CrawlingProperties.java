package net.crawler.model.bao;

import net.crawler.model.dao.CrawlingPropertiesDao;
import net.crawler.model.dao.ScrapingPropertiesDao;

import java.util.Iterator;
import java.util.List;

/**
 * @author Chaim Arbiv
 * @version $id$
 * /
 
/**
 * This a bao class to wrap CrawlingPropertiesDoa and give some more logic like iterator and exporter handling
 * @see net.crawler.model.dao.CrawlingPropertiesDao
 */
public class CrawlingProperties implements Iterable<ScrapingProperties>{

    CrawlingPropertiesDao crawlingPropertiesDao;

    public CrawlingProperties(CrawlingPropertiesDao crawlingPropertiesDao) {
        this.crawlingPropertiesDao = crawlingPropertiesDao;
    }

    public CrawlingProperties() {
        this.crawlingPropertiesDao = new CrawlingPropertiesDao();
    }

    public String getName() {
        return getCrawlingPropertiesDao().getName();
    }

    public void setName(String name) {
        getCrawlingPropertiesDao().setName(name);
    }

    public String getUrlTemplate() {
        return getCrawlingPropertiesDao().getUrlTemplate();
    }

    public void setUrlTemplate(String urlTemplate) {
        getCrawlingPropertiesDao().setUrlTemplate(urlTemplate);
    }

    public String getUrlTemplateDelimiter() {
        return getCrawlingPropertiesDao().getUrlTemplateDelimiter();
    }

    public void setUrlTemplateDelimiter(String urlTemplateDelimiter) {
        getCrawlingPropertiesDao().setUrlTemplateDelimiter(urlTemplateDelimiter);
    }

    public List<String> getParameters() {
        return getCrawlingPropertiesDao().getParameters();
    }

    public void setParameters(List<String> parameters) {
        getCrawlingPropertiesDao().setParameters(parameters);
    }

    public String getParameterDelimiter() {
        return getCrawlingPropertiesDao().getParameterDelimiter();
    }

    public void setParameterDelimiter(String parameterDelimiter) {
        getCrawlingPropertiesDao().setParameterDelimiter(parameterDelimiter);
    }

    public CrawlingPropertiesDao getCrawlingPropertiesDao() {
        if (crawlingPropertiesDao == null)
            return new CrawlingPropertiesDao();
        else
            return crawlingPropertiesDao;
    }

    public ScrapingProperties getScrapingProperty() {
        return new ScrapingProperties(crawlingPropertiesDao.getScrapingPropertyDaos().get(0));
    }

    public void addScrapingProperties(ScrapingProperties sp) {
        getCrawlingPropertiesDao().addScrapingProperties(sp.getScrapingPropertiesDao());
    }

    public Iterator<ScrapingProperties> iterator() {
        return new ScrapingIterator();
    }

    /**
     * iterates over the scrapping properties
     */
    class ScrapingIterator implements Iterator<ScrapingProperties>{

        Iterator<ScrapingPropertiesDao> iter;

        ScrapingIterator(){
            iter = getCrawlingPropertiesDao().getScrapingPropertyDaos().iterator();
        }

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public ScrapingProperties next() {
            ScrapingProperties sp = new ScrapingProperties( iter.next() );
//            sp.setUtils(utils);
            return sp;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("the remove method is not implemented and should not be used");
        }
    }

}
