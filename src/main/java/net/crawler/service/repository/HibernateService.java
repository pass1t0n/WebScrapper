package net.crawler.service.repository;

import net.crawler.general.Utils;
import net.crawler.model.bao.CrawlingProperties;
import net.crawler.model.dao.CrawlingPropertiesDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author Chaim Arbiv
 * @version $id$
 * Provides all the hibernate repository services.
 * Note: I changed the code from working with HibernateTemplate since is not recommended since Spring 3
 */
@Repository
public class HibernateService implements InitializingBean, RepositoryService {
    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    private Utils utils;
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Note that the CrawlingPropertiesDao is lazy loaded
     * @param propertiesId the crawling properties name
     * @return CrawlingProperties which is a CrawlingPropertiesDao with a CrawlingProperties around it.
     *
     * Note: that the CrawlingPropertiesDao is lazy loaded for that we are using the OpenSessionInView pattern
     */
    @Override
    @SuppressWarnings("all")
    public CrawlingProperties getCrawlingProperties(String propertiesId) {

        Criteria crit = this.getSessionFactory().getCurrentSession().createCriteria(CrawlingPropertiesDao.class);
        crit.add(Restrictions.eq("name", propertiesId));
        List<CrawlingPropertiesDao> CrawlingPropertiesDaoLst = crit.list();


        // Checking if we have such a crawler properties in the DB.
        // we can not have more then 1 record since it name is marked as unique
        if (CrawlingPropertiesDaoLst.size()!=1){
            if ( CrawlingPropertiesDaoLst.size()==0 ){
                log.error("could not find properties with name "+ propertiesId);
                throw new IllegalArgumentException("could not find entry with name "+ propertiesId +" in DB - Did you populate the DB?");
            }
        }

        CrawlingProperties cp =  new CrawlingProperties(CrawlingPropertiesDaoLst.get(0));
        return cp;
    }

    /**
     * adds the crawling properties in the database
     * @param cp the crawling properties name
     */
    @Override
    @Transactional(readOnly = false)
    public void putCrawlingProperties(CrawlingProperties cp) {
        this.getSessionFactory().getCurrentSession().saveOrUpdate(cp.getCrawlingPropertiesDao());
        log.trace(cp.getName() + " was successfully stored in the DB");
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(sessionFactory, "sessionFactory can not be null");
    }
}
