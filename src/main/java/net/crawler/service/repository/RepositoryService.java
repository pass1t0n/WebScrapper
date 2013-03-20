package net.crawler.service.repository;

import net.crawler.model.bao.CrawlingProperties;

/**
 * @author Chaim Arbiv
 * @version $id$
 * Defines an interface for implementations of an class that provides all the repository services
 */
public interface RepositoryService {

    /**
     * retrieves the CrawlingProperties from the storage
     * @param domain the crawling properties name
     * @return CrawlingProperties
     * @see CrawlingProperties
     */
    public CrawlingProperties getCrawlingProperties(String domain);

    /**
     *
     * @param cp the crawling properties name
     */
    public void putCrawlingProperties(CrawlingProperties cp);
}
