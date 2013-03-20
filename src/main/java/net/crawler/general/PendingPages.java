package net.crawler.general;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Chaim Arbiv
 * @version $id$
 * Holds the pages that have been already visited.
 *
 * This class was created so I can take advatage of Spring injection/wiring abilities.
 */

@Component
public class PendingPages {
    List<Document> pages = new LinkedList<Document>();

    public void addDoc(Document doc){
        pages.add(doc);
    }

    public List<Document> getPages(){
        return pages;
    }
}
