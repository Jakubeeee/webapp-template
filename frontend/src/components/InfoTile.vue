<!--=========================TEMPLATE=========================-->
<template>
  <div class="tile is-parent">
    <article class="tile is-child box">
      <p class="title">{{content}}</p>
      <p class="subtitle">{{subtitle}}</p>
    </article>
  </div>
</template>
<!--=======================TEMPLATE END=======================-->

<!--==========================SCRIPT==========================-->
<script>
  export default {
    name: "info-tile",
    props: ['content', 'subtitle']
  }
</script>
<!--=========================SCRIPT END========================-->

<!--===========================STYLE===========================-->
<style scoped>
  .tile .is-child {
    margin-top: 2rem;
    text-align: left;
  }

  .tile .title {
    font-weight: 700;
    font-size: 20px;
  }

  .tile .subtitle {
    font-weight: 300;
    font-size: 16px;
    color: #8F99A3;
  }

</style>
<!--=========================STYLE END=========================-->
