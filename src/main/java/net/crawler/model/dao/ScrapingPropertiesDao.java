package net.crawler.model.dao;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Map;

/**
 * @author Chaim Arbiv
 * @version $id$
 *
 * Used to hold all the information to scrap pages from the pendingPages
 * @see net.crawler.general.PendingPages
 */

@Entity
@Table( name="scraping_properties",
        uniqueConstraints = {   @UniqueConstraint(columnNames = "scrapingproperties_id"),
                                @UniqueConstraint(columnNames = "name")})
public class ScrapingPropertiesDao implements InitializingBean {

    private long id;
    private String name;

    private String exporterId;
    private String scrapperId;

    // Header, xpath for value
    private Map<String, XpathTemplate> xpathTemplates;

    private CrawlingPropertiesDao crawlingProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(xpathTemplates, "xpathTemplates can not be null");
    }

    @Id
    @GeneratedValue
    @Column(name="scrapingproperties_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="scraping_properties_to_extended_xpath",
            joinColumns = @JoinColumn( name="scrapingproperties_id"),
            inverseJoinColumns = @JoinColumn( name="extendedxpath_id")
    )
    public Map<String, XpathTemplate> getXpathTemplates() {
        return xpathTemplates;
    }

    public void setXpathTemplates(Map<String, XpathTemplate> xpathTemplates) {
        this.xpathTemplates = xpathTemplates;
    }

    @Column(name="exproter_id")
    public String getExporterId() {
        return exporterId;
    }

    public void setExporterId(String exporterId) {
        this.exporterId = exporterId;
    }

    @Column(name="scrapper_id")
    public String getScrapperId() {
        return scrapperId;
    }

    public void setScrapperId(String scrapperId) {
        this.scrapperId = scrapperId;
    }


    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(name="scrapingproperties_to_crawlingproperties", joinColumns = { @JoinColumn(name = "scrapingproperties_id") }, inverseJoinColumns = { @JoinColumn(name = "crawlingproperties_id") })
    public CrawlingPropertiesDao getCrawlingProperties() {
        return crawlingProperties;
    }

    public void setCrawlingProperties(CrawlingPropertiesDao crawlingPropertiesDao) {
        this.crawlingProperties = crawlingPropertiesDao;
    }
}
