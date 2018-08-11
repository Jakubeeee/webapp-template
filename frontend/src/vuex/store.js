import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios/index";
import router from '../router/index'
import i18n from '../language/lang'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex);

const state = {
  authenticated: false,
  activePage: '',
  language: 'en',
  resetEmailCooldownActive: false,
  changePasswordCooldownActive: false
};

const getters = {
  authenticated: state => state.authenticated,
  activePage: state => state.activePage,
  language: state => state.language,
  resetEmailCooldownActive: state => state.resetEmailCooldownActive,
  changePasswordCooldownActive: state => state.changePasswordCooldownActive
};

const mutations = {
  SET_AUTHENTICATED(state, newValue) {
    state.authenticated = newValue;
  },
  SET_ACTIVE_PAGE(state, newValue) {
    state.activePage = newValue;
  },
  SET_LANGUAGE(state, newValue) {
    state.language = newValue;
  },
  SET_RESET_EMAIL_COOLDOWN_ACTIVE(state, newValue) {
    state.resetEmailCooldownActive = newValue;
  },
  SET_CHANGE_PASSWORD_COOLDOWN_ACTIVE(state, newValue) {
    state.changePasswordCooldownActive = newValue;
  }
};

const actions = {
  login: async (context, credentials) => {
    let params = 'password=' + credentials.password + '&username=' + credentials.username;
    await axios('/login', {
      method: "post",
      data: params,
      withCredentials: true
    }).then(() => {
      context.commit('SET_AUTHENTICATED', true);
      router.push({path: '/home'});
    }).catch(() => {
    })
  },
  logout: async (context) => {
    await axios.post('/logout').then(() => {
        context.commit('SET_AUTHENTICATED', false);
        router.push({path: '/login'});
      }
    )
  },
  checkAuthenticated: async (context) => {
    await axios.get('/isAuthenticated').then((response) => {
      context.commit('SET_AUTHENTICATED', response.data)
    })
  },
  registerPageChange: (context, pageName) => {
    context.commit('SET_ACTIVE_PAGE', pageName);
  },
  beginResetEmailCooldown: (context) => {
    context.commit('SET_RESET_EMAIL_COOLDOWN_ACTIVE', true);
    setTimeout(() => {
      context.commit('SET_RESET_EMAIL_COOLDOWN_ACTIVE', false);
    }, 1000 * 60);
  },
  beginPasswordChangeCooldown: (context) => {
    context.commit('SET_CHANGE_PASSWORD_COOLDOWN_ACTIVE', true);
    setTimeout(() => {
      context.commit('SET_CHANGE_PASSWORD_COOLDOWN_ACTIVE', false);
    }, 10000 * 60);
  },
  changeLanguage: (context, locale) => {
    context.commit('SET_LANGUAGE', locale);
    i18n.locale = locale;
    axios('/changeLocale', {
      method: "post",
      data: getters.language,
      headers: {
        'Content-type': 'text/plain'
      }
    })
  }
};

const store = new Vuex.Store({
  state,
  getters,
  actions,
  mutations,
  plugins: [createPersistedState()]
});

export default store;
