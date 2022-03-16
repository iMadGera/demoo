package it.mad.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.mad.demo.dto.CustomersDTO;
import it.mad.demo.swagger.ApiPageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Customers REST API")
public interface CustomersController {
    @ApiOperation("Add new data")
    CustomersDTO save(@RequestBody CustomersDTO customers);

    @ApiOperation("Find by Id")
    CustomersDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete based on primary key")
    void delete(@PathVariable("id") Long id);

    @ApiOperation("Find all data")
    List<CustomersDTO> list();

    @ApiPageable
    Page<CustomersDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    CustomersDTO update(@RequestBody CustomersDTO dto, @PathVariable("id") Long id);
}