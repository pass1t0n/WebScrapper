package net.crawler.general;

import net.crawler.service.export.Exporter;

import java.util.Map;

/**
 * @author Chaim Arbiv
 * @version $id$
 *
 */
public class Utils{

    // to hang different exporters to use along the process. So each crawling properties contains only the name of the
    // exporter
    private Map<String, Exporter> exporterMap;

    public Map<String, Exporter> getExporterMap() {
        return exporterMap;
    }

    public void setExporterMap(Map<String, Exporter> exporterMap) {
        this.exporterMap  = exporterMap;
    }

}
