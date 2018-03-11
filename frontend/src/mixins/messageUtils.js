export const messageUtils = {
  methods: {
    msg(messageCode, params) {
      messageCode = this.$options.name + '.' + messageCode;
      let message = this.$t(messageCode);
      if (params)
        params.forEach(value => message = message.replace('{}', value));
      return message;
    }
  }
};


