# webapp-template

Template that can be used as foundation for creating a single-page web application. 
It uses spring boot, vue.js and maven multi-module system.

This application is deployed on Heroku platform:  
https://webapplicationtemplate.herokuapp.com

---

#### Features:  
- Creating an account and logging into it
- Forgot my password journey
- User role authorization
- Double form validation (on frontend and backend)
- Localization (support for english and polish languages)

---

#### Used technologies:

- java 10
- spring (boot, security, mvc, jpa (with hibernate), mail, aop) for backend
- html, css (bulma, buefy), vue.js (vuex, vue-router, i18n, vuelidate) for frontend
- h2 embedded database (for development), postgresql database (for production)
- spring boot's embedded tomcat as application server
- maven, git

---

#### Building and configuration:

This project is built using maven. There are two groups of profiles used during build:
- environmental (dev, prod): determines whether the application should be deployed locally or on a remote heroku server
- build type (full-build, back-build): determines whether it should build the entire application from scratch 
or skip refreshing the frontend resources (choose if nothing has changed in them for faster build)

Build requires selection of one profile from each group, 
otherwise it will fail. Example command:<br/>
`mvn clean install -P dev -P full-build`<br/>
The above command will build a local version of the application from scratch

Deploying on a remote server requires:
- having a heroku account
- creatng heroku application and setting its name in the configuration of heroku-maven-plugin
 located in webapp-template/pom.xml file
- configuring heroku postgres database (https://devcenter.heroku.com/articles/heroku-postgresql)
- setting heroku config vars needed in resources/application-prod.properties file 
(eg SPRING_MAIL_SENDER_USERNAME and more)
- installing heroku cli and logging in to your account (https://devcenter.heroku.com/articles/heroku-cli)
- launching the build with the appropriate profile (eg `mvn clean install -P prod -P full-build`)

In order for mailing features to work, you need to add your email account credentials in resources/application-dev.properties
Moreover, you may need to change your email account configuration in order for spring mail to use it.  
Here you can find how to do that with gmail account:  
https://sanaulla.info/2017/09/15/using-gmail-as-smtp-server-from-java-spring-boot-apps/  
If it still doesn't work, you may also need this:  
http://jafty.com/blog/unblocking-gmail-smtp-account-for-sending-emails-from-applications/

This project also uses lombok library that needs to be configured properly in your IDE.  
Examples for intelliJ IDEA and Eclipse:  
https://projectlombok.org/setup/intellij  
https://projectlombok.org/setup/eclipse  

---

#### Module overview:

This project consists of several maven modules:

1) webapp-template - top-level module with parent pom configuration
2) core - module containing application configuration from which the executable jar is built
3) common - module containing general services and utils used by other modules
4) security - module containing elements related to application security
5) frontend - module that contains vue components and prepares them to be served by spring
