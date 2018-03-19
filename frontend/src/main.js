import Vue from 'vue'
import App from './App'
import store from './vuex/store'
import router from './router/index'
import buefy from 'buefy'
import i18n from './language/lang'
import Vuelidate from 'vuelidate'
import lodash from 'lodash';

Vue.use(buefy);
Vue.use(Vuelidate);
Vue.use(lodash);

Vue.config.devTools = true;
Vue.config.productionTip = false;
Vue.config.debug = true;

new Vue({
  el: '#app',
  store,
  router,
  i18n,
  Vuelidate,
  components: {App},
  template: '<App/>'
});
