import Vue from 'vue'
import VueI18n from 'vue-i18n'

Vue.use(VueI18n);

const i18n = new VueI18n({
  locale: '',
  messages: {
    'en': require('./messages_en.json'),
    'pl': require('./messages_pl.json')
  }
});

export default i18n
