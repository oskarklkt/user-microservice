package ui;

import com.griddynamics.user.controllers.UserController;
import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.dtos.UserDto;
import com.griddynamics.user.enums.Gender;

import java.util.Scanner;


public class UserInterface {

    private static final Scanner scanner = new Scanner(System.in);
    private static UserController userController;

    public UserInterface(UserController userController) {
        this.userController = userController;
    }

    public void start() {
        System.out.println("Select an option:");
        System.out.println("1. Add a user");
        System.out.println("2. Get a user by ID");
        System.out.println("3. Get a user by email");
        System.out.println("4. Get all users");
        System.out.println("5. Get user email by ID");
        System.out.println("6. Delete a user");
        System.out.println("7. Update a user");
        System.out.println("8. Check if email is in the database");
        System.out.println("9. Check if user is in the database");
        System.out.println("10. Add an address");
        System.out.println("11. Update an address");
        System.out.println("12. Delete an address");
        System.out.println("13. get addresses by user ID");
        System.out.println("14. Exit");
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    addUser();
                    break;
                case 2:
                    getUserById();
                    break;
                case 3:
                    getUserByEmail();
                    break;
                case 4:
                    getAllUsers();
                    break;
                case 5:
                    getUserEmail();
                    break;
                case 6:
                    deleteUser();
                    break;
                case 7:
                    updateUser();
                    break;
                case 8:
                    isEmailInDatabase();
                    break;
                case 9:
                    isUserInDatabase();
                    break;
                case 10:
                    addAddress();
                    break;
                case 11:
                    updateAddress();
                    break;
                case 12:
                    deleteAddress();
                    break;
                case 13:
                    getAddressesByUserId();
                    break;
                case 14:
                    break;
            }
        } while (option != 14);

    }

    public void addUser() {
        System.out.println("Enter user's first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter user's last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter user's email:");
        String email = scanner.nextLine();
        System.out.println("Enter user's phone number:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter user's date of birth:");
        String dateOfBirth = scanner.nextLine();
        System.out.println("Enter user's Gender(M/F):");
        String gender = scanner.nextLine();
        System.out.println("Enter user's profile photo URL:");
        String profilePhotoUrl = scanner.nextLine();
        UserDto userDto = UserDto.builder()
                .name(firstName)
                .surname(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .birthday(dateOfBirth)
                .gender(Gender.MALE)
                .profilePhotoUrl(profilePhotoUrl)
                .build();
        System.out.println(userController.saveUser(userDto));
    }

    public void getUserById() {
        System.out.println("Enter user's ID:");
        Long id = scanner.nextLong();
        System.out.println(userController.getUser(id));
    }

    public void getUserByEmail() {
        System.out.println("Enter user's email:");
        String email = scanner.nextLine();
        System.out.println(userController.getUserByEmail(email));
    }

    public void getAllUsers() {
        System.out.println(userController.getAllUsers());
    }

    public void getUserEmail() {
        System.out.println("Enter user's ID:");
        Long id = scanner.nextLong();
        System.out.println(userController.getUserEmail(id));
    }

    public void deleteUser() {
        System.out.println("Enter user's ID:");
        Long id = scanner.nextLong();
        userController.deleteUser(id);
    }

    public void updateUser() {
        System.out.println("Enter user's ID:");
        Long id = scanner.nextLong();
        System.out.println("Enter user's first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter user's last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter user's email:");
        String email = scanner.nextLine();
        System.out.println("Enter user's phone number:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter user's date of birth:");
        String dateOfBirth = scanner.nextLine();
        System.out.println("Enter user profile photo URL:");
        String profilePhotoUrl = scanner.nextLine();
        UserDto userDto = UserDto.builder()
                .name(firstName)
                .surname(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .birthday(dateOfBirth)
                .profilePhotoUrl(profilePhotoUrl)
                .build();
        System.out.println(userController.updateUser(id, userDto));
    }

    public void isEmailInDatabase() {
        System.out.println("Enter user's email:");
        String email = scanner.nextLine();
        System.out.println(userController.isEmailInDatabase(email));
    }

    public void isUserInDatabase() {
        System.out.println("Enter user's ID:");
        Long id = scanner.nextLong();
        System.out.println(userController.isUserInDatabase(id));
    }


    /*
        private String country;
    private String name;
    private String surname;
    private String streetAddress;
    private String streetAddress2;
    private String city;
    private String stateProvinceRegion;
    private String zipCode;
    private String phoneNumber;
     */
    public void addAddress() {
        System.out.println("Enter Country:");
        String country = scanner.nextLine();
        System.out.println("Enter Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Surname:");
        String surname = scanner.nextLine();
        System.out.println("Enter Street Address:");
        String streetAddress = scanner.nextLine();
        System.out.println("Enter Street Address 2:");
        String streetAddress2 = scanner.nextLine();
        System.out.println("Enter City:");
        String city = scanner.nextLine();
        System.out.println("Enter State Province Region:");
        String stateProvinceRegion = scanner.nextLine();
        System.out.println("Enter Zip Code:");
        String zipCode = scanner.nextLine();
        System.out.println("Enter Phone Number:");
        String phoneNumber = scanner.nextLine();
        AddressDto addressDto = AddressDto.builder()
                .country(country)
                .name(name)
                .surname(surname)
                .streetAddress(streetAddress)
                .streetAddress2(streetAddress2)
                .city(city)
                .stateProvinceRegion(stateProvinceRegion)
                .zipCode(zipCode)
                .phoneNumber(phoneNumber)
                .build();
        System.out.println(userController.addAddress(1L, addressDto));
    }

    public void updateAddress() {
        System.out.println("Enter Address ID:");
        Long id = scanner.nextLong();
        System.out.println("Enter User ID:");
        Long userId = scanner.nextLong();
        System.out.println("Enter Country:");
        String country = scanner.nextLine();
        System.out.println("Enter Street Address:");
        String streetAddress = scanner.nextLine();
        System.out.println("Enter Street Address 2:");
        String streetAddress2 = scanner.nextLine();
        System.out.println("Enter City:");
        String city = scanner.nextLine();
        System.out.println("Enter State Province Region:");
        String stateProvinceRegion = scanner.nextLine();
        System.out.println("Enter Zip Code:");
        String zipCode = scanner.nextLine();
        System.out.println("Enter Phone Number:");
        String phoneNumber = scanner.nextLine();
        AddressDto addressDto = AddressDto.builder()
                .country(country)
                .streetAddress(streetAddress)
                .streetAddress2(streetAddress2)
                .city(city)
                .stateProvinceRegion(stateProvinceRegion)
                .zipCode(zipCode)
                .phoneNumber(phoneNumber)
                .build();
        System.out.println(userController.updateAddress(id, userId, addressDto));
    }

    public void deleteAddress() {
        System.out.println("Enter User ID:");
        Long userId = scanner.nextLong();
        System.out.println("Enter Address ID:");
        Long id = scanner.nextLong();
        userController.deleteAddress(userId, id);
    }

    public void getAddressesByUserId() {
        System.out.println("Enter User ID:");
        Long userId = scanner.nextLong();
        System.out.println(userController.getAddresses(userId));
    }
}
