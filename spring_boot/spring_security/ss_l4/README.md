## Authentication provider
Authentication provider is an interface that allows us to write our own authentication
logic. This interface mainly have two method that we must overwrite.

1. Authentication authenticate(Authentication authentication)
2. public boolean supports(Class<?> authType)

The authenticate method receives an Authentication type object. This method is
designed in such a way that it should return either an authenticated object
or null. The following table explains what will be returned in which scenerio:


| Return type           | use case                                                             |
|-----------------------|----------------------------------------------------------------------|
| Authentication Object | When the user is fully authenticated                                 |
| null                  | When this authentication provider does not support the authentication |
| Throw exception       | If the request is not authenticated                             |

**boolean supports(Class<?> authentication)**<br>
Returns true if this AuthenticationProvider supports the 
indicated Authentication object.
Returning true does not guarantee an AuthenticationProvider will be able to 
authenticate the presented instance of the Authentication class.
It simply indicates it can support closer evaluation of it. 
An AuthenticationProvider can still return null
from the authenticate(Authentication) 
method to indicate another AuthenticationProvider should be tried.
