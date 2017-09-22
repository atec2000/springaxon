package com.springaxon.commandside.order.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.springaxon.commandside.order.aggregate.UserOrder;

public interface UserOrderJdbcRepository extends PagingAndSortingRepository<UserOrder, String> {
	
}
