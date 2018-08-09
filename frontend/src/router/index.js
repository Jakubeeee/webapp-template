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
      meta: {requiresAuth: false}
    },
    {
      path: '/signup',
      name: 'signUpPage',
      component: SignUpPage,
      meta: {requiresAuth: false}
    },
    {
      path: '/forgotmypassword',
      name: 'forgotMyPasswordPage',
      component: ForgotMyPasswordPage,
      meta: {requiresAuth: false}
    },
    {
      path: '/changepassword',
      name: 'changePasswordPage',
      component: ChangePasswordPage,
      meta: {requiresAuth: false}
    },
    {
      path: '/',
      name: 'contentParentPage',
      component: ContentParentPage,
      meta: {requiresAuth: true},
      children: [
        {
          path: '',
          redirect: '/home',
        },
        {
          path: '/home',
          name: 'homePage',
          component: HomePage,
          meta: {requiresAuth: true}
        },
        {
          path: '/example1',
          name: 'example1Page',
          component: Example1Page,
          meta: {requiresAuth: true}
        },        {
          path: '/example2',
          name: 'example2Page',
          component: Example2Page,
          meta: {requiresAuth: true}
        },        {
          path: '/example3',
          name: 'example3Page',
          component: Example3Page,
          meta: {requiresAuth: true}
        }
      ]
    },
    {
      path: '/about',
      name: 'aboutPage',
      component: AboutPage,
      meta: {requiresAuth: true}
    },
    {
      path: '/contact',
      name: 'contactPage',
      component: ContactPage,
      meta: {requiresAuth: true}
    }]
});

router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth) {
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
});

router.afterEach((to) => {
  store.dispatch('registerPageChange', to.name);
});

export default router;
