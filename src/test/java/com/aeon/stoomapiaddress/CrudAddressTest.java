package com.aeon.stoomapiaddress;

import com.aeon.stoomapiaddress.business.AddressService;
import com.aeon.stoomapiaddress.exception.ResourceDefaultException;
import com.aeon.stoomapiaddress.model.Address;
import com.aeon.stoomapiaddress.repository.AddressRepository;
import com.google.maps.errors.ApiException;
import org.junit.Before;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;


import java.io.IOException;
import java.util.List;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CrudAddressTest {

        @InjectMocks
        private AddressService service;

        @InjectMocks
        private AddressRepository repository;

        private Address viewAddress;

        @Before
        public void init() {
            viewAddress = mockAddressBody();
            service = new AddressService();
            repository = mock(AddressRepository.class);
            ReflectionTestUtils.setField(service, "repor", repository);
        }

        @Test
        public void testCreateAddressWithLatLngSucess() throws InterruptedException, ApiException, IOException {
            viewAddress.setLatitude("-22.8309284");
            viewAddress.setLongitude("-47.0768986");
            Address newAddress = service.addAddress(viewAddress);
            assertNotNull(newAddress);
        }

        @Test
        public void testCreateAddressWithoutLatLngSucess() throws InterruptedException, ApiException, IOException {
            Address newAddress = service.addAddress(viewAddress);
            assertNotNull(newAddress);
            assertEquals("-22.8309284", newAddress.getLatitude());
            assertEquals("-47.0768986", newAddress.getLongitude());
        }

        @Test
        public void testCreateAddressFailureMandatoryParameterNull() throws InterruptedException, ApiException, IOException {
            viewAddress.setNumber(null);

            Throwable exception = assertThrows(ResourceDefaultException.class, () ->
            { service.addAddress(viewAddress);
            });
            assertEquals("Parameter Number is Required!", exception.getMessage());
        }

        @Test
        public void testCreateAddressFailureMandatoryParameterEmpty() throws InterruptedException, ApiException, IOException {
            viewAddress.setStreetName("");

            Throwable exception = assertThrows(ResourceDefaultException.class, () ->
            { service.addAddress(viewAddress);
            });
            assertEquals("Parameter StreetName is Required!", exception.getMessage());
        }

        @Test
        public void testUpdateAddressWithLatLngSucess() throws InterruptedException, ApiException, IOException {
            viewAddress.setLatitude("-22.8309284");
            viewAddress.setLongitude("-47.0768986");
            Address newAddress = service.addAddress(viewAddress);
            assertNotNull(newAddress);

            newAddress.setNumber(249);
            newAddress.setComplement("Fundo");

            Address currentAddress = service.updateAddress(newAddress);
            assertNotNull(currentAddress);
            assertEquals(java.util.Optional.of(249), currentAddress.getNumber());
            assertEquals("Fundo", newAddress.getComplement());
        }

        @Test
        public void testUpdateAddressWithoutLatLngSucess() throws InterruptedException, ApiException, IOException {
            Address newAddress = service.addAddress(viewAddress);
            assertNotNull(newAddress);
            assertEquals("-22.8309284", newAddress.getLatitude());
            assertEquals("-47.0768986", newAddress.getLongitude());

            newAddress.setLatitude("");
            newAddress.setLongitude("");
            newAddress.setNumber(249);
            newAddress.setComplement("Fundo");

            Address currentAddress = service.updateAddress(newAddress);
            assertNotNull(currentAddress);
            assertEquals(java.util.Optional.of(249), currentAddress.getNumber());
            assertEquals("Fundo", newAddress.getComplement());
            assertEquals("-22.8309284", newAddress.getLatitude());
            assertEquals("-47.0768986", newAddress.getLongitude());
        }

        @Test
        public void testUpdateAddressFailureId() throws InterruptedException, ApiException, IOException {
            viewAddress.setLatitude("-22.8309284");
            viewAddress.setLongitude("-47.0768986");
            Address newAddress = service.addAddress(viewAddress);
            assertNotNull(newAddress);

            newAddress.setId(UUID.fromString("08fe3b49-fff6-4f20-bfd1-8701a0a0490c"));
            newAddress.setNumber(249);
            newAddress.setComplement("Fundo");

            Throwable exception = assertThrows(ResourceDefaultException.class, () ->
            { service.updateAddress(newAddress);
            });
            assertEquals("Address not found!", exception.getMessage());
        }

        @Test
        public void testListAddressAllSucess(){
            List<Address> list = service.listAllAddress();
            assertEquals("Lista Vazia", 0, list.size());
        }

        @Test
        public void testDeleteAddressSucess() throws InterruptedException, ApiException, IOException {
            Address newAddress = service.addAddress(viewAddress);
            assertNotNull(newAddress);

            service.deleteAddress(newAddress.getId());
            List<Address> list = service.listAllAddress();
            assertEquals("Lista Vazia", 0, list.size());
        }

        @Test
        public void testDeleteAddressFailureId() throws InterruptedException, ApiException, IOException {
            viewAddress.setLatitude("-22.8309284");
            viewAddress.setLongitude("-47.0768986");
            Address newAddress = service.addAddress(viewAddress);
            assertNotNull(newAddress);

            newAddress.setId(UUID.fromString("08fe3b49-fff6-4f20-bfd1-8701a0a0490c"));
            newAddress.setNumber(249);
            newAddress.setComplement("Fundo");

            Throwable exception = assertThrows(ResourceDefaultException.class, () ->
            { service.deleteAddress(newAddress.getId());
            });
            assertEquals("Address not found!", exception.getMessage());
        }

        private Address mockAddressBody(){
            Address address = new Address();
            address.setStreetName("Rua Tranquilo Prosperi");
            address.setNumber(55);
            address.setComplement("Terreo");
            address.setNeighbourhood("Barão Geraldo");
            address.setCity("Campinas");
            address.setState("SP");
            address.setCountry("Brasil");
            address.setZipcode("00000000");

            return address;
        }
}
