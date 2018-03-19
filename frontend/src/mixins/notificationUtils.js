import store from '../vuex/store'

export const notificationUtils = {
  data: {
    notificationActive: false
  },
  methods: {
    successSnackbar(msg, duration) {
      this.showSnackbar(msg, 'is-success', duration)
    },
    dangerSnackbar(msg, duration) {
      this.showSnackbar(msg, 'is-danger', duration)
    },
    showSnackbar(msg, type, duration) {
      if (this.notificationActive) return;
      let position = '';
      let buttonText = '';
      duration = duration != null ? duration : 4000;
      if (type === 'is-success') {
        position = 'is-top';
        buttonText = 'OK';
      } else if (type === 'is-danger') {
        position = 'is-bottom-left';
        let locale = store.getters.language;
        buttonText = locale === 'pl' ? 'ZAMKNIJ' : 'CLOSE';
      }
      this.$snackbar.open({
        message: msg,
        type: type,
        position: position,
        duration: duration,
        actionText: buttonText,
        onAction: () => {
          this.notificationActive = false
        }
      });
      this.notificationActive = true;
      setTimeout(() => {
        this.notificationActive = false;
      }, duration);
    },
  }
};
