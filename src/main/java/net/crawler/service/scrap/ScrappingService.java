package net.crawler.service.scrap;

import net.crawler.model.bao.CrawlingProperties;
import net.crawler.model.bao.ScrapingProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

/**
 * @author Chaim Arbiv
 * @version $id$
 *          TODO: write a description for this class
 */
@Service
public class ScrappingService {
    @Autowired
    private Scrapper scrapper;

    public void execute(CrawlingProperties cp) {

        //TODO: think how to be able to plug different scrappers. According to the scraping properties so for stock csv I will be able to create headers
        // and for my email I will not have to
        scrapper.beforeGetPage(cp);
        scrapper.getPages(cp);

        ScrapingProperties sp;
        Iterator<ScrapingProperties> spIter = cp.iterator();
        while (spIter.hasNext()){
            sp = spIter.next();
            scrapper.beforePagesScrap(sp);
            scrapper.scrapPages(sp);
            scrapper.afterPagesScrap(sp);
        }

    }

//    scrapper.done();
}
