import Vue from 'vue'
import App from './App'
import router from './router'
import Buefy from 'buefy'
import VueI18n from 'vue-i18n'

Vue.use(Buefy);
Vue.use(VueI18n);

const i18n = new VueI18n({
  locale: 'en',
  messages: {
    'en': require('./messages/messages_en.json'),
    'pl': require('./messages/messages_pl.json')
  }
});

Vue.config.productionTip = false;

new Vue({
  el: '#app',
  router,
  i18n,
  components: { App },
  template: '<App/>'
});
