<!--=========================TEMPLATE=========================-->
<template>
  <section class="hero is-success is-fullheight">
    <div class="hero-body">
      <div class="container has-text-centered">
        <div class="column is-4 is-offset-4">

          <h3 class="title has-text-grey">{{ msg('header') }}</h3>
          <p class="subtitle has-text-grey">{{ msg('subHeader') }}</p>

          <div class="box">

            <!--PASSWORD-->
            <b-field>
              <b-input size="is-large" type="text" v-model="credentials.username" autofocus=""
                       :placeholder="msg('usernamePlaceholder')" icon="account">
              </b-input>
            </b-field>

            <!--PASSWORD CONFIRM-->
            <b-field>
              <b-input size="is-large" type="password" v-model="credentials.password"
                       :placeholder="msg('passwordPlaceholder')" icon="key-variant">
              </b-input>
            </b-field>

            <!--REMEMBER ME-->
            <b-field>
              <b-checkbox>
                {{ msg('rememberMeLabel') }}
              </b-checkbox>
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
  </section>
</template>
<!--=======================TEMPLATE END=======================-->

<!--==========================SCRIPT==========================-->
<script>
  import {messageUtils} from '../mixins/messageUtils'

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
    mixins: [messageUtils],
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
