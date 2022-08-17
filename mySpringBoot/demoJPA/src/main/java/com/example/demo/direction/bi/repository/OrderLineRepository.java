package com.example.demo.direction.bi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.direction.bi.OrderLine;
import com.example.demo.direction.bi.OrderLinePK;

public interface OrderLineRepository extends JpaRepository<OrderLine, OrderLinePK> {

}
