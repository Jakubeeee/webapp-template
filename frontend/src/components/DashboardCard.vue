<!--=========================TEMPLATE=========================-->
<template>
  <div class="columns">
    <div class="column is-12">
      <div class="card events-card">
        <header class="card-header">
          <p class="card-header-title">
            {{title}}
          </p>
        </header>
        <div class="card-table">
          <div class="content">
            <slot name="content"></slot>
          </div>
        </div>
        <footer class="card-footer">
          <slot name="footer"></slot>
        </footer>
      </div>
    </div>
  </div>
</template>
<!--=======================TEMPLATE END=======================-->

<!--==========================SCRIPT==========================-->
<script>
  export default {
    name: "dashboard-card",
    props: ['title']
  }
</script>
<!--=========================SCRIPT END========================-->

<!--===========================STYLE===========================-->
<style scoped>
  .card {
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.18);
  }

  .card-header-title {
    color: #8F99A3;
    font-weight: 400;
  }

  .card .content {
    font-size: 14px;
  }

  .card-table {
    margin-bottom: 0;
  }

  .events-card .card-table {
    max-height: 250px;
    overflow-y: scroll;
  }
</style>
<!--=========================STYLE END=========================-->
