<!--=========================TEMPLATE=========================-->
<template>
  <nav class="navbar is-white is-fixed-top">
    <div class="container">
      <div class="navbar-brand">
        <a class="navbar-item brand-text" href="#/">
          {{ $t('navbar.webAppButtonText') }}
        </a>
        <div class="navbar-burger burger" data-target="navMenu">
          <span></span>
          <span></span>
          <span></span>
        </div>
      </div>
      <div id="navMenu" class="navbar-menu">
        <div class="navbar-start">
          <a class="navbar-item" href="#/">
            {{ $t('navbar.homeButtonText') }}
          </a>
          <a class="navbar-item" href="#/about">
            {{ $t('navbar.aboutButtonText') }}
          </a>
          <a class="navbar-item" href="#/contact">
            {{ $t('navbar.contactButtonText') }}
          </a>
        </div>

        <div class="navbar-item" v-if="!this.$store.getters.authenticated">
          <a class="button is-success is-outlined navbar-item" href="#/login">
            <span>{{ $t('navbar.loginButtonText') }}</span>
            <span class="icon is-small">
              <b-icon icon="login"></b-icon>
            </span>
          </a>
        </div>

        <div class="navbar-item" v-if="this.$store.getters.authenticated">
          <a class="button is-danger is-outlined navbar-item" v-on:click="logout">
            <span>{{ $t('navbar.logoutButtonText') }}</span>
            <b-icon icon="logout"></b-icon>
          </a>
        </div>

        <div class="navbar-item" v-if="!this.$store.getters.authenticated">
          <a class="button is-success is-outlined navbar-item" href="#/signup">
            <span>{{ $t('navbar.signupButtonText') }}</span>
            <b-icon icon="account-plus"></b-icon>
          </a>
        </div>

        <b-dropdown class="navbar-item">
          <button class="button" slot="trigger">
            <span>{{ $t('navbar.chooseLanguageDropdownText') }}</span>
            <b-icon icon="menu-down"></b-icon>
          </button>
          <b-dropdown-item v-on:click="changeLanguage('pl')">{{ $t('navbar.polishLanguageChoice') }}</b-dropdown-item>
          <b-dropdown-item v-on:click="changeLanguage('en')">{{ $t('navbar.englishLanguageChoice') }}</b-dropdown-item>
        </b-dropdown>
      </div>
    </div>
  </nav>
</template>
<!--=======================TEMPLATE END=======================-->

<!--==========================SCRIPT==========================-->
<script>
  import axios from 'axios'

  export default {
    name: "navbar",
    data() {
      return {}
    },
    methods: {
      changeLanguage(locale) {
        this.$store.dispatch('changeLanguage', locale);
      },
      logout() {
        axios.post('/logout').then(() => {
          this.$store.dispatch('setAuthenticated').then(() => {
            this.$router.push('/login');
          });
        });
      }
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
