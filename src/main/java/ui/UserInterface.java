package ui;

import com.griddynamics.user.controllers.UserController;
import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.dtos.UserDto;
import com.griddynamics.user.enums.Gender;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserInterface {
    private final UserController userController;

    public UserInterface(UserController userController) {
        this.userController = userController;
    }

    public void start() {
        System.out.println("Select an option:");
        System.out.println("""
                1. Save user\

                2. Get user\

                3. Get user by email\

                4. Get all users\

                5. Get user email\

                6. Delete user\

                7. Update user\

                8. Check if email is in database\

                9. Check if user is in database\

                10. Add address\

                11. Update address\

                12. Delete address\
                
                13. Get user's addresses\

                14. Exit""");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        while (option != 14) {
            switch (option) {
                case 1:
                    saveUser();
                    break;
                case 2:
                    getUser();
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
                    getAllAddresses();
                    break;
            }
            System.out.println("Select an option:");
            option = scanner.nextInt();
        }

    }

    private void saveUser() {
        System.out.println("Enter user details:");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user name:");
        String name = scanner.next();
        System.out.println("Enter user surname:");
        String surname = scanner.next();
        System.out.println("Enter user gender (M/F):");
        Gender gender = scanner.next().equals("M") ? Gender.MALE : Gender.FEMALE;
        System.out.println("Enter user birthday date (yyyy-mm-dd):");
        String birthday = scanner.next();
        System.out.println("Enter user phone number:");
        String phoneNumber = scanner.next();
        System.out.println("Enter user email:");
        String email = scanner.next();
        System.out.println("Enter user profile photo URL:");
        String profilePhotoUrl = scanner.next();
        new UserDto();
        UserDto userDto = UserDto.builder()
                .name(name)
                .surname(surname)
                .gender(gender)
                .birthday(birthday)
                .phoneNumber(phoneNumber)
                .email(email)
                .profilePhotoUrl(profilePhotoUrl)
                .build();
        if (userController.saveUser(userDto)) {
            System.out.println("User saved successfully!");
        } else {
            System.out.println("User with this email already exists!");
        }
    }

    private void getUser() {
        System.out.println("Enter user id:");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();
        Optional<UserDto> userDto = userController.getUser(id);
        userDto.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("User not found!")
        );
    }

    private void getUserByEmail() {
        System.out.println("Enter user email:");
        Scanner scanner = new Scanner(System.in);
        String email = scanner.next();
        Optional<UserDto> userDto = userController.getUserByEmail(email);
        if (userDto.isEmpty()) {
            System.out.println("User not found!");
        } else {
            System.out.println(userDto);
        }
    }

    private void getAllUsers() {
        System.out.println("All users:");
        List<UserDto> list = userController.getAllUsers();
        if (list.isEmpty()) {
            System.out.println("No users found!");
        } else {
            list.forEach(System.out::println);
        }
    }

    private void getUserEmail() {
        System.out.println("Enter user id:");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();
        if (!userController.isUserInDatabase(id)) {
            System.out.println("User not found!");
        } else {
            System.out.println(userController.getUserEmail(id).orElse("Email not found!"));
        }
    }

    private void deleteUser() {
        System.out.println("Enter user id:");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();
        if (userController.deleteUser(id)) {
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("User not found!");
        }
    }

    private void isEmailInDatabase() {
        System.out.println("Enter user email:");
        Scanner scanner = new Scanner(System.in);
        String email = scanner.next();
        if (userController.isEmailInDatabase(email)) {
            System.out.println("Email is in database!");
        } else {
            System.out.println("Email is not in database!");
        }
    }

    private void isUserInDatabase() {
        System.out.println("Enter user id:");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();
        if (userController.isUserInDatabase(id)) {
            System.out.println("User is in database!");
        } else {
            System.out.println("User is not in database!");
        }
    }

    private void addAddress() {
        System.out.println("Enter user id:");
        Scanner scanner = new Scanner(System.in);
        Long userId = scanner.nextLong();
        System.out.println("Enter address details:");
        System.out.println("Enter country:");
        String country = scanner.next();
        System.out.println("Enter city:");
        String city = scanner.next();
        System.out.println("Enter street address 1:");
        String street1 = scanner.next();
        System.out.println("Enter street address 2:");
        String street2 = scanner.next();
        System.out.println("Enter Region:");
        String region = scanner.next();
        System.out.println("Enter postal code:");
        String postalCode = scanner.next();
        System.out.println("Enter phone number:");
        String phoneNumber = scanner.next();
        Optional<UserDto> optionalUserDto = userController.getUser(userId);
        if (optionalUserDto.isPresent()) {
            UserDto userDto = optionalUserDto.get();
            AddressDto addressDto = AddressDto.builder()
                    .userId(userId)
                    .country(country)
                    .name(userDto.getName())
                    .surname(userDto.getSurname())
                    .streetAddress(street1)
                    .streetAddress2(street2)
                    .city(city)
                    .stateProvinceRegion(region)
                    .zipCode(postalCode)
                    .phoneNumber(phoneNumber)
                    .build();
            if (userController.addAddress(userId, addressDto)) {
                System.out.println("Address added successfully!");
            } else {
                System.out.println("User not found!");
            }
        } else {
            System.out.println("User not found!");
        }
    }

    private void deleteAddress() {
        System.out.println("Enter user id:");
        Scanner scanner = new Scanner(System.in);
        Long userId = scanner.nextLong();
        System.out.println("Enter address id:");
        Long addressId = scanner.nextLong();
        if (userController.deleteAddress(userId, addressId)) {
            System.out.println("Address deleted successfully!");
        } else {
            System.out.println("User not found!");
        }
    }

    private void getAllAddresses() {
        System.out.println("Enter user id:");
        Scanner scanner = new Scanner(System.in);
        Long userId = scanner.nextLong();
        userController.getAddresses(userId).ifPresentOrElse(
                addresses -> addresses.forEach(System.out::println),
                () -> System.out.println("User has no addresses!")
        );
    }

    private void updateUser() {
        System.out.println("Enter user details:");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user id:");
        String id = String.valueOf(scanner.nextLong());
        System.out.println("Enter user name:");
        String name = scanner.next();
        System.out.println("Enter user surname:");
        String surname = scanner.next();
        System.out.println("Enter user gender (M/F):");
        Gender gender = scanner.next().equals("M") ? Gender.MALE : Gender.FEMALE;
        System.out.println("Enter user birthday date (yyyy-mm-dd):");
        String birthday = scanner.next();
        System.out.println("Enter user phone number:");
        String phoneNumber = scanner.next();
        System.out.println("Enter user email:");
        String email = scanner.next();
        if (!userController.isEmailInDatabase(email)) {
            System.out.println("User with this email already exists!");
            return;
        }
        System.out.println("Enter user profile photo URL:");
        String profilePhotoUrl = scanner.next();
        if (userController.updateUser(Long.valueOf(id), UserDto.builder()
                .name(name)
                .surname(surname)
                .gender(gender)
                .birthday(birthday)
                .email(email)
                .phoneNumber(phoneNumber)
                .profilePhotoUrl(profilePhotoUrl)
                .build())) {
                System.out.println("User updated successfully!");
        } else {
            System.out.println("User not found!");
        }
    }

    private void updateAddress() {
        System.out.println("Enter user id:");
        Scanner scanner = new Scanner(System.in);
        Long userId = scanner.nextLong();
        System.out.println("Enter address details:");
        System.out.println("Enter address id:");
        Long addressId = scanner.nextLong();
        System.out.println("Enter country:");
        String country = scanner.next();
        System.out.println("Enter city:");
        String city = scanner.next();
        System.out.println("Enter street address 1:");
        String street1 = scanner.next();
        System.out.println("Enter street address 2:");
        String street2 = scanner.next();
        System.out.println("Enter Region:");
        String region = scanner.next();
        System.out.println("Enter postal code:");
        String postalCode = scanner.next();
        System.out.println("Enter phone number:");
        String phoneNumber = scanner.next();
        Optional<UserDto> optionalUserDto = userController.getUser(userId);
        if (optionalUserDto.isPresent()) {
            UserDto userDto = optionalUserDto.get();
            AddressDto addressDto = AddressDto.builder()
                    .userId(userId)
                    .country(country)
                    .name(userDto.getName())
                    .surname(userDto.getSurname())
                    .streetAddress(street1)
                    .streetAddress2(street2)
                    .city(city)
                    .stateProvinceRegion(region)
                    .zipCode(postalCode)
                    .phoneNumber(phoneNumber)
                    .build();
            if (userController.updateAddress(userId, addressId, addressDto)) {
                System.out.println("Address added successfully!");
            } else {
                System.out.println("User not found!");
            }
        } else {
            System.out.println("User not found!");
        }
    }

}
