package net.crawler.service.export;

/**
 * @author Chaim Arbiv
 * @version $id$
 *
 * Defines an interface for implementations of an class that can receive the data that was gathered during the
 * scrapping process and apply format to the data and potentially deliver it i.e. send a mail once done
 */
public interface Exporter{

    public void init();

    /**
     * adds a value to what ever output template we are using
     * @param value any value that needs to be exported
     */
    public void writeValue(String value);


    /**
     * indicates to that the page was processed and the exporter
     */
    public void afterPageProcessing();

    /**
     * signal that all the pages were processed
     */
    public void close();
}
