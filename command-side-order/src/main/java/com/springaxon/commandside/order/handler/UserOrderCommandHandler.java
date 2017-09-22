package com.springaxon.commandside.order.handler;

import java.util.HashSet;
import java.util.Set;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.springaxon.commandside.order.aggregate.UserOrder;
import com.springaxon.commandside.order.command.CreateOrderCommand;
import com.springaxon.commandside.order.domain.LineItem;
import com.springaxon.commandside.order.repository.UserOrderJdbcRepository;

@Component
public class UserOrderCommandHandler {
	
    private static final Logger LOG = LoggerFactory.getLogger(UserOrderCommandHandler.class);
    
    private Repository<UserOrder> repository;
    
    private UserOrderJdbcRepository userOrderJdbcRepository;

    @CommandHandler
    public String CreateUserOrder(CreateOrderCommand command) throws Exception {
        LOG.debug("Command: 'CreateBlogPostCommand' received.");	
        Set<LineItem> lineItems = new HashSet<LineItem>();
        for (com.springaxon.common.order.model.LineItem li : command.getLineItems()) {
        	LineItem lineItem = new LineItem();
        	lineItem.setName(li.getName());
        	lineItem.setQuantity(li.getQuantity());
        	lineItem.setUnitPrice(li.getUnitPrice());
        	lineItems.add(lineItem);
        }
        repository.newInstance(() -> new UserOrder(command.getId(), command.getName(), lineItems));
        
        //UserOrder userOrder = new UserOrder(command.getId(), command.getName(), lineItems);
        //userOrderJdbcRepository.save(userOrder);
        
        return command.getId();
    }
    
	private Aggregate<UserOrder> loadUserOrderAggregate(String id) {
        return repository.load(id, null);
    }
	
	@Autowired
    @Qualifier("userOrderRepository")
    public void setRepository(Repository<UserOrder> userOrderRepository) {
        this.repository = userOrderRepository;
    }
    
    @Autowired
    public void setUserOrderJdbcRepository(UserOrderJdbcRepository userOrderJdbcRepository) {
        this.userOrderJdbcRepository = userOrderJdbcRepository;
    }
    
}
