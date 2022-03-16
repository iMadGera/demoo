package it.mad.demo.mapper;

import it.mad.demo.dto.CustomerDTO;
import it.mad.demo.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface CustomerMapper extends GenericMapper<Customer, CustomerDTO> {
    @Override
    @Mapping(target = "id")
    Customer asEntity(CustomerDTO dto);
}