<!--=========================TEMPLATE=========================-->
<template>
  <nav class="navbar is-white">
    <div class="container">
      <div class="navbar-brand">
        <a class="navbar-item brand-text" href="#/">
          {{ msg('webAppButtonText') }}
        </a>
        <div class="navbar-burger burger" @click="toggleBurger" :class="{'is-active': burgerActive}"
             data-target="navMenu">
          <span></span>
          <span></span>
          <span></span>
        </div>
      </div>
      <div id="navMenu" class="navbar-menu" :class="{'is-active': burgerActive}">
        <div class="navbar-start">
          <a class="navbar-item" href="#/">
            {{ msg('homeButtonText') }}
          </a>
          <a class="navbar-item" href="#/about">
            {{ msg('aboutButtonText') }}
          </a>
          <a class="navbar-item" href="#/contact">
            {{ msg('contactButtonText') }}
          </a>
        </div>

        <div class="navbar-item" v-if="!authenticated && !burgerActive">
          <a class="button is-success is-outlined navbar-item" href="#/login">
            <span>{{ msg('loginButtonText') }}</span>
            <b-icon icon="login"></b-icon>
          </a>
        </div>

        <div class="navbar-item" v-if="authenticated  && !burgerActive">
          <a class="button is-danger is-outlined navbar-item" @click="logout">
            <span>{{ msg('logoutButtonText') }}</span>
            <b-icon icon="logout"></b-icon>
          </a>
        </div>

        <div class="navbar-item" v-if="!authenticated  && !burgerActive">
          <a class="button is-success is-outlined navbar-item" href="#/signup">
            <span>{{ msg('signupButtonText') }}</span>
            <b-icon icon="account-plus"></b-icon>
          </a>
        </div>

        <b-dropdown class="navbar-item" v-if="!burgerActive">
          <button class="button" slot="trigger">
            <span>{{ msg('chooseLanguageDropdownText') }}</span>
            <b-icon icon="menu-down"></b-icon>
          </button>
          <b-dropdown-item @click="changeLanguage('pl')">{{ msg('polishLanguageChoice') }}</b-dropdown-item>
          <b-dropdown-item @click="changeLanguage('en')">{{ msg('englishLanguageChoice') }}</b-dropdown-item>
        </b-dropdown>

      </div>
    </div>
  </nav>
</template>
<!--=======================TEMPLATE END=======================-->

<!--==========================SCRIPT==========================-->
<script>
  import {messageUtils} from '../mixins/messageUtils'
  import {mapGetters} from 'vuex'

  export default {
    name: "navbar",
    data() {
      return {
        burgerActive: false
      }
    },
    mixins: [messageUtils],
    methods: {
      toggleBurger() {
        this.burgerActive = !this.burgerActive;
      },
      changeLanguage(locale) {
        this.$store.dispatch('changeLanguage', locale);
      },
      logout() {
        this.$store.dispatch('logout');
      }
    },
    computed: {
      ...mapGetters({
        authenticated: 'authenticated'
      }),
    }
  }
</script>
<!--=========================SCRIPT END========================-->

<!--===========================STYLE===========================-->
<style scoped>

  nav.navbar {
    border-top: 4px solid #276cda;
    margin-bottom: 1rem;
  }

  .navbar-item.brand-text {
    font-weight: 300;
    color: #8F99A3;
  }

  .navbar-item, .navbar-link {
    font-size: 14px;
    font-weight: 700;
    padding: 10px;
  }

</style>
<!--=========================STYLE END=========================-->
