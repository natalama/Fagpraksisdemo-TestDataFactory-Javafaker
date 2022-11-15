package fagpraksisdemo.factories;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import fagpraksisdemo.model.User;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

public class UserFactory {

    public static User aValidUser() {
        Faker faker = new Faker();
        User user = new User();
        user.setFirstName(faker.hobbit().character());
        user.setLastName(faker.name().lastName());
        user.setBirthday(LocalDate.ofInstant(faker.date().birthday(18, 67).toInstant(), ZoneId.systemDefault()));
        user.setDescription(faker.lorem().sentence(15));
        user.setEmail(faker.internet().emailAddress());
        return user;
    }

    public static User aValidNorwegianUser() {
        Faker faker = new Faker(Locale.forLanguageTag("nb-NO"));
        final CreditCardType creditCardType = faker.options().option(CreditCardType.values());
        User user = new User();
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setDescription(MessageFormat.format("""
                        Denne personen har følgende kredittkortinformasjon:
                         Kredittkorttype: {0}
                         Kredittkortnummer: {1}
                         Utløper: {2}
                         Kode: {3}""", creditCardType, faker.finance().creditCard(creditCardType), faker.business().creditCardExpiry(),
                faker.number().randomNumber(3, true)));
        user.setBirthday(faker.date().birthday(18,60));
        return user;
    }

    public static User anInvalidUserWithoutFirstName() {
        Faker faker = new Faker();
        User user = new User();
        user.setLastName(faker.name().lastName());
        user.setDescription("This user once said: " + faker.gameOfThrones().quote());
        return user;
    }

    public static User aValidUserWithDiseaseAsLastName() {
        Faker faker = new Faker();
        User user = new User();
        user.setFirstName(faker.funnyName().name());
        user.setLastName(faker.medical().diseaseName());
        user.setBirthday(LocalDate.ofInstant(faker.date().birthday(18, 67).toInstant(), ZoneId.systemDefault()));
        user.setDescription("Author of the book: Journey of "+ faker.backToTheFuture().character() + " and " + faker.friends().character()+" to "+  faker.space().galaxy());
        user.setEmail(faker.internet().emailAddress());
        return user;
    }

    public static User aUserWithExtremelyLongFirstName() {
        Faker faker = new Faker();
        User user = new User();
        user.setFirstName(faker.lorem().sentence(100).substring(0, 101));
        user.setLastName(faker.name().lastName());
        user.setDescription("This user once said: " + faker.twinPeaks().quote());
        return user;
    }


}
