package net.crawler.service.export;

/**
 * @author Chaim Arbiv
 * @version $id$
 *          TODO: write a description for this class
 */
public interface Exporter{

    public void init();
    public void writeValue(String value);
    public void afterPageProcessing();
    public void close();
}
