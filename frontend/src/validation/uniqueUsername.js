import axios from 'axios'
import {req} from 'vuelidate/lib/validators/common'

export default async function (value) {
  if (!req(value)) return true;
  let usernameUnique = true;
   await axios('/isUsernameUnique', {
    method: "post",
    data: value,
    headers: {'Content-type': 'text/plain'}
  }).then((response) => {
    usernameUnique = response.data;
  });
  return usernameUnique;
};
