<!--=========================TEMPLATE=========================-->
<template>
  <section class="hero is-success is-fullheight">
    <div class="hero-body">
      <div class="container has-text-centered">
        <div class="column is-4 is-offset-4">

          <h3 class="title has-text-grey">{{ msg('header') }}</h3>
          <p class="subtitle has-text-grey">{{ msg('subHeader') }}</p>

          <div class="box">

            <!--EMAIL-->
            <b-field>
              <b-input size="is-large" type="text" v-model.trim="email" :placeholder="msg('emailPlaceholder')"
                       icon="email" autofocus="">
              </b-input>
            </b-field>

            <!--SUBMIT BUTTON-->
            <p class="control">
              <button @click="sendPasswordResetToken" class="button is-block is-info is-large is-fullwidth">
                {{ msg('sendButtonText') }}
              </button>
            </p>

          </div>

          <p class="has-text-grey">
            <a href="#/login">{{ msg('loginLink') }}</a> &nbsp;·&nbsp;
            <a href="#/signup">{{ msg('signupLink') }}</a> &nbsp;·&nbsp;
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
  import axios from 'axios'
  import {messageUtils} from '../mixins/messageUtils'

  export default {
    name: "forgotMyPasswordPage",
    data() {
      return {
        email: ''
      }
    },
    mixins: [messageUtils],
    methods: {
      sendPasswordResetToken() {
        axios('/forgotMyPassword', {
          method: "post",
          data: this.email,
          headers: {
            'Content-type': 'text/plain'
          },
        })
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
