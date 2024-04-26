# User Microservice

---
## Quick start
1. Clone this repository
2. Create .env file with the following content:
```properties
DB_PORT={Your localhost port when you want to run db}
DB_USER={Postgres db username}
DB_PASSWORD={Postgres db password}
```
3. Run the following commands (Assuming you are using mac and have homebrew installed):
```shell
brew install colima
brew install docker
brew install docker-compose
docker-compose -f "docker-compose-postgres.yml" up 
```
4. Check if it worked fine using for example DBeaver - if test connection with given in .env file works, then it is fine.

---
## Responsibilities
### Other Microservices: 
- Return user information by userId/email : User
- Check if user with such email already exists : boolean
- Update user data : Response 200
- Delete account : Response 200
- Return user address list by userId/email : List<address>
- Return user email address by userId : email
- Return admins list
- Return Discount info to discount service (Client type, date of acc creation)

---
## Models
### User
```json
{
  "id": "Long",
  "name": "String",
  "surname": "String",
  "gender": "Gender",
  "birthday": "String",
  "phoneNumber": "string",
  "email": "String",
  "profilePhotoUrl": "String"
}
```
### Address
```json
{
  "id": "Long",
  "userId": "Long",
  "country": "String",
  "streetAddress": "String",
  "streetAddress2": "String",
  "city": "string",
  "stateProvinceRegion": "String",
  "zipCode": "String",
  "phoneNumber": "String"
}
```
---
## DTOs
### UserDTO
```json
{
  "name": "String",
  "surname": "String",
  "gender": "Gender",
  "birthday": "String",
  "phoneNumber": "string",
  "email": "String",
  "profilePhotoUrl": "String"
}
```
### AddressDTO
```json
{
  "userId": "Long",
  "country": "String",
  "name": "String",
  "surname": "String",
  "streetAddress": "String",
  "streetAddress2": "String",
  "city": "string",
  "stateProvinceRegion": "String",
  "zipCode": "String",
  "phoneNumber": "String"
}
```
---
## Endpoints
![Endpoints1](/uploads/a066a357d7d75f7f76a88d48d7c40595/Endpoints1.png)

![Endpoints3](/uploads/e3d08b0319f43ef3643d27a608a215dc/Endpoints3.png)

---

## Class Diagram

![image](/uploads/f16a18483577cad37b45cb99fde4f6a5/image.webp)

---

## Database Entity Relationship Diagram
![Gridhub_User_Microservice-2024-04-26_13-04](/uploads/5d5a0407eb518efca5bc7a7a41552096/Gridhub_User_Microservice-2024-04-26_13-04.png)
---

## Test Coverage

soon :) 

---