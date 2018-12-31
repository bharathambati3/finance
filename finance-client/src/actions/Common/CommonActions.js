import * as Constants from '../../constants/Constants'
import axios from "axios";
import * as Config from '../../config/Config'

export const loadCompanyInfo = () => {

  let request = axios.get(`${Config.ROOT_URL}${Config.GET_ALL_COMPANIES}`)

  return({type: Constants.STORE_COMPANY_INFO, payload: request});
}

export const loadSchemeInfo = () => {

  let request = axios.get(`${Config.ROOT_URL}${Config.GET_ALL_SCHEMES}`)

  return({type: Constants.STORE_SCHEME_INFO, payload: request});
}