import Vue from 'vue'
import App from './App'
import store from './vuex/store'
import router from './router/index'
import buefy from 'buefy'
import i18n from './language/lang'

Vue.use(buefy);

Vue.config.productionTip = false;
Vue.config.debug = true;
Vue.config.devTools = true;

new Vue({
  el: '#app',
  store,
  router,
  i18n,
  components: { App },
  template: '<App/>'
});

