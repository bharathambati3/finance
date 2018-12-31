/**
 * Created by bharathambati on 11/08/18.
 */

import * as Constants from '../constants/Constants';

const initialState = {
  selectedCompany: 1,
  selectedScheme: 1,
  investmentsData: null
}
const DashboardReducer = (state = initialState, action) => {
  switch (action.type) {
    case Constants.DASHBOARD_SELECTED_COMPANY:
      return {
        ...state,
        selectedCompany : action.payload
      }
    case Constants.DASHBOARD_SCHEME_WISE_INVESTMENTS:
      return {
        ...state,
        investmentsData: action.payload
      }
    case Constants.DASHBOARD_SELECTED_SCHEME:
      return {
        ...state,
        selectedScheme: action.payload
      }
    default:
      return state
  }
}

export default DashboardReducer