## User details manager
UserDetailsManager is an interface that extends UserDetailService.

* UserDetailsService has only one method. **loadUserByUserName(String var1)**.
UserDetailsService only extracts user's information. However, there are some application
that requires some additional feature. For example, adding a new user
or update a new user.

In our project, we are using JdbcUserDetailsManager. JdbcUserDetailsManager 
implements UserDetailsManager. 