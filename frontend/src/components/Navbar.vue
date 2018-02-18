<!--=========================TEMPLATE=========================-->
<template>
  <nav class="navbar is-white">
    <div class="container">
      <div class="navbar-brand">
        <a class="navbar-item brand-text" href="/">
          {{ $t(webAppName) }}
        </a>
        <div class="navbar-burger burger" data-target="navMenu">
          <span></span>
          <span></span>
          <span></span>
        </div>
      </div>
      <div id="navMenu" class="navbar-menu">
        <div class="navbar-start">
          <a class="navbar-item" href="/">
            Home
          </a>
          <a class="navbar-item" href="/">
            About
          </a>
          <a class="navbar-item" href="/">
            Contact
          </a>
        </div>
        <b-dropdown style="padding:10px;">
          <button class="button is-right" slot="trigger">
            <span>{{ $t('chooseLanguage') }}</span>
            <b-icon icon="menu-down"></b-icon>
          </button>
          <b-dropdown-item v-on:click="changeLanguage('pl')">{{ $t('polishLanguage') }}</b-dropdown-item>
          <b-dropdown-item v-on:click="changeLanguage('en')">{{ $t('englishLanguage') }}</b-dropdown-item>
        </b-dropdown>
      </div>
    </div>
  </nav>
</template>
<!--=======================TEMPLATE END=======================-->

<!--==========================SCRIPT==========================-->
<script>
  export default {
    name: "navbar",
    methods: {
      changeLanguage(locale) {
        this.$i18n.locale = locale;
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
  }

  .navbar-item, .navbar-link {
    font-size: 14px;
    font-weight: 700;
  }

</style>
<!--=========================STYLE END=========================-->
