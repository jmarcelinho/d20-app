## D20-APP ##

A system allowing the rental of card games and trays without the need for an existing playroom in the city, where users can either make their own rental games and rent from other users.
There's support for the following features:

* Conventional email/username based registration with admin support
* Conventional Login using Spring Security and generation of JWT token
* Multiple device login and logout support
* Support for expiration bases email verification. Mail is sent upon registration. 
* Resend the email confirmation email if old one expires
* Support for password updation once logged in
* Support for forgot-password functionality with password reset token sent to mail.
* Supports admin protected urls leveraging Spring security
* API to refresh JWT tokens once the temporary JWT expires. 
* API to check availability of username/email during registration.


---

## Swagger Docs ##
The project has been configured with a basic Swagger docket that exposes the commonly used API's along with the expected params.
![image](https://user-images.githubusercontent.com/28639434/69038898-97518500-09c9-11ea-962e-099364fc6975.png)
![image](https://user-images.githubusercontent.com/28639434/69038875-843eb500-09c9-11ea-9679-c0458295f0d7.png)
![image](https://user-images.githubusercontent.com/28639434/69038884-8b65c300-09c9-11ea-9392-9d5a62f05871.png)
![image](https://user-images.githubusercontent.com/28639434/69038911-9e789300-09c9-11ea-887e-ec8300349257.png)
![image](https://user-images.githubusercontent.com/28639434/69038903-991b4880-09c9-11ea-85ab-9e2e1177e08d.png)

---

## JWT ##
JSON Web Tokens are an open, industry standard RFC 7519 method for representing claims securely between two parties.

![](https://cdn-images-1.medium.com/max/1334/1*7T41R0dSLEzssIXPHpvimQ.png)

## Exception Handling ##
The app throws custom exceptions wherever necessary which are captured through a controller advice. It then returns the appropriate error response to the caller
* AppException
* BadRequestException
* ResourceAlreadyInUseException
* ResourceNotFoundException
* UserLoginException
* UserRegistrationException
* MethodArgumentNotValidException
* UserLogoutException
* TokenRefreshException
* UpdatePasswordException
* PasswordResetException
* PasswordResetLinkException

Moreover, entities are validated using JSR-303 Validation constraints. 

---

## Access D20-APP
1. **Heroku Application**

	You can run D20-app accessing the site -

	```link
	https://d20-app.herokuapp.com
	```
  
### Contribution ###
* Please fork the project and adapt it to your use case.
* Submit a pull request.
* The project is in a nascent stage. As such any issues you find should be reported in the issues section.

---
## Demo Screens ##

1. **Registering a user**
---



2. **Logging in a valid user**
---


3. **Logging in an invalid user**
---


3. **Using the token in request header & accessing resource**
---


4. **Accessing admin resource with invalid permissions/token**
---


5. **Logging out the user device**
---


6. **Resetting the password**
---


7. **Refreshing the authentication token**
---


8. **Confirming the user email verification token**
---


