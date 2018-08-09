<!--=========================TEMPLATE=========================-->
<template>
  <div>
    <aside class="menu">
      <p class="menu-label">
        {{msg('sideMenuLabel1')}}
      </p>
      <ul class="menu-list">
        <li>
          <a href="#/home" :class="{ 'is-active': activePage === 'homePage' }">{{msg('sidePage1')}}</a>
        </li>
        <li>
          <a href="#/example1" :class="{ 'is-active': activePage === 'example1Page' }">{{msg('sidePage2')}}</a>
        </li>
        <p class="menu-label">
          {{msg('sideMenuLabel2')}}
        </p>
        <li>
          <a href="#/example2" :class="{ 'is-active': activePage === 'example2Page' }">{{msg('sidePage3')}}</a>
        </li>
        <li>
          <a href="#/example3" :class="{ 'is-active': activePage === 'example3Page' }">{{msg('sidePage4')}}</a>
        </li>
      </ul>
    </aside>
  </div>
</template>
<!--=======================TEMPLATE END=======================-->

<!--==========================SCRIPT==========================-->
<script>
  import {messageUtils} from '../mixins/messageUtils'
  import {mapGetters} from 'vuex'

  export default {
    name: "sideMenu",
    data() {
      return {}
    },
    mixins: [messageUtils],
    computed: {
      ...mapGetters({
        activePage: 'activePage'
      })
    }
  }
</script>
<!--=========================SCRIPT END========================-->

<!--===========================STYLE===========================-->
<style scoped>
  .menu-label {
    color: #8F99A3;
    letter-spacing: normal;
    font-weight: 700;
  }

  .menu-list a {
    color: #0F1D38;
    font-size: 14px;
    font-weight: 700;
  }

  .menu-list a:hover {
    background-color: transparent;
    color: #276cda;
  }

  .menu-list a.is-active {
    background-color: transparent;
    color: #276cda;
    font-weight: 700;
  }
</style>
<!--=========================STYLE END=========================-->
