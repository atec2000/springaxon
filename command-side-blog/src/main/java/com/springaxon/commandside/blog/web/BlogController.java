package com.springaxon.commandside.blog.web;

import javax.servlet.http.HttpServletResponse;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springaxon.commandside.blog.vo.req.CreateBlogPostRequest;


@RestController
@RequestMapping(value = "/blogpostcommands")
public class BlogController {
	
    private static final Logger LOG = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody CreateBlogPostRequest request, HttpServletResponse response) {
        LOG.debug(CreateBlogPostRequest.class.getSimpleName() + " request received");
        
        /*
        CreateBlogPostCommand command = new CreateBlogPostCommand(request.getTitle(),
                request.getRawContent(), request.getPublicSlug(), request.getDraft(), request.getBroadcast(),
                request.getPublishAt(), request.getCategory(), "user");
        commandGateway.sendAndWait(command);
        LOG.debug(CreateBlogPostCommand.class.getSimpleName() + " sent to command gateway: Blog Post [{}] ", command.getId());
        */
    }
        
}
