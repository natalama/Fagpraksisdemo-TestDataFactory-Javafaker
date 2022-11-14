package fagpraksisdemo.model;


import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

public class User {

    @NotNull(message = "Fornavn kan ikke være null")
    @Size(min = 2, max = 100, message = "For langt fornavn")
    private String firstName;
    @NotNull(message = "Etternavn kan ikke være null")
    @Size(min = 1, max = 100, message = "For langt etternavn")
    private String lastName;
    @NotNull(message = "En person må være født i en viss dato")
    @PastOrPresent(message = "Ugyldig fødselsdato")
    private LocalDate birthday;
    @Email(message = "Ugyldig epost")
    private String email;
    @Size(min = 0, max = Integer.MAX_VALUE)
    private String description;
    private List<Address> addressList = new ArrayList<>(0);
    private List<Phone> phoneList = new ArrayList<>(0);

    public User() {
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhone(Phone phone) {
        IntStream.range(0, phoneList.size())
                .filter(p -> phoneList.get(p).getPhoneType().equals(phone.getPhoneType()))
                .findFirst().ifPresentOrElse(
                        p -> phoneList.set(p, phone),
                        () -> phoneList.add(phone));
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void addAddress(Address address) {
        addressList.add(address);
    }

    public void removeAddress(Address address) {
        addressList.add(address);
    }

    public void replaceAddress(Address address) {
        IntStream.range(0, addressList.size())
                .filter(p -> addressList.get(p).getAddressType().equals(address.getAddressType()))
                .findFirst().ifPresentOrElse(
                        p -> addressList.set(p, address),
                        () -> addressList.add(address));
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = LocalDate.ofInstant(birthday.toInstant(), ZoneId.systemDefault());
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{\n" +
                "firstName='" + firstName + '\'' +
                "\n lastName='" + lastName + '\'' +
                "\n birthday=" + birthday +
                "\n email='" + email + '\'' +
                "\n description='" + description + '\'' +
                "\n addressList=" + addressList +
                "\n phoneList=" + phoneList +
                '}';
    }
}
