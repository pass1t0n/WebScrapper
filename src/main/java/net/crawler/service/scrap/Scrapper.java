package net.crawler.service.scrap;

import net.crawler.model.bao.CrawlingProperties;
import net.crawler.model.bao.ScrapingProperties;

/**
 * @author Chaim Arbiv
 * @version $id$
 *          TODO: write a description for this class
 */
public interface Scrapper {
    void beforeGetPage(CrawlingProperties sp);
    void getPages(CrawlingProperties cp);
    void beforePagesScrap(ScrapingProperties sp);
    void scrapPages(ScrapingProperties sp);
    void afterPagesScrap(ScrapingProperties sp);
}
