<!--=========================TEMPLATE=========================-->
<template>
  <section class="hero is-success">
    <div class="hero-body">
      <div class="container has-text-centered">
        <div class="column is-4 is-offset-4">

          <h3 class="title has-text-grey">{{ msg('header') }}</h3>

          <div class="box">

            <!--EMAIL-->
            <b-field :type="$v.email.$error ? 'is-danger' : 'text'">
              <b-input size="is-large" type="text" v-model.trim="email" :placeholder="msg('emailPlaceholder')"
                       icon="email" @input="$v.email.$touch()" autofocus="">
              </b-input>
            </b-field>
            <b-field class="help is-danger">
              <span v-if="!$v.email.required && $v.email.$dirty">
                {{ msg('fieldRequiredError') }}
              </span>
              <span v-else-if="!$v.email.email && $v.email.$dirty">
                {{ msg('invalidEmailError') }}
              </span>
            </b-field>

            <!--SUBMIT BUTTON-->
            <p class="control">
              <button @click="sendPasswordResetToken()"
                      class="button is-block is-info is-large is-fullwidth">
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
    <b-loading :active.sync="isLoading"></b-loading>
  </section>
</template>
<!--=======================TEMPLATE END=======================-->

<!--==========================SCRIPT==========================-->
<script>
  import axios from 'axios'
  import {messageUtils} from '../../mixins/messageUtils'
  import {required, email} from 'vuelidate/lib/validators'
  import {validationUtils} from '../../mixins/validationUtils'
  import {notificationUtils} from '../../mixins/notificationUtils'
  import {mapGetters} from 'vuex'

  export default {
    name: "forgotMyPasswordPage",
    data() {
      return {
        email: '',
        isLoading: false,
      }
    },
    mixins: [messageUtils, validationUtils, notificationUtils],
    methods: {
      sendPasswordResetToken() {
        if (this.formInvalid()) {
          this.dangerSnackbar(this.msg('invalidEmailNotification'));
          return
        }
        if (this.cooldownActive) {
          this.dangerSnackbar(this.msg('emailCooldownActiveNotification'));
          return;
        }
        this.isLoading = true;
        axios('/forgotMyPassword', {
          method: "post",
          data: this.email,
          headers: {
            'Content-type': 'text/plain'
          },
        }).then(() => {
            this.isLoading = false;
            this.successSnackbar(this.msg('emailSentNotification'), 6000);
            this.$store.dispatch('beginResetEmailCooldown');
          }
        ).catch(() => {
            this.isLoading = false;
            this.dangerSnackbar(this.msg('emailNotFoundNotification'));
          }
        );
      }
    },
    computed: {
      ...mapGetters({
        cooldownActive: 'resetEmailCooldownActive'
      }),
    },
    validations: {
      email: {
        required,
        email
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
