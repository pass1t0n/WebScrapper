package net.crawler.general;

import net.crawler.service.export.Exporter;

import java.util.Map;

/**
 * @author Chaim Arbiv
 * @version $id$
 *
 */
public class Utils{

    // Used to hang different exporters to use along the process. This allows properties to contain only the exporter id
    private Map<String, Exporter> exporterMap;

    public Map<String, Exporter> getExporterMap() {
        return exporterMap;
    }

    public void setExporterMap(Map<String, Exporter> exporterMap) {
        this.exporterMap  = exporterMap;
    }

}
