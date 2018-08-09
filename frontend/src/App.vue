<template>
  <div id="app">
    <navbar/>
    <router-view/>
  </div>
</template>

<script>

  import Navbar from './components/Navbar.vue';

  export default {
    name: 'App',
    components: {Navbar},
    created() {
      this.$i18n.locale = this.$store.getters.language;
    }
  }
</script>

<style>
  html, body {
    font-family: 'Open Sans', serif;
    font-size: 16px;
    line-height: 1.5;
    height: 100%;
    background: #ECF0F3;
  }

  label {
    color: rgb(122, 122, 122);
  }

  input {
    font-weight: 300;
  }

  p {
    font-weight: 700;
  }

  p.subtitle {
    padding-top: 1rem;
  }

  .snackbar.is-bottom-left {
    align-self: start;
  }
</style>
