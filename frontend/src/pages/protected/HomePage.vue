<!--=========================TEMPLATE=========================-->
<template>
  <div>
    <section class="hero is-info welcome is-small">
      <div class="hero-body">
        <div class="container">
          <h1 class="title">
            {{msg('helloWorld')}}
          </h1>
        </div>
      </div>
    </section>
    <section class="info-tiles">
      <div class="tile is-ancestor has-text-centered">
        <template v-for="infoTile in infoTiles">
          <info-tile :content="msg(infoTile.value)" :subtitle="msg(infoTile.title)"></info-tile>
        </template>
      </div>
    </section>
    <dashboard-card :title="msg('exampleCardTitle')">
      <template slot="content">
        <table class="table is-fullwidth is-striped">
          <tbody>
          <tr>
            <td width="15%">
              <button class=" button is-success">{{msg('exampleCardText1')}}</button>
            </td>
            <td width="85%" style="vertical-align:middle">
              {{msg('exampleCardText2')}}
            </td>
          </tr>
          <tr>
            <td width="15%">
              <button class="button is-success">{{msg('exampleCardText3')}}</button>
            </td>
            <td width="85%" style="vertical-align:middle">
              {{msg('exampleCardText4')}}
            </td>
          </tr>
          <tr>
            <td width="15%">
              {{msg('exampleCardText5')}}
            </td>
            <td width="85%" style="vertical-align:middle">
              <progress class="progress is-success" value="20" max="50"></progress>
            </td>
          </tr>
          </tbody>
        </table>
      </template>
    </dashboard-card>
  </div>
</template>

<!--=======================TEMPLATE END=======================-->

<!--==========================SCRIPT==========================-->
<script>
  import InfoTile from '../../components/InfoTile.vue'
  import DashboardCard from '../../components/DashboardCard.vue'
  import {messageUtils} from '../../mixins/messageUtils'

  export default {
    name: "homePage",
    components: {
      'info-tile': InfoTile,
      'dashboard-card': DashboardCard
    },
    mixins: [messageUtils],
    data() {
      return {
        infoTiles: [
          {
            value: 'infoTileValue1',
            title: 'infoTileTitle1'
          },
          {
            value: 'infoTileValue2',
            title: 'infoTileTitle2'
          },
          {
            value: 'infoTileValue3',
            title: 'infoTileTitle3'
          },
          {
            value: 'infoTileValue4',
            title: 'infoTileTitle4'
          }
        ]
      }
    }
  }
</script>
<!--=========================SCRIPT END========================-->

<!--===========================STYLE===========================-->
<style scoped>

  .info-tiles {
    margin: 1rem 0;
  }

  .hero.welcome.is-info {
    background: #36D1DC;
    background: -webkit-linear-gradient(to right, #5B86E5, #36D1DC);
    background: linear-gradient(to right, #5B86E5, #36D1DC);
  }

  .hero.welcome .title, .hero.welcome {
    color: hsl(192, 17%, 99%);
  }
</style>
<!--=========================STYLE END=========================-->
