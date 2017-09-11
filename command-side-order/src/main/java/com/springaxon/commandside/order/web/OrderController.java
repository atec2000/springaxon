package com.springaxon.commandside.order.web;

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

import com.springaxon.commandside.order.command.CreateOrderCommand;
import com.springaxon.commandside.order.vo.req.CreateOrderRequest;



@RestController
@RequestMapping(value = "/orders")
public class OrderController {
	
    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody CreateOrderRequest request, HttpServletResponse response) {
        LOG.debug(CreateOrderRequest.class.getSimpleName() + " request received");
        
        CreateOrderCommand command = new CreateOrderCommand(request.getTitle(),
                request.getRawContent(), request.getPublicSlug(), request.getDraft(), request.getBroadcast(),
                request.getPublishAt(), request.getCategory(), "user");
        commandGateway.sendAndWait(command);
        LOG.debug(CreateOrderCommand.class.getSimpleName() + " sent to command gateway: Order [{}] ", command.getId());
    }
        
}
