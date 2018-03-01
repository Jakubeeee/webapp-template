import Vue from 'vue'
import Router from 'vue-router'
import store from '../vuex/store'
import HomePage from '@/pages/HomePage.vue'
import LoginPage from '@/pages/LoginPage.vue'
import SignUpPage from '@/pages/SignUpPage.vue'
import ForgotMyPasswordPage from '@/pages/ForgotMyPasswordPage.vue'
import ChangePasswordPage from '@/pages/ChangePasswordPage.vue'

Vue.use(Router);

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'homePage',
      component: HomePage,
      meta: {requiresAuth: true}
    },
    {
      path: '/about',
      name: 'aboutPage',
      component: HomePage, //temporary
      meta: {requiresAuth: true}
    },
    {
      path: '/contact',
      name: 'contactPage',
      component: HomePage, //temporary
      meta: {requiresAuth: true}
    },
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
    }
  ]
});

router.beforeEach((to, from, next) => {
  store.dispatch('setAuthenticated').then(() => {
    if (to.meta.requiresAuth) {
      if (store.getters.authenticated)
        next();
      else
        next('/login');
    }
    else {
      if (store.getters.authenticated) {
        next('/');
      }
      else
        next();
    }
  })
});

export default router;
