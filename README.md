# webapp-template

Template that can be used as foundation for creating a single-page web application.

---

Features:  
- Creating an account and logging into it
- Forgot my password journey
- User role authorization
- Double form validation (frontend + backend)
- Localization (support for english and polish)
- Selection of the application profile (dev and prod)

---

Used technologies:

- spring (boot, security, mvc, jpa (with hibernate), mail, aop) for backend
- html, css (bulma, buefy), vue.js (vuex, vue-router, i18n, vuelidate) for frontend
- h2 embedded database
- spring boot's embedded tomcat as application server
- maven, git

---

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
