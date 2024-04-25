# User Microservice

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
![Endpoints1.png](Endpoints1.png)

![Endpoints3.png](Endpoints3.png)

---

## Class Diagram

![image.webp](image.webp)