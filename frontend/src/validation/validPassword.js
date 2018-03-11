import {withParams} from 'vuelidate'
import {req, len} from 'vuelidate/lib/validators/common'

export default (min, max) => withParams({type: 'validPassword', min, max}, value => {
  return /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])./.test(value) && len(value) >= min && len(value) <= max && req(value)
});
