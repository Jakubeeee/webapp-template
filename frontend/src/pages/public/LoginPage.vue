<!--=========================TEMPLATE=========================-->
<template>
  <section class="hero is-success">
    <div class="hero-body">
      <div class="container has-text-centered">
        <div class="column is-4 is-offset-4">

          <h3 class="title has-text-grey">{{ msg('header') }}</h3>

          <div class="box">

            <!--USERNAME-->
            <b-field>
              <b-input size="is-large" type="text" v-model="credentials.username"
                       :placeholder="msg('usernamePlaceholder')" icon="account" autofocus="">
              </b-input>
            </b-field>

            <!--PASSWORD-->
            <b-field>
              <b-input size="is-large" type="password" v-model="credentials.password"
                       :placeholder="msg('passwordPlaceholder')" icon="key-variant">
              </b-input>
            </b-field>

            <!--SUBMIT BUTTON-->
            <button @click="login" class="button is-block is-info is-large is-fullwidth">
              {{ msg('loginButtonText') }}
            </button>

          </div>

          <p class="has-text-grey">
            <a href="#/signup">{{ msg('signupLink') }}</a> &nbsp;·&nbsp;
            <a href="#/forgotmypassword">{{ msg('forgotMyPasswordLink') }}</a> &nbsp;·&nbsp;
            <a href="#/help">{{ msg('needHelpLink') }}</a>
          </p>

        </div>
      </div>
    </div>
    <b-loading :active.sync="isLoading"></b-loading>
  </section>
</template>
<!--=======================TEMPLATE END=======================-->

<!--==========================SCRIPT==========================-->
<script>
  import {messageUtils} from '../../mixins/messageUtils'
  import {notificationUtils} from '../../mixins/notificationUtils'

  export default {
    name: "loginPage",
    data() {
      return {
        credentials: {
          username: '',
          password: ''
        },
        isLoading: false
      }
    },
    mixins: [messageUtils, notificationUtils],
    methods: {
      login() {
        this.isLoading = true;
        this.$store.dispatch('login', this.credentials).then(() => {
          this.isLoading = false;
          if (!this.$store.getters.authenticated) {
            this.dangerSnackbar(this.msg('invalidUsernameOrPasswordNotification'));
            this.credentials.password = '';
          }
        });
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
