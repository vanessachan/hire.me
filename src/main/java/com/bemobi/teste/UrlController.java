package com.bemobi.teste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import static java.lang.String.*;

/**
 * Created by Vanessa Chan on 14/01/2017.
 */

@RestController
public class UrlController {

    @Autowired
    UrlDao urlDao;

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity gravaUrl(@RequestParam(value = "CUSTOM_ALIAS", required = false) String customAlias, @RequestParam String url){
        Long tempoProcessamento = new Date().getTime();
        ShortenUrl sh = new ShortenUrl();
        if(customAlias==null || customAlias.isEmpty()){
            customAlias = String.valueOf(new Date().hashCode());//número gerado aleatório, por ser hashCode a chance de repetição é mínima
        }
        sh.setAlias(customAlias);
        sh.setUrl(url);
        sh.getStatistics().put("time_taken",(new Date().getTime() - tempoProcessamento)+"ms");
        try {
            urlDao.insert(sh);
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AliasError(customAlias,"001","CUSTOM ALIAS ALREADY EXISTS"));
        }

     return ResponseEntity.ok(sh);

    }
    @RequestMapping(value = "/url/{customAlias}", method = RequestMethod.GET)
    public Object getShortenUrl(@PathVariable String customAlias, HttpServletResponse response) throws IOException {
        ShortenUrl sh;
        sh = urlDao.buscaShortenUrl(customAlias);
        if(sh==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AliasError("002","SHORTENED URL NOT FOUND"));

        }
        response.sendRedirect("http://"+sh.getUrl());
        return null;
    }

}
