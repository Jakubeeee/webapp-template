<!--=========================TEMPLATE=========================-->
<template>
  <div class="container">
    <div class="columns">
      <div class="column is-3">
        <side-menu/>
      </div>
      <div class="column is-9">
        <router-view/>
      </div>
    </div>
  </div>
</template>
<!--=======================TEMPLATE END=======================-->

<!--==========================SCRIPT==========================-->
<script>
  import SideMenu from '../../components/SideMenu.vue';

  export default {
    name: "contentParentPage",
    components: {
      'side-menu': SideMenu
    }
  }
</script>
<!--=========================SCRIPT END========================-->

<!--===========================STYLE===========================-->
<style scoped>

</style>
<!--=========================STYLE END=========================-->
