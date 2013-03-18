package net.crawler.service.repository;

import net.crawler.model.dao.ScrapingPropertiesDao;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author Chaim Arbiv
 * @version $id$
 *          TODO: write a description for this class
 */
public class DummyRepositoryService {
    private Map<String, ScrapingPropertiesDao> spMap;



//    @Override
    public ScrapingPropertiesDao getScrapingProperties(String id) {
        return getSpMap().get(id);
    }

//    @Override
    public void putScrapingProperties(ScrapingPropertiesDao sp) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Map<String, ScrapingPropertiesDao> getSpMap() {
        return spMap;
    }

    public void setSpMap(Map<String, ScrapingPropertiesDao> spMap) {
        this.spMap = spMap;
    }


//    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(spMap, "map can not be null");
        Assert.notEmpty(spMap, "map can not be null");
    }
}
