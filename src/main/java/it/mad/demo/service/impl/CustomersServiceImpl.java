package it.mad.demo.service.impl;

import it.mad.demo.dao.CustomersRepository;
import it.mad.demo.entities.Customers;
import it.mad.demo.service.CustomersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomersServiceImpl implements CustomersService {
    private final CustomersRepository repository;

    public CustomersServiceImpl(CustomersRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customers save(Customers entity) {
        return repository.save(entity);
    }

    @Override
    public List<Customers> save(List<Customers> entities) {
        return (List<Customers>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Customers> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Customers> findAll() {
        return (List<Customers>) repository.findAll();
    }

    @Override
    public Page<Customers> findAll(Pageable pageable) {
        Page<Customers> entityPage = repository.findAll(pageable);
        List<Customers> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Customers update(Customers entity, Long id) {
        Optional<Customers> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}