import Vue from 'vue'
import Router from 'vue-router'
import store from '../vuex/store'
import ContentParentPage from '@/pages/protected/ContentParentPage.vue'
import HomePage from '@/pages/protected/HomePage.vue'
import ContactPage from '@/pages/protected/ContactPage.vue'
import AboutPage from '@/pages/protected/AboutPage.vue'
import Example1Page from '@/pages/protected/Example1Page.vue'
import Example2Page from '@/pages/protected/Example2Page.vue'
import Example3Page from '@/pages/protected/Example3Page.vue'
import LoginPage from '@/pages/public/LoginPage.vue'
import SignUpPage from '@/pages/public/SignUpPage.vue'
import ForgotMyPasswordPage from '@/pages/public/ForgotMyPasswordPage.vue'
import ChangePasswordPage from '@/pages/public/ChangePasswordPage.vue'

Vue.use(Router);

const router = new Router({
  routes: [
    {
      path: '/login',
      name: 'loginPage',
      component: LoginPage,
      meta: {protected: false}
    },
    {
      path: '/signup',
      name: 'signUpPage',
      component: SignUpPage,
      meta: {protected: false}
    },
    {
      path: '/forgotmypassword',
      name: 'forgotMyPasswordPage',
      component: ForgotMyPasswordPage,
      meta: {protected: false}
    },
    {
      path: '/changepassword',
      name: 'changePasswordPage',
      component: ChangePasswordPage,
      meta: {protected: false}
    },
    {
      path: '/',
      name: 'contentParentPage',
      component: ContentParentPage,
      meta: {protected: true},
      children: [
        {
          path: '',
          redirect: '/home',
        },
        {
          path: '/home',
          name: 'homePage',
          component: HomePage,
          meta: {protected: true}
        },
        {
          path: '/example1',
          name: 'example1Page',
          component: Example1Page,
          meta: {protected: true}
        }, {
          path: '/example2',
          name: 'example2Page',
          component: Example2Page,
          meta: {protected: true}
        }, {
          path: '/example3',
          name: 'example3Page',
          component: Example3Page,
          meta: {protected: true}
        }
      ]
    },
    {
      path: '/about',
      name: 'aboutPage',
      component: AboutPage,
      meta: {protected: true}
    },
    {
      path: '/contact',
      name: 'contactPage',
      component: ContactPage,
      meta: {protected: true}
    }]
});

router.beforeEach((to, from, next) => {
  store.dispatch('checkAuthenticated').then(() => {
    if (to.meta.protected) {
      if (store.getters.authenticated)
        next();
      else
        next('/login');
    }
    else {
      if (store.getters.authenticated)
        next('/home');
      else
        next();
    }
  })
});

router.afterEach((to) => {
  store.dispatch('registerPageChange', to.name);
});

export default router;
