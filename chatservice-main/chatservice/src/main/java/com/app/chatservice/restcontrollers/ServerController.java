package com.app.chatservice.restcontrollers;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.chatservice.entities.ServerEntity;
import com.app.chatservice.entities.ServerMemberId;
import com.app.chatservice.services.ServerService;

@RestController
@RequestMapping("/server")
public class ServerController {
    private Logger logger = 
        Logger.getLogger(getClass().getName());

    @Autowired
    private ServerService service;

    @PostMapping("/create")
    public ResponseEntity<ServerMemberId> createServer(ServerEntity entity){
        logger.info(entity.toString());
        ServerMemberId result = service.createServer(entity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/serverpfp")
    public String uploadServerPfp(@RequestParam(required = false) MultipartFile file){
        logger.info(file.toString());

        String result = service.fileUpload(file);
;
        return result;
    }

    @GetMapping("/listbyids")
    public List<ServerEntity> listByIds(@RequestParam String[] ids){
        List<ServerEntity> result = service.listByIds(ids);
        return result;
    }

    @GetMapping("/listbyuser")
    public List<ServerEntity> listByUser(@RequestParam String user){
        List<ServerEntity> result = service.listByUser(user);
        return result;
    }

    @GetMapping("/findbyserveridandserverowner")
    public ResponseEntity findByServerIdAndServerOwner(@RequestParam String serverId, @RequestParam String serverOwner){

        ServerEntity entity = service.findByServerIdAndServerOwner(serverId, serverOwner);

        if(entity == null){
            return ResponseEntity.badRequest().body("not the server owner");
        }

        return ResponseEntity.ok().body(entity);
    }

    @GetMapping("/findallexcludejoined")
    public ResponseEntity findAllExcludeJoined(@RequestParam String appuserId){

        List<ServerEntity> result = service.findAllExcludeJoined(appuserId);

        return ResponseEntity.ok().body(result);
    }
}
