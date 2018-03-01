<!--=========================TEMPLATE=========================-->
<template>
  <section class="hero is-success is-fullheight">
    <div class="hero-body">
      <div class="container has-text-centered">
        <div class="column is-4 is-offset-4">

          <h3 class="title has-text-grey">{{ $t('signUpPage.header') }}</h3>
          <p class="subtitle has-text-grey">{{ $t('signUpPage.subHeader') }}</p>

          <div class="box">

            <b-field v-bind:label="$t('signUpPage.usernameLabel')">
              <b-input size="is-large" type="text" v-model="credentials.username"
                       icon="account" autofocus="">
              </b-input>
            </b-field>

            <b-field v-bind:label="$t('signUpPage.emailLabel')">
              <b-input size="is-large" type="text" v-model="credentials.email"
                       icon="email">
              </b-input>
            </b-field>

            <b-field v-bind:label="$t('signUpPage.passwordLabel')">
              <b-input size="is-large" type="password" v-model="credentials.password"
                       icon="key-variant">
              </b-input>
            </b-field>

            <b-field v-bind:label="$t('signUpPage.passwordConfirmLabel')">
              <b-input size="is-large" type="password" v-model="credentials.passwordConfirm"
                       icon="key-variant">
              </b-input>
            </b-field>

            <p class="control">
              <button class="button is-block is-info is-large is-fullwidth" v-on:click="signup">
                {{ $t('signUpPage.signUpButtonText') }}
              </button>
            </p>

          </div>
          <p class="has-text-grey">
            <a href="#/login">{{ $t('signUpPage.loginLink') }}</a> &nbsp;·&nbsp;
            <a href="#/forgotmypassword">{{ $t('signUpPage.forgotMyPasswordLink') }}</a> &nbsp;·&nbsp;
            <a href="#/help">{{ $t('signUpPage.needHelpLink') }}</a>
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
    name: "signUpPage",
    data() {
      return {
        credentials: {
          username: '',
          email: '',
          password: '',
          passwordConfirm: ''
        }
      }
    },
    methods: {
      signup() {
        axios('/createAccount', {
          method: "post",
          data: JSON.stringify(this.credentials),
          withCredentials: true,
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
        }).then(
          () => {
            this.$store.dispatch('login', this.credentials);
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
    text-align: left;
  }

  label {
    color: rgb(122, 122, 122);
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
