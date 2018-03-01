import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios/index";
import router from '../router/index'
import i18n from '../language/lang'

Vue.use(Vuex);

const state = {
  // accessToken: window.localStorage.getItem('access_token'),
  authenticated: false,
  language: 'en'
};

const getters = {
  // accessToken: state => state.accessToken,
  authenticated: state => state.authenticated,
  language: state => state.language,
};

const mutations = {
  SET_AUTHENTICATED(state, newValue) {
    state.authenticated = newValue;
  },
  SET_LANGUAGE(state, newValue) {
    state.language = newValue;
  }
};

const actions = {
  setAuthenticated: (async context => {
    await axios.get('/isAuthenticated')
      .then(response => {
        context.commit('SET_AUTHENTICATED', response.data);
      });
  }),
  login: (context, credentials) => {
    let params = 'password=' + credentials.password + '&username=' + credentials.username;
    axios('/login', {
      method: "post",
      data: params,
      withCredentials: true
    }).then(
      () => {
        store.dispatch('setAuthenticated').then(() => {
          if (getters.authenticated)
            router.push({path: '/'});
        });
      })
  },
  changeLanguage: (context, locale) => {
    context.commit('SET_LANGUAGE', locale);
    i18n.locale = locale;
    sessionStorage.setItem("locale", locale);
    axios('/changeLocale', {
      method: "post",
      data: store.getters.language,
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
  mutations
});

export default store;
