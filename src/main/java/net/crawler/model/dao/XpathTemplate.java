package net.crawler.model.dao;

import javax.persistence.*;
import java.util.Iterator;

/**
 * @author Chaim Arbiv
 * @version $id$
 * Should be used in to create a xpath expression that can have variables in it
 * For example /html/body/div[#]/div[2]
 */

@Entity
@Table( name="extended_xpath",
        uniqueConstraints = {   @UniqueConstraint(columnNames = "extendedxpath_id")})

public class XpathTemplate implements Iterable<String>{
    private String xpathDelimiter = "#";
    private String xpathTemplate;
    private long id;
    private int min;
    private int max;

    @Id
    @GeneratedValue
    @Column(name="extendedxpath_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="xpath_template")
    public String getXpathTemplate() {
        return xpathTemplate;
    }

    public void setXpathTemplate(String xpathTemplate) {
        this.xpathTemplate = xpathTemplate;
    }

    @Column(name="min_value")
    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    @Column(name="max_value")
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Column(name="xpath_delimiter")
    public String getXpathDelimiter() {
        return xpathDelimiter;
    }

    public void setXpathDelimiter(String xpathDelimiter) {
        this.xpathDelimiter = xpathDelimiter;
    }

    @Transient
    public boolean isSimpleExpression() {
        return !getXpathTemplate().contains(getXpathDelimiter());
    }

    @Override
    public Iterator<String> iterator() {
        return new XpathIterator();
    }

    public class XpathIterator implements Iterator<String>{
        int value = min;
        @Override
        public boolean hasNext() {
            return value<=max;
        }

        @Override
        public String next() {
            String exp = xpathTemplate.replace(xpathDelimiter, Integer.toString(value));
            value++;
            return exp;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove method was not implemented");
        }
    }
}
