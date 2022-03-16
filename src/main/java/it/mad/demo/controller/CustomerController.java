package it.mad.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.mad.demo.dto.CustomerDTO;
import it.mad.demo.swagger.ApiPageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Customer API")
public interface CustomerController {
    @ApiOperation("Add new data")
    CustomerDTO save(@RequestBody CustomerDTO customer);

    @ApiOperation("Find by Id")
    CustomerDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete based on primary key")
    void delete(@PathVariable("id") Long id);

    @ApiOperation("Find all data")
    List<CustomerDTO> list();

    @ApiPageable
    Page<CustomerDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    CustomerDTO update(@RequestBody CustomerDTO dto, @PathVariable("id") Long id);
}