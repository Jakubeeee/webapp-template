<!--=========================TEMPLATE=========================-->
<template>
  <section class="hero is-success is-fullheight">
    <div class="hero-body">
      <div class="container has-text-centered">
        <div class="column is-4 is-offset-4">

          <h3 class="title has-text-grey">{{ $t('loginPage.header') }}</h3>
          <p class="subtitle has-text-grey">{{ $t('loginPage.subHeader') }}</p>

          <div class="box">

            <b-field>
              <b-input size="is-large" type="text" v-model="credentials.username" autofocus=""
                       v-bind:placeholder="$t('loginPage.usernamePlaceholder')" icon="account">
              </b-input>
            </b-field>

            <b-field>
              <b-input size="is-large" type="password" v-model="credentials.password"
                       v-bind:placeholder="$t('loginPage.passwordPlaceholder')" icon="key-variant">
              </b-input>
            </b-field>

            <b-field>
              <b-checkbox>
                {{ $t('loginPage.rememberMeLabel') }}
              </b-checkbox>
            </b-field>

            <button class="button is-block is-info is-large is-fullwidth" v-on:click="login">
              {{ $t('loginPage.loginButtonText') }}
            </button>

          </div>

          <p class="has-text-grey">
            <a href="#/signup">{{ $t('loginPage.signupLink') }}</a> &nbsp;·&nbsp;
            <a href="#/forgotmypassword">{{ $t('loginPage.forgotMyPasswordLink') }}</a> &nbsp;·&nbsp;
            <a href="#/help">{{ $t('loginPage.needHelpLink') }}</a>
          </p>

        </div>
      </div>
    </div>
  </section>
</template>
<!--=======================TEMPLATE END=======================-->

<!--==========================SCRIPT==========================-->
<script>
  import axios from 'axios';

  export default {
    name: "loginPage",
    data() {
      return {
        credentials: {
          username: '',
          password: ''
        }
      }
    },
    methods: {
      login() {
        this.$store.dispatch('login', this.credentials);
      }
    }
  }
</script>
<!--=========================SCRIPT END========================-->

<!--===========================STYLE===========================-->
<style scoped>

  .hero.is-success {
    background: #F2F6FA;
  }

  .hero, .hero.is-success {
    -webkit-box-shadow: none;
    box-shadow: none;
  }

  .box {
    margin-top: 2rem;
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

</style>
<!--=========================STYLE END=========================-->
