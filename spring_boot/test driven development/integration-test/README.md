## Integration test
Integration testing is a software testing method in which individual units or components of
a software application are combined and tested as a group. The goal of integration testing
is to ensure that the various components of the application work together as expected and to 
identify any issues that may arise from the interaction between those components. 
This type of testing is typically performed after unit testing. We prefer local environment for integration
test.
[reference](https://www.twilio.com/blog/unit-integration-end-to-end-testing-difference)

There are different types of integration testing available. One of the most famous strategy of integration
test among the developer is **big-bang testing** . In **big-bang** integration testing , all system, modules
are connected together and testing is done as a whole. 

### Integration testing in spring boot
In big-bang integration testing all the components of a system are work together.
For we need a pre-configured environment for this to work. In spring, we do
it by **@SpringBootTest**
[reference](https://www.diffblue.com/blog/java/testing/software%20development/spring-boot-unit-and-integration-testing-overview/#:~:text=Integration%20Tests%20With%20Spring%20Boot%3A%20%40SpringBootTest&text=Most%20of%20the%20time%2C%20you,application%20context%20for%20your%20test.)
SpringbootTest enables the entire application context for our test purpose.
In short, it searches for our main Spring boot entry class to retrieve our context
configuration and starts it accordingly :

```java
@SpringBootApplication
public class DemoApplication {
 
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
 
}
```

It is recommended to execute integration test in separate database. TestContainer
is a good choice for this purpose. By using test-container, we can test our
integration test as near as possible to production level.
[reference](https://medium.com/miq-tech-and-analytics/testcontainers-the-modern-way-of-writing-database-tests-ed49554856e5)

For integration test, we are calling the API end point using mockMvc.
It let us test the controllers without needing to start an HTTP server.
[reference](https://igorski.co/mockmvc-test-spring-boot/#:~:text=MockMvc%20is%20a%20Spring%20Boot,hustle%20of%20actually%20starting%20it.)