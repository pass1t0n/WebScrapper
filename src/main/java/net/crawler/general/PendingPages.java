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
 * I have created this class as oppose to using a List since I can inject it to any other classes that i need it.
 * Springs default injection method is Singleton which works well for me in this case.
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
