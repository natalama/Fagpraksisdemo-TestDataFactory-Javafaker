package fagpraksisdemo.tests;

import fagpraksisdemo.builders.UserTestDataBuilder;
import jakarta.validation.ConstraintViolation;
import fagpraksisdemo.model.Address;
import fagpraksisdemo.model.AddressType;
import fagpraksisdemo.model.User;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import fagpraksisdemo.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static fagpraksisdemo.factories.UserFactory.*;

public class UserTest {

    @Test
    @Disabled(value = """
            Frarådes. Ikke alle heter Ola Nordmann,
            ikke alle er 10 år gamle, osv...
            Det passer kun for akkurat denne casen""")
    public void aTestToAvoid() {
        User user = new User();
        user.setFirstName("Ola");
        user.setLastName("Nordmann");
        user.setBirthday(LocalDate.now().minusMonths(120));
        user.setDescription("tilfeldig beskrivelse");
        user.setEmail("test@test.no");
        Address address = new Address();
        address.setAddressType(AddressType.HOME);
        address.setAddressLine1("Testveien 123");
        address.setPostalCode("0977");
        address.setCountryCode("NO");
        user.addAddress(address);
        System.out.println(address);
    }

    @Test
    /*Her får vi en forskjellig gyldig person hver eneste gang*/
    public void testValidPerson() {
        User user = aValidUser();
        System.out.println(user);
        Set<ConstraintViolation<User>> validationMessages = DefaultValidator.getInstance().validate(user);
        Assertions.assertTrue(CollectionUtils.isEmpty(validationMessages));
    }

    @Test
    public void testValidNorwegianPerson() {
        User bruker = aValidNorwegianUser();
        System.out.println(bruker);
        Set<ConstraintViolation<User>> validationMessages = DefaultValidator.getInstance().validate(bruker);
        Assertions.assertTrue(CollectionUtils.isEmpty(validationMessages));
    }

    @Test
    public void testValidPersonWithDiseaseLastName() {
        User user = aValidUserWithDiseaseAsLastName();
        System.out.println(user);
        Set<ConstraintViolation<User>> validationMessages = DefaultValidator.getInstance().validate(user);
        Assertions.assertTrue(CollectionUtils.isEmpty(validationMessages));
    }

    @Test
    public void testMissingFirstName() {
        User bruker = anInvalidUserWithoutFirstName();
        System.out.println(bruker);
        Set<ConstraintViolation<User>> validationMessages = DefaultValidator.getInstance().validate(bruker);
        Assertions.assertTrue(validationMessages.stream().anyMatch(p -> p.getMessage().equalsIgnoreCase("Fornavn kan ikke være null")));
    }

    @Test
    public void testFirstNameTooLong() {
        User bruker = aUserWithExtremelyLongFirstName();
        System.out.println(bruker);
        Set<ConstraintViolation<User>> validationMessages = DefaultValidator.getInstance().validate(bruker);
        validationMessages.stream().map(p -> p.getMessage()).forEach(System.out::println);
        Assertions.assertTrue(validationMessages.stream().anyMatch(p -> p.getMessage().contains("for langt fornavn")));
    }

    /*Litt mer kompleks data? null problem. Du kan lage en test data builder for det*/
    @Test
    public void testAValidUserWithAddressAndPhone() {
        User user = new UserTestDataBuilder()
                .withValidBasicUserInfo()
                .withValidDefaultAddress()
                .withValidPhone()
                .build();
        System.out.println(user);
        Set<ConstraintViolation<User>> validationMessages = DefaultValidator.getInstance().validate(user);
        Assertions.assertTrue(CollectionUtils.isEmpty(validationMessages));
    }

    @Test
    public void testAValidPersonWithValidAddress() {
        User user = new UserTestDataBuilder()
                .withValidBasicUserInfo()
                .withValidDefaultAddress().build();
        System.out.println(user);
        Set<ConstraintViolation<User>> validationMessages = DefaultValidator.getInstance().validate(user);
        Assertions.assertTrue(CollectionUtils.isEmpty(validationMessages));
    }


}
