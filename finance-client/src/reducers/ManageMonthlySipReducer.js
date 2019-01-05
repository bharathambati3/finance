/**
 * Created by bharathambati on 11/08/18.
 */

import * as Constants from '../constants/Constants';
import moment from 'moment'

const initialState = {
  searchData: null,
  sipInfo: null,
  filterMonth: moment().format('MMMM').toUpperCase(),
  filterYear: moment().format('YYYY').toUpperCase(),
}
const ManageMonthlySipReducer = (state = initialState, action) => {
  switch (action.type) {
    case Constants.STORE_MONTLY_SIP_DATA:
      return {
        ...state,
        searchData : action.payload
      }
    case Constants.SHOW_SINGLE_SIP_INFO:
      return {
        ...state,
        sipInfo : action.payload
      }
    case Constants.FILTER_MONTH_SELECTED:
      return {
        ...state,
        filterMonth: action.payload
      }
    case Constants.FILTER_YEAR_SELECTED:
      return {
          ...state,
          filterYear: action.payload
      }
    default:
      return state
  }
}

export default ManageMonthlySipReducer