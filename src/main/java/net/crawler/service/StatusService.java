package net.crawler.service;

import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Chaim Arbiv
 * @version $id$
 * To be able to return a status to the client
 */

@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class StatusService {
    String status;

    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
}
