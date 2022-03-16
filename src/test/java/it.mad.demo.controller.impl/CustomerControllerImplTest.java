package it.mad.demo.controller.impl;


import it.mad.demo.builders.CustomerBuilder;
import it.mad.demo.entities.Customer;
import it.mad.demo.mapper.CustomerMapper;
import it.mad.demo.mapper.ReferenceMapper;
import it.mad.demo.service.CustomerService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class CustomerControllerImplTest {

    private static final String ENDPOINT_URL = "/api/customer";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private CustomerControllerImpl customerController;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private CustomerMapper customerMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.customerController).build();
    }

    @Test
    void getAll() throws Exception {
        Mockito.when(customerMapper.asDTOList(ArgumentMatchers.any())).thenReturn(CustomerBuilder.getListDTO());

        Mockito.when(customerService.findAll()).thenReturn(CustomerBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "s"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    void getById() throws Exception {
        Mockito.when(customerMapper.asDTO(ArgumentMatchers.any())).thenReturn(CustomerBuilder.getDTO());

        Mockito.when(customerService.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(CustomerBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(customerService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(customerService);
    }

    @Test
    void save() throws Exception {
        Mockito.when(customerMapper.asEntity(ArgumentMatchers.any())).thenReturn(CustomerBuilder.getEntity());
        Mockito.when(customerService.save(ArgumentMatchers.any(Customer.class))).thenReturn(CustomerBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(CustomerBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(customerService, Mockito.times(1)).save(ArgumentMatchers.any(Customer.class));
        Mockito.verifyNoMoreInteractions(customerService);
    }

    @Test
    void update() throws Exception {
        Mockito.when(customerMapper.asEntity(ArgumentMatchers.any())).thenReturn(CustomerBuilder.getEntity());
        Mockito.when(customerService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(CustomerBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(CustomerBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(customerService, Mockito.times(1)).update(ArgumentMatchers.any(Customer.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(customerService);
    }

    @Test
    void delete() throws Exception {
        Mockito.doNothing().when(customerService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(customerService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(customerService);
    }
}