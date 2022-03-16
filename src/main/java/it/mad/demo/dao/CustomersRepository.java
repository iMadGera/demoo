package it.mad.demo.dao;

import it.mad.demo.entities.Customers;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends PagingAndSortingRepository<Customers, Long> {
}