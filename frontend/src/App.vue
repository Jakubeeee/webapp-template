<template>
  <div id="app">
    <navbar></navbar>
    <router-view/>
  </div>
</template>

<script>

  import Navbar from './components/Navbar.vue';

  export default {
    name: 'App',
    components: {Navbar}
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
</style>
