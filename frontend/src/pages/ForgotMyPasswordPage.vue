<!--=========================TEMPLATE=========================-->
<template>
  <section class="hero is-success is-fullheight">
    <div class="hero-body">
      <div class="container has-text-centered">
        <div class="column is-4 is-offset-4">

          <h3 class="title has-text-grey">{{ $t('forgotMyPasswordPage.header') }}</h3>
          <p class="subtitle has-text-grey">{{ $t('forgotMyPasswordPage.subHeader') }}</p>

          <div class="box">

            <b-field>
              <b-input size="is-large" type="text" v-model="email"
                       v-bind:placeholder="$t('forgotMyPasswordPage.emailPlaceholder')"
                       icon="email" autofocus="">
              </b-input>
            </b-field>

            <p class="control">
              <button class="button is-block is-info is-large is-fullwidth" v-on:click="sendPasswordResetToken">
                {{ $t('forgotMyPasswordPage.sendButtonText') }}
              </button>
            </p>

          </div>

          <p class="has-text-grey">
            <a href="#/login">{{ $t('forgotMyPasswordPage.loginLink') }}</a> &nbsp;·&nbsp;
            <a href="#/signup">{{ $t('forgotMyPasswordPage.signupLink') }}</a> &nbsp;·&nbsp;
            <a href="#/help">{{ $t('forgotMyPasswordPage.needHelpLink') }}</a>
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

  export default {
    name: "forgotMyPasswordPage",
    data() {
      return {
        email: ''
      }
    },
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
