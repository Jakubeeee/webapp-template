<!--=========================TEMPLATE=========================-->
<template>
  <section class="hero is-success">
    <div class="hero-body">
      <div class="container has-text-centered">
        <div class="column is-4 is-offset-4">

          <h3 class="title has-text-grey">{{ msg('header') }}</h3>

          <div class="box">

            <!--USERNAME-->
            <b-field :label="msg('usernameLabel')" :type="$v.credentials.username.$error ? 'is-danger' : 'text'">
              <b-input size="is-large" type="text" icon="account" @input="setUsername" autofocus=""></b-input>
            </b-field>
            <b-field class="help is-danger">
              <span v-if="!$v.credentials.username.required && $v.credentials.username.$dirty">
                {{ msg('fieldRequiredError') }}
              </span>
              <span v-else-if="!$v.credentials.username.minLength && $v.credentials.username.$dirty">
                {{ msg('usernameTooShortError', [$v.credentials.username.$params.minLength.min]) }}
              </span>
              <span v-else-if="!$v.credentials.username.maxLength && $v.credentials.username.$dirty">
                {{ msg('usernameTooLongError', [$v.credentials.username.$params.maxLength.max]) }}
              </span>
              <span v-else-if="!$v.credentials.username.uniqueUsername && $v.credentials.username.$dirty
                     && !validationPending()">
              {{ msg('usernameAlreadyRegisteredError') }}
              </span>
            </b-field>

            <!--EMAIL-->
            <b-field :label="msg('emailLabel')" :type="$v.credentials.email.$error ? 'is-danger' : 'text'">
              <b-input size="is-large" type="text" icon="email" @input="setEmail"></b-input>
            </b-field>
            <b-field class="help is-danger">
              <span v-if="!$v.credentials.email.required && $v.credentials.email.$dirty">
                {{ msg('fieldRequiredError') }}
              </span>
              <span v-else-if="!$v.credentials.email.email && $v.credentials.email.$dirty">
                {{ msg('invalidEmailError') }}
              </span>
              <span v-else-if="!$v.credentials.email.uniqueEmail &&  $v.credentials.email.$dirty
                    && !validationPending()">
                {{ msg('emailAlreadyRegisteredError') }}
              </span>
            </b-field>

            <!--PASSWORD-->
            <b-field :label="msg('passwordLabel')" :type="$v.credentials.password.$error ? 'is-danger' : 'text'">
              <b-input size="is-large" type="password" v-model="credentials.password" icon="key-variant"
                       @input="$v.credentials.passwordConfirm.$touch(); $v.credentials.password.$touch()">
              </b-input>
            </b-field>
            <b-field class="help is-danger">
              <span v-if="!$v.credentials.password.validPassword && $v.credentials.password.$dirty">
                {{ msg('passwordRegexUnmatchedError',
                [$v.credentials.password.$params.validPassword.min,
                $v.credentials.password.$params.validPassword.max]) }}
              </span>
            </b-field>

            <!--PASSWORD CONFIRM-->
            <b-field v-bind:label="msg('passwordConfirmLabel')"
                     :type="$v.credentials.passwordConfirm.$error ? 'is-danger' : 'text'">
              <b-input size="is-large" type="password" v-model="credentials.passwordConfirm" icon="key-variant"
                       @input="$v.credentials.passwordConfirm.$touch(); $v.credentials.password.$touch()">
              </b-input>
            </b-field>
            <b-field class="help is-danger">
              <span v-if="!$v.credentials.passwordConfirm.required && $v.credentials.passwordConfirm.$dirty">
                {{ msg('fieldRequiredError') }}
              </span>
              <span v-else-if="!$v.credentials.passwordConfirm.sameAsPassword && $v.credentials.passwordConfirm.$dirty">
                {{ msg('passwordDifferenceError') }}
              </span>
            </b-field>

            <!--SUBMIT BUTTON-->
            <p class="control">
              <button @click="signup" class="button is-block is-info is-large is-fullwidth">
                {{ msg('signUpButtonText') }}
              </button>
            </p>
          </div>

          <p class="has-text-grey">
            <a href="#/login">{{ msg('loginLink') }}</a> &nbsp;·&nbsp;
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
  import axios from 'axios'
  import {messageUtils} from '../../mixins/messageUtils'
  import {required, minLength, maxLength, email, sameAs} from 'vuelidate/lib/validators'
  import validPassword from '../../validation/validPassword'
  import uniqueUsername from '../../validation/uniqueUsername'
  import uniqueEmail from '../../validation/uniqueEmail'
  import {validationUtils} from '../../mixins/validationUtils'
  import _ from 'lodash'
  import {notificationUtils} from '../../mixins/notificationUtils'


  export default {
    name: "signUpPage",
    data() {
      return {
        credentials: {
          username: '',
          email: '',
          password: '',
          passwordConfirm: ''
        },
        isLoading: false
      }
    },
    mixins: [messageUtils, validationUtils, notificationUtils],
    methods: {
      signup() {
        if (this.formInvalid()) {
          this.dangerSnackbar(this.msg('invalidFormNotification'));
          this.credentials.password = '';
          this.credentials.passwordConfirm = '';
          return
        }
        this.isLoading = true;
        axios('/createAccount', {
          method: "post",
          data: JSON.stringify(this.credentials),
          withCredentials: true,
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
        }).then(() => {
          this.$store.dispatch('login', this.credentials).then(() => this.isLoading = false);
        })
      },
      setUsername:
        _.debounce(function (value) {
          this.$v.credentials.username.$touch();
          this.credentials.username = value.trim();
        }, 300),
      setEmail:
        _.debounce(function (value) {
          this.$v.credentials.email.$touch();
          this.credentials.email = value.trim();
        }, 300)
    },
    validations: {
      credentials: {
        username: {
          required,
          minLength: minLength(6),
          maxLength: maxLength(30),
          uniqueUsername
        },
        email: {
          required,
          email,
          uniqueEmail
        },
        password: {
          validPassword: validPassword(8, 25)
        },
        passwordConfirm: {
          required,
          sameAsPassword: sameAs('password')
        }
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
