package net.crawler.service.export;

import net.crawler.general.PendingPages;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Chaim Arbiv
 * @version $id$
 *          TODO: write a description for this class
 */
public class CrawlerExporter implements Exporter{
    @Autowired
    PendingPages pendingPages;

    @Override
    public void init() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void writeValue(String value) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void afterPageProcessing() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void close(){
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
