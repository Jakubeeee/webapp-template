<!--=========================TEMPLATE=========================-->
<template>
  <section class="hero is-success is-fullheight">
    <div class="hero-body">
      <div class="container has-text-centered">
        <div class="column is-4 is-offset-4">

          <h3 class="title has-text-grey">{{ $t('changePasswordPage.header') }}</h3>
          <p class="subtitle has-text-grey">{{ $t('changePasswordPage.subHeader') }}</p>

          <div class="box">

            <b-field v-bind:label="$t('changePasswordPage.passwordLabel')">
              <b-input size="is-large" type="password" v-model="credentials.password" icon="key-variant" autofocus="">
              </b-input>
            </b-field>

            <b-field v-bind:label="$t('changePasswordPage.passwordConfirmLabel')">
              <b-input size="is-large" type="password" v-model="credentials.passwordConfirm" icon="key-variant">
              </b-input>
            </b-field>

            <button class="button is-block is-info is-large is-fullwidth" v-on:click="changePassword">
              {{$t('changePasswordPage.changePasswordButtonText') }}
            </button>

          </div>

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
    name: "changePasswordPage",
    data() {
      return {
        credentials: {
          userId: '',
          resetToken: '',
          password: '',
          passwordConfirm: ''
        }
      }
    },
    methods: {
      changePassword() {
        axios('/changePassword', {
          method: "post",
          data: JSON.stringify(this.credentials),
          withCredentials: true,
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
        }).then(() => {
          // TODO redirect only if password was changed successfully
          // this.$router.push('/');
        })
      }
    },
    created() {
      this.credentials.userId = this.$route.query.id;
      this.credentials.resetToken = this.$route.query.token;
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
