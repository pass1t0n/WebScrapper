package net.crawler.service.repository;

import net.crawler.model.bao.CrawlingProperties;

/**
 * @author Chaim Arbiv
 * @version $id$
 *          TODO: write a description for this class
 */
public interface RepositoryService {

    public CrawlingProperties getCrawlingProperties(String domain);

    public void putCrawlingProperties(CrawlingProperties sp);
}
