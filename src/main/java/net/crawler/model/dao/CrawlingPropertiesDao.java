package net.crawler.model.dao;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Chaim Arbiv
 * @version $id$
 * Properties bag for fetching different pages
 */

@Entity
@Table( name="crawling_properties",
        uniqueConstraints = {   @UniqueConstraint(columnNames = "crawlingproperties_id"),
                                @UniqueConstraint(columnNames = "name")})
public class CrawlingPropertiesDao implements InitializingBean{

    private long id;

    private String name;
    private String urlTemplate;
    private String urlTemplateDelimiter = "#";
    private List<String> parameters; // to apply on urlTemplate to get a valid URL
    private String parameterDelimiter = ",";

    private List<ScrapingPropertiesDao> scrapingPropertyDaos = new LinkedList<ScrapingPropertiesDao>(); // for the next page



    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasLength(urlTemplate, "urlTemplate can not be null or empty");
    }

    @Id
    @GeneratedValue
    @Column(name="crawlingproperties_id")
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

    @Column(name="url_pattern")
    public String getUrlTemplate() {
        return urlTemplate;
    }

    public void setUrlTemplate(String urlTemplate) {
        this.urlTemplate = urlTemplate;
    }

    @Column(name="parameter_delimiter")
    public String getParameterDelimiter() {
        return parameterDelimiter;
    }

    public void setParameterDelimiter(String parameterDelimiter) {
        this.parameterDelimiter = parameterDelimiter;
    }

    @Column(name="url_pattern_delimiter")
    public String getUrlTemplateDelimiter() {
        return urlTemplateDelimiter;
    }

    public void setUrlTemplateDelimiter(String urlTemplateDelimiter) {
        this.urlTemplateDelimiter = urlTemplateDelimiter;
    }

    @ElementCollection
    @CollectionTable(name="url_parameters", joinColumns=@JoinColumn(name="id"))
    @Column(name="parameters")
    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="crawlingproperties_scrapingproperties",
            joinColumns = @JoinColumn( name="crawlingproperties_id"),
            inverseJoinColumns = @JoinColumn( name="scrapingproperties_id")
    )
    public List<ScrapingPropertiesDao> getScrapingPropertyDaos() {
        return scrapingPropertyDaos;
    }

    public void setScrapingPropertyDaos(List<ScrapingPropertiesDao> scrapingPropertyDaos) {
        this.scrapingPropertyDaos = scrapingPropertyDaos;
    }

    public void addScrapingProperties(ScrapingPropertiesDao sp){
        this.scrapingPropertyDaos.add(sp);
    }


}
