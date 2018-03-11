export const validationUtils = {
  methods: {
    formInvalid() {
      if (this.$v.$invalid) {
        this.$v.$touch();
        return true;
      }
      else return false;
    },
    validationPending() {
      return !!this.$v.$pending;
    }
  }
};
