import axios from "axios";
import * as Config from '../../config/Config'
import * as Constants from '../../constants/Constants'

const data = {
  "userId": "a"
}

export const requestMonthlySipData = (req, success, failure) => {
  data.year = (req)?req.year:null;
  data.month = (req)?req.month:null;

  axios.post(`${Config.ROOT_URL}/api/mf/sips/monthly`, data)
    .then(success)
    .catch(failure)
  return({type: Constants.REQUEST_MONTLY_SIP_DATA, payload: data});
}

export const storeSipData = (data) => {
  return({type: Constants.STORE_MONTLY_SIP_DATA, payload: data});
}

export const showSipInfo = (data) => {
  return({type: Constants.SHOW_SINGLE_SIP_INFO, payload: data});
}

export const selectedMonth = (data) => {
  return({type: Constants.FILTER_MONTH_SELECTED, payload: data});
}

let investment = {
  "userId": "a",
  "schemeId": null,
  "purchasedOn":null,
  "investedOn": null,
  "amount": null,
  "purchaseNav": null,
  "type": "SIP"
}


export const createNewInvestment = (data, success, failure) => {
  console.log("new investment "+JSON.stringify(data));
  investment.schemeId = data.scheme.id;
  investment.purchasedOn = data.sipDate+"T00:00:00";
  investment.investedOn = data.investedOn+"T00:00:00";
  investment.amount = data.amount;
  investment.purchaseNav = data.nav;

  const request = console.log("new investment "+JSON.stringify(investment));
  axios.post(`${Config.ROOT_URL}/api/mf/investment`, investment)
    .then(success)
    .catch(failure)
  return({type: Constants.CREATE_NEW_INVESTMENT, payload: request});
}