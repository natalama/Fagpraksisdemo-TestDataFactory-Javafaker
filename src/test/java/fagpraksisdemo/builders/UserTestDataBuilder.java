package fagpraksisdemo.builders;

import com.github.javafaker.Faker;
import fagpraksisdemo.model.*;
import fagpraksisdemo.tests.UserTest;

import java.util.Locale;

/*Test data builder to add more complex data*/
public class UserTestDataBuilder {

    private final User user;
    private final Faker faker;

    private static final String DEFAULT_CONTRY_CODE = "NO";
    private static final String DEFAULT_LANGUAGE = "nb";

    public UserTestDataBuilder() {
        this(new Locale(DEFAULT_LANGUAGE, DEFAULT_CONTRY_CODE));
    }


    public UserTestDataBuilder(Locale locale) {
        faker = new Faker(locale);
        user = new User();
    }


    public UserTestDataBuilder withValidBasicUserInfo() {
        return withValidFirstName().withValidLastName().withValidBirthday().withValidEmail().withBookAuthoredAsDescription();
    }

    public UserTestDataBuilder withValidFirstName() {
        user.setFirstName(faker.name().firstName());
        return this;
    }

    public UserTestDataBuilder withValidLastName() {
        user.setLastName(faker.name().lastName());
        return this;
    }

    public UserTestDataBuilder withValidBirthday() {
        user.setBirthday(faker.date().birthday(18, 60));
        return this;
    }

    public UserTestDataBuilder withValidEmail() {
        user.setEmail(faker.internet().emailAddress());
        return this;
    }

    public UserTestDataBuilder withGameOfThronesQuoteDescription() {
        user.setDescription(faker.gameOfThrones().quote());
        return this;
    }

    public UserTestDataBuilder withBookAuthoredAsDescription() {
        user.setDescription(String.format("Author of \"%s\"", faker.book().title()));
        return this;
    }

    public UserTestDataBuilder withValidDefaultAddress() {
        Address address = new Address();
        address.setDefaultAddress(true);
        address.setAddressType(faker.options().option(AddressType.class));
        address.setAddressLine1(faker.address().streetAddress(true));
        address.setPostalCode(faker.address().zipCode());
        // kunne vært bedre
        address.setCountryCode(DEFAULT_CONTRY_CODE);
        user.addAddress(address);
        return this;
    }

    public UserTestDataBuilder withValidPhone() {
        Phone phone = new Phone();
        phone.setPhoneType(faker.options().option(PhoneType.class));
        phone.setPhoneNumber(faker.phoneNumber().subscriberNumber(8));
        user.addPhone(phone);
        return this;
    }


    public User build() {
        return user;
    }


}
