package com.spring.rental.address;

import com.spring.rental.address.Address;
import com.spring.rental.address.AddressDTO;
import com.spring.rental.address.AddressRepository;
import com.spring.rental.address.AddressService;
import com.spring.rental.client.Client;
import com.spring.rental.client.ClientRepository;
import com.spring.rental.client.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class AddressServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientRepository clientRepository;

    private AddressService addressService;

    @BeforeEach
    public void setUp() {
        addressService = new AddressService(addressRepository, clientRepository);
        entityManager.clear();
    }

    @Test
    public void shouldGetAllAddresses() {
        Client client1 = new Client("john.doe@example.com", "password123", "John", "Doe", 123456789, Role.REGULAR);
        entityManager.persist(client1);

        Client client2 = new Client("jane.doe@example.com", "password123", "Jane", "Doe", 987654321, Role.REGULAR);
        entityManager.persist(client2);

        Address address1 = new Address(client1, "Street1", "City1", "PostalCode1", "Country1");
        entityManager.persist(address1);

        Address address2 = new Address(client2, "Street2", "City2", "PostalCode2", "Country2");
        entityManager.persist(address2);

        List<Address> addresses = addressService.getAllAddresses();

        assertThat(addresses).hasSize(2);
        assertThat(addresses).extracting(Address::getStreet).containsExactlyInAnyOrder("Street1", "Street2");
    }


    @Test
    public void shouldCreateAddress() {
        Client client = new Client("john.doe@example.com", "password123", "John", "Doe", 123456789, Role.REGULAR);
        entityManager.persist(client);

        AddressDTO addressDTO = new AddressDTO(client.getId(), "Street", "City", "PostalCode", "Country", null);

        Address createdAddress = addressService.createAddress(addressDTO);

        assertThat(createdAddress).isNotNull();
        assertThat(createdAddress.getId()).isNotNull();
        assertThat(createdAddress.getStreet()).isEqualTo("Street");
        assertThat(createdAddress.getCity()).isEqualTo("City");
        assertThat(createdAddress.getPostalCode()).isEqualTo("PostalCode");
        assertThat(createdAddress.getCountry()).isEqualTo("Country");
    }

    @Test
    public void shouldGetAddressById() {
        Client client = new Client("john.doe@example.com", "password123", "John", "Doe", 123456789, Role.REGULAR);
        entityManager.persist(client);

        Address address = new Address(client, "Street", "City", "PostalCode", "Country");
        entityManager.persist(address);

        Address retrievedAddress = addressService.getAddressById(address.getId());

        assertThat(retrievedAddress).isNotNull();
        assertThat(retrievedAddress.getId()).isEqualTo(address.getId());
        assertThat(retrievedAddress.getStreet()).isEqualTo("Street");
        assertThat(retrievedAddress.getCity()).isEqualTo("City");
        assertThat(retrievedAddress.getPostalCode()).isEqualTo("PostalCode");
        assertThat(retrievedAddress.getCountry()).isEqualTo("Country");
    }

    @Test
    public void shouldUpdateAddress() {
        Client client = new Client("john.doe@example.com", "password123", "John", "Doe", 123456789, Role.REGULAR);
        entityManager.persist(client);

        Address address = new Address(client, "Street", "City", "PostalCode", "Country");
        entityManager.persist(address);

        Address updatedAddress = new Address(client, "NewStreet", "NewCity", "NewPostalCode", "NewCountry");
        Address resultAddress = addressService.updateAddress(address.getId(), updatedAddress);

        assertThat(resultAddress).isNotNull();
        assertThat(resultAddress.getId()).isEqualTo(address.getId());
        assertThat(resultAddress.getStreet()).isEqualTo("NewStreet");
        assertThat(resultAddress.getCity()).isEqualTo("NewCity");
        assertThat(resultAddress.getPostalCode()).isEqualTo("NewPostalCode");
        assertThat(resultAddress.getCountry()).isEqualTo("NewCountry");
    }

    @Test
    public void shouldDeleteAddress() {
        Client client = new Client("john.doe@example.com", "password123", "John", "Doe", 123456789, Role.REGULAR);
        entityManager.persist(client);

        Address address = new Address(client, "Street", "City", "PostalCode", "Country");
        entityManager.persist(address);

        addressService.deleteAddress(address.getId());

        assertThat(addressRepository.existsById(address.getId())).isFalse();
    }

}
