package it.mad.demo.mapper;

import it.mad.demo.dto.CustomersDTO;
import it.mad.demo.entities.Customers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface CustomersMapper extends GenericMapper<Customers, CustomersDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Customers asEntity(CustomersDTO dto);

    @Override
    @Mapping(target = "id", ignore = false)
    CustomersDTO asDTO(Customers entity);
}