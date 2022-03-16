package it.mad.demo.controller.impl;

import it.mad.demo.controller.CustomersController;
import it.mad.demo.dto.CustomersDTO;
import it.mad.demo.entities.Customers;
import it.mad.demo.mapper.CustomersMapper;
import it.mad.demo.service.CustomersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/customers")
@RestController
public class CustomersControllerImpl implements CustomersController {

    private final CustomersService customersService;
    private final CustomersMapper customersMapper;

    public CustomersControllerImpl(CustomersService customersService, CustomersMapper customersMapper) {
        this.customersService = customersService;
        this.customersMapper = customersMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomersDTO save(@RequestBody CustomersDTO customersDTO) {
        Customers customers = customersMapper.asEntity(customersDTO);
        return customersMapper.asDTO(customersService.save(customers));
    }

    @Override
    @GetMapping("/{id}")
    public CustomersDTO findById(@PathVariable("id") Long id) {
        Customers customers = customersService.findById(id).orElse(null);
        return customersMapper.asDTO(customers);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        customersService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<CustomersDTO> list() {
        return customersMapper.asDTOList(customersService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<CustomersDTO> pageQuery(Pageable pageable) {
        Page<Customers> customersPage = customersService.findAll(pageable);
        List<CustomersDTO> dtoList = customersPage
                .stream()
                .map(customersMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, customersPage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public CustomersDTO update(@RequestBody CustomersDTO customersDTO, @PathVariable("id") Long id) {
        Customers customers = customersMapper.asEntity(customersDTO);
        return customersMapper.asDTO(customersService.update(customers, id));
    }
}