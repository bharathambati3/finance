/**
 * Created by bharathambati on 11/08/18.
 */

import * as Constants from '../constants/Constants';
import moment from 'moment'

const initialState = {
  companies: [],
  schemes: [],
}
const CommonReducer = (state = initialState, action) => {
  switch (action.type) {
    case Constants.STORE_COMPANY_INFO: {
      let companies = action.payload.data;
      if (companies) {
        let none = {id: 0, name: "All"};
        companies.push(none);
      }
      return {
        ...state,
        companies: companies
      }
    }
    case Constants.STORE_SCHEME_INFO: {
      return {
        ...state,
        schemes: action.payload.data
      }
    }
    default:
      return state
  }
}

export default CommonReducer
