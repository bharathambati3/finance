import * as Constants from '../../constants/Constants'
import axios from "axios";
import * as Config from '../../config/Config'

export const onCompanySelected = (e) => {
  let val = e.target.value;
  return {type: Constants.DASHBOARD_SELECTED_COMPANY, payload: val}
}

export const onSchemeSelected = (e) => {
  let val = e.target.value;
  return {type: Constants.DASHBOARD_SELECTED_SCHEME, payload: val}
}

let investmentsReq = {
  "userId" : "a",
  "schemeId": null
}

export const getInvestments = (schemeId) => {
  if (schemeId === 0) {
    investmentsReq.schemeId = null;
  } else {
    investmentsReq.schemeId = schemeId;
  }
  let req = axios.post(`${Config.ROOT_URL}/api/mf/investments`, investmentsReq)
  return {type: Constants.DASHBOARD_SCHEME_WISE_INVESTMENTS, payload: req};
}